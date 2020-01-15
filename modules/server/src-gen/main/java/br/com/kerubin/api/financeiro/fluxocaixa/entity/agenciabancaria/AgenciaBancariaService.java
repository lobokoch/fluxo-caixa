/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.agenciabancaria;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.banco.BancoAutoComplete;

public interface AgenciaBancariaService {
	
	public AgenciaBancariaEntity create(AgenciaBancariaEntity agenciaBancariaEntity);
	
	public AgenciaBancariaEntity read(java.util.UUID id);
	
	public AgenciaBancariaEntity update(java.util.UUID id, AgenciaBancariaEntity agenciaBancariaEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<AgenciaBancariaEntity> list(AgenciaBancariaListFilter agenciaBancariaListFilter, Pageable pageable);
	
	public Collection<AgenciaBancariaAutoComplete> autoComplete(String query);
	
	// Begin relationships autoComplete 
	public Collection<BancoAutoComplete> bancoBancoAutoComplete(String query);
	// End relationships autoComplete
	 
}
