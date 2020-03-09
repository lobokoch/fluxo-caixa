package br.com.kerubin.api.financeiro.fluxocaixa.analytics.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonthlySumContas {
	
	private MonthlySumContasPagar monthlySumContasPagar;
	private MonthlySumContasReceber monthlySumContasReceber;

}
