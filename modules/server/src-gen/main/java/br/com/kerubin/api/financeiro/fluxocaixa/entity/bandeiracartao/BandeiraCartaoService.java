/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.212
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.bandeiracartao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;


public interface BandeiraCartaoService {
	
	public BandeiraCartaoEntity create(BandeiraCartaoEntity bandeiraCartaoEntity);
	
	public BandeiraCartaoEntity read(java.util.UUID id);
	
	public BandeiraCartaoEntity update(java.util.UUID id, BandeiraCartaoEntity bandeiraCartaoEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<BandeiraCartaoEntity> list(BandeiraCartaoListFilter bandeiraCartaoListFilter, Pageable pageable);
	
	public Collection<BandeiraCartaoAutoComplete> autoComplete(String query);
	
	 
}
