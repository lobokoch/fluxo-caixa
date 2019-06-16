/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.464
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario;

import javax.validation.constraints.NotNull;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.CaixaDiarioSituacao;

public class CaixaDiario {

	private java.util.UUID id;
	
	@NotNull(message="'Caixa' é obrigatório.")
	private CaixaLookupResult caixa;
	
	@NotNull(message="'caixaDiarioSituacao' é obrigatório.")
	private CaixaDiarioSituacao caixaDiarioSituacao;
	
	private java.time.LocalDateTime dataHoraAbertura;
	
	private java.math.BigDecimal saldoInicial;
	
	private java.time.LocalDateTime dataHoraFechamento;
	
	private java.math.BigDecimal saldoFinal;
	
	private String observacoes;
	
	private String createdBy;
	
	private java.time.LocalDateTime createdDate;
	
	private String lastModifiedBy;
	
	private java.time.LocalDateTime lastModifiedDate;
	
	private Long version;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public CaixaLookupResult getCaixa() {
		return caixa;
	}
	
	public CaixaDiarioSituacao getCaixaDiarioSituacao() {
		return caixaDiarioSituacao;
	}
	
	public java.time.LocalDateTime getDataHoraAbertura() {
		return dataHoraAbertura;
	}
	
	public java.math.BigDecimal getSaldoInicial() {
		return saldoInicial;
	}
	
	public java.time.LocalDateTime getDataHoraFechamento() {
		return dataHoraFechamento;
	}
	
	public java.math.BigDecimal getSaldoFinal() {
		return saldoFinal;
	}
	
	public String getObservacoes() {
		return observacoes;
	}
	
	public String getCreatedBy() {
		return createdBy;
	}
	
	public java.time.LocalDateTime getCreatedDate() {
		return createdDate;
	}
	
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}
	
	public java.time.LocalDateTime getLastModifiedDate() {
		return lastModifiedDate;
	}
	
	public Long getVersion() {
		return version;
	}
	
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	
	public void setCaixa(CaixaLookupResult caixa) {
		this.caixa = caixa;
	}
	
	public void setCaixaDiarioSituacao(CaixaDiarioSituacao caixaDiarioSituacao) {
		this.caixaDiarioSituacao = caixaDiarioSituacao;
	}
	
	public void setDataHoraAbertura(java.time.LocalDateTime dataHoraAbertura) {
		this.dataHoraAbertura = dataHoraAbertura;
	}
	
	public void setSaldoInicial(java.math.BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	
	public void setDataHoraFechamento(java.time.LocalDateTime dataHoraFechamento) {
		this.dataHoraFechamento = dataHoraFechamento;
	}
	
	public void setSaldoFinal(java.math.BigDecimal saldoFinal) {
		this.saldoFinal = saldoFinal;
	}
	
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	public void setCreatedDate(java.time.LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	
	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}
	
	public void setLastModifiedDate(java.time.LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	public void setVersion(Long version) {
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
		CaixaDiario other = (CaixaDiario) obj;
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
