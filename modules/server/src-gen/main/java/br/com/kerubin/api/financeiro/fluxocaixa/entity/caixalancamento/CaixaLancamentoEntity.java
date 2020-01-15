/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
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
import javax.validation.constraints.NotNull;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioEntity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoLancamentoFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.FormaPagamento;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito.CartaoCreditoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente.ClienteEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.fornecedor.FornecedorEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoFonteMovimento;

@Entity
@Table(name = "caixa_lancamento")
public class CaixaLancamentoEntity  {

	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@NotNull(message="\"Caixa\" é obrigatório.")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caixa_diario")
	private CaixaDiarioEntity caixaDiario;
	
	@NotBlank(message="\"Descrição do lançamento\" é obrigatório.")
	@Size(max = 255, message = "\"Descrição do lançamento\" pode ter no máximo 255 caracteres.")
	@Column(name="descricao")
	private String descricao;
	
	@NotNull(message="\"Data\" é obrigatório.")
	@Column(name="data_lancamento")
	private java.time.LocalDate dataLancamento;
	
	@NotNull(message="\"tipoLancamentoFinanceiro\" é obrigatório.")
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_lancamento_financeiro")
	private TipoLancamentoFinanceiro tipoLancamentoFinanceiro;
	
	@Column(name="valor_credito")
	private java.math.BigDecimal valorCredito;
	
	@Column(name="valor_debito")
	private java.math.BigDecimal valorDebito;
	
	@NotNull(message="\"Forma de pagamento\" é obrigatório.")
	@Enumerated(EnumType.STRING)
	@Column(name="forma_pagamento")
	private FormaPagamento formaPagamento;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conta_bancaria")
	private ContaBancariaEntity contaBancaria;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cartao_credito")
	private CartaoCreditoEntity cartaoCredito;
	
	@Size(max = 255, message = "\"Dados complementares\" pode ter no máximo 255 caracteres.")
	@Column(name="outros_descricao")
	private String outrosDescricao;
	
	@NotNull(message="\"Plano de contas\" é obrigatório.")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plano_contas")
	private PlanoContaEntity planoContas;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cliente")
	private ClienteEntity cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fornecedor")
	private FornecedorEntity fornecedor;
	
	@NotNull(message="\"tipoFonteMovimento\" é obrigatório.")
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_fonte_movimento")
	private TipoFonteMovimento tipoFonteMovimento;
	
	@Column(name="id_fonte_movimento")
	private java.util.UUID idFonteMovimento;
	
	@Size(max = 255, message = "\"Documento\" pode ter no máximo 255 caracteres.")
	@Column(name="documento")
	private String documento;
	
	@Size(max = 255, message = "\"Id da conciliação bancária\" pode ter no máximo 255 caracteres.")
	@Column(name="id_conc_bancaria")
	private String idConcBancaria;
	
	@Size(max = 255, message = "\"Histórico da conciliação bancária\" pode ter no máximo 255 caracteres.")
	@Column(name="hist_conc_bancaria")
	private String histConcBancaria;
	
	@Size(max = 255, message = "\"Documento da conciliação bancária\" pode ter no máximo 255 caracteres.")
	@Column(name="num_doc_conc_bancaria")
	private String numDocConcBancaria;
	
	@Size(max = 1000, message = "\"Observações\" pode ter no máximo 1000 caracteres.")
	@Column(name="observacoes")
	private String observacoes;
	
