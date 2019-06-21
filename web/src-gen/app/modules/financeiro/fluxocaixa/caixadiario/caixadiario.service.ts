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

import { CaixaDiario } from './caixadiario.model';
import { CaixaDiarioAutoComplete } from './caixadiario.model';
import { Caixa } from './../caixa/caixa.model';
import { CaixaDiarioListFilter } from './caixadiario.model';
import { environment } from 'src/environments/environment';
import { CaixaAutoComplete } from './../caixa/caixa.model';

@Injectable()
export class CaixaDiarioService {
	
	url = environment.apiUrl + '/financeiro/fluxo_caixa/entities/caixaDiario';
	
	constructor(private http: HttpClientWithToken) { }
	
	// TODO: Provisório
	private getHeaders(): Headers {
		const headers = new Headers();
	    
	    headers.append('Content-Type', 'application/json');
	    return headers;
	}
	
	create(caixaDiario: CaixaDiario): Promise<CaixaDiario> {
		const headers = this.getHeaders();    
	
	    return this.http.post(this.url, caixaDiario, { headers })
	    .toPromise()
	    .then(response => {
	      const created = response as CaixaDiario;
	      this.adjustNullEntitySlots([created]);
	      this.adjustEntityDates([created]);
	      return created;
	    });
	}
	
	update(caixaDiario: CaixaDiario): Promise<CaixaDiario> {
	    const headers = this.getHeaders();
	
	    return this.http.put(`${this.url}/${caixaDiario.id}`, caixaDiario, { headers })
	    .toPromise()
	    .then(response => {
	      const updated = response as CaixaDiario;
	      this.adjustNullEntitySlots([updated]);
	      this.adjustEntityDates([updated]);
	      return updated;
	    });
	}
	
	delete(id: string): Promise<void> {
	    return this.http.delete(`${this.url}/${id}`)
	    .toPromise()
	    .then(() => null);
	}
	
	retrieve(id: string): Promise<CaixaDiario> {
	    const headers = this.getHeaders();
	    return this.http.get<CaixaDiario>(`${this.url}/${id}`, { headers })
	    .toPromise()
	    .then(response => {
	      const caixaDiario = response as CaixaDiario;
	      this.adjustNullEntitySlots([caixaDiario]);
	      this.adjustEntityDates([caixaDiario]);
	      return caixaDiario;
	    });
	}
	
	caixaDiarioRuleFunctionAbrirCaixa(caixaDiario: CaixaDiario): Promise<CaixaDiario> {
	    const headers = this.getHeaders();
	
	    return this.http.put(`${this.url}/caixaDiarioRuleFunctionAbrirCaixa/${caixaDiario.id}`, caixaDiario, { headers })
	    .toPromise()
	    .then(response => {
	      const updated = response as CaixaDiario;
	      this.adjustNullEntitySlots([updated]);
	      this.adjustEntityDates([updated]);
	      return updated;
	    });
	}
	
	caixaDiarioRuleFunctionFecharCaixa(caixaDiario: CaixaDiario): Promise<CaixaDiario> {
	    const headers = this.getHeaders();
	
	    return this.http.put(`${this.url}/caixaDiarioRuleFunctionFecharCaixa/${caixaDiario.id}`, caixaDiario, { headers })
	    .toPromise()
	    .then(response => {
	      const updated = response as CaixaDiario;
	      this.adjustNullEntitySlots([updated]);
	      this.adjustEntityDates([updated]);
	      return updated;
	    });
	}
	
	
	private adjustEntityDates(entityList: CaixaDiario[]) {
		entityList.forEach(caixaDiario => {
		      if (caixaDiario.dataHoraAbertura) {
		        caixaDiario.dataHoraAbertura = moment(caixaDiario.dataHoraAbertura, 'YYYY-MM-DD H:m:s').toDate();
		      }
		      	
		      
		      if (caixaDiario.dataHoraFechamento) {
		        caixaDiario.dataHoraFechamento = moment(caixaDiario.dataHoraFechamento, 'YYYY-MM-DD H:m:s').toDate();
		      }
		      	
		});
	}
	
	private adjustNullEntitySlots(entityList: CaixaDiario[]) {
		/*entityList.forEach(caixaDiario => {
		      if (!caixaDiario.caixa) {
		        caixaDiario.caixa = new Caixa();
		      }
		      	
		});*/
	}
	
	autoComplete(query: string): Promise<CaixaDiarioAutoComplete[]> {
	    const headers = this.getHeaders();
	
	    let params = new HttpParams();
	    params = params.set('query', query);
	
	    return this.http.get<CaixaDiarioAutoComplete[]>(`${this.url}/autoComplete`, { headers, params })
	      .toPromise()
	      .then(response => {
	        const result = response as CaixaDiarioAutoComplete[];
	        return result;
	      });
	
	}
	
							
	// Begin relationships autoComplete 
	
	caixaCaixaAutoComplete(query: string): Promise<CaixaAutoComplete[]> {
	    const headers = this.getHeaders();
	
	    let params = new HttpParams();
	    params = params.set('query', query);
	
	    return this.http.get<CaixaAutoComplete[]>(`${this.url}/caixaCaixaAutoComplete`, { headers, params })
	      .toPromise()
	      .then(response => {
	        const result = response as CaixaAutoComplete[];
	        return result;
	      });
	
	}
	
	// End relationships autoComplete
	
				
	
	caixaDiarioList(caixaDiarioListFilter: CaixaDiarioListFilter): Promise<any> {
	    const headers = this.getHeaders();
	
	    const params = this.mountAndGetSearchParams(caixaDiarioListFilter);
	
	    return this.http.get<any>(this.url, { headers, params })
	      .toPromise()
	      .then(response => {
	        const data = response;
	        const items = data.content; /* array of CaixaDiario */
	        const totalElements = data.totalElements;
	
	        this.adjustNullEntitySlots(items);
	        this.adjustEntityDates(items);
	
	        const result = {
	          items,
	          totalElements
	        };
	
	        return result;
	      });
	}
	
	
	mountAndGetSearchParams(filter: CaixaDiarioListFilter): HttpParams {
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
	replicateCaixaDiario(id: string, groupId: string, quantity: number): Promise<boolean> {
	    const headers = this.getHeaders();
	
	    const payload = new ReplicateCaixaDiarioPayload(id, quantity, groupId);
	    return this.http.post(`${this.url}/replicateCaixaDiario`, payload, { headers } )
	    .toPromise()
	    .then(response => {
	      return response === true;
	    });
	}
		
	getTotaisfilterCaixaDiario(filter: CaixaDiariorListFilter): Promise<TotaisfilterCaixaDiario> {
	    const headers = this.getHeaders();
	
	    const params = this.mountAndGetSearchParams(filter);
	    return this.http.get<TotaisfilterCaixaDiario>(`${this.url}/getTotaisfilterCaixaDiario`, { headers, params })
	    .toPromise()
	    .then(response => {
	      const result = response as TotaisfilterCaixaDiario;
	      return result;
	    });
	}
	*/
}

