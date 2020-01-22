/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T08:31:13.792
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.CaixaDiarioSituacao;

public interface CaixaDiarioAutoComplete {

	java.util.UUID getId();
	
	CaixaAutoComplete getCaixa();
	
	CaixaDiarioSituacao getCaixaDiarioSituacao();
	
	java.time.LocalDateTime getDataHoraAbertura();
	
	short getVersion();
	
	void setId(java.util.UUID id);
	
	void setCaixa(CaixaAutoComplete caixa);
	
	void setCaixaDiarioSituacao(CaixaDiarioSituacao caixaDiarioSituacao);
	
	void setDataHoraAbertura(java.time.LocalDateTime dataHoraAbertura);
	
	void setVersion(short version);

}
