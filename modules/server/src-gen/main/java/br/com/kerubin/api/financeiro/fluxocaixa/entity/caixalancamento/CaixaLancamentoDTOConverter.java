/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.212
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.financeiro.fluxocaixa.ObjectMapper;

@Component
public class CaixaLancamentoDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public CaixaLancamento convertEntityToDto(CaixaLancamentoEntity entity) {
		CaixaLancamento dto = null;
		if (entity != null) {
			dto = mapper.map(entity, CaixaLancamento.class);
		}
		return dto;
	}


	public CaixaLancamentoEntity convertDtoToEntity(CaixaLancamento dto) {
		CaixaLancamentoEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, CaixaLancamentoEntity.class);
		}
		return entity;
	}


}