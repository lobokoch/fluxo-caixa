package br.com.kerubin.api.financeiro.fluxocaixa.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Coalesce;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.financeiro.fluxocaixa.CaixaDiarioSituacao;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoLancamentoFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiario;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioRuleFunctions;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.QCaixaDiarioEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.QCaixaLancamentoEntity;

@Service
public class CaixaDiarioRuleFunctionsImpl implements CaixaDiarioRuleFunctions {
	
	@Inject
	private CaixaDiarioRepository caixaDiarioRepository;
	
	@Inject
	private CaixaRepository caixaRepository;
	
	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public CaixaDiarioEntity abrirCaixa(UUID id, CaixaDiario caixaDiario) {
		String nomeCaixa = getNomeDoCaixa(caixaDiario);
		CaixaDiarioEntity caixaDiarioAtual = caixaDiarioRepository.findById(id).orElse(null);
		if (caixaDiarioAtual == null) {
			throw new IllegalStateException("Não é possível abrir o caixa \"" + nomeCaixa + "\".");
		}
		
		if (caixaDiarioAtual.getCaixaDiarioSituacao().equals(CaixaDiarioSituacao.ABERTO)) {
			throw new IllegalStateException("O caixa \"" + caixaDiarioAtual.getCaixa().getNome() + "\" já está aberto.");
		}
		
		if (caixaDiarioAtual.getCaixaDiarioSituacao().equals(CaixaDiarioSituacao.FECHADO)) {
			throw new IllegalStateException("O caixa \"" + caixaDiarioAtual.getCaixa().getNome() + "\" está fechado.");
		}
		
		CaixaDiarioEntity caixaDiarioAnterior = getCaixaDiarioAnterior(caixaDiarioAtual.getCaixa().getId(), caixaDiarioAtual.getId());
		boolean hasCaixaAnterior = caixaDiarioAnterior != null;
		if (hasCaixaAnterior && !caixaDiarioAnterior.getCaixaDiarioSituacao().equals(CaixaDiarioSituacao.FECHADO)) {
			throw new IllegalStateException("O caixa \""+ caixaDiarioAnterior.getCaixa().getNome() + "\" anterior deve ser fechado para poder abrir um novo caixa.");
		}
		
		caixaDiarioAtual.setDataHoraAbertura(LocalDateTime.now());
		caixaDiarioAtual.setDataHoraFechamento(null);
		
		BigDecimal saloInicial = new BigDecimal("0.0");
		if (hasCaixaAnterior) {
			saloInicial = saloInicial.add(getSafeVal(caixaDiarioAnterior.getSaldoFinal()));
		}
		caixaDiarioAtual.setSaldoInicial(saloInicial);
		caixaDiarioAtual.setSaldoFinal(null);
		
		caixaDiarioAtual.setCaixaDiarioSituacao(CaixaDiarioSituacao.ABERTO);
		
		caixaDiarioAtual = caixaDiarioRepository.save(caixaDiarioAtual);
		
		//TODO: publicar mensagem de caixa aberto.
		
		return caixaDiarioAtual;
	}

	@Override
	public CaixaDiarioEntity fecharCaixa(UUID id, CaixaDiario caixaDiario) {
		String nomeCaixa = getNomeDoCaixa(caixaDiario);
		CaixaDiarioEntity caixaDiarioAtual = caixaDiarioRepository.findById(id).orElse(null);
		if (caixaDiarioAtual == null) {
			throw new IllegalStateException("Não é possível fechar o caixa \"" + nomeCaixa + "\".");
		}
		
		if (!caixaDiarioAtual.getCaixaDiarioSituacao().equals(CaixaDiarioSituacao.ABERTO)) {
			throw new IllegalStateException("O caixa \"" + caixaDiarioAtual.getCaixa().getNome() + "\" não está aberto.");
		}
		
		BigDecimal saldoDosLancamentosDiarios = getSafeVal(getSaltoDosLancamentosDoCaixaDiario(caixaDiarioAtual.getId()));
		
		BigDecimal saldoFinal = saldoDosLancamentosDiarios.add(getSafeVal(caixaDiarioAtual.getSaldoInicial()));
		caixaDiarioAtual.setSaldoFinal(saldoFinal);
		
		caixaDiarioAtual.setDataHoraFechamento(LocalDateTime.now());
		caixaDiarioAtual.setCaixaDiarioSituacao(CaixaDiarioSituacao.FECHADO);
		
		caixaDiarioAtual = caixaDiarioRepository.save(caixaDiarioAtual);
		
		// Atualiza o saldo do caixa geral.
		CaixaEntity caixaEntity = caixaRepository.findById(caixaDiarioAtual.getCaixa().getId()).orElse(null);
		BigDecimal saldoCaixa = getSafeVal(caixaEntity.getSaldo());
		saldoCaixa = saldoCaixa.add(saldoDosLancamentosDiarios);
		caixaEntity.setSaldo(saldoCaixa);
		caixaRepository.save(caixaEntity);
		//TODO: publicar mensagem de caixa fechado.
		
		return caixaDiarioAtual;
	}
	
