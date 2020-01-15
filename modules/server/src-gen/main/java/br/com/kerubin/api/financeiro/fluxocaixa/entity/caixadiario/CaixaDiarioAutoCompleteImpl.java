/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario;

import lombok.Getter;
import lombok.Setter;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.CaixaDiarioSituacao;

@Getter @Setter
public class CaixaDiarioAutoCompleteImpl implements CaixaDiarioAutoComplete {

	private java.util.UUID id;
	
	private CaixaAutoComplete caixa;
	
	private CaixaDiarioSituacao caixaDiarioSituacao;
	
	private java.time.LocalDateTime dataHoraAbertura;
	
	private short version = 0;
	
	public CaixaDiarioAutoCompleteImpl() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}

}
