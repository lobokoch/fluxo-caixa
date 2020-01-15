/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento;

import org.springframework.util.CollectionUtils;

import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;

@Component
public class CaixaLancamentoListFilterPredicateImpl implements CaixaLancamentoListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(CaixaLancamentoListFilter caixaLancamentoListFilter) {
		if (caixaLancamentoListFilter == null) {
			return null;
		}
		
		QCaixaLancamentoEntity qEntity = QCaixaLancamentoEntity.caixaLancamentoEntity;
		BooleanBuilder where = new BooleanBuilder();
		
		// Begin field: descricao
		if (!CollectionUtils.isEmpty(caixaLancamentoListFilter.getDescricao())) {
			BooleanExpression inExpression = qEntity.descricao.in(caixaLancamentoListFilter.getDescricao());
			where.and(inExpression);
		}
		// End field: descricao
		
		
		// Begin field: DataLancamento
		java.time.LocalDate fieldFromDataLancamento = caixaLancamentoListFilter.getDataLancamentoFrom();
		java.time.LocalDate fieldToDataLancamento = caixaLancamentoListFilter.getDataLancamentoTo();
		
		if (fieldFromDataLancamento != null && fieldToDataLancamento != null) {
			if (fieldFromDataLancamento.isAfter(fieldToDataLancamento)) {
				throw new IllegalArgumentException("Valor de \"Lançamentos de\" não pode ser maior do que valor de \"Até\".");
			}
			
			BooleanExpression between = qEntity.dataLancamento.between(fieldFromDataLancamento, fieldToDataLancamento);
			where.and(between);
		}
		else {
			if (fieldFromDataLancamento != null) {
				where.and(qEntity.dataLancamento.goe(fieldFromDataLancamento));
			}
			else if (fieldToDataLancamento != null) {
				where.and(qEntity.dataLancamento.loe(fieldToDataLancamento));				
			}
		}
		// End field: DataLancamento
		
		// Begin field: tipoLancamentoFinanceiro
		if (caixaLancamentoListFilter.getTipoLancamentoFinanceiro() != null) {
			BooleanExpression tipoLancamentoFinanceiroIsEqualTo = qEntity.tipoLancamentoFinanceiro.eq(caixaLancamentoListFilter.getTipoLancamentoFinanceiro());
			where.and(tipoLancamentoFinanceiroIsEqualTo);
		}
		// End field: tipoLancamentoFinanceiro
		
		
		// Begin field: ValorCredito
		java.math.BigDecimal fieldFromValorCredito = caixaLancamentoListFilter.getValorCreditoFrom();
		java.math.BigDecimal fieldToValorCredito = caixaLancamentoListFilter.getValorCreditoTo();
		
		if (fieldFromValorCredito != null && fieldToValorCredito != null) {
			
			BooleanExpression between = qEntity.valorCredito.between(fieldFromValorCredito, fieldToValorCredito);
			where.and(between);
		}
		else {
			if (fieldFromValorCredito != null) {
				where.and(qEntity.valorCredito.goe(fieldFromValorCredito));
			}
			else if (fieldToValorCredito != null) {
				where.and(qEntity.valorCredito.loe(fieldToValorCredito));				
			}
		}
		// End field: ValorCredito
		
		
		// Begin field: ValorDebito
		java.math.BigDecimal fieldFromValorDebito = caixaLancamentoListFilter.getValorDebitoFrom();
		java.math.BigDecimal fieldToValorDebito = caixaLancamentoListFilter.getValorDebitoTo();
		
		if (fieldFromValorDebito != null && fieldToValorDebito != null) {
			
			BooleanExpression between = qEntity.valorDebito.between(fieldFromValorDebito, fieldToValorDebito);
			where.and(between);
		}
		else {
			if (fieldFromValorDebito != null) {
				where.and(qEntity.valorDebito.goe(fieldFromValorDebito));
			}
			else if (fieldToValorDebito != null) {
				where.and(qEntity.valorDebito.loe(fieldToValorDebito));				
			}
		}
		// End field: ValorDebito
		
		// Begin field: formaPagamento
		if (caixaLancamentoListFilter.getFormaPagamento() != null) {
			BooleanExpression formaPagamentoIsEqualTo = qEntity.formaPagamento.eq(caixaLancamentoListFilter.getFormaPagamento());
			where.and(formaPagamentoIsEqualTo);
		}
		// End field: formaPagamento
		
		// Begin field: IdConcBancaria
		if (caixaLancamentoListFilter.getIdConcBancariaIsNotNull() != null) {		
			if (caixaLancamentoListFilter.isIdConcBancariaIsNotNull()) {
				where.and(qEntity.idConcBancaria.isNotNull());
			}
			else {
				where.and(qEntity.idConcBancaria.isNull());
			}
		}
		// End field: IdConcBancaria
		
		// Begin field: histConcBancaria
		if (!CollectionUtils.isEmpty(caixaLancamentoListFilter.getHistConcBancaria())) {
			BooleanExpression inExpression = qEntity.histConcBancaria.in(caixaLancamentoListFilter.getHistConcBancaria());
			where.and(inExpression);
		}
		// End field: histConcBancaria
		
		return where;
	}

}

