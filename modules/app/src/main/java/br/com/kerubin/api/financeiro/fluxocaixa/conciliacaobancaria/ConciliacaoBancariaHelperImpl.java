package br.com.kerubin.api.financeiro.fluxocaixa.conciliacaobancaria;

import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isEquals;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEmpty;

import java.math.BigDecimal;
import java.time.LocalDate;
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
	public CaixaLancamentoEntity findLancamentoPelaTransacaoBancaria(ConciliacaoTransacaoDTO transacao) {
		
		if (isEmpty(transacao)) {
			return null;
		}
		
		JPAQueryFactory query = new JPAQueryFactory(em);
		QCaixaLancamentoEntity qCaixaLancamentoEntity = QCaixaLancamentoEntity.caixaLancamentoEntity;
		
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
		
		if (isNotEmpty(contas)) {
			CaixaLancamentoEntity contaCandidata = contas.stream().filter(conta -> transacao.getTrnDocumento().equals(conta.getNumDocConcBancaria())).findFirst().orElse(null);
			
			if (isEmpty(contaCandidata)) {
				if (isCredito) {
					contaCandidata = contas.stream().filter(conta -> isEquals(transacao.getTrnValor(), conta.getValorCredito())).findFirst().orElse(null);
				}
				else {
					contaCandidata = contas.stream().filter(conta -> isEquals(transacao.getTrnValor(), conta.getValorDebito())).findFirst().orElse(null);
				}
			}
			return contaCandidata;
		}
		
		return null;
	}
	
	@Transactional(readOnly = true)
	@Override
	public PlanoContaEntity findPlanoContaPelaTransacaoBancaria(ConciliacaoTransacaoDTO transacao) {
		
		if (isEmpty(transacao)) {
			return null;
		}
		
		boolean isCredito = transacao.isCredito();
		UUID id = isCredito ? PLANO_CONTA_DEFAULT_CREDITO : PLANO_CONTA_DEFAULT_DEBITO;
		
		PlanoContaEntity planoConta = planoContasRepository.findById(id).orElse(null);
		
		return planoConta;
		
	}
	
	@Transactional(readOnly = true)
	@Override
	public ContaBancariaEntity findContaBancaria(String bancoId, String agenciaId, String contaBancariaId) {
		ContaBancariaEntity contaBancariaEntity = contaBancariaRepository.findByNumeroContaAndAgenciaNumeroAgenciaAndAgenciaBancoNumero(contaBancariaId, agenciaId, bancoId);
		return contaBancariaEntity;
	}
	
	
}
