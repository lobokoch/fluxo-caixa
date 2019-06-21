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

import { CaixaDiarioService } from './caixadiario.service';
import { FinanceiroFluxoCaixaTranslationService } from './../i18n/financeiro-fluxocaixa-translation.service';
import { CaixaDiario } from './caixadiario.model';
import { CaixaDiarioListFilter } from './caixadiario.model';
import { SortField } from './caixadiario.model';

import { CaixaAutoComplete } from './../caixa/caixa.model';

@Component({
  selector: 'app-list-caixadiario.component',
  templateUrl: './list-caixadiario.component.html',
  styleUrls: ['./list-caixadiario.component.css']
})

export class CaixaDiarioListComponent implements OnInit {
	
	caixaDiarioListItems: CaixaDiario[];
	caixaDiarioListTotalElements = 0;
	caixaDiarioListFilter = new CaixaDiarioListFilter();
	
	
	
	constructor(
	    private caixaDiarioService: CaixaDiarioService,
	    private financeiroFluxoCaixaTranslationService: FinanceiroFluxoCaixaTranslationService,
	    private confirmation: ConfirmationService,
	    private messageHandler: MessageHandlerService
	) { }
	
	ngOnInit() {
	}
	
	caixaDiarioList(pageNumber = 0) {
	    this.caixaDiarioListFilter.pageNumber = pageNumber;
	    this.caixaDiarioService
	    .caixaDiarioList(this.caixaDiarioListFilter)
	    .then(result => {
	      	this.caixaDiarioListItems = result.items;
	      	this.caixaDiarioListTotalElements = result.totalElements;
	      
	    });
		
	}
	
	
	caixaDiarioFilterSearch() {
	    this.caixaDiarioList(0);
	}
	
	deleteCaixaDiario(caixaDiario: CaixaDiario) {
	    this.confirmation.confirm({
	      message: 'Confirma a exclusão do registro?',
	      accept: () => {
	        this.caixaDiarioService.delete(caixaDiario.id)
	        .then(() => {
	          this.messageHandler.showSuccess('Registro excluído!');
	          this.caixaDiarioList(0);
	        })
	        .catch((e) => {
	          this.messageHandler.showError('Erro ao excluir registro: ' + e);
	        });
	      }
	    });
	}
	
	caixaDiarioListOnLazyLoad(event: LazyLoadEvent) {
	    if (event.sortField) {
	      this.caixaDiarioListFilter.sortField = new SortField(event.sortField, event.sortOrder);
	    } else {
	      this.caixaDiarioListFilter.sortField = new SortField('id', 1); // asc
	    }
	    const pageNumber = event.first / event.rows;
	    this.caixaDiarioList(pageNumber);
	}
	
	
	caixaDiarioCaixaAutoCompleteFieldConverter(caixa: CaixaAutoComplete) {
		if (caixa) {
			return (caixa.nome || '<nulo>');
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
