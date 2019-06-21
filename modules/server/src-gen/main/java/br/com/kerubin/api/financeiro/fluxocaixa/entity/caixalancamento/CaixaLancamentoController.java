/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.212
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento;

import java.util.Collection;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.kerubin.api.financeiro.fluxocaixa.common.PageResult;

		
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito.CartaoCreditoAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente.ClienteAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.fornecedor.FornecedorAutoComplete;



@RestController
@RequestMapping("entities/caixaLancamento")
public class CaixaLancamentoController {
	
	@Autowired
	private CaixaLancamentoService caixaLancamentoService;
	
	@Autowired
	CaixaLancamentoDTOConverter caixaLancamentoDTOConverter;
	
	@Transactional
	@PostMapping
	public ResponseEntity<CaixaLancamento> create(@Valid @RequestBody CaixaLancamento caixaLancamento) {
		CaixaLancamentoEntity caixaLancamentoEntity = caixaLancamentoService.create(caixaLancamentoDTOConverter.convertDtoToEntity(caixaLancamento));
		return ResponseEntity.status(HttpStatus.CREATED).body(caixaLancamentoDTOConverter.convertEntityToDto(caixaLancamentoEntity));
	}
	
	@Transactional(readOnly=true)
	@GetMapping("/{id}")
	public ResponseEntity<CaixaLancamento> read(@PathVariable java.util.UUID id) {
		try {
			CaixaLancamentoEntity caixaLancamentoEntity = caixaLancamentoService.read(id);
			return ResponseEntity.ok(caixaLancamentoDTOConverter.convertEntityToDto(caixaLancamentoEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<CaixaLancamento> update(@PathVariable java.util.UUID id, @Valid @RequestBody CaixaLancamento caixaLancamento) {
		try {
			CaixaLancamentoEntity caixaLancamentoEntity = caixaLancamentoService.update(id, caixaLancamentoDTOConverter.convertDtoToEntity(caixaLancamento));
			return ResponseEntity.ok(caixaLancamentoDTOConverter.convertEntityToDto(caixaLancamentoEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable java.util.UUID id) {
		caixaLancamentoService.delete(id);
	}
	
	@Transactional(readOnly=true)
	@GetMapping
	public PageResult<CaixaLancamento> list(CaixaLancamentoListFilter caixaLancamentoListFilter, Pageable pageable) {
		Page<CaixaLancamentoEntity> page = caixaLancamentoService.list(caixaLancamentoListFilter, pageable);
		List<CaixaLancamento> content = page.getContent().stream().map(pe -> caixaLancamentoDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<CaixaLancamento> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly=true)
	@GetMapping("/autoComplete")
	public Collection<CaixaLancamentoAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<CaixaLancamentoAutoComplete> result = caixaLancamentoService.autoComplete(query);
		return result;
	}
	
	
	@GetMapping("/caixaLancamentoSumFields")
	public CaixaLancamentoSumFields getCaixaLancamentoSumFields(CaixaLancamentoListFilter caixaLancamentoListFilter) {
		CaixaLancamentoSumFields result = caixaLancamentoService.getCaixaLancamentoSumFields(caixaLancamentoListFilter);
		return result;
	}
	
	
				
	// Begin relationships autoComplete 
	
	@Transactional(readOnly=true)
	@GetMapping("/caixaDiarioCaixaDiarioAutoComplete")
	public Collection<CaixaDiarioAutoComplete> caixaDiarioCaixaDiarioAutoComplete(@RequestParam("query") String query) {
		Collection<CaixaDiarioAutoComplete> result = caixaLancamentoService.caixaDiarioCaixaDiarioAutoComplete(query);
		return result;
	}
	
	
	@Transactional(readOnly=true)
	@GetMapping("/planoContaPlanoContasAutoComplete")
	public Collection<PlanoContaAutoComplete> planoContaPlanoContasAutoComplete(@RequestParam("query") String query) {
		Collection<PlanoContaAutoComplete> result = caixaLancamentoService.planoContaPlanoContasAutoComplete(query);
		return result;
	}
	
	
	@Transactional(readOnly=true)
	@GetMapping("/contaBancariaContaBancariaAutoComplete")
	public Collection<ContaBancariaAutoComplete> contaBancariaContaBancariaAutoComplete(@RequestParam("query") String query) {
		Collection<ContaBancariaAutoComplete> result = caixaLancamentoService.contaBancariaContaBancariaAutoComplete(query);
		return result;
	}
	
	
	@Transactional(readOnly=true)
	@GetMapping("/cartaoCreditoCartaoCreditoAutoComplete")
	public Collection<CartaoCreditoAutoComplete> cartaoCreditoCartaoCreditoAutoComplete(@RequestParam("query") String query) {
		Collection<CartaoCreditoAutoComplete> result = caixaLancamentoService.cartaoCreditoCartaoCreditoAutoComplete(query);
		return result;
	}
	
	
	@Transactional(readOnly=true)
	@GetMapping("/clienteClienteAutoComplete")
	public Collection<ClienteAutoComplete> clienteClienteAutoComplete(@RequestParam("query") String query) {
		Collection<ClienteAutoComplete> result = caixaLancamentoService.clienteClienteAutoComplete(query);
		return result;
	}
	
	
	@Transactional(readOnly=true)
	@GetMapping("/fornecedorFornecedorAutoComplete")
	public Collection<FornecedorAutoComplete> fornecedorFornecedorAutoComplete(@RequestParam("query") String query) {
		Collection<FornecedorAutoComplete> result = caixaLancamentoService.fornecedorFornecedorAutoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
}
