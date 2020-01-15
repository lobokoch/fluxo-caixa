/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CaixaService {
	
	public CaixaEntity create(CaixaEntity caixaEntity);
	
	public CaixaEntity read(java.util.UUID id);
	
	public CaixaEntity update(java.util.UUID id, CaixaEntity caixaEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<CaixaEntity> list(CaixaListFilter caixaListFilter, Pageable pageable);
	
	public Collection<CaixaAutoComplete> autoComplete(String query);
	
	 
}
