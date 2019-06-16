package br.com.kerubin.api.financeiro.fluxocaixa.service;

import java.util.UUID;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiario;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioEntity;

public interface CaixaDiarioRuleFunctions {
	
	CaixaDiarioEntity abrirCaixa(UUID id, CaixaDiario caixaDiario);
	CaixaDiarioEntity fecharCaixa(UUID id, CaixaDiario caixaDiario);
	
}
