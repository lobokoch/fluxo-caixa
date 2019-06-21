/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.586
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


import { Component, OnInit } from '@angular/core';
import {ConfirmationService, LazyLoadEvent, SelectItem} from 'primeng/api';
import { Dropdown } from 'primeng/dropdown';
import * as moment from 'moment';
import { MessageHandlerService } from 'src/app/core/message-handler.service';

import { CaixaLancamentoService } from './caixalancamento.service';
import { FinanceiroFluxoCaixaTranslationService } from './../i18n/financeiro-fluxocaixa-translation.service';
import { CaixaLancamento } from './caixalancamento.model';
import { CaixaLancamentoListFilter } from './caixalancamento.model';
import { SortField } from './caixalancamento.model';

import { CaixaDiarioAutoComplete } from './../caixadiario/caixadiario.model';

import { PlanoContaAutoComplete } from './../planoconta/planoconta.model';

import { ContaBancariaAutoComplete } from './../contabancaria/contabancaria.model';

import { CartaoCreditoAutoComplete } from './../cartaocredito/cartaocredito.model';

import { ClienteAutoComplete } from './../cliente/cliente.model';

import { FornecedorAutoComplete } from './../fornecedor/fornecedor.model';
import { CaixaLancamentoSumFields } from './caixalancamento.model';

@Component({
  selector: 'app-list-caixalancamento.component',
  templateUrl: './list-caixalancamento.component.html',
  styleUrls: ['./list-caixalancamento.component.css']
})

export class CaixaLancamentoListComponent implements OnInit {
	
	caixaLancamentoListItems: CaixaLancamento[];
	caixaLancamentoListTotalElements = 0;
	caixaLancamentoListFilter = new CaixaLancamentoListFilter();
	
	
	caixaLancamentoSumFields = new CaixaLancamentoSumFields();
	
	constructor(
	    private caixaLancamentoService: CaixaLancamentoService,
	    private financeiroFluxoCaixaTranslationService: FinanceiroFluxoCaixaTranslationService,
	    private confirmation: ConfirmationService,
	    private messageHandler: MessageHandlerService
	) { }
	
	ngOnInit() {
	}
	
	caixaLancamentoList(pageNumber = 0) {
	    this.caixaLancamentoListFilter.pageNumber = pageNumber;
	    this.caixaLancamentoService
	    .caixaLancamentoList(this.caixaLancamentoListFilter)
	    .then(result => {
	      	this.caixaLancamentoListItems = result.items;
	      	this.caixaLancamentoListTotalElements = result.totalElements;
	      
			this.getCaixaLancamentoSumFields();
	    });
		
	}
	
	getCaixaLancamentoSumFields() {
	    this.caixaLancamentoService.getCaixaLancamentoSumFields(this.caixaLancamentoListFilter)
		.then(response => {
		  this.caixaLancamentoSumFields = response;
		})
		.catch(error => {
		  this.messageHandler.showError('Erro ao buscar totais:' + error);
		});
	}
	
	caixaLancamentoFilterSearch() {
	    this.caixaLancamentoList(0);
	}
	
	deleteCaixaLancamento(caixaLancamento: CaixaLancamento) {
	    this.confirmation.confirm({
	      message: 'Confirma a exclusão do registro?',
	      accept: () => {
	        this.caixaLancamentoService.delete(caixaLancamento.id)
	        .then(() => {
	          this.messageHandler.showSuccess('Registro excluído!');
	          this.caixaLancamentoList(0);
	        })
	        .catch((e) => {
	          this.messageHandler.showError('Erro ao excluir registro: ' + e);
	        });
	      }
	    });
	}
	
	caixaLancamentoListOnLazyLoad(event: LazyLoadEvent) {
	    if (event.sortField) {
	      this.caixaLancamentoListFilter.sortField = new SortField(event.sortField, event.sortOrder);
	    } else {
	      this.caixaLancamentoListFilter.sortField = new SortField('dataLancamento', 0); // asc
	    }
	    const pageNumber = event.first / event.rows;
	    this.caixaLancamentoList(pageNumber);
	}
	
	
	caixaLancamentoCaixaDiarioAutoCompleteFieldConverter(caixaDiario: CaixaDiarioAutoComplete) {
		if (caixaDiario) {
			return (caixaDiario.caixa.nome || '<nulo>') + ' - ' + (moment(caixaDiario.dataHoraAbertura).format('DD/MM/YYYY H:m') || '<nulo>');
		} else {
			return null;
		}
	}
	
	caixaLancamentoPlanoContasAutoCompleteFieldConverter(planoContas: PlanoContaAutoComplete) {
		if (planoContas) {
			return (planoContas.codigo || '<nulo>') + ' - ' + (planoContas.descricao || '<nulo>');
		} else {
			return null;
		}
	}
	
	caixaLancamentoContaBancariaAutoCompleteFieldConverter(contaBancaria: ContaBancariaAutoComplete) {
		if (contaBancaria) {
			return (contaBancaria.nomeTitular || '<nulo>') + ' - ' + (contaBancaria.numeroConta || '<nulo>');
		} else {
			return null;
		}
	}
	
	caixaLancamentoCartaoCreditoAutoCompleteFieldConverter(cartaoCredito: CartaoCreditoAutoComplete) {
		if (cartaoCredito) {
			return (cartaoCredito.nomeTitular || '<nulo>') + ' - ' + (cartaoCredito.numeroCartao || '<nulo>');
		} else {
			return null;
		}
	}
	
	caixaLancamentoClienteAutoCompleteFieldConverter(cliente: ClienteAutoComplete) {
		if (cliente) {
			return (cliente.nome || '<nulo>') + ' - ' + (cliente.cpfCNPJ || '<nulo>');
		} else {
			return null;
		}
	}
	
	caixaLancamentoFornecedorAutoCompleteFieldConverter(fornecedor: FornecedorAutoComplete) {
		if (fornecedor) {
			return (fornecedor.nome || '<nulo>') + ' - ' + (fornecedor.cpfCNPJ || '<nulo>');
		} else {
			return null;
		}
	}
	
	
	
	
	// TODO: temporário, só para testes.
	getTranslation(key: string): string {
		const value = this.financeiroFluxoCaixaTranslationService.getTranslation(key);
		return value;
		
		// const result = key.substring(key.lastIndexOf('_') + 1);
		// return result;
	}
}
