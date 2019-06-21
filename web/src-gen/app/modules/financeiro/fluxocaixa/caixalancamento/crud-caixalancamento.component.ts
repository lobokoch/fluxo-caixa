/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.586
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import {MessageService} from 'primeng/api';

import { CaixaLancamento } from './caixalancamento.model';
import { CaixaLancamentoService } from './caixalancamento.service';
import { FinanceiroFluxoCaixaTranslationService } from './../i18n/financeiro-fluxocaixa-translation.service';
import * as moment from 'moment';

import { CaixaDiarioService } from './../caixadiario/caixadiario.service';
import { CaixaDiario } from './../caixadiario/caixadiario.model';
import { CaixaDiarioAutoComplete } from './../caixadiario/caixadiario.model';

import { PlanoContaService } from './../planoconta/planoconta.service';
import { PlanoConta } from './../planoconta/planoconta.model';
import { PlanoContaAutoComplete } from './../planoconta/planoconta.model';

import { ContaBancariaService } from './../contabancaria/contabancaria.service';
import { ContaBancaria } from './../contabancaria/contabancaria.model';
import { ContaBancariaAutoComplete } from './../contabancaria/contabancaria.model';

import { CartaoCreditoService } from './../cartaocredito/cartaocredito.service';
import { CartaoCredito } from './../cartaocredito/cartaocredito.model';
import { CartaoCreditoAutoComplete } from './../cartaocredito/cartaocredito.model';

import { ClienteService } from './../cliente/cliente.service';
import { Cliente } from './../cliente/cliente.model';
import { ClienteAutoComplete } from './../cliente/cliente.model';

import { FornecedorService } from './../fornecedor/fornecedor.service';
import { Fornecedor } from './../fornecedor/fornecedor.model';
import { FornecedorAutoComplete } from './../fornecedor/fornecedor.model';

import { TipoFonteMovimento } from './../enums/financeiro-fluxocaixa-enums.model';

import { TipoLancamentoFinanceiro } from './../enums/financeiro-fluxocaixa-enums.model';

import { FormaPagamento } from './../enums/financeiro-fluxocaixa-enums.model';


@Component({
  selector: 'app-crud-caixalancamento.component',
  templateUrl: './crud-caixalancamento.component.html',
  styleUrls: ['./crud-caixalancamento.component.css']
})

export class CaixaLancamentoComponent implements OnInit {
	caixaLancamento = new CaixaLancamento();
	caixaLancamentoCaixaDiarioAutoCompleteSuggestions: CaixaDiarioAutoComplete[];
	
	
	caixaLancamentoPlanoContasAutoCompleteSuggestions: PlanoContaAutoComplete[];
	
	
	caixaLancamentoContaBancariaAutoCompleteSuggestions: ContaBancariaAutoComplete[];
	
	
	caixaLancamentoCartaoCreditoAutoCompleteSuggestions: CartaoCreditoAutoComplete[];
	
	
	caixaLancamentoClienteAutoCompleteSuggestions: ClienteAutoComplete[];
	
	
	caixaLancamentoFornecedorAutoCompleteSuggestions: FornecedorAutoComplete[];
	caixaLancamentoTipoFonteMovimentoOptions: TipoFonteMovimento[];
	
	
	caixaLancamentoTipoLancamentoFinanceiroOptions: TipoLancamentoFinanceiro[];
	
	
	caixaLancamentoFormaPagamentoOptions: FormaPagamento[];
	
	constructor(
	    private caixaLancamentoService: CaixaLancamentoService,
	    private financeiroFluxoCaixaTranslationService: FinanceiroFluxoCaixaTranslationService,
	    private caixaDiarioService: CaixaDiarioService,
	    
	    
	    private planoContaService: PlanoContaService,
	    
	    
	    private contaBancariaService: ContaBancariaService,
	    
	    
	    private cartaoCreditoService: CartaoCreditoService,
	    
	    
	    private clienteService: ClienteService,
	    
	    
	    private fornecedorService: FornecedorService,
	    private route: ActivatedRoute,
	    private messageService: MessageService
	) { 
		this.initializeCaixaLancamentoTipoFonteMovimentoOptions();
		
		this.initializeCaixaLancamentoTipoLancamentoFinanceiroOptions();
		
		this.initializeCaixaLancamentoFormaPagamentoOptions();
	}
	
