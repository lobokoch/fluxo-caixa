package br.com.kerubin.api.financeiro.fluxocaixa.service;

import static br.com.kerubin.api.servicecore.util.CoreUtils.getSafeValue;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.isNotEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.lowerWithFirstUpper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Coalesce;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.financeiro.fluxocaixa.TipoLancamentoFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.QCaixaLancamentoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.QPlanoContaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.model.CaixaMovimentoItem;
import br.com.kerubin.api.financeiro.fluxocaixa.model.FluxoCaixaMonthItem;
import br.com.kerubin.api.financeiro.fluxocaixa.model.FluxoCaixaMonthItemImpl;
import br.com.kerubin.api.financeiro.fluxocaixa.model.FluxoCaixaPlanoContasForMonth;
import br.com.kerubin.api.financeiro.fluxocaixa.model.FluxoCaixaPlanoContasItem;
import br.com.kerubin.api.financeiro.fluxocaixa.model.FluxoCaixaPlanoContasMonthDBProjection;
import br.com.kerubin.api.financeiro.fluxocaixa.model.MonthVisitor;

@Service
public class FluxoCaixaDashboardImpl implements FluxoCaixaDashboard {
	
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private MonthVisitor monthVisitor;
	
	private QCaixaLancamentoEntity qCaixaLancamentos;
	
