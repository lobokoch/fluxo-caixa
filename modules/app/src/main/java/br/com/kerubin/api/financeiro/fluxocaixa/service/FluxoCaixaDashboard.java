package br.com.kerubin.api.financeiro.fluxocaixa.service;

import java.util.List;

import br.com.kerubin.api.financeiro.fluxocaixa.model.FluxoCaixaMonthItem;

public interface FluxoCaixaDashboard {

	List<FluxoCaixaMonthItem> getFluxoCaixaFromYear(int year);

	List<FluxoCaixaMonthItem> getFluxoCaixaFromCurrentYear();

}
