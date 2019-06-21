/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.586
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { Caixa } from './../caixa/caixa.model';
import { CaixaDiarioSituacao } from './../enums/financeiro-fluxocaixa-enums.model';
import { CaixaAutoComplete } from './../caixa/caixa.model';

export class SortField {
  field: string;
  order: number;

  constructor(field: string, order: number) {
    this.field = field;
    this.order = order;
  }
}

export class PaginationFilter {
  pageNumber: number;
  pageSize: number;
  sortField: SortField;

  constructor() {
    this.pageNumber = 0;
    this.pageSize = 10;
  }
}

export class CaixaDiarioListFilter extends PaginationFilter {
	
}

export class CaixaDiario {
	id: string;
	caixa: Caixa;
	caixaDiarioSituacao: CaixaDiarioSituacao;
	dataHoraAbertura: Date;
	saldoInicial: number;
	dataHoraFechamento: Date;
	saldoFinal: number;
	observacoes: string;
	version: number;
}

export class CaixaDiarioAutoComplete {
	id: string;
	caixa: CaixaAutoComplete;
	dataHoraAbertura: Date;
	version: number;
}

export class CaixaDiarioSumFields {
}
