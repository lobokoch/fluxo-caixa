/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T08:31:13.792
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente;

// import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
 
@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteListFilterPredicate clienteListFilterPredicate;
	
	
	@Transactional
	@Override
	public ClienteEntity create(ClienteEntity clienteEntity) {
		return clienteRepository.save(clienteEntity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public ClienteEntity read(java.util.UUID id) {
		return getClienteEntity(id);
	}
	
	@Transactional
	@Override
	public ClienteEntity update(java.util.UUID id, ClienteEntity clienteEntity) {
		// ClienteEntity entity = getClienteEntity(id);
		// BeanUtils.copyProperties(clienteEntity, entity, "id");
		// entity = clienteRepository.save(entity);
		
		ClienteEntity entity = clienteRepository.save(clienteEntity);
		
		return entity;
	}
	
	@Transactional
	@Override
	public void delete(java.util.UUID id) {
		
		// Delete it.
		clienteRepository.deleteById(id);
		
		// Force flush to the database, for relationship validation and must throw exception because of this here.
		clienteRepository.flush();
		
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Page<ClienteEntity> list(ClienteListFilter clienteListFilter, Pageable pageable) {
		Predicate predicate = clienteListFilterPredicate.mountAndGetPredicate(clienteListFilter);
		
		Page<ClienteEntity> resultPage = clienteRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	protected ClienteEntity getClienteEntity(java.util.UUID id) {
		Optional<ClienteEntity> clienteEntity = clienteRepository.findById(id);
		if (!clienteEntity.isPresent()) {
			throw new IllegalArgumentException("Cliente not found:" + id.toString());
		}
		return clienteEntity.get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<ClienteAutoComplete> autoComplete(String query) {
		Collection<ClienteAutoComplete> result = clienteRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<ClienteNomeAutoComplete> clienteNomeAutoComplete(String query) {
		Collection<ClienteNomeAutoComplete> result = clienteRepository.clienteNomeAutoComplete(query);
		return result;
	}
	
	
}
