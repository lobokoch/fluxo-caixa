package br.com.kerubin.api.financeiro.fluxocaixa.service;

import static br.com.kerubin.api.servicecore.util.CoreUtils.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Coalesce;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.financeiro.fluxocaixa.TipoLancamentoFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.QCaixaLancamentoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.QPlanoContaEntity;
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
				BigDecimal itemBalanceValue = getSafeVal(item.getBalanceValue());
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
	
	

}
