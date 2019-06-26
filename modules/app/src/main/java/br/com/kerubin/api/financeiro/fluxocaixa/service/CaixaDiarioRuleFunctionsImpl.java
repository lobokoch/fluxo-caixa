package br.com.kerubin.api.financeiro.fluxocaixa.service;

import java.util.UUID;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiario;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioRuleFunctions;

@Service
public class CaixaDiarioRuleFunctionsImpl implements CaixaDiarioRuleFunctions {
	
	@Inject
	private CustomCaixaDiarioServiceImpl customCaixaDiarioServiceImpl;

	@Transactional
	@Override
	public CaixaDiarioEntity abrirCaixa(UUID id, CaixaDiario caixaDiario) {
		return customCaixaDiarioServiceImpl.abrirCaixa(id, caixaDiario);
	}

	@Override
	public CaixaDiarioEntity fecharCaixa(UUID id, CaixaDiario caixaDiario) {
		return customCaixaDiarioServiceImpl.fecharCaixa(id, caixaDiario);
	}

}
