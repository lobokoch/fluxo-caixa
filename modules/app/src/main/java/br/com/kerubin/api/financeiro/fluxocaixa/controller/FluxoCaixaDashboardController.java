package br.com.kerubin.api.financeiro.fluxocaixa.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kerubin.api.financeiro.fluxocaixa.model.CaixaMovimentoItem;
import br.com.kerubin.api.financeiro.fluxocaixa.model.FluxoCaixaMonthItem;
import br.com.kerubin.api.financeiro.fluxocaixa.model.FluxoCaixaPlanoContasForMonth;
import br.com.kerubin.api.financeiro.fluxocaixa.service.FluxoCaixaDashboard;

@RestController
@RequestMapping("financeiro/fluxo_caixa/dashboard")
public class FluxoCaixaDashboardController {
	
	@Inject
	private FluxoCaixaDashboard fluxoCaixaDashboard;
	
	@Transactional(readOnly = true)
	@GetMapping("/getFluxoCaixaFromYear/{year}")
	public List<FluxoCaixaMonthItem> getFluxoCaixaFromYear(@PathVariable Integer year) {
		return fluxoCaixaDashboard.getFluxoCaixaFromYear(year);
	}
	
	@GetMapping("/getFluxoCaixaFromYear")
	public List<FluxoCaixaMonthItem> getFluxoCaixaFromYear() {
		List<FluxoCaixaMonthItem> fluxo = fluxoCaixaDashboard.getFluxoCaixaFromCurrentYear();
		fluxo = fluxoCaixaDashboard.decorateWithPrevision(fluxo);
		return fluxo;
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/getResumoMensalPorPlanoContasDebitos/{year}")
	public List<FluxoCaixaPlanoContasForMonth> getResumoMensalPorPlanoContasDebitos(int year) {
		return fluxoCaixaDashboard.getResumoMensalPorPlanoContasDebitos(year);
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/getResumoMensalPorPlanoContasDebitos")
	public List<FluxoCaixaPlanoContasForMonth> getResumoMensalPorPlanoContasDebitos() {
		return fluxoCaixaDashboard.getResumoMensalPorPlanoContasDebitos();
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/getResumoMensalPorPlanoContasCreditos/{year}")
	public List<FluxoCaixaPlanoContasForMonth> getResumoMensalPorPlanoContasCreditos(int year) {
		return fluxoCaixaDashboard.getResumoMensalPorPlanoContasCreditos(year);
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/getResumoMensalPorPlanoContasCreditos")
	public List<FluxoCaixaPlanoContasForMonth> getResumoMensalPorPlanoContasCreditos() {
		return fluxoCaixaDashboard.getResumoMensalPorPlanoContasCreditos();
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/getFluxoCaixaResumoMovimentacoes")
	public List<CaixaMovimentoItem> getFluxoCaixaResumoMovimentacoes() {
		return fluxoCaixaDashboard.getFluxoCaixaResumoMovimentacoes();
	}

}