	ngOnInit() {
		this.initializeEnumFieldsWithDefault();
	    const id = this.route.snapshot.params['id'];
	    if (id) {
	      this.getCaixaLancamentoById(id);
	    }
	}
	
	begin(form: FormControl) {
	    form.reset();
	    setTimeout(function() {
	      this.caixaLancamento = new CaixaLancamento();
	      this.initializeEnumFieldsWithDefault();
	    }.bind(this), 1);
	}
	
	validateAllFormFields(form: FormGroup) {
	    Object.keys(form.controls).forEach(field => {
	      const control = form.get(field);
	
	      if (control instanceof FormControl) {
	        control.markAsDirty({ onlySelf: true });
	      } else if (control instanceof FormGroup) {
	        this.validateAllFormFields(control);
	      }
	    });
	}
	
	save(form: FormGroup) {
		if (!form.valid) {
	      this.validateAllFormFields(form);
	      return;
	    }
		    
	    if (this.isEditing) {
	      this.update();
	    } else {
	      this.create();
	    }
	}
	
	create() {
		
	    this.caixaLancamentoService.create(this.caixaLancamento)
	    .then((caixaLancamento) => {
	      this.caixaLancamento = caixaLancamento;
	      this.showSuccess('Registro criado com sucesso!');
	    }).
	    catch(error => {
	      this.showError('Erro ao criar registro: ' + error);
	    });
	}
	
	update() {
	    this.caixaLancamentoService.update(this.caixaLancamento)
	    .then((caixaLancamento) => {
	      this.caixaLancamento = caixaLancamento;
	      this.showSuccess('Registro alterado!');
	    })
	    .catch(error => {
	      this.showError('Erro ao atualizar registro: ' + error);
	    });
	}
	
	getCaixaLancamentoById(id: string) {
	    this.caixaLancamentoService.retrieve(id)
	    .then((caixaLancamento) => this.caixaLancamento = caixaLancamento)
	    .catch(error => {
	      this.showError('Erro ao buscar registro: ' + id);
	    });
	}
	
	get isEditing() {
	    return Boolean(this.caixaLancamento.id);
	}
	
	initializeEnumFieldsWithDefault() {
		this.caixaLancamento.tipoFonteMovimento = this.caixaLancamentoTipoFonteMovimentoOptions[0].value;
		this.caixaLancamento.tipoLancamentoFinanceiro = this.caixaLancamentoTipoLancamentoFinanceiroOptions[1].value;
		this.caixaLancamento.formaPagamento = this.caixaLancamentoFormaPagamentoOptions[0].value;
	}
	
	
	caixaLancamentoCaixaDiarioAutoCompleteClear(event) {
		// The autoComplete value has been reseted
		this.caixaLancamento.caixaDiario = null;
	}
	
	caixaLancamentoCaixaDiarioAutoComplete(event) {
	    const query = event.query;
	    this.caixaLancamentoService
	      .caixaDiarioCaixaDiarioAutoComplete(query)
	      .then((result) => {
	        this.caixaLancamentoCaixaDiarioAutoCompleteSuggestions = result as CaixaDiarioAutoComplete[];
	      })
	      .catch(error => {
	        this.showError('Erro ao buscar registros com o termo: ' + query);
	      });
	}
	
	caixaLancamentoCaixaDiarioAutoCompleteFieldConverter(caixaDiario: CaixaDiarioAutoComplete) {
		if (caixaDiario) {
			return (caixaDiario.caixa.nome || '<nulo>') + ' - ' + (moment(caixaDiario.dataHoraAbertura).format('DD/MM/YYYY H:m') || '<nulo>');
		} else {
			return null;
		}
	}
	
	
	caixaLancamentoPlanoContasAutoCompleteClear(event) {
		// The autoComplete value has been reseted
		this.caixaLancamento.planoContas = null;
	}
	
