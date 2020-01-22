/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T08:31:13.792
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito;

// import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;


import br.com.kerubin.api.financeiro.fluxocaixa.entity.banco.BancoAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.bandeiracartao.BandeiraCartaoAutoComplete;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.banco.BancoRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.bandeiracartao.BandeiraCartaoRepository;

import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
 
@Service
public class CartaoCreditoServiceImpl implements CartaoCreditoService {
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	@Autowired
	private CartaoCreditoListFilterPredicate cartaoCreditoListFilterPredicate;
	
	
	@Autowired
	private BancoRepository bancoRepository;
	
	@Autowired
	private BandeiraCartaoRepository bandeiraCartaoRepository;
	
	
	@Transactional
	@Override
	public CartaoCreditoEntity create(CartaoCreditoEntity cartaoCreditoEntity) {
		return cartaoCreditoRepository.save(cartaoCreditoEntity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public CartaoCreditoEntity read(java.util.UUID id) {
		return getCartaoCreditoEntity(id);
	}
	
	@Transactional
	@Override
	public CartaoCreditoEntity update(java.util.UUID id, CartaoCreditoEntity cartaoCreditoEntity) {
		// CartaoCreditoEntity entity = getCartaoCreditoEntity(id);
		// BeanUtils.copyProperties(cartaoCreditoEntity, entity, "id");
		// entity = cartaoCreditoRepository.save(entity);
		
		CartaoCreditoEntity entity = cartaoCreditoRepository.save(cartaoCreditoEntity);
		
		return entity;
	}
	
	@Transactional
	@Override
	public void delete(java.util.UUID id) {
		
		// Delete it.
		cartaoCreditoRepository.deleteById(id);
		
		// Force flush to the database, for relationship validation and must throw exception because of this here.
		cartaoCreditoRepository.flush();
		
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Page<CartaoCreditoEntity> list(CartaoCreditoListFilter cartaoCreditoListFilter, Pageable pageable) {
		Predicate predicate = cartaoCreditoListFilterPredicate.mountAndGetPredicate(cartaoCreditoListFilter);
		
		Page<CartaoCreditoEntity> resultPage = cartaoCreditoRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	protected CartaoCreditoEntity getCartaoCreditoEntity(java.util.UUID id) {
		Optional<CartaoCreditoEntity> cartaoCreditoEntity = cartaoCreditoRepository.findById(id);
		if (!cartaoCreditoEntity.isPresent()) {
			throw new IllegalArgumentException("CartaoCredito not found:" + id.toString());
		}
		return cartaoCreditoEntity.get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<CartaoCreditoAutoComplete> autoComplete(String query) {
		Collection<CartaoCreditoAutoComplete> result = cartaoCreditoRepository.autoComplete(query);
		return result;
	}
	
	// Begin relationships autoComplete 
	@Transactional(readOnly = true)
	@Override
	public Collection<BancoAutoComplete> bancoBancoAutoComplete(String query) {
		Collection<BancoAutoComplete> result = bancoRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<BandeiraCartaoAutoComplete> bandeiraCartaoBandeiraCartaoAutoComplete(String query) {
		Collection<BandeiraCartaoAutoComplete> result = bandeiraCartaoRepository.autoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
	
	
}
