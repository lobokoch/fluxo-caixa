/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento;


import java.util.HashMap;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoLancamentoFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.FormaPagamento;

public class CaixaLancamentoListFilter {

	private java.util.List<String> descricao;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.time.LocalDate dataLancamentoFrom;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private java.time.LocalDate dataLancamentoTo;
	
	private TipoLancamentoFinanceiro tipoLancamentoFinanceiro;
	
	private java.math.BigDecimal valorCreditoFrom;
	
	private java.math.BigDecimal valorCreditoTo;
	
	private java.math.BigDecimal valorDebitoFrom;
	
	private java.math.BigDecimal valorDebitoTo;
	
	private FormaPagamento formaPagamento;
	
	private Boolean idConcBancariaIsNotNull;
	
	private java.util.List<String> histConcBancaria;
	
	// Map field for developer customizing parameters.
	private Map<Object, Object> customParams = new HashMap<>();
	
	public java.util.List<String> getDescricao() {
		return descricao;
	}
	
	public void setDescricao(java.util.List<String> descricao) {
		this.descricao = descricao;
	}
	
	public java.time.LocalDate getDataLancamentoFrom() {
		return dataLancamentoFrom;
	}
	
	public void setDataLancamentoFrom(java.time.LocalDate dataLancamentoFrom) {
		this.dataLancamentoFrom = dataLancamentoFrom;
	}
	
	public java.time.LocalDate getDataLancamentoTo() {
		return dataLancamentoTo;
	}
	
	public void setDataLancamentoTo(java.time.LocalDate dataLancamentoTo) {
		this.dataLancamentoTo = dataLancamentoTo;
	}
	
	public TipoLancamentoFinanceiro getTipoLancamentoFinanceiro() {
		return tipoLancamentoFinanceiro;
	}
			
	public void setTipoLancamentoFinanceiro(TipoLancamentoFinanceiro tipoLancamentoFinanceiro) {
		this.tipoLancamentoFinanceiro = tipoLancamentoFinanceiro;
	}
	
	public java.math.BigDecimal getValorCreditoFrom() {
		return valorCreditoFrom;
	}
	
	public void setValorCreditoFrom(java.math.BigDecimal valorCreditoFrom) {
		this.valorCreditoFrom = valorCreditoFrom;
	}
	
	public java.math.BigDecimal getValorCreditoTo() {
		return valorCreditoTo;
	}
	
	public void setValorCreditoTo(java.math.BigDecimal valorCreditoTo) {
		this.valorCreditoTo = valorCreditoTo;
	}
	
	public java.math.BigDecimal getValorDebitoFrom() {
		return valorDebitoFrom;
	}
	
	public void setValorDebitoFrom(java.math.BigDecimal valorDebitoFrom) {
		this.valorDebitoFrom = valorDebitoFrom;
	}
	
	public java.math.BigDecimal getValorDebitoTo() {
		return valorDebitoTo;
	}
	
	public void setValorDebitoTo(java.math.BigDecimal valorDebitoTo) {
		this.valorDebitoTo = valorDebitoTo;
	}
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
			
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public Boolean isIdConcBancariaIsNotNull() {
		return idConcBancariaIsNotNull != null && idConcBancariaIsNotNull;
	}
	
	public Boolean getIdConcBancariaIsNotNull() {
		return idConcBancariaIsNotNull;
	}
			
	public void setIdConcBancariaIsNotNull(Boolean idConcBancariaIsNotNull) {
		this.idConcBancariaIsNotNull = idConcBancariaIsNotNull;
	}
	
	public java.util.List<String> getHistConcBancaria() {
		return histConcBancaria;
	}
	
	public void setHistConcBancaria(java.util.List<String> histConcBancaria) {
		this.histConcBancaria = histConcBancaria;
	}
	
	public Map<Object, Object> getCustomParams() {
		return customParams;
	}
	
	public void setCustomParams(Map<Object, Object> customParams) {
		this.customParams = customParams;
	}
	
}