/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.CaixaDiarioSituacao;
import br.com.kerubin.api.financeiro.fluxocaixa.TestOperation;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import javax.inject.Inject;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaRepository;
import org.springframework.boot.test.mock.mockito.MockBean;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.mockito.ArgumentMatchers.any;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Collection;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaAutoComplete;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import br.com.kerubin.api.financeiro.fluxocaixa.FinanceiroFluxoCaixaBaseEntityTest;


@RunWith(SpringRunner.class)
public class CaixaDiarioServiceTest extends FinanceiroFluxoCaixaBaseEntityTest {
	
	private static final String[] IGNORED_FIELDS = { "id", "createdBy", "createdDate", "lastModifiedBy", "lastModifiedDate", "version" };
	
	@TestConfiguration
	static class CaixaDiarioServiceTestConfig {
		
		@Bean
		public CaixaDiarioListFilterPredicate caixaDiarioListFilterPredicate() {
			return new CaixaDiarioListFilterPredicateImpl();
		}
		
		@Bean
		public CaixaDiarioService caixaDiarioService() {
			return new CaixaDiarioServiceImpl();
		}
		
		@Bean
		public CaixaDiarioDTOConverter caixaDiarioDTOConverter() {
			return new CaixaDiarioDTOConverter();
		}
		
	}
	
	
	@Inject
	protected CaixaDiarioService caixaDiarioService;
	
	@Inject
	protected CaixaDiarioDTOConverter caixaDiarioDTOConverter;
	
	@Inject
	protected CaixaDiarioRepository caixaDiarioRepository;
	
