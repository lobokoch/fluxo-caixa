/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito.CartaoCreditoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente.ClienteEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.fornecedor.FornecedorEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito.CartaoCreditoLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente.ClienteLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.fornecedor.FornecedorLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoLancamentoFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.FormaPagamento;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoFonteMovimento;
import br.com.kerubin.api.financeiro.fluxocaixa.TestOperation;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import javax.inject.Inject;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito.CartaoCreditoRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente.ClienteRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.fornecedor.FornecedorRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.CaixaDiarioSituacao;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.agenciabancaria.AgenciaBancariaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.agenciabancaria.AgenciaBancariaLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoContaBancaria;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.banco.BancoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.bandeiracartao.BandeiraCartaoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.banco.BancoLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.bandeiracartao.BandeiraCartaoLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoPlanoContaFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoReceitaDespesa;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoPessoa;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import br.com.kerubin.api.financeiro.fluxocaixa.common.PageResult;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.data.domain.Sort;
import java.util.Collection;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito.CartaoCreditoAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente.ClienteAutoComplete;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.fornecedor.FornecedorAutoComplete;
import java.math.BigDecimal;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.kerubin.api.financeiro.fluxocaixa.FinanceiroFluxoCaixaBaseEntityTest;


@RunWith(SpringRunner.class)
public class CaixaLancamentoServiceTest extends FinanceiroFluxoCaixaBaseEntityTest {
	
	private static final String[] IGNORED_FIELDS = { "id", "version" };
	
	@TestConfiguration
	static class CaixaLancamentoServiceTestConfig {
		
		@Bean
		public CaixaLancamentoListFilterPredicate caixaLancamentoListFilterPredicate() {
			return new CaixaLancamentoListFilterPredicateImpl();
		}
		
		@Bean
		public CaixaLancamentoService caixaLancamentoService() {
			return new CaixaLancamentoServiceImpl();
		}
		
		@Bean
		public CaixaLancamentoDTOConverter caixaLancamentoDTOConverter() {
			return new CaixaLancamentoDTOConverter();
		}
		
	}
	
	
	@Inject
	protected CaixaLancamentoService caixaLancamentoService;
	
	@Inject
	protected CaixaLancamentoDTOConverter caixaLancamentoDTOConverter;
	
	@Inject
	protected CaixaLancamentoRepository caixaLancamentoRepository;
	
	@Inject
	protected CaixaDiarioRepository caixaDiarioRepository;
	
	@Inject
	protected ContaBancariaRepository contaBancariaRepository;
	
	@Inject
	protected CartaoCreditoRepository cartaoCreditoRepository;
	
	@Inject
	protected PlanoContaRepository planoContaRepository;
	
	@Inject
	protected ClienteRepository clienteRepository;
	
