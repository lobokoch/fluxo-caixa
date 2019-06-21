/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.212
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento;

import javax.validation.constraints.NotNull;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoFonteMovimento;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoLancamentoFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.FormaPagamento;
import javax.validation.constraints.NotBlank;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito.CartaoCreditoLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente.ClienteLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.fornecedor.FornecedorLookupResult;

public class CaixaLancamento {

	private java.util.UUID id;
	
	@NotNull(message="'Caixa' é obrigatório.")
	private CaixaDiarioLookupResult caixaDiario;
	
	@NotNull(message="'tipoFonteMovimento' é obrigatório.")
	private TipoFonteMovimento tipoFonteMovimento;
	
	@NotNull(message="'Data' é obrigatório.")
	private java.time.LocalDate dataLancamento;
	
	@NotNull(message="'tipoLancamentoFinanceiro' é obrigatório.")
	private TipoLancamentoFinanceiro tipoLancamentoFinanceiro;
	
	@NotNull(message="'Valor' é obrigatório.")
	private java.math.BigDecimal valor;
	
	@NotNull(message="'Forma de pagamento' é obrigatório.")
	private FormaPagamento formaPagamento;
	
	@NotBlank(message="'Descrição do lançamento' é obrigatório.")
	private String descricao;
	
	private PlanoContaLookupResult planoContas;
	
	private ContaBancariaLookupResult contaBancaria;
	
	private CartaoCreditoLookupResult cartaoCredito;
	
	private ClienteLookupResult cliente;
	
	private FornecedorLookupResult fornecedor;
	
	private String documento;
	
	private String observacoes;
	
	private short version;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public CaixaDiarioLookupResult getCaixaDiario() {
		return caixaDiario;
	}
	
	public TipoFonteMovimento getTipoFonteMovimento() {
		return tipoFonteMovimento;
	}
	
	public java.time.LocalDate getDataLancamento() {
		return dataLancamento;
	}
	
	public TipoLancamentoFinanceiro getTipoLancamentoFinanceiro() {
		return tipoLancamentoFinanceiro;
	}
	
	public java.math.BigDecimal getValor() {
		return valor;
	}
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public PlanoContaLookupResult getPlanoContas() {
		return planoContas;
	}
	
	public ContaBancariaLookupResult getContaBancaria() {
		return contaBancaria;
	}
	
	public CartaoCreditoLookupResult getCartaoCredito() {
		return cartaoCredito;
	}
	
	public ClienteLookupResult getCliente() {
		return cliente;
	}
	
	public FornecedorLookupResult getFornecedor() {
		return fornecedor;
	}
	
	public String getDocumento() {
		return documento;
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
	
	public void setTipoFonteMovimento(TipoFonteMovimento tipoFonteMovimento) {
		this.tipoFonteMovimento = tipoFonteMovimento;
	}
	
	public void setDataLancamento(java.time.LocalDate dataLancamento) {
		this.dataLancamento = dataLancamento;
	}
	
	public void setTipoLancamentoFinanceiro(TipoLancamentoFinanceiro tipoLancamentoFinanceiro) {
		this.tipoLancamentoFinanceiro = tipoLancamentoFinanceiro;
	}
	
	public void setValor(java.math.BigDecimal valor) {
		this.valor = valor;
	}
	
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public void setPlanoContas(PlanoContaLookupResult planoContas) {
		this.planoContas = planoContas;
	}
	
	public void setContaBancaria(ContaBancariaLookupResult contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	
	public void setCartaoCredito(CartaoCreditoLookupResult cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
	
	public void setCliente(ClienteLookupResult cliente) {
		this.cliente = cliente;
	}
	
	public void setFornecedor(FornecedorLookupResult fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public void setDocumento(String documento) {
		this.documento = documento;
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
