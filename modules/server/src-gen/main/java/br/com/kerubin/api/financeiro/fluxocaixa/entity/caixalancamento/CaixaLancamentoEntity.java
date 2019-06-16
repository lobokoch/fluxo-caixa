/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.464
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
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.FormaPagamento;
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
	
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_lancamento_financeiro")
	private TipoLancamentoFinanceiro tipoLancamentoFinanceiro;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plano_contas")
	
	private PlanoContaEntity planoContas;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="valor")
	private java.math.BigDecimal valor;
	
	@Enumerated(EnumType.STRING)
	@Column(name="forma_pagamento")
	private FormaPagamento formaPagamento;
	
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
	private Long version;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public CaixaDiarioEntity getCaixaDiario() {
		return caixaDiario;
	}
	
	public TipoFonteMovimento getTipoFonteMovimento() {
		return tipoFonteMovimento;
	}
	
	public TipoLancamentoFinanceiro getTipoLancamentoFinanceiro() {
		return tipoLancamentoFinanceiro;
	}
	
	public PlanoContaEntity getPlanoContas() {
		return planoContas;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public java.math.BigDecimal getValor() {
		return valor;
	}
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
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
	
	public Long getVersion() {
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
	
	public void setTipoLancamentoFinanceiro(TipoLancamentoFinanceiro tipoLancamentoFinanceiro) {
		this.tipoLancamentoFinanceiro = tipoLancamentoFinanceiro;
	}
	
	public void setPlanoContas(PlanoContaEntity planoContas) {
		this.planoContas = planoContas;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao != null ? descricao.trim() : descricao; // Chamadas REST fazem trim.
	}
	
	public void setValor(java.math.BigDecimal valor) {
		this.valor = valor;
	}
	
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
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
	
	public void setVersion(Long version) {
		this.version = version;
	}
	
	public void assign(CaixaLancamentoEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setCaixaDiario(source.getCaixaDiario());
			this.setTipoFonteMovimento(source.getTipoFonteMovimento());
			this.setTipoLancamentoFinanceiro(source.getTipoLancamentoFinanceiro());
			this.setPlanoContas(source.getPlanoContas());
			this.setDescricao(source.getDescricao());
			this.setValor(source.getValor());
			this.setFormaPagamento(source.getFormaPagamento());
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
		theClone.setTipoLancamentoFinanceiro(this.getTipoLancamentoFinanceiro());
		theClone.setPlanoContas(this.getPlanoContas());
		theClone.setDescricao(this.getDescricao());
		theClone.setValor(this.getValor());
		theClone.setFormaPagamento(this.getFormaPagamento());
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