	@Inject
	protected FornecedorRepository fornecedorRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testCreateWithAllFields() throws Exception {
		CaixaLancamento caixaLancamento = new CaixaLancamento();
		
		caixaLancamento.setId(java.util.UUID.randomUUID());
		
		CaixaDiarioEntity caixaDiarioEntityParam = newCaixaDiarioEntity();
		CaixaDiarioLookupResult caixaDiario = newCaixaDiarioLookupResult(caixaDiarioEntityParam);
		caixaLancamento.setCaixaDiario(caixaDiario);
		
		caixaLancamento.setDescricao(generateRandomString(255));
		caixaLancamento.setDataLancamento(getNextDate());
		caixaLancamento.setTipoLancamentoFinanceiro(TipoLancamentoFinanceiro.DEBITO);
		caixaLancamento.setValorCredito(new java.math.BigDecimal("17282.26246"));
		caixaLancamento.setValorDebito(new java.math.BigDecimal("18192.17086"));
		caixaLancamento.setFormaPagamento(FormaPagamento.DINHEIRO);
		
		ContaBancariaEntity contaBancariaEntityParam = newContaBancariaEntity();
		ContaBancariaLookupResult contaBancaria = newContaBancariaLookupResult(contaBancariaEntityParam);
		caixaLancamento.setContaBancaria(contaBancaria);
		
		
		CartaoCreditoEntity cartaoCreditoEntityParam = newCartaoCreditoEntity();
		CartaoCreditoLookupResult cartaoCredito = newCartaoCreditoLookupResult(cartaoCreditoEntityParam);
		caixaLancamento.setCartaoCredito(cartaoCredito);
		
		caixaLancamento.setOutrosDescricao(generateRandomString(255));
		
		PlanoContaEntity planoContaEntityParam = newPlanoContaEntity();
		PlanoContaLookupResult planoContas = newPlanoContaLookupResult(planoContaEntityParam);
		caixaLancamento.setPlanoContas(planoContas);
		
		
		ClienteEntity clienteEntityParam = newClienteEntity();
		ClienteLookupResult cliente = newClienteLookupResult(clienteEntityParam);
		caixaLancamento.setCliente(cliente);
		
		
		FornecedorEntity fornecedorEntityParam = newFornecedorEntity();
		FornecedorLookupResult fornecedor = newFornecedorLookupResult(fornecedorEntityParam);
		caixaLancamento.setFornecedor(fornecedor);
		
		caixaLancamento.setTipoFonteMovimento(TipoFonteMovimento.LANCEMENTO_CAIXA);
		caixaLancamento.setIdFonteMovimento(java.util.UUID.randomUUID());
		caixaLancamento.setDocumento(generateRandomString(255));
		caixaLancamento.setIdConcBancaria(generateRandomString(255));
		caixaLancamento.setHistConcBancaria(generateRandomString(255));
		caixaLancamento.setNumDocConcBancaria(generateRandomString(255));
		caixaLancamento.setObservacoes(generateRandomString(1000));
		
		testVisitor.visit(this, "testCreateWithAllFields", caixaLancamento, TestOperation.BEFORE);
		CaixaLancamentoEntity caixaLancamentoEntity = caixaLancamentoService.create(caixaLancamentoDTOConverter.convertDtoToEntity(caixaLancamento));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testCreateWithAllFields", caixaLancamentoEntity, TestOperation.AFTER);
		CaixaLancamento actual = caixaLancamentoDTOConverter.convertEntityToDto(caixaLancamentoEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(caixaLancamento, IGNORED_FIELDS);
		
		
		assertThat(actual.getCaixaDiario().getId()).isNotNull();
		assertThat(actual.getCaixaDiario()).isEqualToIgnoringGivenFields(caixaLancamento.getCaixaDiario(), IGNORED_FIELDS);
		
		
		assertThat(actual.getContaBancaria().getId()).isNotNull();
		assertThat(actual.getContaBancaria()).isEqualToIgnoringGivenFields(caixaLancamento.getContaBancaria(), IGNORED_FIELDS);
		
		
		assertThat(actual.getCartaoCredito().getId()).isNotNull();
		assertThat(actual.getCartaoCredito()).isEqualToIgnoringGivenFields(caixaLancamento.getCartaoCredito(), IGNORED_FIELDS);
		
		
		assertThat(actual.getPlanoContas().getId()).isNotNull();
		assertThat(actual.getPlanoContas()).isEqualToIgnoringGivenFields(caixaLancamento.getPlanoContas(), IGNORED_FIELDS);
		
		
		assertThat(actual.getCliente().getId()).isNotNull();
		assertThat(actual.getCliente()).isEqualToIgnoringGivenFields(caixaLancamento.getCliente(), IGNORED_FIELDS);
		
		
		assertThat(actual.getFornecedor().getId()).isNotNull();
		assertThat(actual.getFornecedor()).isEqualToIgnoringGivenFields(caixaLancamento.getFornecedor(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testCreateWithOnlyRecairedFields() throws Exception {
		CaixaLancamento caixaLancamento = new CaixaLancamento();
		
		caixaLancamento.setId(java.util.UUID.randomUUID());
		
		CaixaDiarioEntity caixaDiarioEntityParam = newCaixaDiarioEntity();
		CaixaDiarioLookupResult caixaDiario = newCaixaDiarioLookupResult(caixaDiarioEntityParam);
		caixaLancamento.setCaixaDiario(caixaDiario);
		
		caixaLancamento.setDescricao(generateRandomString(255));
		caixaLancamento.setDataLancamento(getNextDate());
		caixaLancamento.setTipoLancamentoFinanceiro(TipoLancamentoFinanceiro.DEBITO);
		caixaLancamento.setFormaPagamento(FormaPagamento.DINHEIRO);
		
		PlanoContaEntity planoContaEntityParam = newPlanoContaEntity();
		PlanoContaLookupResult planoContas = newPlanoContaLookupResult(planoContaEntityParam);
		caixaLancamento.setPlanoContas(planoContas);
		
		caixaLancamento.setTipoFonteMovimento(TipoFonteMovimento.LANCEMENTO_CAIXA);
		
		testVisitor.visit(this, "testCreateWithOnlyRecairedFields", caixaLancamento, TestOperation.BEFORE);
		CaixaLancamentoEntity caixaLancamentoEntity = caixaLancamentoService.create(caixaLancamentoDTOConverter.convertDtoToEntity(caixaLancamento));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testCreateWithOnlyRecairedFields", caixaLancamentoEntity, TestOperation.AFTER);
		CaixaLancamento actual = caixaLancamentoDTOConverter.convertEntityToDto(caixaLancamentoEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(caixaLancamento, IGNORED_FIELDS);
		
		
		assertThat(actual.getCaixaDiario().getId()).isNotNull();
		assertThat(actual.getCaixaDiario()).isEqualToIgnoringGivenFields(caixaLancamento.getCaixaDiario(), IGNORED_FIELDS);
		
		
		assertThat(actual.getPlanoContas().getId()).isNotNull();
		assertThat(actual.getPlanoContas()).isEqualToIgnoringGivenFields(caixaLancamento.getPlanoContas(), IGNORED_FIELDS);
		
		assertThat(actual.getContaBancaria()).isNull();
		assertThat(actual.getCartaoCredito()).isNull();
		assertThat(actual.getCliente()).isNull();
		assertThat(actual.getFornecedor()).isNull();
		
	}
	// END CREATE TESTS
	
	// BEGIN READ TESTS
	
	@Test
	public void testRead1() {
		CaixaLancamentoEntity expectedCaixaLancamentoEntity = newCaixaLancamentoEntity();
		java.util.UUID id = expectedCaixaLancamentoEntity.getId();
		CaixaLancamento expected = caixaLancamentoDTOConverter.convertEntityToDto(expectedCaixaLancamentoEntity);
		CaixaLancamentoEntity readCaixaLancamentoEntity = caixaLancamentoService.read(id);
		CaixaLancamento actual = caixaLancamentoDTOConverter.convertEntityToDto(readCaixaLancamentoEntity);
		
		
		testVisitor.visit(this, "testRead1", expected, TestOperation.BEFORE);
		
		testVisitor.visit(this, "testRead1", actual, TestOperation.AFTER);
		assertThat(actual).isEqualToComparingFieldByField(expected);
		
	}
	// END READ TESTS
	
	// BEGIN UPDATE TESTS
	
	@Test
	public void testUpdateWithAllFields() throws Exception {
		CaixaLancamentoEntity oldCaixaLancamentoEntity = newCaixaLancamentoEntity();
		java.util.UUID id = oldCaixaLancamentoEntity.getId();
				
		CaixaLancamento caixaLancamento = new CaixaLancamento();
		caixaLancamento.setId(id);
		
		
		CaixaDiarioEntity caixaDiarioEntityParam = newCaixaDiarioEntity();
		CaixaDiarioLookupResult caixaDiario = newCaixaDiarioLookupResult(caixaDiarioEntityParam);
		caixaLancamento.setCaixaDiario(caixaDiario);
		
		caixaLancamento.setDescricao(generateRandomString(255));
		caixaLancamento.setDataLancamento(getNextDate());
		caixaLancamento.setTipoLancamentoFinanceiro(TipoLancamentoFinanceiro.DEBITO);
		caixaLancamento.setValorCredito(new java.math.BigDecimal("31761.18104"));
		caixaLancamento.setValorDebito(new java.math.BigDecimal("14379.14138"));
		caixaLancamento.setFormaPagamento(FormaPagamento.DINHEIRO);
		
		ContaBancariaEntity contaBancariaEntityParam = newContaBancariaEntity();
		ContaBancariaLookupResult contaBancaria = newContaBancariaLookupResult(contaBancariaEntityParam);
		caixaLancamento.setContaBancaria(contaBancaria);
		
		
		CartaoCreditoEntity cartaoCreditoEntityParam = newCartaoCreditoEntity();
		CartaoCreditoLookupResult cartaoCredito = newCartaoCreditoLookupResult(cartaoCreditoEntityParam);
		caixaLancamento.setCartaoCredito(cartaoCredito);
		
		caixaLancamento.setOutrosDescricao(generateRandomString(255));
		
		PlanoContaEntity planoContaEntityParam = newPlanoContaEntity();
		PlanoContaLookupResult planoContas = newPlanoContaLookupResult(planoContaEntityParam);
		caixaLancamento.setPlanoContas(planoContas);
		
		
		ClienteEntity clienteEntityParam = newClienteEntity();
		ClienteLookupResult cliente = newClienteLookupResult(clienteEntityParam);
		caixaLancamento.setCliente(cliente);
		
		
		FornecedorEntity fornecedorEntityParam = newFornecedorEntity();
		FornecedorLookupResult fornecedor = newFornecedorLookupResult(fornecedorEntityParam);
		caixaLancamento.setFornecedor(fornecedor);
		
		caixaLancamento.setTipoFonteMovimento(TipoFonteMovimento.LANCEMENTO_CAIXA);
		caixaLancamento.setIdFonteMovimento(java.util.UUID.randomUUID());
		caixaLancamento.setDocumento(generateRandomString(255));
		caixaLancamento.setIdConcBancaria(generateRandomString(255));
		caixaLancamento.setHistConcBancaria(generateRandomString(255));
		caixaLancamento.setNumDocConcBancaria(generateRandomString(255));
		caixaLancamento.setObservacoes(generateRandomString(1000));
		
		testVisitor.visit(this, "testUpdateWithAllFields", caixaLancamento, TestOperation.BEFORE);
		CaixaLancamentoEntity caixaLancamentoEntity = caixaLancamentoService.update(id, caixaLancamentoDTOConverter.convertDtoToEntity(caixaLancamento));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testUpdateWithAllFields", caixaLancamentoEntity, TestOperation.AFTER);
		
		CaixaLancamento actual = caixaLancamentoDTOConverter.convertEntityToDto(caixaLancamentoEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(caixaLancamento, IGNORED_FIELDS);
		
		
		assertThat(actual.getCaixaDiario().getId()).isNotNull();
		assertThat(actual.getCaixaDiario()).isEqualToIgnoringGivenFields(caixaLancamento.getCaixaDiario(), IGNORED_FIELDS);
		
		
		assertThat(actual.getContaBancaria().getId()).isNotNull();
		assertThat(actual.getContaBancaria()).isEqualToIgnoringGivenFields(caixaLancamento.getContaBancaria(), IGNORED_FIELDS);
		
		
		assertThat(actual.getCartaoCredito().getId()).isNotNull();
		assertThat(actual.getCartaoCredito()).isEqualToIgnoringGivenFields(caixaLancamento.getCartaoCredito(), IGNORED_FIELDS);
		
		
		assertThat(actual.getPlanoContas().getId()).isNotNull();
		assertThat(actual.getPlanoContas()).isEqualToIgnoringGivenFields(caixaLancamento.getPlanoContas(), IGNORED_FIELDS);
		
		
		assertThat(actual.getCliente().getId()).isNotNull();
		assertThat(actual.getCliente()).isEqualToIgnoringGivenFields(caixaLancamento.getCliente(), IGNORED_FIELDS);
		
		
		assertThat(actual.getFornecedor().getId()).isNotNull();
		assertThat(actual.getFornecedor()).isEqualToIgnoringGivenFields(caixaLancamento.getFornecedor(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testUpdateWithOnlyRecairedFields() throws Exception {
		CaixaLancamentoEntity oldCaixaLancamentoEntity = newCaixaLancamentoEntity();
		java.util.UUID id = oldCaixaLancamentoEntity.getId();
				
		CaixaLancamento caixaLancamento = new CaixaLancamento();
		caixaLancamento.setId(id);
		
		
		CaixaDiarioEntity caixaDiarioEntityParam = newCaixaDiarioEntity();
		CaixaDiarioLookupResult caixaDiario = newCaixaDiarioLookupResult(caixaDiarioEntityParam);
		caixaLancamento.setCaixaDiario(caixaDiario);
		
		caixaLancamento.setDescricao(generateRandomString(255));
		caixaLancamento.setDataLancamento(getNextDate());
		caixaLancamento.setTipoLancamentoFinanceiro(TipoLancamentoFinanceiro.DEBITO);
		caixaLancamento.setFormaPagamento(FormaPagamento.DINHEIRO);
		
		PlanoContaEntity planoContaEntityParam = newPlanoContaEntity();
		PlanoContaLookupResult planoContas = newPlanoContaLookupResult(planoContaEntityParam);
		caixaLancamento.setPlanoContas(planoContas);
		
		caixaLancamento.setTipoFonteMovimento(TipoFonteMovimento.LANCEMENTO_CAIXA);
		
		testVisitor.visit(this, "testUpdateWithOnlyRecairedFields", caixaLancamento, TestOperation.BEFORE);
		CaixaLancamentoEntity caixaLancamentoEntity = caixaLancamentoService.update(id, caixaLancamentoDTOConverter.convertDtoToEntity(caixaLancamento));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testUpdateWithOnlyRecairedFields", caixaLancamentoEntity, TestOperation.AFTER);
		
		CaixaLancamento actual = caixaLancamentoDTOConverter.convertEntityToDto(caixaLancamentoEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(caixaLancamento, IGNORED_FIELDS);
		
		
		assertThat(actual.getCaixaDiario().getId()).isNotNull();
		assertThat(actual.getCaixaDiario()).isEqualToIgnoringGivenFields(caixaLancamento.getCaixaDiario(), IGNORED_FIELDS);
		
		
		assertThat(actual.getPlanoContas().getId()).isNotNull();
		assertThat(actual.getPlanoContas()).isEqualToIgnoringGivenFields(caixaLancamento.getPlanoContas(), IGNORED_FIELDS);
		
		assertThat(actual.getContaBancaria()).isNull();
		assertThat(actual.getCartaoCredito()).isNull();
		assertThat(actual.getCliente()).isNull();
		assertThat(actual.getFornecedor()).isNull();
		
	}
	// END UPDATE TESTS
	
	// BEGIN DELETE TESTS
	
	@Test
	public void testDelete1() {
		CaixaLancamentoEntity expected = newCaixaLancamentoEntity();
		java.util.UUID id = expected.getId();
		
		
		expected = em.find(CaixaLancamentoEntity.class, id);
		
		testVisitor.visit(this, "testDelete1", expected, TestOperation.BEFORE);
		assertThat(expected).isNotNull();
		caixaLancamentoService.delete(id);
		verify(publisher, times(0)).publish(any());
		
		expected = em.find(CaixaLancamentoEntity.class, id);
		
		testVisitor.visit(this, "testDelete1", expected, TestOperation.AFTER);
		assertThat(expected).isNull();
	}
	// END DELETE TESTS
	
	// BEGIN LIST TESTS
	
	@Test
	public void testList_FilteringByDescricao() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
		
		// Generate 33 records of data for CaixaLancamentoEntity for this test.
		List<CaixaLancamentoEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCaixaLancamentoEntity());
		}
		
		// Check if 33 records of CaixaLancamentoEntity was generated.
		long count = caixaLancamentoRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity CaixaLancamento.
		CaixaLancamentoListFilter listFilter = new CaixaLancamentoListFilter();
		
		// Extracts 7 records of CaixaLancamentoEntity randomly from testData.
		final int resultSize = 7;
		List<CaixaLancamentoEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only CaixaLancamentoEntity.descricao field and configure this list as a filter.
		List<String> descricaoListFilter = filterTestData.stream().map(CaixaLancamentoEntity::getDescricao).collect(Collectors.toList());
		listFilter.setDescricao(descricaoListFilter);
		
		// Generates a pageable configuration, without sorting.
		int pageIndex = 0; // First page starts at index zero.
		int size = 33; // Max of 33 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size);
		
		testVisitor.visit(this, "testList_FilteringByDescricao", descricaoListFilter, TestOperation.BEFORE);
		// Call service list method.
		Page<CaixaLancamentoEntity> page = caixaLancamentoService.list(listFilter, pageable);
		
		testVisitor.visit(this, "testList_FilteringByDescricao", page, TestOperation.AFTER);
		
		// Converts found entities to DTOs and mount the result page.
		List<CaixaLancamento> content = page.getContent().stream().map(it -> caixaLancamentoDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<CaixaLancamento> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Asserts that result has size 7, in any order and has only rows with descricaoListFilter elements based on descricao field.
		assertThat(pageResult.getContent())
		.hasSize(7)
		.extracting(CaixaLancamento::getDescricao)
		.containsExactlyInAnyOrderElementsOf(descricaoListFilter);
		
		// Asserts some page result elements.
		assertThat(pageResult.getNumber()).isEqualTo(pageIndex);
		assertThat(pageResult.getNumberOfElements()).isEqualTo(7);
		assertThat(pageResult.getTotalElements()).isEqualTo(7);
		assertThat(pageResult.getTotalPages()).isEqualTo(1);
		
	}
	
	@Test
	public void testList_FilteringByDescricaoWithoutResults() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for CaixaLancamentoEntity for this test.
		List<CaixaLancamentoEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCaixaLancamentoEntity());
		}
		
		// Check if 33 records of CaixaLancamentoEntity was generated.
		long count = caixaLancamentoRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity CaixaLancamento.
		CaixaLancamentoListFilter listFilter = new CaixaLancamentoListFilter();
		
		// Generates a list with only CaixaLancamentoEntity.descricao field with 1 not found data in the database and configure this list as a filter.
		List<String> descricaoListFilter = Arrays.asList(generateRandomString(255));
		listFilter.setDescricao(descricaoListFilter);
		
		// Generates a pageable configuration, without sorting.
		int pageIndex = 0; // First page starts at index zero.
		int size = 33; // Max of 33 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size);
		
		testVisitor.visit(this, "testList_FilteringByDescricaoWithoutResults", descricaoListFilter, TestOperation.BEFORE);
		// Call service list method.
		Page<CaixaLancamentoEntity> page = caixaLancamentoService.list(listFilter, pageable);
		
		testVisitor.visit(this, "testList_FilteringByDescricaoWithoutResults", page, TestOperation.AFTER);
		
		// Converts found entities to DTOs and mount the result page.
		List<CaixaLancamento> content = page.getContent().stream().map(it -> caixaLancamentoDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<CaixaLancamento> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Asserts that result has size 0 for unknown descricao field.
		assertThat(pageResult.getContent()).hasSize(0);
		
	}
	
	@Test
	public void testList_SortingByDataLancamento() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
		
		// Generate 10 records of data for CaixaLancamentoEntity for this test.
		List<CaixaLancamentoEntity> testData = new ArrayList<>();
		final int lastRecord = 10;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCaixaLancamentoEntity());
		}
		
		// Check if 10 records of CaixaLancamentoEntity was generated.
		long count = caixaLancamentoRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity CaixaLancamento.
		CaixaLancamentoListFilter listFilter = new CaixaLancamentoListFilter();
		
		// Generates a pageable configuration, with sorting.
		Sort sort = Sort.by("dataLancamento").ascending(); // select ... order by dataLancamento ascending
		int pageIndex = 0; // First page starts at index zero.
		int size = 10; // Max of 10 records per page.
		Pageable pageable = PageRequest.of(pageIndex, size, sort);
		
		
		testVisitor.visit(this, "testList_SortingByDataLancamento", pageable, TestOperation.BEFORE);
		// Call service list method.
		Page<CaixaLancamentoEntity> page = caixaLancamentoService.list(listFilter, pageable);
		
		testVisitor.visit(this, "testList_SortingByDataLancamento", page, TestOperation.AFTER);
		
		// Converts found entities to DTOs and mount the result page.
		List<CaixaLancamento> content = page.getContent().stream().map(it -> caixaLancamentoDTOConverter.convertEntityToDto(it)).collect(Collectors.toList());
		PageResult<CaixaLancamento> pageResult = new PageResult<>(content, page.getNumber(), page.getSize(), page.getTotalElements());
		
		// Extracts a list with only CaixaLancamentoEntity.dataLancamento fields.
		List<java.time.LocalDate> dataLancamentoTestDataList = testData.stream().map(CaixaLancamentoEntity::getDataLancamento).collect(Collectors.toList());
		
		// Sort dataLancamento in ascending order.
		Collections.sort(dataLancamentoTestDataList);
	
		// Asserts that result has size 10 in a specific order.
		assertThat(pageResult.getContent())
		.hasSize(10)
		.extracting(CaixaLancamento::getDataLancamento)
		.containsExactlyElementsOf(dataLancamentoTestDataList);
		
		// Asserts some page result elements.
		assertThat(pageResult.getNumber()).isEqualTo(pageIndex);
		assertThat(pageResult.getNumberOfElements()).isEqualTo(10);
		assertThat(pageResult.getTotalElements()).isEqualTo(10);
		assertThat(pageResult.getTotalPages()).isEqualTo(1);
		
	}
	// END LIST TESTS
	
	// BEGIN Autocomplete TESTS
	@Test
	public void testAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for CaixaLancamentoEntity for this test.
		List<CaixaLancamentoEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCaixaLancamentoEntity());
		}
		
		// Check if 33 records of CaixaLancamentoEntity was generated.
		long count = caixaLancamentoRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of CaixaLancamentoEntity randomly from testData.
		final int resultSize = 1;
		List<CaixaLancamentoEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only CaixaLancamentoEntity.descricao field and configure this list as a filter.
		List<String> descricaoListFilter = filterTestData.stream().map(CaixaLancamentoEntity::getDescricao).collect(Collectors.toList());
		
		testVisitor.visit(this, "testAutoComplete", descricaoListFilter, TestOperation.BEFORE);
		// Mount the autocomplete query expression and call it.
		String query = descricaoListFilter.get(0);
		Collection<CaixaLancamentoAutoComplete> result = caixaLancamentoService.autoComplete(query);
		
		testVisitor.visit(this, "testAutoComplete", result, TestOperation.AFTER);
		
		// Assert CaixaLancamentoAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(CaixaLancamentoAutoComplete::getDescricao)
		.containsExactlyInAnyOrderElementsOf(descricaoListFilter);
	}
	