	private BigDecimal getSaltoDosLancamentosDoCaixaDiario(UUID caixaDiarioId) {
		JPAQueryFactory query = new JPAQueryFactory(em);
		QCaixaLancamentoEntity qCaixaLancamento = QCaixaLancamentoEntity.caixaLancamentoEntity;
		
		Coalesce<BigDecimal> sumValorCredito = qCaixaLancamento.valorCredito.sum().coalesce(BigDecimal.ZERO);
		Coalesce<BigDecimal> sumValorDebito = qCaixaLancamento.valorDebito.sum().coalesce(BigDecimal.ZERO);
		
		JPQLQuery<BigDecimal> sumCredito = JPAExpressions
				.select(sumValorCredito.as("sumCredito"))
				.from(qCaixaLancamento)
				.where(qCaixaLancamento.caixaDiario.id.eq(caixaDiarioId).
						and(qCaixaLancamento.tipoLancamentoFinanceiro.eq(TipoLancamentoFinanceiro.CREDITO)));
		
		JPQLQuery<BigDecimal> sumDebito = JPAExpressions
				.select(sumValorDebito.as("sumDebito"))
				.from(qCaixaLancamento)
				.where(qCaixaLancamento.caixaDiario.id.eq(caixaDiarioId).
						and(qCaixaLancamento.tipoLancamentoFinanceiro.eq(TipoLancamentoFinanceiro.DEBITO)));
		
		BigDecimal creditos = new BigDecimal("0.0");
		BigDecimal debitos = new BigDecimal("0.0");
		Tuple data = query.selectDistinct(sumCredito, sumDebito).from(qCaixaLancamento).fetchOne();
		if (data != null) {
			creditos = data.get(0, BigDecimal.class);
			debitos = data.get(1, BigDecimal.class);
		}
		
		return creditos.subtract(debitos);
	}

	private CaixaDiarioEntity getCaixaDiarioAnterior(UUID caixaId, UUID caixaDiarioAtual) {
		JPAQueryFactory query = new JPAQueryFactory(em);
		QCaixaDiarioEntity qCaixaDiarioEntity = QCaixaDiarioEntity.caixaDiarioEntity;
		
		JPQLQuery<LocalDateTime> maxLastModifiedDate = JPAExpressions
				.select(qCaixaDiarioEntity.lastModifiedDate.max())
				.from(qCaixaDiarioEntity)
				.where(qCaixaDiarioEntity.caixa.id.eq(caixaId)
						.and(qCaixaDiarioEntity.id.ne(caixaDiarioAtual)));
		
		List<CaixaDiarioEntity> caixas = query
			.selectFrom(qCaixaDiarioEntity)
			.where(qCaixaDiarioEntity.caixa.id.eq(caixaId)
					.and(qCaixaDiarioEntity.id.ne(caixaDiarioAtual))
					.and(qCaixaDiarioEntity.lastModifiedDate.eq(maxLastModifiedDate)))
			.orderBy(qCaixaDiarioEntity.version.desc())
			.fetch();
		
		if (caixas.isEmpty()) {
			return null;
		}
		
		return caixas.get(0);
	}
	
	private String getNomeDoCaixa(CaixaDiario caixaDiario) {
		String result = "<Sem nome>";
		if (caixaDiario != null && caixaDiario.getCaixa() != null && caixaDiario.getCaixa().getNome() != null) {
			result = caixaDiario.getCaixa().getNome();
		}
		
		return result;
	}
	
	private BigDecimal getSafeVal(BigDecimal value) {
		return value != null ? value : new BigDecimal("0.0");
	}

}
