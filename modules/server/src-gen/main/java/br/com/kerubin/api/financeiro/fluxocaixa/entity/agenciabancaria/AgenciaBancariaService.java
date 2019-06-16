/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.464
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.agenciabancaria;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

public interface AgenciaBancariaService {
	
	public AgenciaBancariaEntity create(AgenciaBancariaEntity agenciaBancariaEntity);
	
	public AgenciaBancariaEntity read(java.util.UUID id);
	
	public AgenciaBancariaEntity update(java.util.UUID id, AgenciaBancariaEntity agenciaBancariaEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<AgenciaBancariaEntity> list(AgenciaBancariaListFilter agenciaBancariaListFilter, Pageable pageable);
	
	public Collection<AgenciaBancariaAutoComplete> autoComplete(String query);
}
