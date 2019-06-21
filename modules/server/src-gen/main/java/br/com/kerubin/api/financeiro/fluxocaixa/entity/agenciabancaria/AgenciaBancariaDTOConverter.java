/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.212
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.financeiro.fluxocaixa.entity.agenciabancaria;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.financeiro.fluxocaixa.ObjectMapper;
import br.com.kerubin.api.cadastros.banco.entity.agenciabancaria.AgenciaBancariaEvent;

@Component
public class AgenciaBancariaDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public AgenciaBancaria convertEntityToDto(AgenciaBancariaEntity entity) {
		AgenciaBancaria dto = null;
		if (entity != null) {
			dto = mapper.map(entity, AgenciaBancaria.class);
		}
		return dto;
	}


	public AgenciaBancariaEntity convertDtoToEntity(AgenciaBancaria dto) {
		AgenciaBancariaEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, AgenciaBancariaEntity.class);
		}
		return entity;
	}


	public AgenciaBancariaEntity convertDtoToEntity(AgenciaBancariaEvent dto) {
		AgenciaBancariaEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, AgenciaBancariaEntity.class);
		}
		return entity;
	}


}