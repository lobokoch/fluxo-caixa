/**********************************************************************************************
Code generated with MKL Plug-in version: 30.0.4
Code generated at time stamp: 2019-11-18T21:49:06.682
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.conciliacaobancaria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConciliacaoTransacaoTituloDTO {

	private java.util.UUID id;
	
	private java.util.UUID tituloConciliadoId;
	
	private String tituloConciliadoDesc;
	
	private java.time.LocalDate tituloConciliadoDataVen;
	
	private java.time.LocalDate tituloConciliadoDataPag;
	
	private java.time.LocalDate dataConciliacao;
	
	private SituacaoConciliacaoTrn situacaoConciliacaoTrn;
	
	
}
