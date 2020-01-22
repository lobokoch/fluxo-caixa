/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T08:31:13.792
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento;

import javax.validation.constraints.NotNull;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioLookupResult;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoLancamentoFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.FormaPagamento;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito.CartaoCreditoLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente.ClienteLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.fornecedor.FornecedorLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoFonteMovimento;

public class CaixaLancamento {

	private java.util.UUID id;
	
	@NotNull(message="\"Caixa\" é obrigatório.")
	private CaixaDiarioLookupResult caixaDiario;
	
	@NotBlank(message="\"Descrição do lançamento\" é obrigatório.")
	@Size(max = 255, message = "\"Descrição do lançamento\" pode ter no máximo 255 caracteres.")
	private String descricao;
	
	@NotNull(message="\"Data\" é obrigatório.")
	private java.time.LocalDate dataLancamento;
	
	@NotNull(message="\"tipoLancamentoFinanceiro\" é obrigatório.")
	private TipoLancamentoFinanceiro tipoLancamentoFinanceiro;
	
	private java.math.BigDecimal valorCredito;
	
	private java.math.BigDecimal valorDebito;
	
	@NotNull(message="\"Forma de pagamento\" é obrigatório.")
	private FormaPagamento formaPagamento;
	
	private ContaBancariaLookupResult contaBancaria;
	
	private CartaoCreditoLookupResult cartaoCredito;
	
	@Size(max = 255, message = "\"Dados complementares\" pode ter no máximo 255 caracteres.")
	private String outrosDescricao;
	
	@NotNull(message="\"Plano de contas\" é obrigatório.")
	private PlanoContaLookupResult planoContas;
	
	private ClienteLookupResult cliente;
	
	private FornecedorLookupResult fornecedor;
	
	@NotNull(message="\"tipoFonteMovimento\" é obrigatório.")
	private TipoFonteMovimento tipoFonteMovimento;
	
	private java.util.UUID idFonteMovimento;
	
	@Size(max = 255, message = "\"Documento\" pode ter no máximo 255 caracteres.")
	private String documento;
	
	@Size(max = 255, message = "\"Id da conciliação bancária\" pode ter no máximo 255 caracteres.")
	private String idConcBancaria;
	
	@Size(max = 255, message = "\"Histórico da conciliação bancária\" pode ter no máximo 255 caracteres.")
	private String histConcBancaria;
	
	@Size(max = 255, message = "\"Documento da conciliação bancária\" pode ter no máximo 255 caracteres.")
	private String numDocConcBancaria;
	
	@Size(max = 1000, message = "\"Observações\" pode ter no máximo 1000 caracteres.")
	private String observacoes;
	
	private short version;
	
	
	public CaixaLancamento() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	
	public java.util.UUID getId() {
		return id;
	}
	
	public CaixaDiarioLookupResult getCaixaDiario() {
		return caixaDiario;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public java.time.LocalDate getDataLancamento() {
		return dataLancamento;
	}
	
	public TipoLancamentoFinanceiro getTipoLancamentoFinanceiro() {
		return tipoLancamentoFinanceiro;
	}
	
	public java.math.BigDecimal getValorCredito() {
		return valorCredito;
	}
	
	public java.math.BigDecimal getValorDebito() {
		return valorDebito;
	}
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	
	public ContaBancariaLookupResult getContaBancaria() {
		return contaBancaria;
	}
	
	public CartaoCreditoLookupResult getCartaoCredito() {
		return cartaoCredito;
	}
	
	public String getOutrosDescricao() {
		return outrosDescricao;
	}
	
	public PlanoContaLookupResult getPlanoContas() {
		return planoContas;
	}
	
	public ClienteLookupResult getCliente() {
		return cliente;
	}
	
	public FornecedorLookupResult getFornecedor() {
		return fornecedor;
	}
	
	public TipoFonteMovimento getTipoFonteMovimento() {
		return tipoFonteMovimento;
	}
	
	public java.util.UUID getIdFonteMovimento() {
		return idFonteMovimento;
	}
	
	public String getDocumento() {
		return documento;
	}
	
	public String getIdConcBancaria() {
		return idConcBancaria;
	}
	
	public String getHistConcBancaria() {
		return histConcBancaria;
	}
	
	public String getNumDocConcBancaria() {
		return numDocConcBancaria;
	}
	
	public String getObservacoes() {
		return observacoes;
	}
	
	public short getVersion() {
		return version;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setCaixaDiario(CaixaDiarioLookupResult caixaDiario) {
		this.caixaDiario = caixaDiario;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setDataLancamento(java.time.LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	public void setTipoLancamentoFinanceiro(TipoLancamentoFinanceiro tipoLancamentoFinanceiro) {
		this.tipoLancamentoFinanceiro = tipoLancamentoFinanceiro;
	}
	
	public void setValorCredito(java.math.BigDecimal valorCredito) {
		this.valorCredito = valorCredito;
	}
	
	public void setValorDebito(java.math.BigDecimal valorDebito) {
		this.valorDebito = valorDebito;
	}
	
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public void setContaBancaria(ContaBancariaLookupResult contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	
	public void setCartaoCredito(CartaoCreditoLookupResult cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
	
	public void setOutrosDescricao(String outrosDescricao) {
		this.outrosDescricao = outrosDescricao;
	}
	
	public void setPlanoContas(PlanoContaLookupResult planoContas) {
		this.planoContas = planoContas;
	}
	
	public void setCliente(ClienteLookupResult cliente) {
		this.cliente = cliente;
	}
	
	public void setFornecedor(FornecedorLookupResult fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public void setTipoFonteMovimento(TipoFonteMovimento tipoFonteMovimento) {
		this.tipoFonteMovimento = tipoFonteMovimento;
	}
	
	public void setIdFonteMovimento(java.util.UUID idFonteMovimento) {
		this.idFonteMovimento = idFonteMovimento;
	}
	
	public void setDocumento(String documento) {
		this.documento = documento;
	}
	
	public void setIdConcBancaria(String idConcBancaria) {
		this.idConcBancaria = idConcBancaria;
	}
	
	public void setHistConcBancaria(String histConcBancaria) {
		this.histConcBancaria = histConcBancaria;
	}
	
	public void setNumDocConcBancaria(String numDocConcBancaria) {
		this.numDocConcBancaria = numDocConcBancaria;
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	public void setVersion(short version) {
		this.version = version;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaixaLancamento other = (CaixaLancamento) obj;
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
