/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.586
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


import { Headers } from '@angular/http';
import { Injectable } from '@angular/core';
import { HttpParams } from '@angular/common/http';
import * as moment from 'moment';

import { HttpClientWithToken } from '../../../../security/http-client-token';

import { Caixa } from './caixa.model';
import { CaixaAutoComplete } from './caixa.model';
import { CaixaListFilter } from './caixa.model';
import { environment } from 'src/environments/environment';

@Injectable()
export class CaixaService {
	
	url = environment.apiUrl + '/financeiro/fluxo_caixa/entities/caixa';
	
	constructor(private http: HttpClientWithToken) { }
	
	// TODO: Provisório
	private getHeaders(): Headers {
		const headers = new Headers();
	    
	    headers.append('Content-Type', 'application/json');
	    return headers;
	}
	
	create(caixa: Caixa): Promise<Caixa> {
		const headers = this.getHeaders();    
	
	    return this.http.post(this.url, caixa, { headers })
	    .toPromise()
	    .then(response => {
	      const created = response as Caixa;
	      this.adjustEntityDates([created]);
	      return created;
	    });
	}
	
	update(caixa: Caixa): Promise<Caixa> {
	    const headers = this.getHeaders();
	
	    return this.http.put(`${this.url}/${caixa.id}`, caixa, { headers })
	    .toPromise()
	    .then(response => {
	      const updated = response as Caixa;
	      this.adjustEntityDates([updated]);
	      return updated;
	    });
	}
	
	delete(id: string): Promise<void> {
	    return this.http.delete(`${this.url}/${id}`)
	    .toPromise()
	    .then(() => null);
	}
	
	retrieve(id: string): Promise<Caixa> {
	    const headers = this.getHeaders();
	    return this.http.get<Caixa>(`${this.url}/${id}`, { headers })
	    .toPromise()
	    .then(response => {
	      const caixa = response as Caixa;
	      this.adjustEntityDates([caixa]);
	      return caixa;
	    });
	}
	
	
	private adjustEntityDates(entityList: Caixa[]) {
		entityList.forEach(caixa => {
		});
	}
	
	
	autoComplete(query: string): Promise<CaixaAutoComplete[]> {
	    const headers = this.getHeaders();
	
	    let params = new HttpParams();
	    params = params.set('query', query);
	
	    return this.http.get<CaixaAutoComplete[]>(`${this.url}/autoComplete`, { headers, params })
	      .toPromise()
	      .then(response => {
	        const result = response as CaixaAutoComplete[];
	        return result;
	      });
	
	}
	
				
	
	caixaList(caixaListFilter: CaixaListFilter): Promise<any> {
	    const headers = this.getHeaders();
	
	    const params = this.mountAndGetSearchParams(caixaListFilter);
	
	    return this.http.get<any>(this.url, { headers, params })
	      .toPromise()
	      .then(response => {
	        const data = response;
	        const items = data.content; /* array of Caixa */
	        const totalElements = data.totalElements;
	
	        this.adjustEntityDates(items);
	
	        const result = {
	          items,
	          totalElements
	        };
	
	        return result;
	      });
	}
	
	
	mountAndGetSearchParams(filter: CaixaListFilter): HttpParams {
	    let params = new HttpParams();
	    if (filter.pageNumber) {
	      params = params.set('page', filter.pageNumber.toString());
	    }
	
	    if (filter.pageSize) {
	      params = params.set('size', filter.pageSize.toString());
	    }
		
	
	    // Sort
	    if (filter.sortField) {
	      // search/nameStartsWith?name=K&sort=name,desc
	      const sortField = filter.sortField;
	      const sortValue = `${sortField.field},${sortField.order > 0 ? 'asc' : 'desc'}`;
	      params = params.set('sort', sortValue);
	    }
	
	    return params;
	  }
	
	dateToStr(data: Date): string {
	    return moment(data).format('YYYY-MM-DD');
	}
	
	/*** TODO: avaliar se vai ser feito isso.
	replicateCaixa(id: string, groupId: string, quantity: number): Promise<boolean> {
	    const headers = this.getHeaders();
	
	    const payload = new ReplicateCaixaPayload(id, quantity, groupId);
	    return this.http.post(`${this.url}/replicateCaixa`, payload, { headers } )
	    .toPromise()
	    .then(response => {
	      return response === true;
	    });
	}
		
	getTotaisfilterCaixa(filter: CaixarListFilter): Promise<TotaisfilterCaixa> {
	    const headers = this.getHeaders();
	
	    const params = this.mountAndGetSearchParams(filter);
	    return this.http.get<TotaisfilterCaixa>(`${this.url}/getTotaisfilterCaixa`, { headers, params })
	    .toPromise()
	    .then(response => {
	      const result = response as TotaisfilterCaixa;
	      return result;
	    });
	}
	*/
}

