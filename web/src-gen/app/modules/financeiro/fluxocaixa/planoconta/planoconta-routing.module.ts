/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.741
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { PlanoContaComponent } from './crud-planoconta.component';
import { AuthGuard } from '../../../../security/auth.guard';
import { PlanoContaListComponent } from './list-planoconta.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const routes: Routes = [
  // Must add in forRoot
  // { path: 'planoconta', loadChildren: './modules/financeiro/fluxo_caixa/planoconta/planoconta.module#PlanoContaModule' }
  {
    path: '',
    component: PlanoContaListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'novo',
    component: PlanoContaComponent,
    canActivate: [AuthGuard]
  },
  {
    path: ':id',
    component: PlanoContaComponent,
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

export class PlanoContaRoutingModule { }
