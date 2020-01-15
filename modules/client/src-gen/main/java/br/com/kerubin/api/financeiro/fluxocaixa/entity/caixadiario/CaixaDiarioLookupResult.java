/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.CaixaDiarioSituacao;
public class CaixaDiarioLookupResult {

	private java.util.UUID id;
	
	private CaixaLookupResult caixa;
	
	private CaixaDiarioSituacao caixaDiarioSituacao;
	
	private java.time.LocalDateTime dataHoraAbertura;
	
	private short version;
	
	
	public CaixaDiarioLookupResult() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}
	
	
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
	
	public short getVersion() {
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
		CaixaDiarioLookupResult other = (CaixaDiarioLookupResult) obj;
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
