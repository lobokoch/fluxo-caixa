package br.com.kerubin.api.financeiro.fluxocaixa.model;

import java.math.BigDecimal;

public interface FluxoCaixaMonthItem extends MonthItem, MonthVisitable {
	
	BigDecimal getBalanceValue();
	
	void setBalanceAccumulated(BigDecimal balanceAccumulated);

}
