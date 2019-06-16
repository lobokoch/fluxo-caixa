/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.464
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface CaixaService {
	
	public CaixaEntity create(CaixaEntity caixaEntity);
	
	public CaixaEntity read(java.util.UUID id);
	
	public CaixaEntity update(java.util.UUID id, CaixaEntity caixaEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<CaixaEntity> list(CaixaListFilter caixaListFilter, Pageable pageable);
	
	public Collection<CaixaAutoComplete> autoComplete(String query);
}
