/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.741
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { BandeiraCartaoComponent } from './crud-bandeiracartao.component';
import { AuthGuard } from '../../../../security/auth.guard';
import { BandeiraCartaoListComponent } from './list-bandeiracartao.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const routes: Routes = [
  // Must add in forRoot
  // { path: 'bandeiracartao', loadChildren: './modules/financeiro/fluxo_caixa/bandeiracartao/bandeiracartao.module#BandeiraCartaoModule' }
  {
    path: '',
    component: BandeiraCartaoListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'novo',
    component: BandeiraCartaoComponent,
    canActivate: [AuthGuard]
  },
  {
    path: ':id',
    component: BandeiraCartaoComponent,
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

export class BandeiraCartaoRoutingModule { }
