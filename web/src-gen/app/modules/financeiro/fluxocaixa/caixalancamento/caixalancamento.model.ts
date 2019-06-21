/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.586
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { CaixaDiario } from './../caixadiario/caixadiario.model';
import { TipoFonteMovimento } from './../enums/financeiro-fluxocaixa-enums.model';
import { TipoLancamentoFinanceiro } from './../enums/financeiro-fluxocaixa-enums.model';
import { FormaPagamento } from './../enums/financeiro-fluxocaixa-enums.model';
import { PlanoConta } from './../planoconta/planoconta.model';
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
	dataLancamento: Date;
	tipoLancamentoFinanceiro: TipoLancamentoFinanceiro;
	valor: number;
	formaPagamento: FormaPagamento;
	descricao: string;
	planoContas: PlanoConta;
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
	sumValor: number;
}
