/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.212
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.financeiro.fluxocaixa.ObjectMapper;
import br.com.kerubin.api.cadastros.banco.entity.contabancaria.ContaBancariaEvent;

@Component
public class ContaBancariaDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public ContaBancaria convertEntityToDto(ContaBancariaEntity entity) {
		ContaBancaria dto = null;
		if (entity != null) {
			dto = mapper.map(entity, ContaBancaria.class);
		}
		return dto;
	}


	public ContaBancariaEntity convertDtoToEntity(ContaBancaria dto) {
		ContaBancariaEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, ContaBancariaEntity.class);
		}
		return entity;
	}


	public ContaBancariaEntity convertDtoToEntity(ContaBancariaEvent dto) {
		ContaBancariaEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, ContaBancariaEntity.class);
		}
		return entity;
	}


}