	public FluxoCaixaDashboardImpl() {
		qCaixaLancamentos = QCaixaLancamentoEntity.caixaLancamentoEntity;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<FluxoCaixaMonthItem> getFluxoCaixaFromCurrentYear() {
		return getFluxoCaixaFromYear(LocalDate.now().getYear());
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<FluxoCaixaMonthItem> getFluxoCaixaFromYear(int year) {
		JPAQueryFactory query = new JPAQueryFactory(em);
		
		QCaixaLancamentoEntity qLancamentos = QCaixaLancamentoEntity.caixaLancamentoEntity;
		
		NumberExpression<Integer> monthId = qLancamentos.dataLancamento.month();
		Coalesce<BigDecimal> creditValue = qLancamentos.valorCredito.sum().coalesce(BigDecimal.ZERO);
		Coalesce<BigDecimal> debitValue = qLancamentos.valorDebito.sum().coalesce(BigDecimal.ZERO);
		
		NumberExpression<?> balanceValue = creditValue.asNumber().subtract(debitValue.asNumber());
		
		List<FluxoCaixaMonthItemImpl> dbItems = query
			.select(
				Projections.bean(FluxoCaixaMonthItemImpl.class,
				monthId.as("monthId"),
				creditValue.as("creditValue"),
				debitValue.as("debitValue"),
				balanceValue.as("balanceValue")
				)
			)
			.from(qLancamentos)
			.where(qLancamentos.dataLancamento.year().eq(year))
			.groupBy(monthId)
			.orderBy(monthId.asc())
			.fetch();
		
		return buildFullYearList(dbItems);
		
	}
	
	private List<FluxoCaixaMonthItem> buildFullYearList(List<FluxoCaixaMonthItemImpl> actual) {
		
		List<FluxoCaixaMonthItem> items = new ArrayList<>(12);
		BigDecimal balanceAccumulated = new BigDecimal(0.0);
		int currentMonth = LocalDate.now().getMonthValue();
		for (Integer month = 1; month <= 12; month++) {
			final Integer monthId = month;
			FluxoCaixaMonthItem item = actual.stream().filter(it -> monthId.equals(it.getMonthId())).findFirst().orElse(new FluxoCaixaMonthItemImpl(monthId));
			item.accectMonthVisitor(monthVisitor);
			if (month <= currentMonth ) {
				BigDecimal itemBalanceValue = getSafeValue(item.getBalanceValue());
				balanceAccumulated = balanceAccumulated.add(itemBalanceValue);
				item.setBalanceAccumulated(balanceAccumulated);
			}
			items.add(item);
		}
		
		return items;
		
	}
	
	@Transactional
	@Override
	public List<FluxoCaixaPlanoContasForMonth> getResumoMensalPorPlanoContasDebitos() {
		return getResumoMensalPorPlanoContas(LocalDate.now().getYear(), TipoLancamentoFinanceiro.DEBITO);
	}
	
	@Transactional
	@Override
	public List<FluxoCaixaPlanoContasForMonth> getResumoMensalPorPlanoContasDebitos(int year) {
		return getResumoMensalPorPlanoContas(year, TipoLancamentoFinanceiro.DEBITO);
	}
	
	@Transactional
	@Override
	public List<FluxoCaixaPlanoContasForMonth> getResumoMensalPorPlanoContasCreditos() {
		return getResumoMensalPorPlanoContas(LocalDate.now().getYear(), TipoLancamentoFinanceiro.CREDITO);
	}
	
	@Transactional
	@Override
	public List<FluxoCaixaPlanoContasForMonth> getResumoMensalPorPlanoContasCreditos(int year) {
		return getResumoMensalPorPlanoContas(year, TipoLancamentoFinanceiro.CREDITO);
	}
	
	public List<FluxoCaixaPlanoContasForMonth> getResumoMensalPorPlanoContas(int year, TipoLancamentoFinanceiro tipoLancamento) {
		JPAQueryFactory query = new JPAQueryFactory(em);
		
		QCaixaLancamentoEntity qLancamentos = QCaixaLancamentoEntity.caixaLancamentoEntity;
		QPlanoContaEntity qPlanoContas = QPlanoContaEntity.planoContaEntity;
		QPlanoContaEntity qPlanoContasPai = new QPlanoContaEntity("planoContasPai");
		
		NumberExpression<Integer> monthId = qLancamentos.dataLancamento.month();
		
		boolean isCredito = TipoLancamentoFinanceiro.CREDITO.equals(tipoLancamento);
		NumberPath<BigDecimal> valueField = isCredito ? qLancamentos.valorCredito : qLancamentos.valorDebito;
		
		Coalesce<BigDecimal> sumValueField = valueField.sum().coalesce(BigDecimal.ZERO); 
				
		List<FluxoCaixaPlanoContasMonthDBProjection> dbItems = query
			.select(
				Projections.bean(FluxoCaixaPlanoContasMonthDBProjection.class,
				monthId.as("monthId"),
				qPlanoContasPai.codigo.as("planoContaCode"),
				qPlanoContasPai.descricao.as("planoContaDescription"),
				sumValueField.as("value")
				)
			)
			.from(qLancamentos)
			.leftJoin(qPlanoContas).on(qPlanoContas.id.eq(qLancamentos.planoContas.id))
			.leftJoin(qPlanoContasPai).on(qPlanoContasPai.id.eq(qPlanoContas.planoContaPai.id))
			.where(
					qLancamentos.dataLancamento.year().eq(year).
					and(qLancamentos.tipoLancamentoFinanceiro.eq(tipoLancamento))
			)
			.groupBy(monthId, qPlanoContasPai.codigo, qPlanoContasPai.descricao)
			.orderBy(monthId.asc(), sumValueField.desc())
			.fetch();
		
		Map<Integer, List<FluxoCaixaPlanoContasMonthDBProjection>> itemsGroupedByMonth = dbItems.stream().collect(Collectors.groupingBy(FluxoCaixaPlanoContasMonthDBProjection::getMonthId));
		
		// Check if current month is in the results.
		int currentMonth = LocalDate.now().getMonthValue(); 
		if (!itemsGroupedByMonth.containsKey(currentMonth)) {
			FluxoCaixaPlanoContasMonthDBProjection thisMonth = new FluxoCaixaPlanoContasMonthDBProjection();
			thisMonth.setMonthId(currentMonth);
			thisMonth.setValue(BigDecimal.ZERO);
			itemsGroupedByMonth.put(currentMonth, Arrays.asList(thisMonth));
		}
		
		List<FluxoCaixaPlanoContasForMonth> itemsByMonth = new ArrayList<>();
		itemsGroupedByMonth.entrySet().stream().forEach(monthGrouped -> {
			FluxoCaixaPlanoContasForMonth forMonth = new FluxoCaixaPlanoContasForMonth();
			
			forMonth.setMonthId(monthGrouped.getKey());
			forMonth.accectMonthVisitor(monthVisitor);
			
			List<FluxoCaixaPlanoContasItem> items = monthGrouped.getValue()
				.stream()
				.map(dbProjection -> new FluxoCaixaPlanoContasItem(dbProjection.getValue(), dbProjection.getPlanoContaCode(), dbProjection.getPlanoContaDescription()))
				.collect(Collectors.toList());
			
			items.forEach(it -> {
				it.setPlanoContaDescription(lowerWithFirstUpper(it.getPlanoContaDescription()));
				
				if (isEmpty(it.getPlanoContaCode())) {
					it.setPlanoContaCode("0");
				}
				if (isEmpty(it.getPlanoContaDescription())) {
					it.setPlanoContaDescription("N/A");
				}
				
			});
			
			forMonth.setItems(items);
			
			itemsByMonth.add(forMonth);
		});
		
		
		
		return itemsByMonth;
	}
	
	// OLD
	public List<FluxoCaixaPlanoContasForMonth> getDespesasMensaisPorPlanoContasDetail(int year) {
		JPAQueryFactory query = new JPAQueryFactory(em);
		
		QCaixaLancamentoEntity qLancamentos = QCaixaLancamentoEntity.caixaLancamentoEntity;
		QPlanoContaEntity qPlanoContas = QPlanoContaEntity.planoContaEntity;
		//QPlanoContaEntity qPlanoContasPai = new QPlanoContaEntity("planoContasPai");
		
		NumberExpression<Integer> monthId = qLancamentos.dataLancamento.month();
		Coalesce<BigDecimal> debitValue = qLancamentos.valorDebito.sum().coalesce(BigDecimal.ZERO);
		
		List<FluxoCaixaPlanoContasMonthDBProjection> dbItems = query
				.select(
						Projections.bean(FluxoCaixaPlanoContasMonthDBProjection.class,
								monthId.as("monthId"),
								qPlanoContas.codigo.as("planoContaCode"),
								qPlanoContas.descricao.as("planoContaDescription"),
								debitValue.as("value")
								)
						)
				.from(qLancamentos)
				.leftJoin(qPlanoContas).on(qPlanoContas.id.eq(qLancamentos.planoContas.id))
				//.leftJoin(qPlanoContasPai).on(qPlanoContasPai.id.eq(qPlanoContas.planoContaPai.id))
				.where(
						qLancamentos.dataLancamento.year().eq(year).
						and(qLancamentos.tipoLancamentoFinanceiro.eq(TipoLancamentoFinanceiro.DEBITO))
						)
				.groupBy(monthId, qPlanoContas.codigo, qPlanoContas.descricao)
				.orderBy(monthId.asc(), debitValue.desc())
				.fetch();
		
		Map<Integer, List<FluxoCaixaPlanoContasMonthDBProjection>> itemsGroupedByMonth = dbItems.stream().collect(Collectors.groupingBy(FluxoCaixaPlanoContasMonthDBProjection::getMonthId));
		
		List<FluxoCaixaPlanoContasForMonth> itemsByMonth = new ArrayList<>();
		itemsGroupedByMonth.entrySet().stream().forEach(monthGrouped -> {
			FluxoCaixaPlanoContasForMonth forMonth = new FluxoCaixaPlanoContasForMonth();
			
			forMonth.setMonthId(monthGrouped.getKey());
			forMonth.accectMonthVisitor(monthVisitor);
			
			List<FluxoCaixaPlanoContasItem> items = monthGrouped.getValue()
					.stream()
					.map(dbProjection -> new FluxoCaixaPlanoContasItem(dbProjection.getValue(), dbProjection.getPlanoContaCode(), dbProjection.getPlanoContaDescription()))
					.collect(Collectors.toList());
			
			items.forEach(it -> {
				if (isEmpty(it.getPlanoContaCode())) {
					it.setPlanoContaCode("0");
				}
				if (isEmpty(it.getPlanoContaDescription())) {
					it.setPlanoContaDescription("N/A");
				}
				
				it.setPlanoContaDescription(lowerWithFirstUpper(it.getPlanoContaDescription()));
			});
			
			forMonth.setItems(items);
			
			itemsByMonth.add(forMonth);
		});
		
		return itemsByMonth;
	}
	
	@Transactional(readOnly = true)
	@Override
	public List<CaixaMovimentoItem> getFluxoCaixaResumoMovimentacoes() {
		NumberPath<BigDecimal> fieldValorCredito = qCaixaLancamentos.valorCredito;
		NumberPath<BigDecimal> fieldValorDebito = qCaixaLancamentos.valorDebito;
		
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		LocalDate monthStart = today.withDayOfMonth(1);
		LocalDate monthEnd = today.withDayOfMonth(today.lengthOfMonth());
		
		// Mês atual
		JPQLQuery<BigDecimal> querySomaCreditosDoMes = buildFieldSumQueryBetweenDates(fieldValorCredito, monthStart, monthEnd, "somaCreditosDoMes");
		JPQLQuery<BigDecimal> querySomaDebitosDoMes = buildFieldSumQueryBetweenDates(fieldValorDebito, monthStart, monthEnd, "somaDebitosDoMes");
		
		// Últimos n=3 dias
		long lastDays = 3;
		LocalDate lastDaysDate = today.minusDays(lastDays);
		JPQLQuery<BigDecimal> querySomaCreditosLastDays = buildFieldSumQueryBetweenDates(fieldValorCredito, lastDaysDate, today, "somaCreditosLastDays");
		JPQLQuery<BigDecimal> querySomaDebitosLastDays = buildFieldSumQueryBetweenDates(fieldValorDebito, lastDaysDate, today, "somaDebitosLastDays");
		
		// Ontem
		JPQLQuery<BigDecimal> querySomaCreditosOntem = buildFieldSumQueryBetweenDates(fieldValorCredito, yesterday, yesterday, "somaCreditosOntem");
		JPQLQuery<BigDecimal> querySomaDebitosOntem = buildFieldSumQueryBetweenDates(fieldValorDebito, yesterday, yesterday, "somaDebitosOntem");
		
		// Saldo Atual
		LocalDate statDate = LocalDate.of(2019, 1, 1);
		JPQLQuery<BigDecimal> querySomaCreditosTudo = buildFieldSumQueryBetweenDates(fieldValorCredito, statDate, today, "somaCreditosTudo");
		JPQLQuery<BigDecimal> querySomaDebitosTudo = buildFieldSumQueryBetweenDates(fieldValorDebito, statDate, today, "somaDebitosTudo");
		
		JPAQueryFactory query = new JPAQueryFactory(em);
		
		Tuple tuple = query
				.selectDistinct( //TODO: deveria ser apenas "select", vide TODO logo abaixo.
						querySomaCreditosTudo, 
						querySomaDebitosTudo,
						
						querySomaCreditosDoMes,
						querySomaDebitosDoMes,
						
						querySomaCreditosLastDays,
						querySomaDebitosLastDays,
						
						querySomaCreditosOntem,
						querySomaDebitosOntem
				)
				.from(qCaixaLancamentos) // TODO: O PostgreSQL suporta sem o from mas o JPA parece que não, dá erro: java.lang.IllegalArgumentException: No sources given.
				.fetchOne();
		
		List<CaixaMovimentoItem> movimentos = new ArrayList<>();
		if (isNotEmpty(tuple)) {
			final Locale PT_BR = new Locale("pt", "BR"); //Locale para o Brasil
			String yesterdayStr = yesterday.getDayOfWeek().getDisplayName(TextStyle.FULL, PT_BR);
			
			int index = 0;
			CaixaMovimentoItem item = new CaixaMovimentoItem("Tudo até o momento", getTupleValue(tuple, index++), getTupleValue(tuple, index++));
			movimentos.add(item);
			
			String thisMontName = today.getMonth().getDisplayName(TextStyle.FULL, PT_BR);
			item = new CaixaMovimentoItem("Este mês (" + thisMontName + ")", getTupleValue(tuple, index++), getTupleValue(tuple, index++));
			movimentos.add(item);
			
			item = new CaixaMovimentoItem("Últimos " + lastDays + " dias", getTupleValue(tuple, index++), getTupleValue(tuple, index++));
			movimentos.add(item);
			
			
			item = new CaixaMovimentoItem("Ontem (" + yesterdayStr + ")", getTupleValue(tuple, index++), getTupleValue(tuple, index++));
			movimentos.add(item);
		}
		
		return movimentos;
	}
	
	private BigDecimal getTupleValue(Tuple tuple, int index) {
		return tuple.get(index, BigDecimal.class);
	}
	
	private JPQLQuery<BigDecimal> buildFieldSumQueryBetweenDates(NumberPath<BigDecimal> fieldToSum, LocalDate dateFrom, LocalDate dateTo, String alias) {
		
		Coalesce<BigDecimal> fieldValue = fieldToSum.sum().coalesce(BigDecimal.ZERO);
		
		JPQLQuery<BigDecimal> query = JPAExpressions
				.select(fieldValue.as(alias))
				.from(qCaixaLancamentos)
				.where(qCaixaLancamentos.dataLancamento.between(dateFrom, dateTo));
		
		return query;
	}
	

}
