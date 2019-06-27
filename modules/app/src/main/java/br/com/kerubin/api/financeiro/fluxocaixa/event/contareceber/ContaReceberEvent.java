/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T20:05:10.238
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.event.contareceber;

import br.com.kerubin.api.messaging.core.DomainEvent;

public class ContaReceberEvent implements DomainEvent {
	
	public static final String CONTA_RECEBER_CONTAPAGA = "contaReceberContaPaga";
	private java.util.UUID id;
	
	private java.util.UUID planoContas;
	
	private String descricao;
	
	private FormaPagamento formaPagamento;
	
	private java.util.UUID contaBancaria;
	
	private java.util.UUID cartaoCredito;
	
	private java.time.LocalDate dataPagamento;
	
	private java.math.BigDecimal valorPago;
	
	private java.util.UUID cliente;
	
	private String numDocumento;
	
	public ContaReceberEvent() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	public ContaReceberEvent(java.util.UUID id, java.util.UUID planoContas, String descricao, FormaPagamento formaPagamento, java.util.UUID contaBancaria, java.util.UUID cartaoCredito, java.time.LocalDate dataPagamento, java.math.BigDecimal valorPago, java.util.UUID cliente, String numDocumento) {
		this.id = id;
		this.planoContas = planoContas;
		this.descricao = descricao;
		this.formaPagamento = formaPagamento;
		this.contaBancaria = contaBancaria;
		this.cartaoCredito = cartaoCredito;
		this.dataPagamento = dataPagamento;
		this.valorPago = valorPago;
		this.cliente = cliente;
		this.numDocumento = numDocumento;
	}
	
	public java.util.UUID getId() {
		return id;
	}
	
	public java.util.UUID getPlanoContas() {
		return planoContas;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	
	public java.util.UUID getContaBancaria() {
		return contaBancaria;
	}
	
	public java.util.UUID getCartaoCredito() {
		return cartaoCredito;
	}
	
	public java.time.LocalDate getDataPagamento() {
		return dataPagamento;
	}
	
	public java.math.BigDecimal getValorPago() {
		return valorPago;
	}
	
	public java.util.UUID getCliente() {
		return cliente;
	}
	
	public String getNumDocumento() {
		return numDocumento;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setPlanoContas(java.util.UUID planoContas) {
		this.planoContas = planoContas;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public void setContaBancaria(java.util.UUID contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	
	public void setCartaoCredito(java.util.UUID cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
	
	public void setDataPagamento(java.time.LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	public void setValorPago(java.math.BigDecimal valorPago) {
		this.valorPago = valorPago;
	}
	
	public void setCliente(java.util.UUID cliente) {
		this.cliente = cliente;
	}
	
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContaReceberEvent other = (ContaReceberEvent) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		
		return true;
	}
	
	@Override
	public int hashCode() {
		return 31;
	}
	
	/* 
	@Override
	public String toString() {
		// Enabling toString for JPA entities will implicitly trigger lazy loading on all fields.
	}
	*/

}
