/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa;

import br.com.kerubin.api.financeiro.fluxocaixa.TestOperation;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import javax.inject.Inject;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Collection;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.kerubin.api.financeiro.fluxocaixa.FinanceiroFluxoCaixaBaseEntityTest;


@RunWith(SpringRunner.class)
public class CaixaServiceTest extends FinanceiroFluxoCaixaBaseEntityTest {
	
	private static final String[] IGNORED_FIELDS = { "id", "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate", "version" };
	
	@TestConfiguration
	static class CaixaServiceTestConfig {
		
		@Bean
		public CaixaListFilterPredicate caixaListFilterPredicate() {
			return new CaixaListFilterPredicateImpl();
		}
		
		@Bean
		public CaixaService caixaService() {
			return new CaixaServiceImpl();
		}
		
		@Bean
		public CaixaDTOConverter caixaDTOConverter() {
			return new CaixaDTOConverter();
		}
		
	}
	
	
	@Inject
	protected CaixaService caixaService;
	
	@Inject
	protected CaixaDTOConverter caixaDTOConverter;
	
	@Inject
	protected CaixaRepository caixaRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testCreateWithAllFields() throws Exception {
		Caixa caixa = new Caixa();
		
		caixa.setId(java.util.UUID.randomUUID());
		caixa.setNome(generateRandomString(255));
		caixa.setAtivo(true);
		caixa.setSaldo(new java.math.BigDecimal("4607.4823"));
		caixa.setObservacoes(generateRandomString(255));
		
		testVisitor.visit(this, "testCreateWithAllFields", caixa, TestOperation.BEFORE);
		CaixaEntity caixaEntity = caixaService.create(caixaDTOConverter.convertDtoToEntity(caixa));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testCreateWithAllFields", caixaEntity, TestOperation.AFTER);
		Caixa actual = caixaDTOConverter.convertEntityToDto(caixaEntity);
		
		// Begin applying RuleFormOnCreate 
		caixa.setSaldo(new BigDecimal(0.0));
		// End applying RuleFormOnCreate 
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(caixa, IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testCreateWithOnlyRecairedFields() throws Exception {
		Caixa caixa = new Caixa();
		
		caixa.setId(java.util.UUID.randomUUID());
		caixa.setNome(generateRandomString(255));
		caixa.setAtivo(true);
		
		testVisitor.visit(this, "testCreateWithOnlyRecairedFields", caixa, TestOperation.BEFORE);
		CaixaEntity caixaEntity = caixaService.create(caixaDTOConverter.convertDtoToEntity(caixa));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testCreateWithOnlyRecairedFields", caixaEntity, TestOperation.AFTER);
		Caixa actual = caixaDTOConverter.convertEntityToDto(caixaEntity);
		
		// Begin applying RuleFormOnCreate 
		caixa.setSaldo(new BigDecimal(0.0));
		// End applying RuleFormOnCreate 
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(caixa, IGNORED_FIELDS);
		
		
	}
	// END CREATE TESTS
	
	// BEGIN READ TESTS
	
	@Test
	public void testRead1() {
		CaixaEntity expectedCaixaEntity = newCaixaEntity();
		java.util.UUID id = expectedCaixaEntity.getId();
		Caixa expected = caixaDTOConverter.convertEntityToDto(expectedCaixaEntity);
		CaixaEntity readCaixaEntity = caixaService.read(id);
		Caixa actual = caixaDTOConverter.convertEntityToDto(readCaixaEntity);
		
		
		testVisitor.visit(this, "testRead1", expected, TestOperation.BEFORE);
		
		testVisitor.visit(this, "testRead1", actual, TestOperation.AFTER);
		assertThat(actual).isEqualToComparingFieldByField(expected);
		
	}
	// END READ TESTS
	
	// BEGIN UPDATE TESTS
	
	@Test
	public void testUpdateWithAllFields() throws Exception {
		CaixaEntity oldCaixaEntity = newCaixaEntity();
		java.util.UUID id = oldCaixaEntity.getId();
				
		Caixa caixa = new Caixa();
		caixa.setId(id);
		
		caixa.setNome(generateRandomString(255));
		caixa.setAtivo(true);
		caixa.setSaldo(new java.math.BigDecimal("16827.23100"));
		caixa.setObservacoes(generateRandomString(255));
		
		testVisitor.visit(this, "testUpdateWithAllFields", caixa, TestOperation.BEFORE);
		CaixaEntity caixaEntity = caixaService.update(id, caixaDTOConverter.convertDtoToEntity(caixa));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testUpdateWithAllFields", caixaEntity, TestOperation.AFTER);
		
		Caixa actual = caixaDTOConverter.convertEntityToDto(caixaEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(caixa, IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testUpdateWithOnlyRecairedFields() throws Exception {
		CaixaEntity oldCaixaEntity = newCaixaEntity();
		java.util.UUID id = oldCaixaEntity.getId();
				
		Caixa caixa = new Caixa();
		caixa.setId(id);
		
		caixa.setNome(generateRandomString(255));
		caixa.setAtivo(true);
		
		testVisitor.visit(this, "testUpdateWithOnlyRecairedFields", caixa, TestOperation.BEFORE);
		CaixaEntity caixaEntity = caixaService.update(id, caixaDTOConverter.convertDtoToEntity(caixa));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testUpdateWithOnlyRecairedFields", caixaEntity, TestOperation.AFTER);
		
		Caixa actual = caixaDTOConverter.convertEntityToDto(caixaEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(caixa, IGNORED_FIELDS);
		
		
	}
	// END UPDATE TESTS
	
	// BEGIN DELETE TESTS
	
	@Test
	public void testDelete1() {
		CaixaEntity expected = newCaixaEntity();
		java.util.UUID id = expected.getId();
		
		
		expected = em.find(CaixaEntity.class, id);
		
		testVisitor.visit(this, "testDelete1", expected, TestOperation.BEFORE);
		assertThat(expected).isNotNull();
		caixaService.delete(id);
		verify(publisher, times(0)).publish(any());
		
		expected = em.find(CaixaEntity.class, id);
		
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
					
		// Generate 33 records of data for CaixaEntity for this test.
		List<CaixaEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newCaixaEntity());
		}
		
		// Check if 33 records of CaixaEntity was generated.
		long count = caixaRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of CaixaEntity randomly from testData.
		final int resultSize = 1;
		List<CaixaEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only CaixaEntity.nome field and configure this list as a filter.
		List<String> nomeListFilter = filterTestData.stream().map(CaixaEntity::getNome).collect(Collectors.toList());
		
		testVisitor.visit(this, "testAutoComplete", nomeListFilter, TestOperation.BEFORE);
		// Mount the autocomplete query expression and call it.
		String query = nomeListFilter.get(0);
		Collection<CaixaAutoComplete> result = caixaService.autoComplete(query);
		
		testVisitor.visit(this, "testAutoComplete", result, TestOperation.AFTER);
		
		// Assert CaixaAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(CaixaAutoComplete::getNome)
		.containsExactlyInAnyOrderElementsOf(nomeListFilter);
	}
	
	// END Autocomplete TESTS
	
	
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN TESTS DEPENDENCIES
	
	
	protected CaixaEntity newCaixaEntity() {
		CaixaEntity caixaEntity = new CaixaEntity();
		
		caixaEntity.setNome(generateRandomString(255));
		caixaEntity.setAtivo(true);
		caixaEntity.setSaldo(new java.math.BigDecimal("7173.2747"));
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
	// END TESTS DEPENDENCIES

}
