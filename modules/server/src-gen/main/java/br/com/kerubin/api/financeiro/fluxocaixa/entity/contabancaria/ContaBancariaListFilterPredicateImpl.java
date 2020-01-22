/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T08:31:13.792
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria;


import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;

@Component
public class ContaBancariaListFilterPredicateImpl implements ContaBancariaListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(ContaBancariaListFilter contaBancariaListFilter) {
		if (contaBancariaListFilter == null) {
			return null;
		}
		
		BooleanBuilder where = new BooleanBuilder();
		
		
		return where;
	}

}

