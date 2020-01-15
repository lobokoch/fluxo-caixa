/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaAutoComplete;

public interface CaixaDiarioService {
	
	public CaixaDiarioEntity create(CaixaDiarioEntity caixaDiarioEntity);
	
	public CaixaDiarioEntity read(java.util.UUID id);
	
	public CaixaDiarioEntity update(java.util.UUID id, CaixaDiarioEntity caixaDiarioEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<CaixaDiarioEntity> list(CaixaDiarioListFilter caixaDiarioListFilter, Pageable pageable);
	
	public Collection<CaixaDiarioAutoComplete> autoComplete(String query);
	
	// Begin relationships autoComplete 
	public Collection<CaixaAutoComplete> caixaCaixaAutoComplete(String query);
	// End relationships autoComplete
	 
}
