/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.464
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario;		

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import java.util.Optional;
import java.util.Collection;

 
@Service
public class CaixaDiarioServiceImpl implements CaixaDiarioService {
	
	@Autowired
	private CaixaDiarioRepository caixaDiarioRepository;
	
	@Autowired
	private CaixaDiarioListFilterPredicate caixaDiarioListFilterPredicate;
	
	
	
	@Transactional
	public CaixaDiarioEntity create(CaixaDiarioEntity caixaDiarioEntity) {
		return caixaDiarioRepository.save(caixaDiarioEntity);
	}
	
	@Transactional(readOnly = true)
	public CaixaDiarioEntity read(java.util.UUID id) {
		return getCaixaDiarioEntity(id);
	}
	
	@Transactional
	public CaixaDiarioEntity update(java.util.UUID id, CaixaDiarioEntity caixaDiarioEntity) {
		CaixaDiarioEntity entity = getCaixaDiarioEntity(id);
		BeanUtils.copyProperties(caixaDiarioEntity, entity, "id");
		entity = caixaDiarioRepository.save(entity);
		
		return entity;
	}
	
	@Transactional
	public void delete(java.util.UUID id) {
		caixaDiarioRepository.deleteById(id);
		
	}
	
	
	@Transactional(readOnly = true)
	public Page<CaixaDiarioEntity> list(CaixaDiarioListFilter caixaDiarioListFilter, Pageable pageable) {
		Predicate predicate = caixaDiarioListFilterPredicate.mountAndGetPredicate(caixaDiarioListFilter);
		
		Page<CaixaDiarioEntity> resultPage = caixaDiarioRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	private CaixaDiarioEntity getCaixaDiarioEntity(java.util.UUID id) {
		Optional<CaixaDiarioEntity> caixaDiarioEntity = caixaDiarioRepository.findById(id);
		if (!caixaDiarioEntity.isPresent()) {
			throw new IllegalArgumentException("CaixaDiario not found:" + id.toString());
		}
		return caixaDiarioEntity.get();
	}
	
	@Transactional(readOnly = true)
	public Collection<CaixaDiarioAutoComplete> autoComplete(String query) {
		Collection<CaixaDiarioAutoComplete> result = caixaDiarioRepository.autoComplete(query);
		return result;
	}
	
	
}
