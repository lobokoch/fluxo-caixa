/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.212
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ClienteAutoCompleteImpl implements ClienteAutoComplete {

	private java.util.UUID id;
	
	private String nome;
	
	private String cpfCNPJ;
	
	public ClienteAutoCompleteImpl() {
		// Contructor for reflexion, injection, Jackson, QueryDSL, etc proposal.
	}

}