/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.464
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaLookupResult;

public class CaixaDiarioLookupResult {

	private java.util.UUID id;
	
	private CaixaLookupResult caixa;
	
	private java.time.LocalDateTime dataHoraAbertura;
	
	private Long version;
	
	public java.util.UUID getId() {
		return id;
	}
	
	public CaixaLookupResult getCaixa() {
		return caixa;
	}
	
	public java.time.LocalDateTime getDataHoraAbertura() {
		return dataHoraAbertura;
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
	
	public void setDataHoraAbertura(java.time.LocalDateTime dataHoraAbertura) {
		this.dataHoraAbertura = dataHoraAbertura;
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
