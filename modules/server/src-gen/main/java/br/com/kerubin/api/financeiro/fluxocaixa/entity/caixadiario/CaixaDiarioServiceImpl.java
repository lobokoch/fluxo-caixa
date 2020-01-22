/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T08:31:13.792
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario;

// import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;


import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaAutoComplete;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaRepository;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
 
@Service
public class CaixaDiarioServiceImpl implements CaixaDiarioService {
	
	@Autowired
	private CaixaDiarioRepository caixaDiarioRepository;
	
	@Autowired
	private CaixaDiarioListFilterPredicate caixaDiarioListFilterPredicate;
	
	
	@Autowired
	private CaixaRepository caixaRepository;
	
	
	@Transactional
	@Override
	public CaixaDiarioEntity create(CaixaDiarioEntity caixaDiarioEntity) {
		return caixaDiarioRepository.save(caixaDiarioEntity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public CaixaDiarioEntity read(java.util.UUID id) {
		return getCaixaDiarioEntity(id);
	}
	
	@Transactional
	@Override
	public CaixaDiarioEntity update(java.util.UUID id, CaixaDiarioEntity caixaDiarioEntity) {
		// CaixaDiarioEntity entity = getCaixaDiarioEntity(id);
		// BeanUtils.copyProperties(caixaDiarioEntity, entity, "id");
		// entity = caixaDiarioRepository.save(entity);
		
		CaixaDiarioEntity entity = caixaDiarioRepository.save(caixaDiarioEntity);
		
		return entity;
	}
	
	@Transactional
	@Override
	public void delete(java.util.UUID id) {
		
		// Delete it.
		caixaDiarioRepository.deleteById(id);
		
		// Force flush to the database, for relationship validation and must throw exception because of this here.
		caixaDiarioRepository.flush();
		
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Page<CaixaDiarioEntity> list(CaixaDiarioListFilter caixaDiarioListFilter, Pageable pageable) {
		Predicate predicate = caixaDiarioListFilterPredicate.mountAndGetPredicate(caixaDiarioListFilter);
		
		Page<CaixaDiarioEntity> resultPage = caixaDiarioRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	protected CaixaDiarioEntity getCaixaDiarioEntity(java.util.UUID id) {
		Optional<CaixaDiarioEntity> caixaDiarioEntity = caixaDiarioRepository.findById(id);
		if (!caixaDiarioEntity.isPresent()) {
			throw new IllegalArgumentException("CaixaDiario not found:" + id.toString());
		}
		return caixaDiarioEntity.get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<CaixaDiarioAutoComplete> autoComplete(String query) {
		Collection<CaixaDiarioAutoComplete> result = caixaDiarioRepository.autoComplete(query);
		return result;
	}
	
	// Begin relationships autoComplete 
	@Transactional(readOnly = true)
	@Override
	public Collection<CaixaAutoComplete> caixaCaixaAutoComplete(String query) {
		Collection<CaixaAutoComplete> result = caixaRepository.autoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
	
	
}
