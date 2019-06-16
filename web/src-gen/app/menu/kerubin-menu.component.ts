/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.741
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-kerubin-menu',
  templateUrl: './kerubin-menu.component.html',
  styleUrls: ['./kerubin-menu.component.css']
})
export class KerubinMenuComponent implements OnInit {

  items: MenuItem[];


  constructor() { }

  ngOnInit() {
    this.loadMenu();
  }

  loadMenu() {
    this.items = [

      {
      	label: 'Financeiro',
      	icon: 'pi pi-pw',
      	items: [
      		
      		{
      			label: 'Fluxo de caixa',
      			icon: 'pi pi-fw ',
      			items: [
      				{ label: 'Cadastro de caixa', icon: 'pi pi-fw', routerLink: '/caixa' }, 
      				{ label: 'Abertura/Fechamento de caixa', icon: 'pi pi-fw', routerLink: '/caixadiario' }, 
      				{ label: 'Lançamentos no caixa', icon: 'pi pi-fw', routerLink: '/caixalancamento' }
      			]
      		}
      		
      	]
      }


    ];
  }

}
