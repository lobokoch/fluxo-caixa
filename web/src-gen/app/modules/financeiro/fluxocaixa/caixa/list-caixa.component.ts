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

import { CaixaService } from './caixa.service';
import { FinanceiroFluxoCaixaTranslationService } from './../i18n/financeiro-fluxocaixa-translation.service';
import { Caixa } from './caixa.model';
import { CaixaListFilter } from './caixa.model';
import { SortField } from './caixa.model';

@Component({
  selector: 'app-list-caixa.component',
  templateUrl: './list-caixa.component.html',
  styleUrls: ['./list-caixa.component.css']
})

export class CaixaListComponent implements OnInit {
	
	caixaListItems: Caixa[];
	caixaListTotalElements = 0;
	caixaListFilter = new CaixaListFilter();
	
	
	
	constructor(
	    private caixaService: CaixaService,
	    private financeiroFluxoCaixaTranslationService: FinanceiroFluxoCaixaTranslationService,
	    private confirmation: ConfirmationService,
	    private messageHandler: MessageHandlerService
	) { }
	
	ngOnInit() {
	}
	
	caixaList(pageNumber = 0) {
	    this.caixaListFilter.pageNumber = pageNumber;
	    this.caixaService
	    .caixaList(this.caixaListFilter)
	    .then(result => {
	      	this.caixaListItems = result.items;
	      	this.caixaListTotalElements = result.totalElements;
	      
	    });
		
	}
	
	
	caixaFilterSearch() {
	    this.caixaList(0);
	}
	
	deleteCaixa(caixa: Caixa) {
	    this.confirmation.confirm({
	      message: 'Confirma a exclusão do registro?',
	      accept: () => {
	        this.caixaService.delete(caixa.id)
	        .then(() => {
	          this.messageHandler.showSuccess('Registro excluído!');
	          this.caixaList(0);
	        })
	        .catch((e) => {
	          this.messageHandler.showError('Erro ao excluir registro: ' + e);
	        });
	      }
	    });
	}
	
	caixaListOnLazyLoad(event: LazyLoadEvent) {
	    if (event.sortField) {
	      this.caixaListFilter.sortField = new SortField(event.sortField, event.sortOrder);
	    } else {
	      this.caixaListFilter.sortField = new SortField('id', 1); // asc
	    }
	    const pageNumber = event.first / event.rows;
	    this.caixaList(pageNumber);
	}
	
	
	
	
	
	// TODO: temporário, só para testes.
	getTranslation(key: string): string {
		const value = this.financeiroFluxoCaixaTranslationService.getTranslation(key);
		return value;
		
		// const result = key.substring(key.lastIndexOf('_') + 1);
		// return result;
	}
}
