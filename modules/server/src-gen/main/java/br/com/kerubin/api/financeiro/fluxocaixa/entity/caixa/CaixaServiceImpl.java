/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.464
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa;		

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
public class CaixaServiceImpl implements CaixaService {
	
	@Autowired
	private CaixaRepository caixaRepository;
	
	@Autowired
	private CaixaListFilterPredicate caixaListFilterPredicate;
	
	
	
	@Transactional
	public CaixaEntity create(CaixaEntity caixaEntity) {
		return caixaRepository.save(caixaEntity);
	}
	
	@Transactional(readOnly = true)
	public CaixaEntity read(java.util.UUID id) {
		return getCaixaEntity(id);
	}
	
	@Transactional
	public CaixaEntity update(java.util.UUID id, CaixaEntity caixaEntity) {
		CaixaEntity entity = getCaixaEntity(id);
		BeanUtils.copyProperties(caixaEntity, entity, "id");
		entity = caixaRepository.save(entity);
		
		return entity;
	}
	
	@Transactional
	public void delete(java.util.UUID id) {
		caixaRepository.deleteById(id);
		
	}
	
	
	@Transactional(readOnly = true)
	public Page<CaixaEntity> list(CaixaListFilter caixaListFilter, Pageable pageable) {
		Predicate predicate = caixaListFilterPredicate.mountAndGetPredicate(caixaListFilter);
		
		Page<CaixaEntity> resultPage = caixaRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	private CaixaEntity getCaixaEntity(java.util.UUID id) {
		Optional<CaixaEntity> caixaEntity = caixaRepository.findById(id);
		if (!caixaEntity.isPresent()) {
			throw new IllegalArgumentException("Caixa not found:" + id.toString());
		}
		return caixaEntity.get();
	}
	
	@Transactional(readOnly = true)
	public Collection<CaixaAutoComplete> autoComplete(String query) {
		Collection<CaixaAutoComplete> result = caixaRepository.autoComplete(query);
		return result;
	}
	
	
}
