/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T08:31:13.792
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.agenciabancaria;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AgenciaBancariaAutoCompleteImpl implements AgenciaBancariaAutoComplete {

	private java.util.UUID id;
	
	private String numeroAgencia;
	
	private String digitoAgencia;
	
	private String endereco;
	
	public AgenciaBancariaAutoCompleteImpl() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}

}
