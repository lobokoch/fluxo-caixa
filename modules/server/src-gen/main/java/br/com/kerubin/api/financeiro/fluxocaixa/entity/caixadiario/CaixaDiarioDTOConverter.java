/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.servicecore.mapper.ObjectMapper;

@Component
public class CaixaDiarioDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public CaixaDiario convertEntityToDto(CaixaDiarioEntity entity) {
		CaixaDiario dto = null;
		if (entity != null) {
			dto = mapper.map(entity, CaixaDiario.class, true); // Do not permit passwords fields go outside.
		}
		return dto;
	}


	public CaixaDiarioEntity convertDtoToEntity(CaixaDiario dto) {
		CaixaDiarioEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, CaixaDiarioEntity.class);
		}
		return entity;
	}


}