/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T08:31:13.792
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario;

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

import br.com.kerubin.api.financeiro.fluxocaixa.common.PageResult;

		
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaAutoComplete;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("financeiro/fluxo_caixa/entities/caixaDiario")
public class CaixaDiarioController {
	
	@Autowired
	private CaixaDiarioService caixaDiarioService;
	
	@Autowired
	CaixaDiarioDTOConverter caixaDiarioDTOConverter;
	
	@Autowired
	private CaixaDiarioRuleFunctions caixaDiarioRuleFunctions;
	
	@Transactional
	@PostMapping
	public ResponseEntity<CaixaDiario> create(@Valid @RequestBody CaixaDiario caixaDiario) {
		CaixaDiarioEntity caixaDiarioEntity = caixaDiarioService.create(caixaDiarioDTOConverter.convertDtoToEntity(caixaDiario));
		return ResponseEntity.status(HttpStatus.CREATED).body(caixaDiarioDTOConverter.convertEntityToDto(caixaDiarioEntity));
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	public ResponseEntity<CaixaDiario> read(@PathVariable java.util.UUID id) {
		try {
			CaixaDiarioEntity caixaDiarioEntity = caixaDiarioService.read(id);
			return ResponseEntity.ok(caixaDiarioDTOConverter.convertEntityToDto(caixaDiarioEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<CaixaDiario> update(@PathVariable java.util.UUID id, @Valid @RequestBody CaixaDiario caixaDiario) {
		try {
			CaixaDiarioEntity caixaDiarioEntity = caixaDiarioService.update(id, caixaDiarioDTOConverter.convertDtoToEntity(caixaDiario));
			return ResponseEntity.ok(caixaDiarioDTOConverter.convertEntityToDto(caixaDiarioEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable java.util.UUID id) {
		caixaDiarioService.delete(id);
	}
	
	@Transactional(readOnly = true)
	@GetMapping
	public PageResult<CaixaDiario> list(CaixaDiarioListFilter caixaDiarioListFilter, Pageable pageable) {
		Page<CaixaDiarioEntity> page = caixaDiarioService.list(caixaDiarioListFilter, pageable);
		List<CaixaDiario> content = page.getContent().stream().map(pe -> caixaDiarioDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<CaixaDiario> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/autoComplete")
	public Collection<CaixaDiarioAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<CaixaDiarioAutoComplete> result = caixaDiarioService.autoComplete(query);
		return result;
	}
	
	
	
	@Transactional
	@PutMapping("/caixaDiarioRuleFunctionAbrirCaixa/{id}")
	public ResponseEntity<CaixaDiario> caixaDiarioRuleFunctionAbrirCaixa(@PathVariable java.util.UUID id, @Valid @RequestBody CaixaDiario caixaDiario) {
		try {
			CaixaDiarioEntity caixaDiarioEntity = caixaDiarioRuleFunctions.abrirCaixa(id, caixaDiario);
			return ResponseEntity.ok(caixaDiarioDTOConverter.convertEntityToDto(caixaDiarioEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	
	@Transactional
	@PutMapping("/caixaDiarioRuleFunctionFecharCaixa/{id}")
	public ResponseEntity<CaixaDiario> caixaDiarioRuleFunctionFecharCaixa(@PathVariable java.util.UUID id, @Valid @RequestBody CaixaDiario caixaDiario) {
		try {
			CaixaDiarioEntity caixaDiarioEntity = caixaDiarioRuleFunctions.fecharCaixa(id, caixaDiario);
			return ResponseEntity.ok(caixaDiarioDTOConverter.convertEntityToDto(caixaDiarioEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	// Begin relationships autoComplete 
	
	@Transactional(readOnly = true)
	@GetMapping("/caixaCaixaAutoComplete")
	public Collection<CaixaAutoComplete> caixaCaixaAutoComplete(@RequestParam("query") String query) {
		Collection<CaixaAutoComplete> result = caixaDiarioService.caixaCaixaAutoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
}
