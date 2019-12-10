package br.com.kerubin.api.financeiro.fluxocaixa.service;

import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.financeiro.fluxocaixa.CaixaDiarioSituacao;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoPlanoContaFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaAutoCompleteImpl;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioAutoCompleteImpl;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.QCaixaDiarioEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamento;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoListFilter;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoServiceImpl;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaAutoCompleteImpl;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.QPlanoContaEntity;

@Primary
@Service
public class CustomCaixaLancamentoServiceImpl extends CaixaLancamentoServiceImpl {
	
	// private static final UUID PLANO_CONTA_RECEITAS_ID = UUID.fromString("1ea1d30c-83e2-4f8f-8c39-ee53ef0d79fe");
	// private static final UUID PLANO_CONTA_DESPESA_ID =  UUID.fromString("5cd7d81e-7e69-4c26-bf2f-12ad2e286fc5");
	private static final String EMPTY_PLANO_CONTA_PAI_DESC =  " -  / ";
	
	
	@Autowired
	private EntityManager em;
	
	@Inject
	private CaixaDiarioRepository caixaDiarioRepository;
	
	@Inject
	private CaixaLancamentoRepository caixaLancamentoRepository;
	
	///// Begin custom for plano de contas
	
	@Transactional(readOnly = true)
	@Override
	public Page<CaixaLancamentoEntity> list(CaixaLancamentoListFilter caixaLancamentoListFilter, Pageable pageable) {
		Page<CaixaLancamentoEntity> result = super.list(caixaLancamentoListFilter, pageable);
		
		if (isNotEmpty(result)) {
			result.forEach(item -> decoratePlanoContas(item.getPlanoContas()));
		}
		
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public CaixaLancamentoEntity read(UUID id) {
		CaixaLancamentoEntity result = super.read(id);
		if (isNotEmpty(result)) {
			decoratePlanoContas(result.getPlanoContas());
		}
		return result;
	}
	
	@Transactional
	@Override
	public Collection<PlanoContaAutoComplete> planoContaPlanoContasAutoComplete(String query,
			CaixaLancamento caixaLancamento) {
		
		TipoPlanoContaFinanceiro tipoPlanoContaFinanceiro = null;
		
		if (isNotEmpty(caixaLancamento)) {
			switch (caixaLancamento.getTipoLancamentoFinanceiro()) {
			case CREDITO:
				tipoPlanoContaFinanceiro = TipoPlanoContaFinanceiro.RECEITA;
				break;
				
			case DEBITO:
				tipoPlanoContaFinanceiro = TipoPlanoContaFinanceiro.DESPESA;
				break;
				
			default:
				break;
			}
		}
		
		JPAQueryFactory queryDSL = new JPAQueryFactory(em);
		QPlanoContaEntity qPlanoConta = QPlanoContaEntity.planoContaEntity;
		QPlanoContaEntity qPlanoContaPai = new QPlanoContaEntity("planoContaPai");
		
		BooleanBuilder where = new BooleanBuilder();
		if (isNotEmpty(query)) {
			where.and(qPlanoConta.descricao.containsIgnoreCase(query).or(qPlanoContaPai.descricao.containsIgnoreCase(query)));
		}
		
		where.and(qPlanoConta.ativo.isTrue())
		.and(qPlanoConta.deleted.isFalse().or(qPlanoConta.deleted.isNull()));
		
		if (isNotEmpty(tipoPlanoContaFinanceiro)) {
			where.and(qPlanoConta.tipoFinanceiro.eq(tipoPlanoContaFinanceiro));
		}
		
		StringExpression descricaoConcatenation = emptyIfNull(qPlanoContaPai.codigo)
				.concat(" - ")
				.concat(emptyIfNull(qPlanoContaPai.descricao))
				.concat(" / ")
				.concat(qPlanoConta.codigo)
				.concat(" - ")
				.concat(qPlanoConta.descricao);
		
		JPAQuery<Tuple> query_ = queryDSL.select(
				qPlanoConta.id, 
				qPlanoConta.codigo,
				descricaoConcatenation
				)
		.from(qPlanoConta)
		.leftJoin(qPlanoConta.planoContaPai, qPlanoContaPai).on(qPlanoContaPai.id.eq(qPlanoConta.planoContaPai.id))
		.where(where)
		.orderBy(qPlanoConta.codigo.asc());
		
		List<Tuple> tuples = query_.fetch();
		
		List<PlanoContaAutoComplete> items = new ArrayList<>();
		if (tuples != null && !tuples.isEmpty()) {
			for (Tuple tuple: tuples) {
				PlanoContaAutoCompleteImpl plano = new PlanoContaAutoCompleteImpl();
				plano.setId(tuple.get(0, UUID.class));
				plano.setCodigo(tuple.get(1, String.class));
				plano.setDescricao(tuple.get(2, String.class));
				items.add(plano);
			} // for
		}
		
		items = items.stream()
				.peek(it -> it.setDescricao(it.getDescricao().replace(EMPTY_PLANO_CONTA_PAI_DESC, "")))
				.sorted(Comparator.comparingInt(this::codigoToInt))
				.collect(Collectors.toList());
		
		return items;
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public Collection<CaixaDiarioAutoComplete> caixaDiarioCaixaDiarioAutoComplete(String query) {
		JPAQueryFactory queryDSL = new JPAQueryFactory(em);
		 QCaixaDiarioEntity qCaixaDiario = QCaixaDiarioEntity.caixaDiarioEntity;
		 
		 Collection<? extends CaixaDiarioAutoComplete> result = queryDSL.select(
				Projections.bean(CaixaDiarioAutoCompleteImpl.class, 
				qCaixaDiario.id, 
					Projections.bean(
							CaixaAutoCompleteImpl.class, 
							qCaixaDiario.caixa.id,
							qCaixaDiario.caixa.nome,
							qCaixaDiario.caixa.version).as("caixa"),
				qCaixaDiario.dataHoraAbertura, 
				qCaixaDiario.version)
		)
		.from(qCaixaDiario)
		.where(
				qCaixaDiario.caixa.nome.containsIgnoreCase(query).
				and(qCaixaDiario.caixaDiarioSituacao.eq(CaixaDiarioSituacao.ABERTO))
			)
		.orderBy(qCaixaDiario.dataHoraAbertura.desc())
		.fetch();
		 
		return (Collection<CaixaDiarioAutoComplete>) result;
	}
	
	@Transactional
	@Override
	public CaixaLancamentoEntity create(CaixaLancamentoEntity caixaLancamentoEntity) {
		validateCaixa(caixaLancamentoEntity);
		
		return super.create(caixaLancamentoEntity);
	}
	
	@Transactional
	@Override
	public CaixaLancamentoEntity update(UUID id, CaixaLancamentoEntity caixaLancamentoEntity) {
		validateCaixa(caixaLancamentoEntity);
		
		return super.update(id, caixaLancamentoEntity);
	}
	
	@Transactional
	@Override
	public void delete(UUID id) {
		CaixaLancamentoEntity caixaLancamentoEntity = caixaLancamentoRepository.findById(id).orElse(null);
		validateCaixa(caixaLancamentoEntity);
		super.delete(id);
	}
	

	private void validateCaixa(CaixaLancamentoEntity caixaLancamentoEntity) {
		if (caixaLancamentoEntity == null) {
			throw new IllegalStateException("Lançamento inválido.");
		}
		
		if (caixaLancamentoEntity.getCaixaDiario() == null) {
			throw new IllegalStateException("Caixa não informado para o lançamento.");
		}
		
		UUID caixaDiarioId = caixaLancamentoEntity.getCaixaDiario().getId();
		if (caixaDiarioId == null) {
			throw new IllegalStateException("Identificador do caixa é inválido para o lançamento.");
		}
		
		CaixaDiarioEntity caixaDiario = caixaDiarioRepository.findById(caixaDiarioId).orElse(null);
		if (caixaDiario == null) {
			throw new IllegalStateException("Caixa inválido para o lançamento.");
		}
		if (!CaixaDiarioSituacao.ABERTO.equals(caixaDiario.getCaixaDiarioSituacao())) {
			throw new IllegalStateException("O caixa do lançamento deve estar aberto.");
		}
	}
	
	private void decoratePlanoContas(PlanoContaEntity planoContas) {
		if (isNotEmpty(planoContas)) { // Adjusts the field descricao of plano de contas and plano de contas pai.
			String descricao = planoContas.getCodigo() + " - " + planoContas.getDescricao();
			PlanoContaEntity planoContasPai = planoContas.getPlanoContaPai();
			if (isNotEmpty(planoContasPai)) {
				descricao = planoContasPai.getCodigo() + " - " + planoContasPai.getDescricao() + " / " + descricao;
			}
			planoContas.setDescricao(descricao);
		} 
	}
	
	private int codigoToInt(Object toOrderObj) {
		if (toOrderObj != null && toOrderObj instanceof PlanoContaAutoComplete) {
			PlanoContaAutoComplete toOrder = (PlanoContaAutoComplete) toOrderObj;
			String codigo = toOrder.getCodigo();
			if (isNotEmpty(codigo)) {
				codigo = codigo.replace(".", "");
				try {
					return Integer.parseInt(codigo);
				} catch(Exception e) {
					return 0;
				}
			}
		}
		
		return 0;
	}
	
	private static StringExpression emptyIfNull(StringExpression expression) {
	    return expression.coalesce("").asString();
	}

}
