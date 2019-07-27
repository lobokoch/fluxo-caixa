package br.com.kerubin.api.financeiro.fluxocaixa.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FluxoCaixaPlanoContasItem {
	
	private BigDecimal value;
	private String planoContaCode; 
	private String planoContaDescription;
	
	public FluxoCaixaPlanoContasItem() {
		
	}
	
	public FluxoCaixaPlanoContasItem(BigDecimal value, String planoContaCode, String planoContaDescription) {
		this();
		this.value = value;
		this.planoContaCode = planoContaCode;
		this.planoContaDescription = planoContaDescription;
	}

}
