package br.com.kerubin.api.financeiro.fluxocaixa.conciliacaobancaria;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.QCaixaLancamentoEntity;
import br.com.kerubin.api.servicecore.util.CoreUtils;

import java.math.BigDecimal;

@Service
public class ConciliacaoBancariaServiceImpl implements ConciliacaoBancariaService {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public ConciliacaoBancariaDTO verificarTransacoes(ConciliacaoBancariaDTO conciliacaoBancariaDTO) {
		
		if (conciliacaoBancariaDTO == null || conciliacaoBancariaDTO.getTransacoes() == null) {
			return conciliacaoBancariaDTO;
		}
		
		JPAQueryFactory query = new JPAQueryFactory(em);
		
		QCaixaLancamentoEntity qCaixaLancamentoEntity = QCaixaLancamentoEntity.caixaLancamentoEntity;
		
		conciliacaoBancariaDTO.getTransacoes().forEach(transacao -> {
			
			boolean isCredito = TipoTransacao.CREDITO.equals(transacao.getTrnTipo());
			NumberPath<BigDecimal> valor = isCredito ? qCaixaLancamentoEntity.valorCredito : qCaixaLancamentoEntity.valorDebito;
			
			BooleanBuilder filtroDados = new BooleanBuilder();
			filtroDados
			.and(qCaixaLancamentoEntity.numDocConcBancaria.eq(transacao.getTrnDocumento()))
			.or(valor.eq(transacao.getTrnValor()));
			
			LocalDate from = transacao.getTrnData().minusDays(30);
			LocalDate to = transacao.getTrnData().plusDays(30);
			
			BooleanBuilder filtroPerido = new BooleanBuilder();
			filtroPerido.and(qCaixaLancamentoEntity.dataLancamento.between(from, to));
			
			BooleanBuilder where = new BooleanBuilder();
			where.and(filtroDados).and(filtroPerido);
			
			List<CaixaLancamentoEntity> contas = query.selectFrom(qCaixaLancamentoEntity).where(where).fetch();
			
			if (contas != null && !contas.isEmpty()) {
				CaixaLancamentoEntity contaCandidata = contas.stream().filter(conta -> transacao.getTrnDocumento().equals(conta.getNumDocConcBancaria())).findFirst().orElse(null);
				
				if (contaCandidata == null) {
					if (isCredito) {
						contaCandidata = contas.stream().filter(conta -> CoreUtils.isEquals(transacao.getTrnValor(), conta.getValorCredito())).findFirst().orElse(null);
					}
					else {
						contaCandidata = contas.stream().filter(conta -> CoreUtils.isEquals(transacao.getTrnValor(), conta.getValorDebito())).findFirst().orElse(null);
					}
				}
				
				if (contaCandidata != null) {
					transacao.setTituloConciliadoId(contaCandidata.getId());
					transacao.setTituloConciliadoDesc(contaCandidata.getDescricao());
					
					SituacaoConciliacaoTrn situacaoConciliacaoTrn = transacao.getSituacaoConciliacaoTrn(); // Valor atual é o default.
					if (contaCandidata.getNumDocConcBancaria() != null) { // Pagamento normal, sem conciliação
						situacaoConciliacaoTrn = SituacaoConciliacaoTrn.CONCILIADO_CAIXA;
					}
					else {
						situacaoConciliacaoTrn = SituacaoConciliacaoTrn.CAIXA_BAIXADO_SEM_CONCILIACAO;
					}
					
					transacao.setSituacaoConciliacaoTrn(situacaoConciliacaoTrn);
				}
			}
			
		});
		
		return conciliacaoBancariaDTO;
	}

}
