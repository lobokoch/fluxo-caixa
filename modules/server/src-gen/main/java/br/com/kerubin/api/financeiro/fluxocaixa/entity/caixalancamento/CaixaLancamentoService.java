/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito.CartaoCreditoAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente.ClienteAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.fornecedor.FornecedorAutoComplete;

public interface CaixaLancamentoService {
	
	public CaixaLancamentoEntity create(CaixaLancamentoEntity caixaLancamentoEntity);
	
	public CaixaLancamentoEntity read(java.util.UUID id);
	
	public CaixaLancamentoEntity update(java.util.UUID id, CaixaLancamentoEntity caixaLancamentoEntity);
	
	public void delete(java.util.UUID id);
	
	public Page<CaixaLancamentoEntity> list(CaixaLancamentoListFilter caixaLancamentoListFilter, Pageable pageable);
	
	public Collection<CaixaLancamentoAutoComplete> autoComplete(String query);
	
	// Begin relationships autoComplete 
	public Collection<CaixaDiarioAutoComplete> caixaDiarioCaixaDiarioAutoComplete(String query);
	public Collection<ContaBancariaAutoComplete> contaBancariaContaBancariaAutoComplete(String query);
	public Collection<CartaoCreditoAutoComplete> cartaoCreditoCartaoCreditoAutoComplete(String query);
	public Collection<PlanoContaAutoComplete> planoContaPlanoContasAutoComplete(String query, CaixaLancamento caixaLancamento);
	public Collection<ClienteAutoComplete> clienteClienteAutoComplete(String query);
	public Collection<FornecedorAutoComplete> fornecedorFornecedorAutoComplete(String query);
	// End relationships autoComplete
	 
	
	public Collection<CaixaLancamentoDescricaoAutoComplete> caixaLancamentoDescricaoAutoComplete(String query);
	
	public Collection<CaixaLancamentoHistConcBancariaAutoComplete> caixaLancamentoHistConcBancariaAutoComplete(String query);
	
	public CaixaLancamentoSumFields getCaixaLancamentoSumFields(CaixaLancamentoListFilter caixaLancamentoListFilter);
}
