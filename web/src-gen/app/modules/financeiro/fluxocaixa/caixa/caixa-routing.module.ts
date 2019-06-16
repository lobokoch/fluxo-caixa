/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.741
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { CaixaComponent } from './crud-caixa.component';
import { AuthGuard } from '../../../../security/auth.guard';
import { CaixaListComponent } from './list-caixa.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const routes: Routes = [
  // Must add in forRoot
  // { path: 'caixa', loadChildren: './modules/financeiro/fluxo_caixa/caixa/caixa.module#CaixaModule' }
  {
    path: '',
    component: CaixaListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'novo',
    component: CaixaComponent,
    canActivate: [AuthGuard]
  },
  {
    path: ':id',
    component: CaixaComponent,
    canActivate: [AuthGuard]
  }
];


@NgModule({

  imports: [
    RouterModule.forChild(routes)
  ],

  exports: [
    RouterModule
  ]

})

export class CaixaRoutingModule { }
