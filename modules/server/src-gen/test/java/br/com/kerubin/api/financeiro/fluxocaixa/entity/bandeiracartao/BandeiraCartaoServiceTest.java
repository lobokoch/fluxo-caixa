/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-22T08:31:13.792
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.bandeiracartao;

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
public class BandeiraCartaoServiceTest extends FinanceiroFluxoCaixaBaseEntityTest {
	
	private static final String[] IGNORED_FIELDS = { "id" };
	
	@TestConfiguration
	static class BandeiraCartaoServiceTestConfig {
		
		@Bean
		public BandeiraCartaoListFilterPredicate bandeiraCartaoListFilterPredicate() {
			return new BandeiraCartaoListFilterPredicateImpl();
		}
		
		@Bean
		public BandeiraCartaoService bandeiraCartaoService() {
			return new BandeiraCartaoServiceImpl();
		}
		
		@Bean
		public BandeiraCartaoDTOConverter bandeiraCartaoDTOConverter() {
			return new BandeiraCartaoDTOConverter();
		}
		
	}
	
	
	@Inject
	protected BandeiraCartaoService bandeiraCartaoService;
	
	@Inject
	protected BandeiraCartaoDTOConverter bandeiraCartaoDTOConverter;
	
	@Inject
	protected BandeiraCartaoRepository bandeiraCartaoRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testCreateWithAllFields() throws Exception {
		BandeiraCartao bandeiraCartao = new BandeiraCartao();
		
		bandeiraCartao.setId(java.util.UUID.randomUUID());
		bandeiraCartao.setNomeBandeira(generateRandomString(255));
		bandeiraCartao.setDeleted(false);
		
		testVisitor.visit(this, "testCreateWithAllFields", bandeiraCartao, TestOperation.BEFORE);
		BandeiraCartaoEntity bandeiraCartaoEntity = bandeiraCartaoService.create(bandeiraCartaoDTOConverter.convertDtoToEntity(bandeiraCartao));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testCreateWithAllFields", bandeiraCartaoEntity, TestOperation.AFTER);
		BandeiraCartao actual = bandeiraCartaoDTOConverter.convertEntityToDto(bandeiraCartaoEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(bandeiraCartao, IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testCreateWithOnlyRecairedFields() throws Exception {
		BandeiraCartao bandeiraCartao = new BandeiraCartao();
		
		bandeiraCartao.setId(java.util.UUID.randomUUID());
		bandeiraCartao.setNomeBandeira(generateRandomString(255));
		
		testVisitor.visit(this, "testCreateWithOnlyRecairedFields", bandeiraCartao, TestOperation.BEFORE);
		BandeiraCartaoEntity bandeiraCartaoEntity = bandeiraCartaoService.create(bandeiraCartaoDTOConverter.convertDtoToEntity(bandeiraCartao));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testCreateWithOnlyRecairedFields", bandeiraCartaoEntity, TestOperation.AFTER);
		BandeiraCartao actual = bandeiraCartaoDTOConverter.convertEntityToDto(bandeiraCartaoEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(bandeiraCartao, IGNORED_FIELDS);
		
		
	}
	// END CREATE TESTS
	
	// BEGIN READ TESTS
	
	@Test
	public void testRead1() {
		BandeiraCartaoEntity expectedBandeiraCartaoEntity = newBandeiraCartaoEntity();
		java.util.UUID id = expectedBandeiraCartaoEntity.getId();
		BandeiraCartao expected = bandeiraCartaoDTOConverter.convertEntityToDto(expectedBandeiraCartaoEntity);
		BandeiraCartaoEntity readBandeiraCartaoEntity = bandeiraCartaoService.read(id);
		BandeiraCartao actual = bandeiraCartaoDTOConverter.convertEntityToDto(readBandeiraCartaoEntity);
		
		
		testVisitor.visit(this, "testRead1", expected, TestOperation.BEFORE);
		
		testVisitor.visit(this, "testRead1", actual, TestOperation.AFTER);
		assertThat(actual).isEqualToComparingFieldByField(expected);
		
	}
	// END READ TESTS
	
	// BEGIN UPDATE TESTS
	
	@Test
	public void testUpdateWithAllFields() throws Exception {
		BandeiraCartaoEntity oldBandeiraCartaoEntity = newBandeiraCartaoEntity();
		java.util.UUID id = oldBandeiraCartaoEntity.getId();
				
		BandeiraCartao bandeiraCartao = new BandeiraCartao();
		bandeiraCartao.setId(id);
		
		bandeiraCartao.setNomeBandeira(generateRandomString(255));
		bandeiraCartao.setDeleted(false);
		
		testVisitor.visit(this, "testUpdateWithAllFields", bandeiraCartao, TestOperation.BEFORE);
		BandeiraCartaoEntity bandeiraCartaoEntity = bandeiraCartaoService.update(id, bandeiraCartaoDTOConverter.convertDtoToEntity(bandeiraCartao));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testUpdateWithAllFields", bandeiraCartaoEntity, TestOperation.AFTER);
		
		BandeiraCartao actual = bandeiraCartaoDTOConverter.convertEntityToDto(bandeiraCartaoEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(bandeiraCartao, IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testUpdateWithOnlyRecairedFields() throws Exception {
		BandeiraCartaoEntity oldBandeiraCartaoEntity = newBandeiraCartaoEntity();
		java.util.UUID id = oldBandeiraCartaoEntity.getId();
				
		BandeiraCartao bandeiraCartao = new BandeiraCartao();
		bandeiraCartao.setId(id);
		
		bandeiraCartao.setNomeBandeira(generateRandomString(255));
		
		testVisitor.visit(this, "testUpdateWithOnlyRecairedFields", bandeiraCartao, TestOperation.BEFORE);
		BandeiraCartaoEntity bandeiraCartaoEntity = bandeiraCartaoService.update(id, bandeiraCartaoDTOConverter.convertDtoToEntity(bandeiraCartao));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testUpdateWithOnlyRecairedFields", bandeiraCartaoEntity, TestOperation.AFTER);
		
		BandeiraCartao actual = bandeiraCartaoDTOConverter.convertEntityToDto(bandeiraCartaoEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(bandeiraCartao, IGNORED_FIELDS);
		
		
	}
	// END UPDATE TESTS
	
	// BEGIN DELETE TESTS
	
	@Test
	public void testDelete1() {
		BandeiraCartaoEntity expected = newBandeiraCartaoEntity();
		java.util.UUID id = expected.getId();
		
		
		expected = em.find(BandeiraCartaoEntity.class, id);
		
		testVisitor.visit(this, "testDelete1", expected, TestOperation.BEFORE);
		assertThat(expected).isNotNull();
		bandeiraCartaoService.delete(id);
		verify(publisher, times(0)).publish(any());
		
		expected = em.find(BandeiraCartaoEntity.class, id);
		
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
					
		// Generate 33 records of data for BandeiraCartaoEntity for this test.
		List<BandeiraCartaoEntity> testData = new ArrayList<>();
		final int lastRecord = 33;
		final int firstRecord = 1;
		for (int i = firstRecord; i <= lastRecord; i++) {
			testData.add(newBandeiraCartaoEntity());
		}
		
		// Check if 33 records of BandeiraCartaoEntity was generated.
		long count = bandeiraCartaoRepository.count();
		assertThat(count).isEqualTo(lastRecord);
		
		// Extracts 1 records of BandeiraCartaoEntity randomly from testData.
		final int resultSize = 1;
		List<BandeiraCartaoEntity> filterTestData = getRandomItemsOf(testData, resultSize);
		
		// Extracts a list with only BandeiraCartaoEntity.nomeBandeira field and configure this list as a filter.
		List<String> nomeBandeiraListFilter = filterTestData.stream().map(BandeiraCartaoEntity::getNomeBandeira).collect(Collectors.toList());
		
		testVisitor.visit(this, "testAutoComplete", nomeBandeiraListFilter, TestOperation.BEFORE);
		// Mount the autocomplete query expression and call it.
		String query = nomeBandeiraListFilter.get(0);
		Collection<BandeiraCartaoAutoComplete> result = bandeiraCartaoService.autoComplete(query);
		
		testVisitor.visit(this, "testAutoComplete", result, TestOperation.AFTER);
		
		// Assert BandeiraCartaoAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(BandeiraCartaoAutoComplete::getNomeBandeira)
		.containsExactlyInAnyOrderElementsOf(nomeBandeiraListFilter);
	}
	
	// END Autocomplete TESTS
	
	
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN TESTS DEPENDENCIES
	
	
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
	// END TESTS DEPENDENCIES

}
