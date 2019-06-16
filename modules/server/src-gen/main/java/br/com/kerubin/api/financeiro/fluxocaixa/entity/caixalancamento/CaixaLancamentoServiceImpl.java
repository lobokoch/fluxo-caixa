/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.464
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento;		

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
public class CaixaLancamentoServiceImpl implements CaixaLancamentoService {
	
	@Autowired
	private CaixaLancamentoRepository caixaLancamentoRepository;
	
	@Autowired
	private CaixaLancamentoListFilterPredicate caixaLancamentoListFilterPredicate;
	
	
	
	@Transactional
	public CaixaLancamentoEntity create(CaixaLancamentoEntity caixaLancamentoEntity) {
		return caixaLancamentoRepository.save(caixaLancamentoEntity);
	}
	
	@Transactional(readOnly = true)
	public CaixaLancamentoEntity read(java.util.UUID id) {
		return getCaixaLancamentoEntity(id);
	}
	
	@Transactional
	public CaixaLancamentoEntity update(java.util.UUID id, CaixaLancamentoEntity caixaLancamentoEntity) {
		CaixaLancamentoEntity entity = getCaixaLancamentoEntity(id);
		BeanUtils.copyProperties(caixaLancamentoEntity, entity, "id");
		entity = caixaLancamentoRepository.save(entity);
		
		return entity;
	}
	
	@Transactional
	public void delete(java.util.UUID id) {
		caixaLancamentoRepository.deleteById(id);
		
	}
	
	
	@Transactional(readOnly = true)
	public Page<CaixaLancamentoEntity> list(CaixaLancamentoListFilter caixaLancamentoListFilter, Pageable pageable) {
		Predicate predicate = caixaLancamentoListFilterPredicate.mountAndGetPredicate(caixaLancamentoListFilter);
		
		Page<CaixaLancamentoEntity> resultPage = caixaLancamentoRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	private CaixaLancamentoEntity getCaixaLancamentoEntity(java.util.UUID id) {
		Optional<CaixaLancamentoEntity> caixaLancamentoEntity = caixaLancamentoRepository.findById(id);
		if (!caixaLancamentoEntity.isPresent()) {
			throw new IllegalArgumentException("CaixaLancamento not found:" + id.toString());
		}
		return caixaLancamentoEntity.get();
	}
	
	@Transactional(readOnly = true)
	public Collection<CaixaLancamentoAutoComplete> autoComplete(String query) {
		Collection<CaixaLancamentoAutoComplete> result = caixaLancamentoRepository.autoComplete(query);
		return result;
	}
	
	
}
