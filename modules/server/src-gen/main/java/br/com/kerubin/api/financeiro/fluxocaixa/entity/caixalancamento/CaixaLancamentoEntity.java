/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.212
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento;

import javax.persistence.Version;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioEntity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoFonteMovimento;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoLancamentoFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.FormaPagamento;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito.CartaoCreditoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente.ClienteEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.fornecedor.FornecedorEntity;

@Entity
@Table(name = "caixa_lancamento")
public class CaixaLancamentoEntity  {

	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caixa_diario")
	
	private CaixaDiarioEntity caixaDiario;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_fonte_movimento")
	private TipoFonteMovimento tipoFonteMovimento;
	
	@Column(name="data_lancamento")
	private java.time.LocalDate dataLancamento;
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_lancamento_financeiro")
	private TipoLancamentoFinanceiro tipoLancamentoFinanceiro;
	
	@Column(name="valor")
	private java.math.BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	@Column(name="forma_pagamento")
	private FormaPagamento formaPagamento;
	
	@Column(name="descricao")
	private String descricao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plano_contas")
	
	private PlanoContaEntity planoContas;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conta_bancaria")
	
	private ContaBancariaEntity contaBancaria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cartao_credito")
	
	private CartaoCreditoEntity cartaoCredito;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente")
	
	private ClienteEntity cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fornecedor")
	
	private FornecedorEntity fornecedor;
	
	@Column(name="documento")
	private String documento;
	
	@Column(name="observacoes")
	private String observacoes;
	
	@Version
	@Column(name="version")
	private short version;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public CaixaDiarioEntity getCaixaDiario() {
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
	
	public PlanoContaEntity getPlanoContas() {
		return planoContas;
	}
	
	public ContaBancariaEntity getContaBancaria() {
		return contaBancaria;
	}
	
	public CartaoCreditoEntity getCartaoCredito() {
		return cartaoCredito;
	}
	
	public ClienteEntity getCliente() {
		return cliente;
	}
	
	public FornecedorEntity getFornecedor() {
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
	
	public void setCaixaDiario(CaixaDiarioEntity caixaDiario) {
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
		this.descricao = descricao != null ? descricao.trim() : descricao; // Chamadas REST fazem trim.
	}
	
	public void setPlanoContas(PlanoContaEntity planoContas) {
		this.planoContas = planoContas;
	}
	
	public void setContaBancaria(ContaBancariaEntity contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	
	public void setCartaoCredito(CartaoCreditoEntity cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
	
	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
	
	public void setFornecedor(FornecedorEntity fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public void setDocumento(String documento) {
		this.documento = documento != null ? documento.trim() : documento; // Chamadas REST fazem trim.
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes != null ? observacoes.trim() : observacoes; // Chamadas REST fazem trim.
	}
	
	public void setVersion(short version) {
		this.version = version;
	}
	
	public void assign(CaixaLancamentoEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setCaixaDiario(source.getCaixaDiario());
			this.setTipoFonteMovimento(source.getTipoFonteMovimento());
			this.setDataLancamento(source.getDataLancamento());
			this.setTipoLancamentoFinanceiro(source.getTipoLancamentoFinanceiro());
			this.setValor(source.getValor());
			this.setFormaPagamento(source.getFormaPagamento());
			this.setDescricao(source.getDescricao());
			this.setPlanoContas(source.getPlanoContas());
			this.setContaBancaria(source.getContaBancaria());
			this.setCartaoCredito(source.getCartaoCredito());
			this.setCliente(source.getCliente());
			this.setFornecedor(source.getFornecedor());
			this.setDocumento(source.getDocumento());
			this.setObservacoes(source.getObservacoes());
			this.setVersion(source.getVersion());
		}
	}
	
	public CaixaLancamentoEntity clone() {
		CaixaLancamentoEntity theClone = new CaixaLancamentoEntity();
		theClone.setId(this.getId());
		theClone.setCaixaDiario(this.getCaixaDiario());
		theClone.setTipoFonteMovimento(this.getTipoFonteMovimento());
		theClone.setDataLancamento(this.getDataLancamento());
		theClone.setTipoLancamentoFinanceiro(this.getTipoLancamentoFinanceiro());
		theClone.setValor(this.getValor());
		theClone.setFormaPagamento(this.getFormaPagamento());
		theClone.setDescricao(this.getDescricao());
		theClone.setPlanoContas(this.getPlanoContas());
		theClone.setContaBancaria(this.getContaBancaria());
		theClone.setCartaoCredito(this.getCartaoCredito());
		theClone.setCliente(this.getCliente());
		theClone.setFornecedor(this.getFornecedor());
		theClone.setDocumento(this.getDocumento());
		theClone.setObservacoes(this.getObservacoes());
		theClone.setVersion(this.getVersion());
		
		return theClone;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CaixaLancamentoEntity other = (CaixaLancamentoEntity) obj;
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
