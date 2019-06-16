/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T05:27:20.128
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiario;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioDTOConverter;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.service.CaixaDiarioRuleFunctions;


@RestController
@RequestMapping("entities/caixaDiario")
public class CustomCaixaDiarioController {
	
	//@Autowired
	//private CaixaDiarioService caixaDiarioService;
	
	@Autowired
	CaixaDiarioDTOConverter caixaDiarioDTOConverter;
	
	@Autowired
	private CaixaDiarioRuleFunctions caixaDiarioRuleFunctions;
	
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
	
}
