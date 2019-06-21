/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.212
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente;

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



@RestController
@RequestMapping("entities/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	ClienteDTOConverter clienteDTOConverter;
	
	@Transactional
	@PostMapping
	public ResponseEntity<Cliente> create(@Valid @RequestBody Cliente cliente) {
		ClienteEntity clienteEntity = clienteService.create(clienteDTOConverter.convertDtoToEntity(cliente));
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteDTOConverter.convertEntityToDto(clienteEntity));
	}
	
	@Transactional(readOnly=true)
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> read(@PathVariable java.util.UUID id) {
		try {
			ClienteEntity clienteEntity = clienteService.read(id);
			return ResponseEntity.ok(clienteDTOConverter.convertEntityToDto(clienteEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> update(@PathVariable java.util.UUID id, @Valid @RequestBody Cliente cliente) {
		try {
			ClienteEntity clienteEntity = clienteService.update(id, clienteDTOConverter.convertDtoToEntity(cliente));
			return ResponseEntity.ok(clienteDTOConverter.convertEntityToDto(clienteEntity));
		}
		catch(IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable java.util.UUID id) {
		clienteService.delete(id);
	}
	
	@Transactional(readOnly=true)
	@GetMapping
	public PageResult<Cliente> list(ClienteListFilter clienteListFilter, Pageable pageable) {
		Page<ClienteEntity> page = clienteService.list(clienteListFilter, pageable);
		List<Cliente> content = page.getContent().stream().map(pe -> clienteDTOConverter.convertEntityToDto(pe)).collect(Collectors.toList());
		PageResult<Cliente> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		return pageResult;
	}
	
	@Transactional(readOnly=true)
	@GetMapping("/autoComplete")
	public Collection<ClienteAutoComplete> autoComplete(@RequestParam("query") String query) {
		Collection<ClienteAutoComplete> result = clienteService.autoComplete(query);
		return result;
	}
	
	
	
	
}
