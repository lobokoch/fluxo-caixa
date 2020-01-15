/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa;

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

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("financeiro/fluxo_caixa/entities/caixa")
public class CaixaController {
	
	@Autowired
	private CaixaService caixaService;
	
	@Autowired
	CaixaDTOConverter caixaDTOConverter;
	
	@Transactional
	@PostMapping
	public ResponseEntity<Caixa> create(@Valid @RequestBody Caixa caixa) {
		CaixaEntity caixaEntity = caixaService.create(caixaDTOConverter.convertDtoToEntity(caixa));
		return ResponseEntity.status(HttpStatus.CREATED).body(caixaDTOConverter.convertEntityToDto(caixaEntity));
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/{id}")
	public ResponseEntity<Caixa> read(@PathVariable java.util.UUID id) {
		try {
			CaixaEntity caixaEntity = caixaService.read(id);
			return ResponseEntity.ok(caixaDTOConverter.convertEntityToDto(caixaEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Caixa> update(@PathVariable java.util.UUID id, @Valid @RequestBody Caixa caixa) {
		try {
			CaixaEntity caixaEntity = caixaService.update(id, caixaDTOConverter.convertDtoToEntity(caixa));
			return ResponseEntity.ok(caixaDTOConverter.convertEntityToDto(caixaEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable java.util.UUID id) {
		caixaService.delete(id);
	}
	
	@Transactional(readOnly = true)
	@GetMapping
	public PageResult<Caixa> list(CaixaListFilter caixaListFilter, Pageable pageable) {
		Page<CaixaEntity> page = caixaService.list(caixaListFilter, pageable);
		List<Caixa> content = page.getContent().stream().map(pe -> caixaDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<Caixa> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly = true)
	@GetMapping("/autoComplete")
	public Collection<CaixaAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<CaixaAutoComplete> result = caixaService.autoComplete(query);
		return result;
	}
	
	
	
}
