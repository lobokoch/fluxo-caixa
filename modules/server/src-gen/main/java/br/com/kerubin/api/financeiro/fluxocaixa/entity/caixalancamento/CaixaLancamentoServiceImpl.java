/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.212
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
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.Optional;
import java.util.Collection;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito.CartaoCreditoAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente.ClienteAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.fornecedor.FornecedorAutoComplete;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito.CartaoCreditoRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente.ClienteRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.fornecedor.FornecedorRepository;

import java.math.BigDecimal;
 
@Service
public class CaixaLancamentoServiceImpl implements CaixaLancamentoService {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private CaixaLancamentoRepository caixaLancamentoRepository;
	
	@Autowired
	private CaixaLancamentoListFilterPredicate caixaLancamentoListFilterPredicate;
	
	
	@Autowired
	private CaixaDiarioRepository caixaDiarioRepository;
	
	@Autowired
	private PlanoContaRepository planoContaRepository;
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	
	@Autowired
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	
	@Transactional
	@Override
	public CaixaLancamentoEntity create(CaixaLancamentoEntity caixaLancamentoEntity) {
		return caixaLancamentoRepository.save(caixaLancamentoEntity);
	}
	
	@Transactional(readOnly = true)
	@Override
	public CaixaLancamentoEntity read(java.util.UUID id) {
		return getCaixaLancamentoEntity(id);
	}
	
	@Transactional
	@Override
	public CaixaLancamentoEntity update(java.util.UUID id, CaixaLancamentoEntity caixaLancamentoEntity) {
		CaixaLancamentoEntity entity = getCaixaLancamentoEntity(id);
		BeanUtils.copyProperties(caixaLancamentoEntity, entity, "id");
		entity = caixaLancamentoRepository.save(entity);
		
		return entity;
	}
	
	@Transactional
	@Override
	public void delete(java.util.UUID id) {
		caixaLancamentoRepository.deleteById(id);
		
	}
	
	
	@Transactional(readOnly = true)
	@Override
	public Page<CaixaLancamentoEntity> list(CaixaLancamentoListFilter caixaLancamentoListFilter, Pageable pageable) {
		Predicate predicate = caixaLancamentoListFilterPredicate.mountAndGetPredicate(caixaLancamentoListFilter);
		
		Page<CaixaLancamentoEntity> resultPage = caixaLancamentoRepository.findAll(predicate, pageable);
		return resultPage;
	}
	
	@Transactional(readOnly = true)
	protected CaixaLancamentoEntity getCaixaLancamentoEntity(java.util.UUID id) {
		Optional<CaixaLancamentoEntity> caixaLancamentoEntity = caixaLancamentoRepository.findById(id);
		if (!caixaLancamentoEntity.isPresent()) {
			throw new IllegalArgumentException("CaixaLancamento not found:" + id.toString());
		}
		return caixaLancamentoEntity.get();
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<CaixaLancamentoAutoComplete> autoComplete(String query) {
		Collection<CaixaLancamentoAutoComplete> result = caixaLancamentoRepository.autoComplete(query);
		return result;
	}
	
	// Begin relationships autoComplete 
	@Transactional(readOnly = true)
	@Override
	public Collection<CaixaDiarioAutoComplete> caixaDiarioCaixaDiarioAutoComplete(String query) {
		Collection<CaixaDiarioAutoComplete> result = caixaDiarioRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<PlanoContaAutoComplete> planoContaPlanoContasAutoComplete(String query) {
		Collection<PlanoContaAutoComplete> result = planoContaRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<ContaBancariaAutoComplete> contaBancariaContaBancariaAutoComplete(String query) {
		Collection<ContaBancariaAutoComplete> result = contaBancariaRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<CartaoCreditoAutoComplete> cartaoCreditoCartaoCreditoAutoComplete(String query) {
		Collection<CartaoCreditoAutoComplete> result = cartaoCreditoRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<ClienteAutoComplete> clienteClienteAutoComplete(String query) {
		Collection<ClienteAutoComplete> result = clienteRepository.autoComplete(query);
		return result;
	}
	
	@Transactional(readOnly = true)
	@Override
	public Collection<FornecedorAutoComplete> fornecedorFornecedorAutoComplete(String query) {
		Collection<FornecedorAutoComplete> result = fornecedorRepository.autoComplete(query);
		return result;
	}
	
	// End relationships autoComplete
	
	
	@Transactional(readOnly = true)
	@Override
	public CaixaLancamentoSumFields getCaixaLancamentoSumFields(CaixaLancamentoListFilter caixaLancamentoListFilter) {
		Predicate predicate = caixaLancamentoListFilterPredicate.mountAndGetPredicate(caixaLancamentoListFilter);
		
		QCaixaLancamentoEntity qEntity = QCaixaLancamentoEntity.caixaLancamentoEntity;
		JPAQueryFactory query = new JPAQueryFactory(em);
		CaixaLancamentoSumFields result = query.select(
				Projections.bean(CaixaLancamentoSumFields.class, 
						qEntity.valor.sum().coalesce(BigDecimal.ZERO).as("sumValor")
				))
		.from(qEntity)
		.where(predicate)
		.fetchOne();
		
		return result;
	}
	
}
