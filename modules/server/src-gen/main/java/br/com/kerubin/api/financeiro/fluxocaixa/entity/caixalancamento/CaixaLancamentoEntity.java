/**********************************************************************************************
Code generated by MKL Plug-in
Copyright: Kerubin - kerubin.platform@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento;

import javax.persistence.Version;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import br.com.kerubin.api.database.entity.AuditingEntity;
import javax.persistence.Transient;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.GenericGenerator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioEntity;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoLancamentoFinanceiro;
import javax.validation.constraints.Positive;
import br.com.kerubin.api.financeiro.fluxocaixa.FormaPagamento;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito.CartaoCreditoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente.ClienteEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.fornecedor.FornecedorEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoFonteMovimento;

@Entity
@Table(name = "caixa_lancamento")
public class CaixaLancamentoEntity extends AuditingEntity {

	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Id
	@Column(name="id")
	private java.util.UUID id;
	
	@NotBlank(message="\"Descrição do lançamento\" é obrigatório.")
	@Size(max = 255, message = "\"Descrição do lançamento\" pode ter no máximo 255 caracteres.")
	@Column(name="descricao")
	private String descricao;
	
	@NotNull(message="\"Caixa para lançamento\" é obrigatório.")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "caixa_diario")
	private CaixaDiarioEntity caixaDiario;
	
	@NotNull(message="\"Data do movimento\" é obrigatório.")
	@Column(name="data_lancamento")
	private java.time.LocalDate dataLancamento;
	
	@NotNull(message="\"tipoLancamentoFinanceiro\" é obrigatório.")
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_lancamento_financeiro")
	private TipoLancamentoFinanceiro tipoLancamentoFinanceiro;
	
	@Positive(message="O valor recebido deve ser maior do que zero.")
	@Column(name="valor_credito")
	private java.math.BigDecimal valorCredito;
	
	@Positive(message="O valor pago deve ser maior do que zero.")
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
	
	@Transient
	private Boolean maisOpcoes = false;
	
	@Size(max = 255, message = "\"Documento\" pode ter no máximo 255 caracteres.")
	@Column(name="documento")
	private String documento;
	
	@Size(max = 1000, message = "\"Observações\" pode ter no máximo 1000 caracteres.")
	@Column(name="observacoes")
	private String observacoes;
	
	@Size(max = 255, message = "\"Id da conciliação bancária\" pode ter no máximo 255 caracteres.")
	@Column(name="id_conc_bancaria")
	private String idConcBancaria;
	
	@Size(max = 255, message = "\"Histórico da conciliação bancária\" pode ter no máximo 255 caracteres.")
	@Column(name="hist_conc_bancaria")
	private String histConcBancaria;
	
	@Size(max = 255, message = "\"Documento da conciliação bancária\" pode ter no máximo 255 caracteres.")
	@Column(name="num_doc_conc_bancaria")
	private String numDocConcBancaria;
	
	@Column(name="estorno")
	private Boolean estorno = false;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "estorno_lancamento")
	private CaixaLancamentoEntity estornoLancamento;
	
	@Size(max = 1000, message = "\"Histórico do estorno\" pode ter no máximo 1000 caracteres.")
	@Column(name="estorno_historico")
	private String estornoHistorico;
	
	@NotNull(message="\"tipoFonteMovimento\" é obrigatório.")
	@Enumerated(EnumType.STRING)
	@Column(name="tipo_fonte_movimento")
	private TipoFonteMovimento tipoFonteMovimento;
	
	@Column(name="id_fonte_movimento")
	private java.util.UUID idFonteMovimento;
	
	@Version
	@Column(name="entity_version")
	private short version;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public CaixaDiarioEntity getCaixaDiario() {
		return caixaDiario;
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
	
	public Boolean getMaisOpcoes() {
		return maisOpcoes;
	}
	
	public String getDocumento() {
		return documento;
	}
	
	public String getObservacoes() {
		return observacoes;
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
	
	public Boolean getEstorno() {
		return estorno;
	}
	
	public CaixaLancamentoEntity getEstornoLancamento() {
		return estornoLancamento;
	}
	
	public String getEstornoHistorico() {
		return estornoHistorico;
	}
	
	public TipoFonteMovimento getTipoFonteMovimento() {
		return tipoFonteMovimento;
	}
	
	public java.util.UUID getIdFonteMovimento() {
		return idFonteMovimento;
	}
	
	public short getVersion() {
		return version;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao != null ? descricao.trim() : descricao; // Chamadas REST fazem trim.
	}
	
	public void setCaixaDiario(CaixaDiarioEntity caixaDiario) {
		this.caixaDiario = caixaDiario;
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
	
	public void setMaisOpcoes(Boolean maisOpcoes) {
		this.maisOpcoes = maisOpcoes;
	}
	
	public void setDocumento(String documento) {
		this.documento = documento != null ? documento.trim() : documento; // Chamadas REST fazem trim.
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes != null ? observacoes.trim() : observacoes; // Chamadas REST fazem trim.
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
	
	public void setEstorno(Boolean estorno) {
		this.estorno = estorno;
	}
	
	public void setEstornoLancamento(CaixaLancamentoEntity estornoLancamento) {
		this.estornoLancamento = estornoLancamento;
	}
	
	public void setEstornoHistorico(String estornoHistorico) {
		this.estornoHistorico = estornoHistorico != null ? estornoHistorico.trim() : estornoHistorico; // Chamadas REST fazem trim.
	}
	
	public void setTipoFonteMovimento(TipoFonteMovimento tipoFonteMovimento) {
		this.tipoFonteMovimento = tipoFonteMovimento;
	}
	
	public void setIdFonteMovimento(java.util.UUID idFonteMovimento) {
		this.idFonteMovimento = idFonteMovimento;
	}
	
	public void setVersion(short version) {
		this.version = version;
	}
	
	public void assign(CaixaLancamentoEntity source) {
		if (source != null) {
			this.setId(source.getId());
			this.setDescricao(source.getDescricao());
			this.setCaixaDiario(source.getCaixaDiario());
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
			this.setMaisOpcoes(source.getMaisOpcoes());
			this.setDocumento(source.getDocumento());
			this.setObservacoes(source.getObservacoes());
			this.setIdConcBancaria(source.getIdConcBancaria());
			this.setHistConcBancaria(source.getHistConcBancaria());
			this.setNumDocConcBancaria(source.getNumDocConcBancaria());
			this.setEstorno(source.getEstorno());
			this.setEstornoLancamento(source.getEstornoLancamento());
			this.setEstornoHistorico(source.getEstornoHistorico());
			this.setTipoFonteMovimento(source.getTipoFonteMovimento());
			this.setIdFonteMovimento(source.getIdFonteMovimento());
			this.setCreatedBy(source.getCreatedBy());
			this.setCreatedDate(source.getCreatedDate());
			this.setLastModifiedBy(source.getLastModifiedBy());
			this.setLastModifiedDate(source.getLastModifiedDate());
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
		theClone.setDescricao(this.getDescricao());
		theClone.setCaixaDiario(this.getCaixaDiario() != null ? this.getCaixaDiario().clone(visited) : null);
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
		theClone.setMaisOpcoes(this.getMaisOpcoes());
		theClone.setDocumento(this.getDocumento());
		theClone.setObservacoes(this.getObservacoes());
		theClone.setIdConcBancaria(this.getIdConcBancaria());
		theClone.setHistConcBancaria(this.getHistConcBancaria());
		theClone.setNumDocConcBancaria(this.getNumDocConcBancaria());
		theClone.setEstorno(this.getEstorno());
		theClone.setEstornoLancamento(this.getEstornoLancamento() != null ? this.getEstornoLancamento().clone(visited) : null);
		theClone.setEstornoHistorico(this.getEstornoHistorico());
		theClone.setTipoFonteMovimento(this.getTipoFonteMovimento());
		theClone.setIdFonteMovimento(this.getIdFonteMovimento());
		theClone.setCreatedBy(this.getCreatedBy());
		theClone.setCreatedDate(this.getCreatedDate());
		theClone.setLastModifiedBy(this.getLastModifiedBy());
		theClone.setLastModifiedDate(this.getLastModifiedDate());
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
