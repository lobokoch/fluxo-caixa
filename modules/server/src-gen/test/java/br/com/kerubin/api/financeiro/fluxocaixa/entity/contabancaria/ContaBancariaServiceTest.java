/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T08:31:13.792
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.agenciabancaria.AgenciaBancariaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.agenciabancaria.AgenciaBancariaLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoContaBancaria;
import br.com.kerubin.api.financeiro.fluxocaixa.TestOperation;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import javax.inject.Inject;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.agenciabancaria.AgenciaBancariaRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.banco.BancoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.banco.BancoLookupResult;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Collection;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.agenciabancaria.AgenciaBancariaAutoComplete;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.kerubin.api.financeiro.fluxocaixa.FinanceiroFluxoCaixaBaseEntityTest;


@RunWith(SpringRunner.class)
public class ContaBancariaServiceTest extends FinanceiroFluxoCaixaBaseEntityTest {
	
	private static final String[] IGNORED_FIELDS = { "id" };
	
	@TestConfiguration
	static class ContaBancariaServiceTestConfig {
		
		@Bean
		public ContaBancariaListFilterPredicate contaBancariaListFilterPredicate() {
			return new ContaBancariaListFilterPredicateImpl();
		}
		
		@Bean
		public ContaBancariaService contaBancariaService() {
			return new ContaBancariaServiceImpl();
		}
		
		@Bean
		public ContaBancariaDTOConverter contaBancariaDTOConverter() {
			return new ContaBancariaDTOConverter();
		}
		
	}
	
	
	@Inject
	protected ContaBancariaService contaBancariaService;
	
	@Inject
	protected ContaBancariaDTOConverter contaBancariaDTOConverter;
	
	@Inject
	protected ContaBancariaRepository contaBancariaRepository;
	
