package br.com.kerubin.api.financeiro.fluxocaixa.service;

import java.util.List;

import br.com.kerubin.api.financeiro.fluxocaixa.model.CaixaMovimentoItem;
import br.com.kerubin.api.financeiro.fluxocaixa.model.FluxoCaixaMonthItem;
import br.com.kerubin.api.financeiro.fluxocaixa.model.FluxoCaixaPlanoContasForMonth;

public interface FluxoCaixaDashboard {

	List<FluxoCaixaMonthItem> getFluxoCaixaFromYear(int year);

	List<FluxoCaixaMonthItem> getFluxoCaixaFromCurrentYear();

	
	// Débitos
	List<FluxoCaixaPlanoContasForMonth> getResumoMensalPorPlanoContasDebitos();
	List<FluxoCaixaPlanoContasForMonth> getResumoMensalPorPlanoContasDebitos(int year);
	
	//Créditos
	List<FluxoCaixaPlanoContasForMonth> getResumoMensalPorPlanoContasCreditos();
	List<FluxoCaixaPlanoContasForMonth> getResumoMensalPorPlanoContasCreditos(int year);

	List<CaixaMovimentoItem> getFluxoCaixaResumoMovimentacoes();

	List<FluxoCaixaMonthItem> decorateWithPrevision(List<FluxoCaixaMonthItem> fluxo);

}