	caixaLancamentoPlanoContasAutoComplete(event) {
	    const query = event.query;
	    this.caixaLancamentoService
	      .planoContaPlanoContasAutoComplete(query)
	      .then((result) => {
	        this.caixaLancamentoPlanoContasAutoCompleteSuggestions = result as PlanoContaAutoComplete[];
	      })
	      .catch(error => {
	        this.showError('Erro ao buscar registros com o termo: ' + query);
	      });
	}
	
	caixaLancamentoPlanoContasAutoCompleteFieldConverter(planoContas: PlanoContaAutoComplete) {
		if (planoContas) {
			return (planoContas.codigo || '<nulo>') + ' - ' + (planoContas.descricao || '<nulo>');
		} else {
			return null;
		}
	}
	
	
	caixaLancamentoContaBancariaAutoCompleteClear(event) {
		// The autoComplete value has been reseted
		this.caixaLancamento.contaBancaria = null;
	}
	
	caixaLancamentoContaBancariaAutoComplete(event) {
	    const query = event.query;
	    this.caixaLancamentoService
	      .contaBancariaContaBancariaAutoComplete(query)
	      .then((result) => {
	        this.caixaLancamentoContaBancariaAutoCompleteSuggestions = result as ContaBancariaAutoComplete[];
	      })
	      .catch(error => {
	        this.showError('Erro ao buscar registros com o termo: ' + query);
	      });
	}
	
	caixaLancamentoContaBancariaAutoCompleteFieldConverter(contaBancaria: ContaBancariaAutoComplete) {
		if (contaBancaria) {
			return (contaBancaria.nomeTitular || '<nulo>') + ' - ' + (contaBancaria.numeroConta || '<nulo>');
		} else {
			return null;
		}
	}
	
	
	caixaLancamentoCartaoCreditoAutoCompleteClear(event) {
		// The autoComplete value has been reseted
		this.caixaLancamento.cartaoCredito = null;
	}
	
	caixaLancamentoCartaoCreditoAutoComplete(event) {
	    const query = event.query;
	    this.caixaLancamentoService
	      .cartaoCreditoCartaoCreditoAutoComplete(query)
	      .then((result) => {
	        this.caixaLancamentoCartaoCreditoAutoCompleteSuggestions = result as CartaoCreditoAutoComplete[];
	      })
	      .catch(error => {
	        this.showError('Erro ao buscar registros com o termo: ' + query);
	      });
	}
	
	caixaLancamentoCartaoCreditoAutoCompleteFieldConverter(cartaoCredito: CartaoCreditoAutoComplete) {
		if (cartaoCredito) {
			return (cartaoCredito.nomeTitular || '<nulo>') + ' - ' + (cartaoCredito.numeroCartao || '<nulo>');
		} else {
			return null;
		}
	}
	
	
	caixaLancamentoClienteAutoCompleteClear(event) {
		// The autoComplete value has been reseted
		this.caixaLancamento.cliente = null;
	}
	
	caixaLancamentoClienteAutoComplete(event) {
	    const query = event.query;
	    this.caixaLancamentoService
	      .clienteClienteAutoComplete(query)
	      .then((result) => {
	        this.caixaLancamentoClienteAutoCompleteSuggestions = result as ClienteAutoComplete[];
	      })
	      .catch(error => {
	        this.showError('Erro ao buscar registros com o termo: ' + query);
	      });
	}
	
	caixaLancamentoClienteAutoCompleteFieldConverter(cliente: ClienteAutoComplete) {
		if (cliente) {
			return (cliente.nome || '<nulo>') + ' - ' + (cliente.cpfCNPJ || '<nulo>');
		} else {
			return null;
		}
	}
	
	
	caixaLancamentoFornecedorAutoCompleteClear(event) {
		// The autoComplete value has been reseted
		this.caixaLancamento.fornecedor = null;
	}
	
	caixaLancamentoFornecedorAutoComplete(event) {
	    const query = event.query;
	    this.caixaLancamentoService
	      .fornecedorFornecedorAutoComplete(query)
	      .then((result) => {
	        this.caixaLancamentoFornecedorAutoCompleteSuggestions = result as FornecedorAutoComplete[];
	      })
	      .catch(error => {
	        this.showError('Erro ao buscar registros com o termo: ' + query);
	      });
	}
	
