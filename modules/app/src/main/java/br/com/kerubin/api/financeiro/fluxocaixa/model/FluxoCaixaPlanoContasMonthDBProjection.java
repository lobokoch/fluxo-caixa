package br.com.kerubin.api.financeiro.fluxocaixa.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FluxoCaixaPlanoContasMonthDBProjection extends MonthItemImpl implements FluxoCaixaByPlanoContasMonth {
	
	private BigDecimal value;
	private String planoContaCode; 
	private String planoContaDescription;
	
	public FluxoCaixaPlanoContasMonthDBProjection() {
		
	}

}
