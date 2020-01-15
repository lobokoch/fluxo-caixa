/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/


package br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente;

import javax.inject.Inject;
import org.springframework.stereotype.Component;
import br.com.kerubin.api.servicecore.mapper.ObjectMapper;
import br.com.kerubin.api.cadastros.cliente.entity.cliente.ClienteEvent;

@Component
public class ClienteDTOConverter {

	@Inject
	private ObjectMapper mapper;

	public Cliente convertEntityToDto(ClienteEntity entity) {
		Cliente dto = null;
		if (entity != null) {
			dto = mapper.map(entity, Cliente.class, true); // Do not permit passwords fields go outside.
		}
		return dto;
	}


	public ClienteEntity convertDtoToEntity(Cliente dto) {
		ClienteEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, ClienteEntity.class);
		}
		return entity;
	}


	public ClienteEntity convertDtoToEntity(ClienteEvent dto) {
		ClienteEntity entity = null;
		if (dto != null) {
			entity = mapper.map(dto, ClienteEntity.class);
		}
		return entity;
	}


}