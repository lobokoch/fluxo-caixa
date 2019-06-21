/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.586
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { BancoComponent } from './crud-banco.component';
import { AuthGuard } from '../../../../security/auth.guard';
import { BancoListComponent } from './list-banco.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const routes: Routes = [
  // Must add in forRoot
  // { path: 'banco', loadChildren: './modules/financeiro/fluxo_caixa/banco/banco.module#BancoModule' }
  {
    path: '',
    component: BancoListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'novo',
    component: BancoComponent,
    canActivate: [AuthGuard]
  },
  {
    path: ':id',
    component: BancoComponent,
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

export class BancoRoutingModule { }
