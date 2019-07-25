package br.com.kerubin.api.financeiro.fluxocaixa.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Coalesce;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.QCaixaLancamentoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.model.FluxoCaixaMonthItem;
import br.com.kerubin.api.financeiro.fluxocaixa.model.FluxoCaixaMonthItemImpl;
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
		for (Integer month = 1; month <= 12; month++) {
			final Integer monthId = month;
			FluxoCaixaMonthItem item = actual.stream().filter(it -> monthId.equals(it.getMonthId())).findFirst().orElse(new FluxoCaixaMonthItemImpl(monthId));
			item.accectMonthVisitor(monthVisitor);
			items.add(item);
		}
		
		return items;
		
	}

}
