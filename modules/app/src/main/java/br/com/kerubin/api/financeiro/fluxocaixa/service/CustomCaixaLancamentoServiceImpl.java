package br.com.kerubin.api.financeiro.fluxocaixa.service;

import java.util.Collection;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
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
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoServiceImpl;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaAutoCompleteImpl;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.QPlanoContaEntity;
import static br.com.kerubin.api.messaging.utils.Utils.*;

@Primary
@Service
public class CustomCaixaLancamentoServiceImpl extends CaixaLancamentoServiceImpl {
	
	@Autowired
	private EntityManager em;
	
	@Inject
	private CaixaDiarioRepository caixaDiarioRepository;
	
	@Inject
	private CaixaLancamentoRepository caixaLancamentoRepository;
	
	@SuppressWarnings("unchecked")
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
		
		BooleanBuilder where = new BooleanBuilder();
		if (isNotEmpty(query)) {
			where.and(qPlanoConta.descricao.containsIgnoreCase(query));
		}
		
		where.and(qPlanoConta.ativo.isTrue())
		.and(qPlanoConta.deleted.isFalse());
		
		if (isNotEmpty(tipoPlanoContaFinanceiro)) {
			where.and(qPlanoConta.tipoFinanceiro.eq(tipoPlanoContaFinanceiro));
		}
		
		
		Collection<? extends PlanoContaAutoComplete> result = queryDSL.select(
				Projections.bean(PlanoContaAutoCompleteImpl.class, 
						qPlanoConta.id, 
						qPlanoConta.codigo, 
						qPlanoConta.descricao
						)
		)
		.from(qPlanoConta)
		.where(where)
		.orderBy(qPlanoConta.codigo.asc())
		.fetch();
		 
		return (Collection<PlanoContaAutoComplete>) result;
		
	}
	
	@SuppressWarnings("unchecked")
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
	
	@Override
	public CaixaLancamentoEntity create(CaixaLancamentoEntity caixaLancamentoEntity) {
		validateCaixa(caixaLancamentoEntity);
		
		return super.create(caixaLancamentoEntity);
	}
	
	@Override
	public CaixaLancamentoEntity update(UUID id, CaixaLancamentoEntity caixaLancamentoEntity) {
		validateCaixa(caixaLancamentoEntity);
		
		return super.update(id, caixaLancamentoEntity);
	}
	
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
	
	

}
