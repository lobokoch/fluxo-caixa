package br.com.kerubin.api.financeiro.fluxocaixa.service;

import java.util.UUID;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import br.com.kerubin.api.financeiro.fluxocaixa.core.CaixaException;
import br.com.kerubin.api.financeiro.fluxocaixa.core.CaixaGeral;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaServiceImpl;

@Primary
@Service
public class CustomCaixaServiceImpl extends CaixaServiceImpl {
	
	@Override
	public void delete(UUID id) {
		
		// Não pode apagar o caixa geral
		if (CaixaGeral.CAIXA_GERAL_ID.equals(id)) {
			throw new CaixaException("O caixa geral não pode ser excluído.");
		}
		
		super.delete(id);
	}

}
