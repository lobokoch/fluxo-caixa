/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.464
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.fornecedor;


import org.springframework.stereotype.Component;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.BooleanBuilder;

@Component
public class FornecedorListFilterPredicateImpl implements FornecedorListFilterPredicate {
	
	@Override
	public Predicate mountAndGetPredicate(FornecedorListFilter fornecedorListFilter) {
		if (fornecedorListFilter == null) {
			return null;
		}
		
		BooleanBuilder where = new BooleanBuilder();
		
		
		return where;
	}

}

