/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.464
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.fornecedor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface FornecedorService {
	
	public FornecedorEntity create(FornecedorEntity fornecedorEntity);
	
	public FornecedorEntity read(java.util.UUID id);
	
	public FornecedorEntity update(java.util.UUID id, FornecedorEntity fornecedorEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<FornecedorEntity> list(FornecedorListFilter fornecedorListFilter, Pageable pageable);
	
	public Collection<FornecedorAutoComplete> autoComplete(String query);
}
