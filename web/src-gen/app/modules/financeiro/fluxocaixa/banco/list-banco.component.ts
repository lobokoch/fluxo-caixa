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

import { BancoService } from './banco.service';
import { FinanceiroFluxoCaixaTranslationService } from './../i18n/financeiro-fluxocaixa-translation.service';
import { Banco } from './banco.model';
import { BancoListFilter } from './banco.model';
import { SortField } from './banco.model';

@Component({
  selector: 'app-list-banco.component',
  templateUrl: './list-banco.component.html',
  styleUrls: ['./list-banco.component.css']
})

export class BancoListComponent implements OnInit {
	
	bancoListItems: Banco[];
	bancoListTotalElements = 0;
	bancoListFilter = new BancoListFilter();
	
	
	
	constructor(
	    private bancoService: BancoService,
	    private financeiroFluxoCaixaTranslationService: FinanceiroFluxoCaixaTranslationService,
	    private confirmation: ConfirmationService,
	    private messageHandler: MessageHandlerService
	) { }
	
	ngOnInit() {
	}
	
	bancoList(pageNumber = 0) {
	    this.bancoListFilter.pageNumber = pageNumber;
	    this.bancoService
	    .bancoList(this.bancoListFilter)
	    .then(result => {
	      	this.bancoListItems = result.items;
	      	this.bancoListTotalElements = result.totalElements;
	      
	    });
		
	}
	
	
	bancoFilterSearch() {
	    this.bancoList(0);
	}
	
	deleteBanco(banco: Banco) {
	    this.confirmation.confirm({
	      message: 'Confirma a exclusão do registro?',
	      accept: () => {
	        this.bancoService.delete(banco.id)
	        .then(() => {
	          this.messageHandler.showSuccess('Registro excluído!');
	          this.bancoList(0);
	        })
	        .catch((e) => {
	          this.messageHandler.showError('Erro ao excluir registro: ' + e);
	        });
	      }
	    });
	}
	
	bancoListOnLazyLoad(event: LazyLoadEvent) {
	    if (event.sortField) {
	      this.bancoListFilter.sortField = new SortField(event.sortField, event.sortOrder);
	    } else {
	      this.bancoListFilter.sortField = new SortField('id', 1); // asc
	    }
	    const pageNumber = event.first / event.rows;
	    this.bancoList(pageNumber);
	}
	
	
	
	
	
	// TODO: temporário, só para testes.
	getTranslation(key: string): string {
		const value = this.financeiroFluxoCaixaTranslationService.getTranslation(key);
		return value;
		
		// const result = key.substring(key.lastIndexOf('_') + 1);
		// return result;
	}
}
