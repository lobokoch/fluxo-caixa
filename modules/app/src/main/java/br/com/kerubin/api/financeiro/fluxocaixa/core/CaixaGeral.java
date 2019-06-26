package br.com.kerubin.api.financeiro.fluxocaixa.core;

import static br.com.kerubin.api.financeiro.fluxocaixa.core.CaixaUtils.getCaixaDiarioAnterior;

import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.kerubin.api.financeiro.fluxocaixa.CaixaDiarioSituacao;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.service.CustomCaixaDiarioServiceImpl;

@Component
public class CaixaGeral {
	
	public static final String CAIXA_GERAL_ID_STR = "bd1e9cb7-e7f6-40da-af5c-1f461dac1d11";
	public static final UUID CAIXA_GERAL_ID = UUID.fromString(CAIXA_GERAL_ID_STR);
	
	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private CaixaRepository caixaRepository;
	
	@Inject
	private CustomCaixaDiarioServiceImpl caixaDiarioService;
	
	@Transactional(readOnly = true)
	public CaixaEntity getCaixaGeral() {
		CaixaEntity caixaGeral = caixaRepository.findById(CAIXA_GERAL_ID).orElseThrow(() -> new CaixaException("O caixa geral não foi encontrado."));
		return caixaGeral;
	}
	
	@Transactional
	public CaixaDiarioEntity getCaixaGeralDiarioAberto() {
		CaixaEntity caixaGeral = getCaixaGeral();
		CaixaDiarioEntity caixaDiario = getCaixaDiarioAnterior(em, caixaGeral.getId());
		if (caixaDiario == null || !CaixaDiarioSituacao.ABERTO.equals(caixaDiario.getCaixaDiarioSituacao())) {
			caixaDiario = new CaixaDiarioEntity();
			caixaDiario.setCaixa(caixaGeral);
			caixaDiario.setCaixaDiarioSituacao(CaixaDiarioSituacao.NAO_INICIADO);
			caixaDiario = caixaDiarioService.create(caixaDiario);
			caixaDiario = caixaDiarioService.abrirCaixa(caixaDiario);
		}
		
		return caixaDiario;
	}
	
}
