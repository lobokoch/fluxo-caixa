/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.741
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { CaixaDiario } from './../caixadiario/caixadiario.model';
import { TipoFonteMovimento } from './../enums/financeiro-fluxocaixa-enums.model';
import { TipoLancamentoFinanceiro } from './../enums/financeiro-fluxocaixa-enums.model';
import { PlanoConta } from './../planoconta/planoconta.model';
import { FormaPagamento } from './../enums/financeiro-fluxocaixa-enums.model';
import { ContaBancaria } from './../contabancaria/contabancaria.model';
import { CartaoCredito } from './../cartaocredito/cartaocredito.model';
import { Cliente } from './../cliente/cliente.model';
import { Fornecedor } from './../fornecedor/fornecedor.model';

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

export class CaixaLancamentoListFilter extends PaginationFilter {
	
}

export class CaixaLancamento {
	id: string;
	caixaDiario: CaixaDiario;
	tipoFonteMovimento: TipoFonteMovimento;
	tipoLancamentoFinanceiro: TipoLancamentoFinanceiro;
	planoContas: PlanoConta;
	descricao: string;
	valor: number;
	formaPagamento: FormaPagamento;
	contaBancaria: ContaBancaria;
	cartaoCredito: CartaoCredito;
	cliente: Cliente;
	fornecedor: Fornecedor;
	documento: string;
	observacoes: string;
	version: number;
}

export class CaixaLancamentoAutoComplete {
	id: string;
	descricao: string;
	version: number;
}

export class CaixaLancamentoSumFields {
}
