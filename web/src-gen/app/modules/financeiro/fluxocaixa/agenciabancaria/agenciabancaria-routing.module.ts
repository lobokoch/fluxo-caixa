/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.586
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { AgenciaBancariaComponent } from './crud-agenciabancaria.component';
import { AuthGuard } from '../../../../security/auth.guard';
import { AgenciaBancariaListComponent } from './list-agenciabancaria.component';
import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';

const routes: Routes = [
  // Must add in forRoot
  // { path: 'agenciabancaria', loadChildren: './modules/financeiro/fluxo_caixa/agenciabancaria/agenciabancaria.module#AgenciaBancariaModule' }
  {
    path: '',
    component: AgenciaBancariaListComponent,
    canActivate: [AuthGuard]
  },
  {
    path: 'novo',
    component: AgenciaBancariaComponent,
    canActivate: [AuthGuard]
  },
  {
    path: ':id',
    component: AgenciaBancariaComponent,
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

export class AgenciaBancariaRoutingModule { }