	// END Autocomplete TESTS
	
	// BEGIN ListFilter Autocomplete TESTS
	
	@Test
	public void testCaixaLancamentoDescricaoAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for CaixaLancamentoEntity for this test.
		List<CaixaLancamentoEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCaixaLancamentoEntity());
		}
		
		// Check if 33 records of CaixaLancamentoEntity was generated.
		long count = caixaLancamentoRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of CaixaLancamentoEntity randomly from testData.
		final int resultSize = 1;
		List<CaixaLancamentoEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only CaixaLancamentoEntity.descricao field and configure this list as a filter.
		List<String> descricaoListFilter = filterTestData.stream().map(CaixaLancamentoEntity::getDescricao).collect(Collectors.toList());
		
		testVisitor.visit(this, "testCaixaLancamentoDescricaoAutoComplete", descricaoListFilter, TestOperation.BEFORE);
		// Mount the autocomplete query expression and call it.
		String query = descricaoListFilter.get(0);
		Collection<CaixaLancamentoDescricaoAutoComplete> result = caixaLancamentoService.caixaLancamentoDescricaoAutoComplete(query);
		
		testVisitor.visit(this, "testCaixaLancamentoDescricaoAutoComplete", result, TestOperation.AFTER);
		// Assert CaixaLancamentoDescricaoAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(CaixaLancamentoDescricaoAutoComplete::getDescricao)
		.containsExactlyInAnyOrderElementsOf(descricaoListFilter);
	}
	
	
	@Test
	public void testCaixaLancamentoHistConcBancariaAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for CaixaLancamentoEntity for this test.
		List<CaixaLancamentoEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCaixaLancamentoEntity());
		}
		
		// Check if 33 records of CaixaLancamentoEntity was generated.
		long count = caixaLancamentoRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of CaixaLancamentoEntity randomly from testData.
		final int resultSize = 1;
		List<CaixaLancamentoEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only CaixaLancamentoEntity.histConcBancaria field and configure this list as a filter.
		List<String> histConcBancariaListFilter = filterTestData.stream().map(CaixaLancamentoEntity::getHistConcBancaria).collect(Collectors.toList());
		
		testVisitor.visit(this, "testCaixaLancamentoHistConcBancariaAutoComplete", histConcBancariaListFilter, TestOperation.BEFORE);
		// Mount the autocomplete query expression and call it.
		String query = histConcBancariaListFilter.get(0);
		Collection<CaixaLancamentoHistConcBancariaAutoComplete> result = caixaLancamentoService.caixaLancamentoHistConcBancariaAutoComplete(query);
		
		testVisitor.visit(this, "testCaixaLancamentoHistConcBancariaAutoComplete", result, TestOperation.AFTER);
		// Assert CaixaLancamentoHistConcBancariaAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(CaixaLancamentoHistConcBancariaAutoComplete::getHistConcBancaria)
		.containsExactlyInAnyOrderElementsOf(histConcBancariaListFilter);
	}
	
	// END ListFilter Autocomplete TESTS
	
	// BEGIN Relationships Autocomplete TESTS
	
	@Test
	public void testCaixaLancamentoCaixaDiarioAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for CaixaDiarioEntity for this test.
		List<CaixaDiarioEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCaixaDiarioEntity());
		}
		
		// Check if 33 records of CaixaDiarioEntity was generated.
		long count = caixaDiarioRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of CaixaDiarioEntity randomly from testData.
		final int resultSize = 1;
		List<CaixaDiarioEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only CaixaDiarioEntity.caixa field and configure this list as a filter.
		List<String> caixaListFilter = filterTestData.stream().map(it -> it.getCaixa().getNome()).collect(Collectors.toList());
		String query = caixaListFilter.get(0);
		
		
		testVisitor.visit(this, "testCaixaLancamentoCaixaDiarioAutoComplete", query, TestOperation.BEFORE);
		Collection<CaixaDiarioAutoComplete> result = caixaLancamentoService.caixaDiarioCaixaDiarioAutoComplete(query);
		
		testVisitor.visit(this, "testCaixaLancamentoCaixaDiarioAutoComplete", result, TestOperation.AFTER);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(it -> it.getCaixa().getNome())
		.containsExactlyInAnyOrderElementsOf(caixaListFilter);
	}
	
	
	@Test
	public void testCaixaLancamentoContaBancariaAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for ContaBancariaEntity for this test.
		List<ContaBancariaEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newContaBancariaEntity());
		}
		
		// Check if 33 records of ContaBancariaEntity was generated.
		long count = contaBancariaRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of ContaBancariaEntity randomly from testData.
		final int resultSize = 1;
		List<ContaBancariaEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only ContaBancariaEntity.nomeTitular field and configure this list as a filter.
		List<String> nomeTitularListFilter = filterTestData.stream().map(ContaBancariaEntity::getNomeTitular).collect(Collectors.toList());
		String query = nomeTitularListFilter.get(0);
		
		
		testVisitor.visit(this, "testCaixaLancamentoContaBancariaAutoComplete", query, TestOperation.BEFORE);
		Collection<ContaBancariaAutoComplete> result = caixaLancamentoService.contaBancariaContaBancariaAutoComplete(query);
		
		testVisitor.visit(this, "testCaixaLancamentoContaBancariaAutoComplete", result, TestOperation.AFTER);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(ContaBancariaAutoComplete::getNomeTitular)
		.containsExactlyInAnyOrderElementsOf(nomeTitularListFilter);
	}
	
	
	@Test
	public void testCaixaLancamentoCartaoCreditoAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for CartaoCreditoEntity for this test.
		List<CartaoCreditoEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCartaoCreditoEntity());
		}
		
		// Check if 33 records of CartaoCreditoEntity was generated.
		long count = cartaoCreditoRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of CartaoCreditoEntity randomly from testData.
		final int resultSize = 1;
		List<CartaoCreditoEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only CartaoCreditoEntity.nomeTitular field and configure this list as a filter.
		List<String> nomeTitularListFilter = filterTestData.stream().map(CartaoCreditoEntity::getNomeTitular).collect(Collectors.toList());
		String query = nomeTitularListFilter.get(0);
		
		
		testVisitor.visit(this, "testCaixaLancamentoCartaoCreditoAutoComplete", query, TestOperation.BEFORE);
		Collection<CartaoCreditoAutoComplete> result = caixaLancamentoService.cartaoCreditoCartaoCreditoAutoComplete(query);
		
		testVisitor.visit(this, "testCaixaLancamentoCartaoCreditoAutoComplete", result, TestOperation.AFTER);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(CartaoCreditoAutoComplete::getNomeTitular)
		.containsExactlyInAnyOrderElementsOf(nomeTitularListFilter);
	}
	
	
	@Test
	public void testCaixaLancamentoPlanoContasAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for PlanoContaEntity for this test.
		List<PlanoContaEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newPlanoContaEntity());
		}
		
		// Check if 33 records of PlanoContaEntity was generated.
		long count = planoContaRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of PlanoContaEntity randomly from testData.
		final int resultSize = 1;
		List<PlanoContaEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only PlanoContaEntity.codigo field and configure this list as a filter.
		List<String> codigoListFilter = filterTestData.stream().map(PlanoContaEntity::getCodigo).collect(Collectors.toList());
		String query = codigoListFilter.get(0);
		
		
		CaixaLancamento caixaLancamento = null;
		
		
		testVisitor.visit(this, "testCaixaLancamentoPlanoContasAutoComplete", query, TestOperation.BEFORE);
		Collection<PlanoContaAutoComplete> result = caixaLancamentoService.planoContaPlanoContasAutoComplete(query, caixaLancamento);
		
		testVisitor.visit(this, "testCaixaLancamentoPlanoContasAutoComplete", result, TestOperation.AFTER);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(PlanoContaAutoComplete::getCodigo)
		.containsExactlyInAnyOrderElementsOf(codigoListFilter);
	}
	
	
	@Test
	public void testCaixaLancamentoClienteAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for ClienteEntity for this test.
		List<ClienteEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newClienteEntity());
		}
		
		// Check if 33 records of ClienteEntity was generated.
		long count = clienteRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of ClienteEntity randomly from testData.
		final int resultSize = 1;
		List<ClienteEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only ClienteEntity.nome field and configure this list as a filter.
		List<String> nomeListFilter = filterTestData.stream().map(ClienteEntity::getNome).collect(Collectors.toList());
		String query = nomeListFilter.get(0);
		
		
		testVisitor.visit(this, "testCaixaLancamentoClienteAutoComplete", query, TestOperation.BEFORE);
		Collection<ClienteAutoComplete> result = caixaLancamentoService.clienteClienteAutoComplete(query);
		
		testVisitor.visit(this, "testCaixaLancamentoClienteAutoComplete", result, TestOperation.AFTER);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(ClienteAutoComplete::getNome)
		.containsExactlyInAnyOrderElementsOf(nomeListFilter);
	}
	
	
	@Test
	public void testCaixaLancamentoFornecedorAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for FornecedorEntity for this test.
		List<FornecedorEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newFornecedorEntity());
		}
		
		// Check if 33 records of FornecedorEntity was generated.
		long count = fornecedorRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of FornecedorEntity randomly from testData.
		final int resultSize = 1;
		List<FornecedorEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only FornecedorEntity.nome field and configure this list as a filter.
		List<String> nomeListFilter = filterTestData.stream().map(FornecedorEntity::getNome).collect(Collectors.toList());
		String query = nomeListFilter.get(0);
		
		
		testVisitor.visit(this, "testCaixaLancamentoFornecedorAutoComplete", query, TestOperation.BEFORE);
		Collection<FornecedorAutoComplete> result = caixaLancamentoService.fornecedorFornecedorAutoComplete(query);
		
		testVisitor.visit(this, "testCaixaLancamentoFornecedorAutoComplete", result, TestOperation.AFTER);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(FornecedorAutoComplete::getNome)
		.containsExactlyInAnyOrderElementsOf(nomeListFilter);
	}
	
	// END Relationships Autocomplete TESTS
	
	// BEGIN tests for Sum Fields
	
	@Test
	public void testGetCaixaLancamentoSumFields() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
		
		// Generate 2 records of data for CaixaLancamentoEntity for this test.
		List<CaixaLancamentoEntity> testData = new ArrayList<>();
		final int lastRecord = 2;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCaixaLancamentoEntity());
		}
		
		// Check if 2 records of CaixaLancamentoEntity was generated.
		long count = caixaLancamentoRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Creates a list filter for entity CaixaLancamento.
		CaixaLancamentoListFilter listFilter = new CaixaLancamentoListFilter();
		
		// Extracts 2 records of CaixaLancamentoEntity randomly from testData.
		final int resultSize = 2;
		List<CaixaLancamentoEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		CaixaLancamentoSumFields expected = new CaixaLancamentoSumFields();
		
		BigDecimal sumValorCredito = filterTestData.stream().map(it -> it.getValorCredito()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumValorCredito(sumValorCredito);
		
		BigDecimal sumValorDebito = filterTestData.stream().map(it -> it.getValorDebito()).reduce(BigDecimal.ZERO, BigDecimal::add);
		expected.setSumValorDebito(sumValorDebito);
		
		testVisitor.visit(this, "testGetCaixaLancamentoSumFields", listFilter, TestOperation.BEFORE);
		CaixaLancamentoSumFields actual = caixaLancamentoService.getCaixaLancamentoSumFields(listFilter);
		
		testVisitor.visit(this, "testGetCaixaLancamentoSumFields", actual, TestOperation.AFTER);
		
		assertThat(actual).isEqualToComparingFieldByField(expected);
	}
	// END tests for Sum Fields
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN TESTS DEPENDENCIES
	
	
	protected CaixaLancamentoEntity newCaixaLancamentoEntity() {
		CaixaLancamentoEntity caixaLancamentoEntity = new CaixaLancamentoEntity();
		
		caixaLancamentoEntity.setCaixaDiario(newCaixaDiarioEntity());
		caixaLancamentoEntity.setDescricao(generateRandomString(255));
		caixaLancamentoEntity.setDataLancamento(getNextDate());
		caixaLancamentoEntity.setTipoLancamentoFinanceiro(TipoLancamentoFinanceiro.DEBITO);
		caixaLancamentoEntity.setValorCredito(new java.math.BigDecimal("7462.26831"));
		caixaLancamentoEntity.setValorDebito(new java.math.BigDecimal("28132.26603"));
		caixaLancamentoEntity.setFormaPagamento(FormaPagamento.DINHEIRO);
		caixaLancamentoEntity.setContaBancaria(newContaBancariaEntity());
		caixaLancamentoEntity.setCartaoCredito(newCartaoCreditoEntity());
		caixaLancamentoEntity.setOutrosDescricao(generateRandomString(255));
		caixaLancamentoEntity.setPlanoContas(newPlanoContaEntity());
		caixaLancamentoEntity.setCliente(newClienteEntity());
		caixaLancamentoEntity.setFornecedor(newFornecedorEntity());
		caixaLancamentoEntity.setTipoFonteMovimento(TipoFonteMovimento.LANCEMENTO_CAIXA);
		caixaLancamentoEntity.setIdFonteMovimento(java.util.UUID.randomUUID());
		caixaLancamentoEntity.setDocumento(generateRandomString(255));
		caixaLancamentoEntity.setIdConcBancaria(generateRandomString(255));
		caixaLancamentoEntity.setHistConcBancaria(generateRandomString(255));
		caixaLancamentoEntity.setNumDocConcBancaria(generateRandomString(255));
		caixaLancamentoEntity.setObservacoes(generateRandomString(1000));
		
		caixaLancamentoEntity = em.persistAndFlush(caixaLancamentoEntity);
		return caixaLancamentoEntity;
	}
	
	
	protected CaixaLancamentoLookupResult newCaixaLancamentoLookupResult(CaixaLancamentoEntity caixaLancamentoEntity) {
		CaixaLancamentoLookupResult caixaLancamento = new CaixaLancamentoLookupResult();
		
		caixaLancamento.setId(caixaLancamentoEntity.getId());
		caixaLancamento.setDescricao(caixaLancamentoEntity.getDescricao());
		caixaLancamento.setVersion(caixaLancamentoEntity.getVersion());
		
		return caixaLancamento;
	}
	
	
	protected CaixaDiarioEntity newCaixaDiarioEntity() {
		CaixaDiarioEntity caixaDiarioEntity = new CaixaDiarioEntity();
		
		caixaDiarioEntity.setCaixa(newCaixaEntity());
		caixaDiarioEntity.setCaixaDiarioSituacao(CaixaDiarioSituacao.NAO_INICIADO);
		caixaDiarioEntity.setDataHoraAbertura(java.time.LocalDateTime.now());
		caixaDiarioEntity.setSaldoInicial(new java.math.BigDecimal("20008.30266"));
		caixaDiarioEntity.setDataHoraFechamento(java.time.LocalDateTime.now());
		caixaDiarioEntity.setSaldoFinal(new java.math.BigDecimal("28668.7963"));
		caixaDiarioEntity.setObservacoes(generateRandomString(1000));
		
		caixaDiarioEntity = em.persistAndFlush(caixaDiarioEntity);
		return caixaDiarioEntity;
	}
	
	
	protected CaixaDiarioLookupResult newCaixaDiarioLookupResult(CaixaDiarioEntity caixaDiarioEntity) {
		CaixaDiarioLookupResult caixaDiario = new CaixaDiarioLookupResult();
		
		caixaDiario.setId(caixaDiarioEntity.getId());
		caixaDiario.setCaixa(newCaixaLookupResult(caixaDiarioEntity.getCaixa()));
		caixaDiario.setCaixaDiarioSituacao(caixaDiarioEntity.getCaixaDiarioSituacao());
		caixaDiario.setDataHoraAbertura(caixaDiarioEntity.getDataHoraAbertura());
		caixaDiario.setVersion(caixaDiarioEntity.getVersion());
		
		return caixaDiario;
	}
	
	
	protected CaixaEntity newCaixaEntity() {
		CaixaEntity caixaEntity = new CaixaEntity();
		
		caixaEntity.setNome(generateRandomString(255));
		caixaEntity.setAtivo(true);
		caixaEntity.setSaldo(new java.math.BigDecimal("18977.11218"));
		caixaEntity.setObservacoes(generateRandomString(255));
		
		caixaEntity = em.persistAndFlush(caixaEntity);
		return caixaEntity;
	}
	
	
	protected CaixaLookupResult newCaixaLookupResult(CaixaEntity caixaEntity) {
		CaixaLookupResult caixa = new CaixaLookupResult();
		
		caixa.setId(caixaEntity.getId());
		caixa.setNome(caixaEntity.getNome());
		caixa.setVersion(caixaEntity.getVersion());
		
		return caixa;
	}
	
	
	protected ContaBancariaEntity newContaBancariaEntity() {
		ContaBancariaEntity contaBancariaEntity = new ContaBancariaEntity();
		
		contaBancariaEntity.setId(java.util.UUID.randomUUID());
		contaBancariaEntity.setNomeTitular(generateRandomString(255));
		contaBancariaEntity.setAgencia(newAgenciaBancariaEntity());
		contaBancariaEntity.setTipoContaBancaria(TipoContaBancaria.CONTA_CORRENTE);
		contaBancariaEntity.setNumeroConta(generateRandomString(30));
		contaBancariaEntity.setDigito(generateRandomString(10));
		contaBancariaEntity.setDataValidade(getNextDate());
		contaBancariaEntity.setAtivo(true);
		contaBancariaEntity.setDeleted(false);
		
		contaBancariaEntity = em.persistAndFlush(contaBancariaEntity);
		return contaBancariaEntity;
	}
	
	
	protected ContaBancariaLookupResult newContaBancariaLookupResult(ContaBancariaEntity contaBancariaEntity) {
		ContaBancariaLookupResult contaBancaria = new ContaBancariaLookupResult();
		
		contaBancaria.setId(contaBancariaEntity.getId());
		contaBancaria.setNomeTitular(contaBancariaEntity.getNomeTitular());
		contaBancaria.setNumeroConta(contaBancariaEntity.getNumeroConta());
		
		return contaBancaria;
	}
	
	
	protected AgenciaBancariaEntity newAgenciaBancariaEntity() {
		AgenciaBancariaEntity agenciaBancariaEntity = new AgenciaBancariaEntity();
		
		agenciaBancariaEntity.setId(java.util.UUID.randomUUID());
		agenciaBancariaEntity.setBanco(newBancoEntity());
		agenciaBancariaEntity.setNumeroAgencia(generateRandomString(50));
		agenciaBancariaEntity.setDigitoAgencia(generateRandomString(10));
		agenciaBancariaEntity.setEndereco(generateRandomString(255));
		agenciaBancariaEntity.setDeleted(false);
		
		agenciaBancariaEntity = em.persistAndFlush(agenciaBancariaEntity);
		return agenciaBancariaEntity;
	}
	
	
	protected AgenciaBancariaLookupResult newAgenciaBancariaLookupResult(AgenciaBancariaEntity agenciaBancariaEntity) {
		AgenciaBancariaLookupResult agenciaBancaria = new AgenciaBancariaLookupResult();
		
		agenciaBancaria.setId(agenciaBancariaEntity.getId());
		agenciaBancaria.setNumeroAgencia(agenciaBancariaEntity.getNumeroAgencia());
		agenciaBancaria.setDigitoAgencia(agenciaBancariaEntity.getDigitoAgencia());
		agenciaBancaria.setEndereco(agenciaBancariaEntity.getEndereco());
		
		return agenciaBancaria;
	}
	
	
	protected BancoEntity newBancoEntity() {
		BancoEntity bancoEntity = new BancoEntity();
		
		bancoEntity.setId(java.util.UUID.randomUUID());
		bancoEntity.setNumero(generateRandomString(20));
		bancoEntity.setNome(generateRandomString(255));
		bancoEntity.setDeleted(false);
		
		bancoEntity = em.persistAndFlush(bancoEntity);
		return bancoEntity;
	}
	
	
	protected BancoLookupResult newBancoLookupResult(BancoEntity bancoEntity) {
		BancoLookupResult banco = new BancoLookupResult();
		
		banco.setId(bancoEntity.getId());
		banco.setNumero(bancoEntity.getNumero());
		banco.setNome(bancoEntity.getNome());
		
		return banco;
	}
	
	
	protected CartaoCreditoEntity newCartaoCreditoEntity() {
		CartaoCreditoEntity cartaoCreditoEntity = new CartaoCreditoEntity();
		
		cartaoCreditoEntity.setId(java.util.UUID.randomUUID());
		cartaoCreditoEntity.setBanco(newBancoEntity());
		cartaoCreditoEntity.setNomeTitular(generateRandomString(255));
		cartaoCreditoEntity.setNumeroCartao(generateRandomString(50));
		cartaoCreditoEntity.setValidade(getNextDate());
		cartaoCreditoEntity.setValorLimite(new java.math.BigDecimal("13366.14806"));
		cartaoCreditoEntity.setBandeiraCartao(newBandeiraCartaoEntity());
		cartaoCreditoEntity.setAtivo(true);
		cartaoCreditoEntity.setDeleted(false);
		
		cartaoCreditoEntity = em.persistAndFlush(cartaoCreditoEntity);
		return cartaoCreditoEntity;
	}
	
	
	protected CartaoCreditoLookupResult newCartaoCreditoLookupResult(CartaoCreditoEntity cartaoCreditoEntity) {
		CartaoCreditoLookupResult cartaoCredito = new CartaoCreditoLookupResult();
		
		cartaoCredito.setId(cartaoCreditoEntity.getId());
		cartaoCredito.setNomeTitular(cartaoCreditoEntity.getNomeTitular());
		cartaoCredito.setNumeroCartao(cartaoCreditoEntity.getNumeroCartao());
		
		return cartaoCredito;
	}
	
	
	protected BandeiraCartaoEntity newBandeiraCartaoEntity() {
		BandeiraCartaoEntity bandeiraCartaoEntity = new BandeiraCartaoEntity();
		
		bandeiraCartaoEntity.setId(java.util.UUID.randomUUID());
		bandeiraCartaoEntity.setNomeBandeira(generateRandomString(255));
		bandeiraCartaoEntity.setDeleted(false);
		
		bandeiraCartaoEntity = em.persistAndFlush(bandeiraCartaoEntity);
		return bandeiraCartaoEntity;
	}
	
	
	protected BandeiraCartaoLookupResult newBandeiraCartaoLookupResult(BandeiraCartaoEntity bandeiraCartaoEntity) {
		BandeiraCartaoLookupResult bandeiraCartao = new BandeiraCartaoLookupResult();
		
		bandeiraCartao.setId(bandeiraCartaoEntity.getId());
		bandeiraCartao.setNomeBandeira(bandeiraCartaoEntity.getNomeBandeira());
		
		return bandeiraCartao;
	}
	
	
	protected PlanoContaEntity newPlanoContaEntity() {
		PlanoContaEntity planoContaEntity = new PlanoContaEntity();
		
		planoContaEntity.setId(java.util.UUID.randomUUID());
		planoContaEntity.setCodigo(generateRandomString(255));
		planoContaEntity.setDescricao(generateRandomString(255));
		planoContaEntity.setTipoFinanceiro(TipoPlanoContaFinanceiro.RECEITA);
		planoContaEntity.setTipoReceitaDespesa(TipoReceitaDespesa.VARIAVEL);
		planoContaEntity.setPlanoContaPai(null);
		planoContaEntity.setAtivo(true);
		planoContaEntity.setDeleted(false);
		
		planoContaEntity = em.persistAndFlush(planoContaEntity);
		return planoContaEntity;
	}
	
	
	protected PlanoContaLookupResult newPlanoContaLookupResult(PlanoContaEntity planoContaEntity) {
		PlanoContaLookupResult planoConta = new PlanoContaLookupResult();
		
		planoConta.setId(planoContaEntity.getId());
		planoConta.setCodigo(planoContaEntity.getCodigo());
		planoConta.setDescricao(planoContaEntity.getDescricao());
		
		return planoConta;
	}
	
	
	protected ClienteEntity newClienteEntity() {
		ClienteEntity clienteEntity = new ClienteEntity();
		
		clienteEntity.setId(java.util.UUID.randomUUID());
		clienteEntity.setTipoPessoa(TipoPessoa.PESSOA_JURIDICA);
		clienteEntity.setNome(generateRandomString(255));
		clienteEntity.setCnpjCPF(generateRandomString(255));
		clienteEntity.setDeleted(false);
		
		clienteEntity = em.persistAndFlush(clienteEntity);
		return clienteEntity;
	}
	
	
	protected ClienteLookupResult newClienteLookupResult(ClienteEntity clienteEntity) {
		ClienteLookupResult cliente = new ClienteLookupResult();
		
		cliente.setId(clienteEntity.getId());
		cliente.setNome(clienteEntity.getNome());
		
		return cliente;
	}
	
	
	protected FornecedorEntity newFornecedorEntity() {
		FornecedorEntity fornecedorEntity = new FornecedorEntity();
		
		fornecedorEntity.setId(java.util.UUID.randomUUID());
		fornecedorEntity.setTipoPessoa(TipoPessoa.PESSOA_JURIDICA);
		fornecedorEntity.setNome(generateRandomString(255));
		fornecedorEntity.setCnpjCPF(generateRandomString(255));
		fornecedorEntity.setDeleted(false);
		
		fornecedorEntity = em.persistAndFlush(fornecedorEntity);
		return fornecedorEntity;
	}
	
	
	protected FornecedorLookupResult newFornecedorLookupResult(FornecedorEntity fornecedorEntity) {
		FornecedorLookupResult fornecedor = new FornecedorLookupResult();
		
		fornecedor.setId(fornecedorEntity.getId());
		fornecedor.setNome(fornecedorEntity.getNome());
		
		return fornecedor;
	}
	// END TESTS DEPENDENCIES

}
