package br.com.kerubin.api.financeiro.fluxocaixa.model;

public interface FluxoCaixaMonthItem extends MonthVisitable {
	
	Integer getMonthId();
	
	void setMonthName(String monthName);

}