	@Inject
	protected AgenciaBancariaRepository agenciaBancariaRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testCreateWithAllFields() throws Exception {
		ContaBancaria contaBancaria = new ContaBancaria();
		
		contaBancaria.setId(java.util.UUID.randomUUID());
		contaBancaria.setNomeTitular(generateRandomString(255));
		
		AgenciaBancariaEntity agenciaBancariaEntityParam = newAgenciaBancariaEntity();
		AgenciaBancariaLookupResult agencia = newAgenciaBancariaLookupResult(agenciaBancariaEntityParam);
		contaBancaria.setAgencia(agencia);
		
		contaBancaria.setTipoContaBancaria(TipoContaBancaria.CONTA_CORRENTE);
		contaBancaria.setNumeroConta(generateRandomString(30));
		contaBancaria.setDigito(generateRandomString(10));
		contaBancaria.setDataValidade(getNextDate());
		contaBancaria.setAtivo(true);
		contaBancaria.setDeleted(false);
		
		testVisitor.visit(this, "testCreateWithAllFields", contaBancaria, TestOperation.BEFORE);
		ContaBancariaEntity contaBancariaEntity = contaBancariaService.create(contaBancariaDTOConverter.convertDtoToEntity(contaBancaria));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testCreateWithAllFields", contaBancariaEntity, TestOperation.AFTER);
		ContaBancaria actual = contaBancariaDTOConverter.convertEntityToDto(contaBancariaEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(contaBancaria, IGNORED_FIELDS);
		
		
		assertThat(actual.getAgencia().getId()).isNotNull();
		assertThat(actual.getAgencia()).isEqualToIgnoringGivenFields(contaBancaria.getAgencia(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testCreateWithOnlyRecairedFields() throws Exception {
		ContaBancaria contaBancaria = new ContaBancaria();
		
		contaBancaria.setId(java.util.UUID.randomUUID());
		contaBancaria.setNomeTitular(generateRandomString(255));
		
		AgenciaBancariaEntity agenciaBancariaEntityParam = newAgenciaBancariaEntity();
		AgenciaBancariaLookupResult agencia = newAgenciaBancariaLookupResult(agenciaBancariaEntityParam);
		contaBancaria.setAgencia(agencia);
		
		contaBancaria.setTipoContaBancaria(TipoContaBancaria.CONTA_CORRENTE);
		contaBancaria.setNumeroConta(generateRandomString(30));
		contaBancaria.setAtivo(true);
		
		testVisitor.visit(this, "testCreateWithOnlyRecairedFields", contaBancaria, TestOperation.BEFORE);
		ContaBancariaEntity contaBancariaEntity = contaBancariaService.create(contaBancariaDTOConverter.convertDtoToEntity(contaBancaria));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testCreateWithOnlyRecairedFields", contaBancariaEntity, TestOperation.AFTER);
		ContaBancaria actual = contaBancariaDTOConverter.convertEntityToDto(contaBancariaEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(contaBancaria, IGNORED_FIELDS);
		
		
		assertThat(actual.getAgencia().getId()).isNotNull();
		assertThat(actual.getAgencia()).isEqualToIgnoringGivenFields(contaBancaria.getAgencia(), IGNORED_FIELDS);
		
		
	}
	// END CREATE TESTS
	
	// BEGIN READ TESTS
	
	@Test
	public void testRead1() {
		ContaBancariaEntity expectedContaBancariaEntity = newContaBancariaEntity();
		java.util.UUID id = expectedContaBancariaEntity.getId();
		ContaBancaria expected = contaBancariaDTOConverter.convertEntityToDto(expectedContaBancariaEntity);
		ContaBancariaEntity readContaBancariaEntity = contaBancariaService.read(id);
		ContaBancaria actual = contaBancariaDTOConverter.convertEntityToDto(readContaBancariaEntity);
		
		
		testVisitor.visit(this, "testRead1", expected, TestOperation.BEFORE);
		
		testVisitor.visit(this, "testRead1", actual, TestOperation.AFTER);
		assertThat(actual).isEqualToComparingFieldByField(expected);
		
	}
	// END READ TESTS
	
	// BEGIN UPDATE TESTS
	
	@Test
	public void testUpdateWithAllFields() throws Exception {
		ContaBancariaEntity oldContaBancariaEntity = newContaBancariaEntity();
		java.util.UUID id = oldContaBancariaEntity.getId();
				
		ContaBancaria contaBancaria = new ContaBancaria();
		contaBancaria.setId(id);
		
		contaBancaria.setNomeTitular(generateRandomString(255));
		
		AgenciaBancariaEntity agenciaBancariaEntityParam = newAgenciaBancariaEntity();
		AgenciaBancariaLookupResult agencia = newAgenciaBancariaLookupResult(agenciaBancariaEntityParam);
		contaBancaria.setAgencia(agencia);
		
		contaBancaria.setTipoContaBancaria(TipoContaBancaria.CONTA_CORRENTE);
		contaBancaria.setNumeroConta(generateRandomString(30));
		contaBancaria.setDigito(generateRandomString(10));
		contaBancaria.setDataValidade(getNextDate());
		contaBancaria.setAtivo(true);
		contaBancaria.setDeleted(false);
		
		testVisitor.visit(this, "testUpdateWithAllFields", contaBancaria, TestOperation.BEFORE);
		ContaBancariaEntity contaBancariaEntity = contaBancariaService.update(id, contaBancariaDTOConverter.convertDtoToEntity(contaBancaria));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testUpdateWithAllFields", contaBancariaEntity, TestOperation.AFTER);
		
		ContaBancaria actual = contaBancariaDTOConverter.convertEntityToDto(contaBancariaEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(contaBancaria, IGNORED_FIELDS);
		
		
		assertThat(actual.getAgencia().getId()).isNotNull();
		assertThat(actual.getAgencia()).isEqualToIgnoringGivenFields(contaBancaria.getAgencia(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testUpdateWithOnlyRecairedFields() throws Exception {
		ContaBancariaEntity oldContaBancariaEntity = newContaBancariaEntity();
		java.util.UUID id = oldContaBancariaEntity.getId();
				
		ContaBancaria contaBancaria = new ContaBancaria();
		contaBancaria.setId(id);
		
		contaBancaria.setNomeTitular(generateRandomString(255));
		
		AgenciaBancariaEntity agenciaBancariaEntityParam = newAgenciaBancariaEntity();
		AgenciaBancariaLookupResult agencia = newAgenciaBancariaLookupResult(agenciaBancariaEntityParam);
		contaBancaria.setAgencia(agencia);
		
		contaBancaria.setTipoContaBancaria(TipoContaBancaria.CONTA_CORRENTE);
		contaBancaria.setNumeroConta(generateRandomString(30));
		contaBancaria.setAtivo(true);
		
		testVisitor.visit(this, "testUpdateWithOnlyRecairedFields", contaBancaria, TestOperation.BEFORE);
		ContaBancariaEntity contaBancariaEntity = contaBancariaService.update(id, contaBancariaDTOConverter.convertDtoToEntity(contaBancaria));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testUpdateWithOnlyRecairedFields", contaBancariaEntity, TestOperation.AFTER);
		
		ContaBancaria actual = contaBancariaDTOConverter.convertEntityToDto(contaBancariaEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(contaBancaria, IGNORED_FIELDS);
		
		
		assertThat(actual.getAgencia().getId()).isNotNull();
		assertThat(actual.getAgencia()).isEqualToIgnoringGivenFields(contaBancaria.getAgencia(), IGNORED_FIELDS);
		
		
	}
	// END UPDATE TESTS
	
	// BEGIN DELETE TESTS
	
	@Test
	public void testDelete1() {
		ContaBancariaEntity expected = newContaBancariaEntity();
		java.util.UUID id = expected.getId();
		
		
		expected = em.find(ContaBancariaEntity.class, id);
		
		testVisitor.visit(this, "testDelete1", expected, TestOperation.BEFORE);
		assertThat(expected).isNotNull();
		contaBancariaService.delete(id);
		verify(publisher, times(0)).publish(any());
		
		expected = em.find(ContaBancariaEntity.class, id);
		
		testVisitor.visit(this, "testDelete1", expected, TestOperation.AFTER);
		assertThat(expected).isNull();
	}
	// END DELETE TESTS
	
	// BEGIN LIST TESTS
	// END LIST TESTS
	
	// BEGIN Autocomplete TESTS
	@Test
	public void testAutoComplete() {
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
		
		testVisitor.visit(this, "testAutoComplete", nomeTitularListFilter, TestOperation.BEFORE);
		// Mount the autocomplete query expression and call it.
		String query = nomeTitularListFilter.get(0);
		Collection<ContaBancariaAutoComplete> result = contaBancariaService.autoComplete(query);
		
		testVisitor.visit(this, "testAutoComplete", result, TestOperation.AFTER);
		
		// Assert ContaBancariaAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(ContaBancariaAutoComplete::getNomeTitular)
		.containsExactlyInAnyOrderElementsOf(nomeTitularListFilter);
	}
	
	// END Autocomplete TESTS
	
	
	// BEGIN Relationships Autocomplete TESTS
	
	@Test
	public void testContaBancariaAgenciaAutoComplete() {
		// Reset lastDate field to start LocalDate fields with today in this test. 
		resetNextDate();
					
		// Generate 33 records of data for AgenciaBancariaEntity for this test.
		List<AgenciaBancariaEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newAgenciaBancariaEntity());
		}
		
		// Check if 33 records of AgenciaBancariaEntity was generated.
		long count = agenciaBancariaRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of AgenciaBancariaEntity randomly from testData.
		final int resultSize = 1;
		List<AgenciaBancariaEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only AgenciaBancariaEntity.numeroAgencia field and configure this list as a filter.
		List<String> numeroAgenciaListFilter = filterTestData.stream().map(AgenciaBancariaEntity::getNumeroAgencia).collect(Collectors.toList());
		String query = numeroAgenciaListFilter.get(0);
		
		
		testVisitor.visit(this, "testContaBancariaAgenciaAutoComplete", query, TestOperation.BEFORE);
		Collection<AgenciaBancariaAutoComplete> result = contaBancariaService.agenciaBancariaAgenciaAutoComplete(query);
		
		testVisitor.visit(this, "testContaBancariaAgenciaAutoComplete", result, TestOperation.AFTER);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(AgenciaBancariaAutoComplete::getNumeroAgencia)
		.containsExactlyInAnyOrderElementsOf(numeroAgenciaListFilter);
	}
	
	// END Relationships Autocomplete TESTS
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN TESTS DEPENDENCIES
	
	
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
	// END TESTS DEPENDENCIES

}
