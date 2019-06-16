/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.741
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

import { Router } from '@angular/router';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'Kerubin';
  urls = ['/login', '/newaccount', '/confirmaccount'];
  constructor(private router: Router) {
    //
  }

  canShowMenu() {
    const url = this.router.url.toLowerCase();
    const exists = this.urls.some(it => url.includes(it));
    return !exists;
  }
}