	@Version
	@Column(name="entity_version")
	private short version;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public CaixaDiarioEntity getCaixaDiario() {
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
	
	public ContaBancariaEntity getContaBancaria() {
		return contaBancaria;
	}
	
	public CartaoCreditoEntity getCartaoCredito() {
		return cartaoCredito;
	}
	
	public String getOutrosDescricao() {
		return outrosDescricao;
	}
	
	public PlanoContaEntity getPlanoContas() {
		return planoContas;
	}
	
	public ClienteEntity getCliente() {
		return cliente;
	}
	
	public FornecedorEntity getFornecedor() {
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
	
	public void setCaixaDiario(CaixaDiarioEntity caixaDiario) {
		this.caixaDiario = caixaDiario;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao != null ? descricao.trim() : descricao; // Chamadas REST fazem trim.
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
	
	public void setContaBancaria(ContaBancariaEntity contaBancaria) {
		this.contaBancaria = contaBancaria;
	}
	
	public void setCartaoCredito(CartaoCreditoEntity cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}
	
	public void setOutrosDescricao(String outrosDescricao) {
		this.outrosDescricao = outrosDescricao != null ? outrosDescricao.trim() : outrosDescricao; // Chamadas REST fazem trim.
	}
	
	public void setPlanoContas(PlanoContaEntity planoContas) {
		this.planoContas = planoContas;
	}
	
	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}
	
	public void setFornecedor(FornecedorEntity fornecedor) {
		this.fornecedor = fornecedor;
	}
	
	public void setTipoFonteMovimento(TipoFonteMovimento tipoFonteMovimento) {
		this.tipoFonteMovimento = tipoFonteMovimento;
	}
	
	public void setIdFonteMovimento(java.util.UUID idFonteMovimento) {
		this.idFonteMovimento = idFonteMovimento;
	}
	
	public void setDocumento(String documento) {
		this.documento = documento != null ? documento.trim() : documento; // Chamadas REST fazem trim.
	}
	
	public void setIdConcBancaria(String idConcBancaria) {
		this.idConcBancaria = idConcBancaria != null ? idConcBancaria.trim() : idConcBancaria; // Chamadas REST fazem trim.
	}
	
	public void setHistConcBancaria(String histConcBancaria) {
		this.histConcBancaria = histConcBancaria != null ? histConcBancaria.trim() : histConcBancaria; // Chamadas REST fazem trim.
	}
	
	public void setNumDocConcBancaria(String numDocConcBancaria) {
		this.numDocConcBancaria = numDocConcBancaria != null ? numDocConcBancaria.trim() : numDocConcBancaria; // Chamadas REST fazem trim.
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
			this.setDescricao(source.getDescricao());
			this.setDataLancamento(source.getDataLancamento());
			this.setTipoLancamentoFinanceiro(source.getTipoLancamentoFinanceiro());
			this.setValorCredito(source.getValorCredito());
			this.setValorDebito(source.getValorDebito());
			this.setFormaPagamento(source.getFormaPagamento());
			this.setContaBancaria(source.getContaBancaria());
			this.setCartaoCredito(source.getCartaoCredito());
			this.setOutrosDescricao(source.getOutrosDescricao());
			this.setPlanoContas(source.getPlanoContas());
			this.setCliente(source.getCliente());
			this.setFornecedor(source.getFornecedor());
			this.setTipoFonteMovimento(source.getTipoFonteMovimento());
			this.setIdFonteMovimento(source.getIdFonteMovimento());
			this.setDocumento(source.getDocumento());
			this.setIdConcBancaria(source.getIdConcBancaria());
			this.setHistConcBancaria(source.getHistConcBancaria());
			this.setNumDocConcBancaria(source.getNumDocConcBancaria());
			this.setObservacoes(source.getObservacoes());
			this.setVersion(source.getVersion());
		}
	}
	
	public CaixaLancamentoEntity clone() {
		return clone(new java.util.HashMap<>());
	}
	
	public CaixaLancamentoEntity clone(java.util.Map<Object, Object> visited) {
		if (visited.containsKey(this)) {
			return (CaixaLancamentoEntity) visited.get(this);
		}
				
		CaixaLancamentoEntity theClone = new CaixaLancamentoEntity();
		visited.put(this, theClone);
		
		theClone.setId(this.getId());
		theClone.setCaixaDiario(this.getCaixaDiario() != null ? this.getCaixaDiario().clone(visited) : null);
		theClone.setDescricao(this.getDescricao());
		theClone.setDataLancamento(this.getDataLancamento());
		theClone.setTipoLancamentoFinanceiro(this.getTipoLancamentoFinanceiro());
		theClone.setValorCredito(this.getValorCredito());
		theClone.setValorDebito(this.getValorDebito());
		theClone.setFormaPagamento(this.getFormaPagamento());
		theClone.setContaBancaria(this.getContaBancaria() != null ? this.getContaBancaria().clone(visited) : null);
		theClone.setCartaoCredito(this.getCartaoCredito() != null ? this.getCartaoCredito().clone(visited) : null);
		theClone.setOutrosDescricao(this.getOutrosDescricao());
		theClone.setPlanoContas(this.getPlanoContas() != null ? this.getPlanoContas().clone(visited) : null);
		theClone.setCliente(this.getCliente() != null ? this.getCliente().clone(visited) : null);
		theClone.setFornecedor(this.getFornecedor() != null ? this.getFornecedor().clone(visited) : null);
		theClone.setTipoFonteMovimento(this.getTipoFonteMovimento());
		theClone.setIdFonteMovimento(this.getIdFonteMovimento());
		theClone.setDocumento(this.getDocumento());
		theClone.setIdConcBancaria(this.getIdConcBancaria());
		theClone.setHistConcBancaria(this.getHistConcBancaria());
		theClone.setNumDocConcBancaria(this.getNumDocConcBancaria());
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
