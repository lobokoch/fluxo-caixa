package br.com.kerubin.api.financeiro.fluxocaixa.conciliacaobancaria;

import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEmpty;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.QCaixaLancamentoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaRepository;

@Component
public class ConciliacaoBancariaHelperImpl implements ConciliacaoBancariaHelper {
	
	public static UUID PLANO_CONTA_DEFAULT_DEBITO = UUID.fromString("5c29f176-eef5-4658-b00a-7e6d282429db");
	public static UUID PLANO_CONTA_DEFAULT_CREDITO = UUID.fromString("34ff8dca-0bea-41ef-84a6-25440c87b211");
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private ContaBancariaRepository contaBancariaRepository;
	
	@Inject
	private PlanoContaRepository planoContasRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<CaixaLancamentoEntity> findLancamentosPelaTransacaoBancaria(ConciliacaoTransacaoDTO transacao) {
		
		if (isEmpty(transacao)) {
			return null;
		}
		
		JPAQueryFactory query = new JPAQueryFactory(em);
		QCaixaLancamentoEntity qCaixaLancamentoEntity = QCaixaLancamentoEntity.caixaLancamentoEntity;
		
		boolean isCredito = TipoTransacao.CREDITO.equals(transacao.getTrnTipo());
		NumberPath<BigDecimal> valor = isCredito ? qCaixaLancamentoEntity.valorCredito : qCaixaLancamentoEntity.valorDebito;
		
		BooleanBuilder filtroDados = new BooleanBuilder();
		filtroDados
		.and(qCaixaLancamentoEntity.idConcBancaria.eq(transacao.getTrnId()))
		.or(valor.eq(transacao.getTrnValor()));
		
		/*LocalDate from = transacao.getTrnData().minusDays(30);
		LocalDate to = transacao.getTrnData().plusDays(30);*/
		
		BooleanBuilder filtroPerido = new BooleanBuilder();
		//filtroPerido.and(qCaixaLancamentoEntity.dataLancamento.between(from, to));
		filtroPerido.and(qCaixaLancamentoEntity.dataLancamento.eq(transacao.getTrnData()));
		
		BooleanBuilder where = new BooleanBuilder();
		where.and(filtroDados).and(filtroPerido);
		
		List<CaixaLancamentoEntity> lancamentos = query
				.selectFrom(qCaixaLancamentoEntity)
				.where(where)
				.orderBy(qCaixaLancamentoEntity.dataLancamento.asc())
				.fetch();
		
		return lancamentos;
	}
	
	@Transactional(readOnly = true)
	@Override
	public PlanoContaEntity findPlanoContaPelaTransacaoBancaria(ConciliacaoTransacaoDTO transacao) {
		
		if (isEmpty(transacao)) {
			return null;
		}
		
		UUID planoContaId = isNotEmpty(transacao.getTituloPlanoContas()) ? transacao.getTituloPlanoContas().getId() : null; 
		
		if (isEmpty(planoContaId)) {
			boolean isCredito = transacao.isCredito();
			planoContaId = isCredito ? PLANO_CONTA_DEFAULT_CREDITO : PLANO_CONTA_DEFAULT_DEBITO;
		}
		
		PlanoContaEntity planoConta = planoContasRepository.findById(planoContaId).orElse(null);
		
		return planoConta;
		
	}
	
	@Transactional(readOnly = true)
	@Override
	public ContaBancariaEntity findContaBancaria(String bancoId, String agenciaId, String contaBancariaId) {
		ContaBancariaEntity contaBancariaEntity = contaBancariaRepository.findByNumeroContaAndAgenciaNumeroAgenciaAndAgenciaBancoNumero(contaBancariaId, agenciaId, bancoId);
		return contaBancariaEntity;
	}
	
	
}
