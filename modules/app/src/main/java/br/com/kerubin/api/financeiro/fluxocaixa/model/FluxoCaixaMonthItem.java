package br.com.kerubin.api.financeiro.fluxocaixa.model;

import java.math.BigDecimal;

public interface FluxoCaixaMonthItem extends MonthVisitable {
	
	Integer getMonthId();
	
	void setMonthName(String monthName);
	
	BigDecimal getBalanceValue();
	
	void setBalanceAccumulated(BigDecimal balanceAccumulated);

}
