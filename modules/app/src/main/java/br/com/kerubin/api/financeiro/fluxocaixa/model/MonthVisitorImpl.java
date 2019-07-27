package br.com.kerubin.api.financeiro.fluxocaixa.model;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import static br.com.kerubin.api.servicecore.util.CoreUtils.*;

@Component
public class MonthVisitorImpl implements MonthVisitor {
	
	private static final Map<Integer, String> MONTHS = new HashMap<>();
	private static final String JANUARY = "Janeiro"; // "January";
	private static final String FEBRUARY = "Fevereiro"; // "February";
	private static final String MARCH = "Março"; // "March";
	private static final String APRIL = "Abril"; // "April";
	private static final String MAY = "Maio"; // "May";
	private static final String JUNE = "Junho"; // "June";
	private static final String JULY = "Julho"; // "July";
	private static final String AUGUST = "Agosto"; // "August";
	private static final String SEPTEMBER = "Setembro"; // "September";
	private static final String OCTOBER = "Outubro"; // "October";
	private static final String NOVEMBER = "Novembro"; // "November";
	private static final String DECEMBER = "Dezembro"; // "December";
	
	public MonthVisitorImpl() {
		MONTHS.put(1, JANUARY);
		MONTHS.put(2, FEBRUARY);
		MONTHS.put(3, MARCH);
		MONTHS.put(4, APRIL);
		MONTHS.put(5, MAY);
		MONTHS.put(6, JUNE);
		MONTHS.put(7, JULY);
		MONTHS.put(8, AUGUST);
		MONTHS.put(9, SEPTEMBER);
		MONTHS.put(10, OCTOBER);
		MONTHS.put(11, NOVEMBER);
		MONTHS.put(12, DECEMBER);
	}

	@Override
	public void visit(MonthItem monthItem) {
		
		int month = monthItem.getMonthId();
		String monthName = MONTHS.get(month);
		if (isNotEmpty(monthName)) {
			monthItem.setMonthName(monthName);
		}
	}

}
