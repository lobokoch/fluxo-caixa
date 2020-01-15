/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa;

// import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.Objects;
 
@Service
public class CaixaServiceImpl implements CaixaService {
	
	@Autowired
	private CaixaRepository caixaRepository;
	
	@Autowired
	private CaixaListFilterPredicate caixaListFilterPredicate;
	
	
	@Transactional
	@Override
	public CaixaEntity create(CaixaEntity caixaEntity) {
		caixaRuleDisableCUD(caixaEntity);
		
		ruleOnCreate(caixaEntity);
		
		return caixaRepository.save(caixaEntity);
	}
	
	protected void ruleOnCreate(CaixaEntity caixaEntity) {
		caixaEntity.setSaldo(new BigDecimal(0.0));
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public CaixaEntity read(java.util.UUID id) {
		return getCaixaEntity(id);
	}
	
	@Transactional
	@Override
	public CaixaEntity update(java.util.UUID id, CaixaEntity caixaEntity) {
		caixaRuleDisableCUD(caixaEntity);
		
		// CaixaEntity entity = getCaixaEntity(id);
		// BeanUtils.copyProperties(caixaEntity, entity, "id");
		// entity = caixaRepository.save(entity);
		
		CaixaEntity entity = caixaRepository.save(caixaEntity);
		
		return entity;
	}
	
	@Transactional
	@Override
	public void delete(java.util.UUID id) {
		caixaRuleDisableCUD(getCaixaEntity(id));
		
		
		// Delete it.
		caixaRepository.deleteById(id);
		
		// Force flush to the database, for relationship validation and must throw exception because of this here.
		caixaRepository.flush();
		
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Page<CaixaEntity> list(CaixaListFilter caixaListFilter, Pageable pageable) {
		Predicate predicate = caixaListFilterPredicate.mountAndGetPredicate(caixaListFilter);
		
		Page<CaixaEntity> resultPage = caixaRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	protected CaixaEntity getCaixaEntity(java.util.UUID id) {
		Optional<CaixaEntity> caixaEntity = caixaRepository.findById(id);
		if (!caixaEntity.isPresent()) {
			throw new IllegalArgumentException("Caixa not found:" + id.toString());
		}
		return caixaEntity.get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<CaixaAutoComplete> autoComplete(String query) {
		Collection<CaixaAutoComplete> result = caixaRepository.autoComplete(query);
		return result;
	}
	
	
	protected void caixaRuleDisableCUD(CaixaEntity caixa) {
		boolean expression = Objects.nonNull(caixa.getId()) && caixa.getId().toString().equals("bd1e9cb7-e7f6-40da-af5c-1f461dac1d11");
		if (expression) {
			throw new IllegalStateException("Opeção não permitida para este objeto.");
		}	
		
	}
}