	caixaLancamentoFornecedorAutoCompleteFieldConverter(fornecedor: FornecedorAutoComplete) {
		if (fornecedor) {
			return (fornecedor.nome || '<nulo>') + ' - ' + (fornecedor.cpfCNPJ || '<nulo>');
		} else {
			return null;
		}
	}
	
	private initializeCaixaLancamentoTipoFonteMovimentoOptions() {
	    this.caixaLancamentoTipoFonteMovimentoOptions = [
	    	{ label: this.getTranslation('financeiro.fluxo_caixa.caixaLancamento_tipoFonteMovimento_lancemento_caixa'), value: 'LANCEMENTO_CAIXA' }, 
	    	{ label: this.getTranslation('financeiro.fluxo_caixa.caixaLancamento_tipoFonteMovimento_contas_pagar'), value: 'CONTAS_PAGAR' }, 
	    	{ label: this.getTranslation('financeiro.fluxo_caixa.caixaLancamento_tipoFonteMovimento_contas_receber'), value: 'CONTAS_RECEBER' }
	    ];
	}
	  
	private initializeCaixaLancamentoTipoLancamentoFinanceiroOptions() {
	    this.caixaLancamentoTipoLancamentoFinanceiroOptions = [
	    	{ label: this.getTranslation('financeiro.fluxo_caixa.caixaLancamento_tipoLancamentoFinanceiro_credito'), value: 'CREDITO' }, 
	    	{ label: this.getTranslation('financeiro.fluxo_caixa.caixaLancamento_tipoLancamentoFinanceiro_debito'), value: 'DEBITO' }
	    ];
	}
	  
	private initializeCaixaLancamentoFormaPagamentoOptions() {
	    this.caixaLancamentoFormaPagamentoOptions = [
	    	{ label: this.getTranslation('financeiro.fluxo_caixa.caixaLancamento_formaPagamento_dinheiro'), value: 'DINHEIRO' }, 
	    	{ label: this.getTranslation('financeiro.fluxo_caixa.caixaLancamento_formaPagamento_conta_bancaria'), value: 'CONTA_BANCARIA' }, 
	    	{ label: this.getTranslation('financeiro.fluxo_caixa.caixaLancamento_formaPagamento_cartao_credito'), value: 'CARTAO_CREDITO' }, 
	    	{ label: this.getTranslation('financeiro.fluxo_caixa.caixaLancamento_formaPagamento_cheque'), value: 'CHEQUE' }, 
	    	{ label: this.getTranslation('financeiro.fluxo_caixa.caixaLancamento_formaPagamento_outros'), value: 'OUTROS' }
	    ];
	}
	  
	
	public showSuccess(msg: string) {
	    this.messageService.add({severity: 'success', summary: 'Successo', detail: msg});
	}
	
	public showError(msg: string) {
	    this.messageService.add({severity: 'error', summary: 'Erro', detail: msg});
	}
	
	// TODO: temporário, só para testes.
	getTranslation(key: string): string {
		const value = this.financeiroFluxoCaixaTranslationService.getTranslation(key);
		return value;
		
		// const result = key.substring(key.lastIndexOf('_') + 1);
		// return result;
	}
	
	
										
	// Begin RuleWithSlotAppyStyleClass 
	ruleContaBancariaAppyStyleClass() {
		const expression = (String(this.caixaLancamento.formaPagamento) !== 'CONTA_BANCARIA');
		if (expression) {
			return 'hidden';
		} else {
			return '';
		}
		
	}
	ruleCartaoCreditoAppyStyleClass() {
		const expression = (String(this.caixaLancamento.formaPagamento) !== 'CARTAO_CREDITO');
		if (expression) {
			return 'hidden';
		} else {
			return '';
		}
		
	}
	ruleClienteAppyStyleClass() {
		const expression = (String(this.caixaLancamento.tipoLancamentoFinanceiro) !== 'CREDITO');
		if (expression) {
			return 'hidden';
		} else {
			return '';
		}
		
	}
	ruleFornecedorAppyStyleClass() {
		const expression = (String(this.caixaLancamento.tipoLancamentoFinanceiro) !== 'DEBITO');
		if (expression) {
			return 'hidden';
		} else {
			return '';
		}
		
	}
	// End Begin RuleWithSlotAppyStyleClass
	
}
