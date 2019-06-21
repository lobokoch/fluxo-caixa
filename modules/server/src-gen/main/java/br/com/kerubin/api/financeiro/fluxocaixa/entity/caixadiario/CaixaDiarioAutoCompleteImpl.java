/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.212
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario;

import lombok.Getter;
import lombok.Setter;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaAutoComplete;

@Getter @Setter
public class CaixaDiarioAutoCompleteImpl implements CaixaDiarioAutoComplete {

	private java.util.UUID id;
	
	private CaixaAutoComplete caixa;
	
	private java.time.LocalDateTime dataHoraAbertura;
	
	private short version = 0;
	
	public CaixaDiarioAutoCompleteImpl() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}

}
