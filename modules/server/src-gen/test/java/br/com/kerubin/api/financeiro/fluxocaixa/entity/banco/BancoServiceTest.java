/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T08:31:13.792
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.banco;

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
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Collection;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.kerubin.api.financeiro.fluxocaixa.FinanceiroFluxoCaixaBaseEntityTest;


@RunWith(SpringRunner.class)
public class BancoServiceTest extends FinanceiroFluxoCaixaBaseEntityTest {
	
	private static final String[] IGNORED_FIELDS = { "id" };
	
	@TestConfiguration
	static class BancoServiceTestConfig {
		
		@Bean
		public BancoListFilterPredicate bancoListFilterPredicate() {
			return new BancoListFilterPredicateImpl();
		}
		
		@Bean
		public BancoService bancoService() {
			return new BancoServiceImpl();
		}
		
		@Bean
		public BancoDTOConverter bancoDTOConverter() {
			return new BancoDTOConverter();
		}
		
	}
	
	
	@Inject
	protected BancoService bancoService;
	
	@Inject
	protected BancoDTOConverter bancoDTOConverter;
	
	@Inject
	protected BancoRepository bancoRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testCreateWithAllFields() throws Exception {
		Banco banco = new Banco();
		
		banco.setId(java.util.UUID.randomUUID());
		banco.setNumero(generateRandomString(20));
		banco.setNome(generateRandomString(255));
		banco.setDeleted(false);
		
		testVisitor.visit(this, "testCreateWithAllFields", banco, TestOperation.BEFORE);
		BancoEntity bancoEntity = bancoService.create(bancoDTOConverter.convertDtoToEntity(banco));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testCreateWithAllFields", bancoEntity, TestOperation.AFTER);
		Banco actual = bancoDTOConverter.convertEntityToDto(bancoEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(banco, IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testCreateWithOnlyRecairedFields() throws Exception {
		Banco banco = new Banco();
		
		banco.setId(java.util.UUID.randomUUID());
		banco.setNumero(generateRandomString(20));
		banco.setNome(generateRandomString(255));
		
		testVisitor.visit(this, "testCreateWithOnlyRecairedFields", banco, TestOperation.BEFORE);
		BancoEntity bancoEntity = bancoService.create(bancoDTOConverter.convertDtoToEntity(banco));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testCreateWithOnlyRecairedFields", bancoEntity, TestOperation.AFTER);
		Banco actual = bancoDTOConverter.convertEntityToDto(bancoEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(banco, IGNORED_FIELDS);
		
		
	}
	// END CREATE TESTS
	
	// BEGIN READ TESTS
	
	@Test
	public void testRead1() {
		BancoEntity expectedBancoEntity = newBancoEntity();
		java.util.UUID id = expectedBancoEntity.getId();
		Banco expected = bancoDTOConverter.convertEntityToDto(expectedBancoEntity);
		BancoEntity readBancoEntity = bancoService.read(id);
		Banco actual = bancoDTOConverter.convertEntityToDto(readBancoEntity);
		
		
		testVisitor.visit(this, "testRead1", expected, TestOperation.BEFORE);
		
		testVisitor.visit(this, "testRead1", actual, TestOperation.AFTER);
		assertThat(actual).isEqualToComparingFieldByField(expected);
		
	}
	// END READ TESTS
	
	// BEGIN UPDATE TESTS
	
	@Test
	public void testUpdateWithAllFields() throws Exception {
		BancoEntity oldBancoEntity = newBancoEntity();
		java.util.UUID id = oldBancoEntity.getId();
				
		Banco banco = new Banco();
		banco.setId(id);
		
		banco.setNumero(generateRandomString(20));
		banco.setNome(generateRandomString(255));
		banco.setDeleted(false);
		
		testVisitor.visit(this, "testUpdateWithAllFields", banco, TestOperation.BEFORE);
		BancoEntity bancoEntity = bancoService.update(id, bancoDTOConverter.convertDtoToEntity(banco));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testUpdateWithAllFields", bancoEntity, TestOperation.AFTER);
		
		Banco actual = bancoDTOConverter.convertEntityToDto(bancoEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(banco, IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testUpdateWithOnlyRecairedFields() throws Exception {
		BancoEntity oldBancoEntity = newBancoEntity();
		java.util.UUID id = oldBancoEntity.getId();
				
		Banco banco = new Banco();
		banco.setId(id);
		
		banco.setNumero(generateRandomString(20));
		banco.setNome(generateRandomString(255));
		
		testVisitor.visit(this, "testUpdateWithOnlyRecairedFields", banco, TestOperation.BEFORE);
		BancoEntity bancoEntity = bancoService.update(id, bancoDTOConverter.convertDtoToEntity(banco));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testUpdateWithOnlyRecairedFields", bancoEntity, TestOperation.AFTER);
		
		Banco actual = bancoDTOConverter.convertEntityToDto(bancoEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(banco, IGNORED_FIELDS);
		
		
	}
	// END UPDATE TESTS
	
	// BEGIN DELETE TESTS
	
	@Test
	public void testDelete1() {
		BancoEntity expected = newBancoEntity();
		java.util.UUID id = expected.getId();
		
		
		expected = em.find(BancoEntity.class, id);
		
		testVisitor.visit(this, "testDelete1", expected, TestOperation.BEFORE);
		assertThat(expected).isNotNull();
		bancoService.delete(id);
		verify(publisher, times(0)).publish(any());
		
		expected = em.find(BancoEntity.class, id);
		
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
					
		// Generate 33 records of data for BancoEntity for this test.
		List<BancoEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newBancoEntity());
		}
		
		// Check if 33 records of BancoEntity was generated.
		long count = bancoRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of BancoEntity randomly from testData.
		final int resultSize = 1;
		List<BancoEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only BancoEntity.numero field and configure this list as a filter.
		List<String> numeroListFilter = filterTestData.stream().map(BancoEntity::getNumero).collect(Collectors.toList());
		
		testVisitor.visit(this, "testAutoComplete", numeroListFilter, TestOperation.BEFORE);
		// Mount the autocomplete query expression and call it.
		String query = numeroListFilter.get(0);
		Collection<BancoAutoComplete> result = bancoService.autoComplete(query);
		
		testVisitor.visit(this, "testAutoComplete", result, TestOperation.AFTER);
		
		// Assert BancoAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(BancoAutoComplete::getNumero)
		.containsExactlyInAnyOrderElementsOf(numeroListFilter);
	}
	
	// END Autocomplete TESTS
	
	
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN TESTS DEPENDENCIES
	
	
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