	@Inject
	protected CaixaRepository caixaRepository;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testCreateWithAllFields() throws Exception {
		CaixaDiario caixaDiario = new CaixaDiario();
		
		caixaDiario.setId(java.util.UUID.randomUUID());
		
		CaixaEntity caixaEntityParam = newCaixaEntity();
		CaixaLookupResult caixa = newCaixaLookupResult(caixaEntityParam);
		caixaDiario.setCaixa(caixa);
		
		caixaDiario.setCaixaDiarioSituacao(CaixaDiarioSituacao.NAO_INICIADO);
		caixaDiario.setDataHoraAbertura(java.time.LocalDateTime.now());
		caixaDiario.setSaldoInicial(new java.math.BigDecimal("7396.17123"));
		caixaDiario.setDataHoraFechamento(java.time.LocalDateTime.now());
		caixaDiario.setSaldoFinal(new java.math.BigDecimal("5854.20154"));
		caixaDiario.setObservacoes(generateRandomString(1000));
		
		testVisitor.visit(this, "testCreateWithAllFields", caixaDiario, TestOperation.BEFORE);
		CaixaDiarioEntity caixaDiarioEntity = caixaDiarioService.create(caixaDiarioDTOConverter.convertDtoToEntity(caixaDiario));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testCreateWithAllFields", caixaDiarioEntity, TestOperation.AFTER);
		CaixaDiario actual = caixaDiarioDTOConverter.convertEntityToDto(caixaDiarioEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(caixaDiario, IGNORED_FIELDS);
		
		
		assertThat(actual.getCaixa().getId()).isNotNull();
		assertThat(actual.getCaixa()).isEqualToIgnoringGivenFields(caixaDiario.getCaixa(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testCreateWithOnlyRecairedFields() throws Exception {
		CaixaDiario caixaDiario = new CaixaDiario();
		
		caixaDiario.setId(java.util.UUID.randomUUID());
		
		CaixaEntity caixaEntityParam = newCaixaEntity();
		CaixaLookupResult caixa = newCaixaLookupResult(caixaEntityParam);
		caixaDiario.setCaixa(caixa);
		
		caixaDiario.setCaixaDiarioSituacao(CaixaDiarioSituacao.NAO_INICIADO);
		
		testVisitor.visit(this, "testCreateWithOnlyRecairedFields", caixaDiario, TestOperation.BEFORE);
		CaixaDiarioEntity caixaDiarioEntity = caixaDiarioService.create(caixaDiarioDTOConverter.convertDtoToEntity(caixaDiario));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testCreateWithOnlyRecairedFields", caixaDiarioEntity, TestOperation.AFTER);
		CaixaDiario actual = caixaDiarioDTOConverter.convertEntityToDto(caixaDiarioEntity);
		
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(caixaDiario, IGNORED_FIELDS);
		
		
		assertThat(actual.getCaixa().getId()).isNotNull();
		assertThat(actual.getCaixa()).isEqualToIgnoringGivenFields(caixaDiario.getCaixa(), IGNORED_FIELDS);
		
		
	}
	// END CREATE TESTS
	
	// BEGIN READ TESTS
	
	@Test
	public void testRead1() {
		CaixaDiarioEntity expectedCaixaDiarioEntity = newCaixaDiarioEntity();
		java.util.UUID id = expectedCaixaDiarioEntity.getId();
		CaixaDiario expected = caixaDiarioDTOConverter.convertEntityToDto(expectedCaixaDiarioEntity);
		CaixaDiarioEntity readCaixaDiarioEntity = caixaDiarioService.read(id);
		CaixaDiario actual = caixaDiarioDTOConverter.convertEntityToDto(readCaixaDiarioEntity);
		
		
		testVisitor.visit(this, "testRead1", expected, TestOperation.BEFORE);
		
		testVisitor.visit(this, "testRead1", actual, TestOperation.AFTER);
		assertThat(actual).isEqualToComparingFieldByField(expected);
		
	}
	// END READ TESTS
	
	// BEGIN UPDATE TESTS
	
	@Test
	public void testUpdateWithAllFields() throws Exception {
		CaixaDiarioEntity oldCaixaDiarioEntity = newCaixaDiarioEntity();
		java.util.UUID id = oldCaixaDiarioEntity.getId();
				
		CaixaDiario caixaDiario = new CaixaDiario();
		caixaDiario.setId(id);
		
		
		CaixaEntity caixaEntityParam = newCaixaEntity();
		CaixaLookupResult caixa = newCaixaLookupResult(caixaEntityParam);
		caixaDiario.setCaixa(caixa);
		
		caixaDiario.setCaixaDiarioSituacao(CaixaDiarioSituacao.NAO_INICIADO);
		caixaDiario.setDataHoraAbertura(java.time.LocalDateTime.now());
		caixaDiario.setSaldoInicial(new java.math.BigDecimal("20741.8156"));
		caixaDiario.setDataHoraFechamento(java.time.LocalDateTime.now());
		caixaDiario.setSaldoFinal(new java.math.BigDecimal("31052.32400"));
		caixaDiario.setObservacoes(generateRandomString(1000));
		
		testVisitor.visit(this, "testUpdateWithAllFields", caixaDiario, TestOperation.BEFORE);
		CaixaDiarioEntity caixaDiarioEntity = caixaDiarioService.update(id, caixaDiarioDTOConverter.convertDtoToEntity(caixaDiario));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testUpdateWithAllFields", caixaDiarioEntity, TestOperation.AFTER);
		
		CaixaDiario actual = caixaDiarioDTOConverter.convertEntityToDto(caixaDiarioEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(caixaDiario, IGNORED_FIELDS);
		
		
		assertThat(actual.getCaixa().getId()).isNotNull();
		assertThat(actual.getCaixa()).isEqualToIgnoringGivenFields(caixaDiario.getCaixa(), IGNORED_FIELDS);
		
		
	}
	
	@Test
	public void testUpdateWithOnlyRecairedFields() throws Exception {
		CaixaDiarioEntity oldCaixaDiarioEntity = newCaixaDiarioEntity();
		java.util.UUID id = oldCaixaDiarioEntity.getId();
				
		CaixaDiario caixaDiario = new CaixaDiario();
		caixaDiario.setId(id);
		
		
		CaixaEntity caixaEntityParam = newCaixaEntity();
		CaixaLookupResult caixa = newCaixaLookupResult(caixaEntityParam);
		caixaDiario.setCaixa(caixa);
		
		caixaDiario.setCaixaDiarioSituacao(CaixaDiarioSituacao.NAO_INICIADO);
		
		testVisitor.visit(this, "testUpdateWithOnlyRecairedFields", caixaDiario, TestOperation.BEFORE);
		CaixaDiarioEntity caixaDiarioEntity = caixaDiarioService.update(id, caixaDiarioDTOConverter.convertDtoToEntity(caixaDiario));
		em.flush();
		verify(publisher, times(0)).publish(any());
		
		testVisitor.visit(this, "testUpdateWithOnlyRecairedFields", caixaDiarioEntity, TestOperation.AFTER);
		
		CaixaDiario actual = caixaDiarioDTOConverter.convertEntityToDto(caixaDiarioEntity);
		
		assertThat(actual).isNotNull();
		assertThat(actual.getId()).isNotNull();
		assertThat(actual).isEqualToIgnoringGivenFields(caixaDiario, IGNORED_FIELDS);
		
		
		assertThat(actual.getCaixa().getId()).isNotNull();
		assertThat(actual.getCaixa()).isEqualToIgnoringGivenFields(caixaDiario.getCaixa(), IGNORED_FIELDS);
		
		
	}
	// END UPDATE TESTS
	
	// BEGIN DELETE TESTS
	
	@Test
	public void testDelete1() {
		CaixaDiarioEntity expected = newCaixaDiarioEntity();
		java.util.UUID id = expected.getId();
		
		
		expected = em.find(CaixaDiarioEntity.class, id);
		
		testVisitor.visit(this, "testDelete1", expected, TestOperation.BEFORE);
		assertThat(expected).isNotNull();
		caixaDiarioService.delete(id);
		verify(publisher, times(0)).publish(any());
		
		expected = em.find(CaixaDiarioEntity.class, id);
		
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
		
		testVisitor.visit(this, "testAutoComplete", caixaListFilter, TestOperation.BEFORE);
		// Mount the autocomplete query expression and call it.
		String query = caixaListFilter.get(0);
		Collection<CaixaDiarioAutoComplete> result = caixaDiarioService.autoComplete(query);
		
		testVisitor.visit(this, "testAutoComplete", result, TestOperation.AFTER);
		
		// Assert CaixaDiarioAutoComplete results.
		assertThat(result).isNotNull().hasSize(1)
		.extracting(it -> it.getCaixa().getNome())
		.containsExactlyInAnyOrderElementsOf(caixaListFilter);
	}
	
	// END Autocomplete TESTS
	
	
	// BEGIN Relationships Autocomplete TESTS
	
	@Test
	public void testCaixaDiarioCaixaAutoComplete() {
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
		String query = nomeListFilter.get(0);
		
		
		testVisitor.visit(this, "testCaixaDiarioCaixaAutoComplete", query, TestOperation.BEFORE);
		Collection<CaixaAutoComplete> result = caixaDiarioService.caixaCaixaAutoComplete(query);
		
		testVisitor.visit(this, "testCaixaDiarioCaixaAutoComplete", result, TestOperation.AFTER);
		
		assertThat(result).isNotNull().hasSize(1)
		.extracting(CaixaAutoComplete::getNome)
		.containsExactlyInAnyOrderElementsOf(nomeListFilter);
	}
	
	// END Relationships Autocomplete TESTS
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN tests for Sum Fields
	// END tests for Sum Fields
	
	// BEGIN TESTS DEPENDENCIES
	
	
	protected CaixaDiarioEntity newCaixaDiarioEntity() {
		CaixaDiarioEntity caixaDiarioEntity = new CaixaDiarioEntity();
		
		caixaDiarioEntity.setCaixa(newCaixaEntity());
		caixaDiarioEntity.setCaixaDiarioSituacao(CaixaDiarioSituacao.NAO_INICIADO);
		caixaDiarioEntity.setDataHoraAbertura(java.time.LocalDateTime.now());
		caixaDiarioEntity.setSaldoInicial(new java.math.BigDecimal("20968.21452"));
		caixaDiarioEntity.setDataHoraFechamento(java.time.LocalDateTime.now());
		caixaDiarioEntity.setSaldoFinal(new java.math.BigDecimal("26061.29842"));
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
		caixaEntity.setSaldo(new java.math.BigDecimal("6899.4838"));
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
