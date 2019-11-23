/**********************************************************************************************
Code generated with MKL Plug-in version: 27.0.12
Code generated at time stamp: 2019-11-06T06:23:00.711
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.conciliacaobancaria;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.kerubin.api.financeiro.fluxocaixa.CaixaDiarioSituacao;
import br.com.kerubin.api.financeiro.fluxocaixa.FormaPagamento;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoContaBancaria;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoFonteMovimento;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoLancamentoFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoPessoa;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoPlanoContaFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoReceitaDespesa;
import br.com.kerubin.api.financeiro.fluxocaixa.core.CaixaGeral;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.agenciabancaria.AgenciaBancariaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.agenciabancaria.AgenciaBancariaLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.banco.BancoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.banco.BancoLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.bandeiracartao.BandeiraCartaoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.bandeiracartao.BandeiraCartaoLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioListFilterPredicate;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioListFilterPredicateImpl;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioService;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoDTOConverter;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoListFilterPredicate;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoListFilterPredicateImpl;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoService;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoServiceImpl;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito.CartaoCreditoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito.CartaoCreditoLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.fornecedor.FornecedorEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.fornecedor.FornecedorLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.service.CustomCaixaDiarioServiceImpl;
import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;


@RunWith(SpringRunner.class)
public class VerificarTransacoesConciliacaoBancariaServiceTest extends FinanceiroFluxoCaixaBaseEntityTest {
	
	private static final String BANCO_ID = "237";
	private static final String AGENDIA_ID = "12345";
	private static final String CONTA_BANCARIA_ID = "98765";
	private static final BigDecimal VALOR = new BigDecimal("1269.4");
	private static final Object SUCESSO = "Sucesso";
	
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
		
		@Bean
		public CaixaDiarioListFilterPredicate caixaDiarioListFilterPredicate() {
			return new CaixaDiarioListFilterPredicateImpl();
		}
		
		@Bean
		public CaixaDiarioService caixaDiarioService() {
			return new CustomCaixaDiarioServiceImpl();
		}
		
		@Bean
		public CaixaGeral caixaGeral() {
			return new CaixaGeral();
		}
		
		
		@Bean
		public ConciliacaoBancariaHelper conciliacaoBancariaHelper() {
			return new ConciliacaoBancariaHelperImpl();
		}
		
		@Bean
		public ConciliacaoBancariaService conciliacaoBancariaService() {
			return new ConciliacaoBancariaServiceImpl();
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
	protected PlanoContaRepository planoContaRepository;
	
	@Inject
	protected ConciliacaoBancariaHelper conciliacaoBancariaHelper;
	
	@Inject
	private ConciliacaoBancariaService conciliacaoBancariaService;
	
	@MockBean
	protected DomainEntityEventsPublisher publisher;
	
	@Test
	public void testVerificarTransacoes_DuasTransacoesSemLancamento() {
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = newConciliacaoBancariaDTO();
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.of(2019, 6, 10))
				.tituloConciliadoId(null)
				.trnId("123")
				.trnDocumento("123")
				.trnHistorico("Teste 123")
				.build();
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.of(2019, 6, 10))
				.tituloConciliadoId(null)
				.trnId("321")
				.trnDocumento("321")
				.trnHistorico("Teste 321")
				.build();
		
		transacoes.add(t1);
		transacoes.add(t2);
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		assertThat(transacoes).hasSize(2)
		.extracting(
				ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::isDebito,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(
				tuple(null, null, null, false, SUCESSO, true, SituacaoConciliacaoTrn.CONCILIAR_CAIXA),
				tuple(null, null, null, false, SUCESSO, false, SituacaoConciliacaoTrn.CONCILIAR_CAIXA)
				);
	}
	
	@Test
	public void testVerificarTransacoes_1LancSemConc_1LancComConc_2TranSemLanc() {
		List<CaixaLancamentoEntity> lancamentos = criarLancamentos();
		
		CaixaLancamentoEntity lanc1 = lancamentos.get(0);
		CaixaLancamentoEntity lanc2 = lancamentos.get(1);
		
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = newConciliacaoBancariaDTO();
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.of(2019, 7, 4))
				.tituloConciliadoId(null)
				.trnId("1")
				.trnDocumento("1")
				.trnHistorico("Teste 1")
				.build();
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.of(2019, 8, 4))
				.tituloConciliadoId(null)
				.trnId("2")
				.trnDocumento("2")
				.trnHistorico("Teste 2")
				.build();
		
		lanc2.setIdConcBancaria(t2.getTrnId());
		lanc2.setNumDocConcBancaria(t2.getTrnDocumento());
		lanc2.setHistConcBancaria(t2.getTrnHistorico());
		
		ConciliacaoTransacaoDTO t3 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR.add(BigDecimal.ONE))
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(LocalDate.of(2019, 9, 4))
				.tituloConciliadoId(null)
				.trnId("3")
				.trnDocumento("3")
				.trnHistorico("Teste 3")
				.build();
		
		ConciliacaoTransacaoDTO t4 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(VALOR.add(BigDecimal.TEN))
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(LocalDate.of(2019, 9, 5))
				.tituloConciliadoId(null)
				.trnId("4")
				.trnDocumento("4")
				.trnHistorico("Teste 4")
				.build();
		
		transacoes.addAll(Arrays.asList(t1, t2, t3, t4));
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.verificarTransacoes(conciliacaoBancariaDTO);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		
		assertThat(transacoes).hasSize(4)
		.extracting(
				ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::isDebito,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(
				tuple(lanc1.getId(), 
				lanc1.getDescricao(),
				null,
				false,
				SUCESSO,
				TipoLancamentoFinanceiro.DEBITO.equals(lanc1.getTipoLancamentoFinanceiro()),				
				SituacaoConciliacaoTrn.CAIXA_BAIXADO_SEM_CONCILIACAO),
				
				tuple(lanc2.getId(), 
						lanc2.getDescricao(),
						LocalDate.now(),
						false,
						SUCESSO,
						TipoLancamentoFinanceiro.DEBITO.equals(lanc2.getTipoLancamentoFinanceiro()),
						SituacaoConciliacaoTrn.CONCILIADO_CAIXA),
				
				tuple(null, null, null, false, SUCESSO, true, SituacaoConciliacaoTrn.CONCILIAR_CAIXA),
				tuple(null, null, null, false, SUCESSO, false, SituacaoConciliacaoTrn.CONCILIAR_CAIXA)
				
				);
	}
	
	private ConciliacaoBancariaDTO newConciliacaoBancariaDTO() {
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		return conciliacaoBancariaDTO;
	}
	
	// BEGIN TESTS DEPENDENCIES
	
	private List<CaixaLancamentoEntity> criarLancamentos() {
		int quantidade = 10;
		LocalDate dataInicial = LocalDate.of(2019, 7, 4);
		List<CaixaLancamentoEntity> contas = criarLancamentos(quantidade, dataInicial, VALOR);
		assertThat(contas).hasSize(quantidade);
		assertThat(contas.get(0).getDataLancamento()).isEqualTo(LocalDate.of(2019, 7, 4));
		assertThat(contas.get(contas.size() - 1).getDataLancamento()).isEqualTo(LocalDate.of(2020, 4, 4));
		
		return contas;
	}
	
	protected List<CaixaLancamentoEntity> criarLancamentos(int quantidade, LocalDate dataInicial, BigDecimal valor) {
		LocalDate dataVencimento = dataInicial;
		List<CaixaLancamentoEntity> lancamentos = new ArrayList<>(quantidade);
		for (int i = 1; i <= quantidade; i++) {
			CaixaLancamentoEntity caixaLancamentoEntity = new CaixaLancamentoEntity();
			
			caixaLancamentoEntity.setCaixaDiario(newCaixaDiarioEntity());
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			String dataVencStr = dataVencimento.format(formatter);
			
			caixaLancamentoEntity.setDescricao("Lançamento número: " + i + ", vencimento: " + dataVencStr);
			
			caixaLancamentoEntity.setDataLancamento(dataVencimento);
			
			TipoLancamentoFinanceiro tlf = TipoLancamentoFinanceiro.DEBITO;
			boolean isCredito = i % 2 == 0;
			if (isCredito) {
				tlf = TipoLancamentoFinanceiro.CREDITO;
				caixaLancamentoEntity.setValorCredito(valor);
				caixaLancamentoEntity.setValorDebito(null);
			}
			else { // Débito
				caixaLancamentoEntity.setValorCredito(null);
				caixaLancamentoEntity.setValorDebito(valor);
			}
			
			caixaLancamentoEntity.setTipoLancamentoFinanceiro(tlf);
			
			caixaLancamentoEntity.setFormaPagamento(FormaPagamento.CONTA_BANCARIA);
			caixaLancamentoEntity.setContaBancaria(newContaBancariaConciliacao());
			caixaLancamentoEntity.setCartaoCredito(null);
			caixaLancamentoEntity.setOutrosDescricao(null);
			caixaLancamentoEntity.setPlanoContas(newPlanoContaEntity());
			caixaLancamentoEntity.setTipoFonteMovimento(TipoFonteMovimento.LANCEMENTO_CAIXA);
			caixaLancamentoEntity.setCliente(null);
			caixaLancamentoEntity.setFornecedor(null);
			caixaLancamentoEntity.setDocumento(null);
			caixaLancamentoEntity.setNumDocConcBancaria(null);
			caixaLancamentoEntity.setHistConcBancaria(null);
			caixaLancamentoEntity.setObservacoes(null);
			
			
			
			caixaLancamentoEntity = em.persistAndFlush(caixaLancamentoEntity);
			lancamentos.add(caixaLancamentoEntity);
			dataVencimento = dataVencimento.plusMonths(1);
		}
		
		return lancamentos;
	}
	
	protected CaixaDiarioEntity newCaixaDiarioEntity() {
		CaixaDiarioEntity caixaDiarioEntity = new CaixaDiarioEntity();
		
		caixaDiarioEntity.setCaixa(newCaixaEntity());
		caixaDiarioEntity.setCaixaDiarioSituacao(CaixaDiarioSituacao.NAO_INICIADO);
		caixaDiarioEntity.setDataHoraAbertura(java.time.LocalDateTime.now());
		caixaDiarioEntity.setSaldoInicial(new java.math.BigDecimal("5847.29273"));
		caixaDiarioEntity.setDataHoraFechamento(java.time.LocalDateTime.now());
		caixaDiarioEntity.setSaldoFinal(new java.math.BigDecimal("11836.14317"));
		caixaDiarioEntity.setObservacoes(generateRandomString(1000));
		
		caixaDiarioEntity = em.persistAndFlush(caixaDiarioEntity);
		
		return caixaDiarioEntity;
	}
	
	protected CaixaEntity newCaixaEntity() {
		CaixaEntity caixaEntity = new CaixaEntity();
		
		caixaEntity.setNome(generateRandomString(255));
		caixaEntity.setAtivo(true);
		caixaEntity.setSaldo(new java.math.BigDecimal("26429.18625"));
		caixaEntity.setObservacoes(generateRandomString(255));
		
		caixaEntity = em.persistAndFlush(caixaEntity);
		
		return caixaEntity;
	}
	
	protected PlanoContaEntity newPlanoContaEntity() {
		PlanoContaEntity planoContaEntity = new PlanoContaEntity();
		
		planoContaEntity.setId(java.util.UUID.randomUUID());
		planoContaEntity.setCodigo(generateRandomString(255));
		planoContaEntity.setDescricao(generateRandomString(255));
		planoContaEntity.setTipoFinanceiro(TipoPlanoContaFinanceiro.DESPESA);
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
	
	ContaBancariaEntity contaBancariaEntity;
	protected ContaBancariaEntity newContaBancariaConciliacao() {
		if (contaBancariaEntity == null) {
			contaBancariaEntity = new ContaBancariaEntity();
			
			contaBancariaEntity.setId(java.util.UUID.randomUUID());
			contaBancariaEntity.setNomeTitular("Kerubin");
			contaBancariaEntity.setAgencia(newAgenciaBancariaConciliacao());
			contaBancariaEntity.setTipoContaBancaria(TipoContaBancaria.CONTA_CORRENTE);
			contaBancariaEntity.setNumeroConta(CONTA_BANCARIA_ID);
			contaBancariaEntity.setDigito("01");
			contaBancariaEntity.setDataValidade(getNextDate());
			contaBancariaEntity.setAtivo(true);
			contaBancariaEntity.setDeleted(false);
			
			contaBancariaEntity = em.persistAndFlush(contaBancariaEntity);
		}
		
		return contaBancariaEntity;
	}
	
	protected ContaBancariaLookupResult newContaBancariaLookupResult(ContaBancariaEntity contaBancariaEntity) {
		ContaBancariaLookupResult contaBancaria = new ContaBancariaLookupResult();
		
		contaBancaria.setId(contaBancariaEntity.getId());
		contaBancaria.setNomeTitular(contaBancariaEntity.getNomeTitular());
		contaBancaria.setNumeroConta(contaBancariaEntity.getNumeroConta());
		
		return contaBancaria;
	}
	
	AgenciaBancariaEntity agenciaBancariaEntity;
	protected AgenciaBancariaEntity newAgenciaBancariaConciliacao() {
		if (agenciaBancariaEntity == null) {
			agenciaBancariaEntity = new AgenciaBancariaEntity();
			
			agenciaBancariaEntity.setId(java.util.UUID.randomUUID());
			agenciaBancariaEntity.setBanco(newBancoBradesco());
			agenciaBancariaEntity.setNumeroAgencia(AGENDIA_ID);
			agenciaBancariaEntity.setDigitoAgencia("12");
			agenciaBancariaEntity.setEndereco("Blumenau");
			agenciaBancariaEntity.setDeleted(false);
			
			agenciaBancariaEntity = em.persistAndFlush(agenciaBancariaEntity);
			
		}
		
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
	
	BancoEntity bancoBradesco;
	protected BancoEntity newBancoBradesco() {
		if (bancoBradesco == null) {
			bancoBradesco = new BancoEntity();
			bancoBradesco.setId(java.util.UUID.randomUUID());
			bancoBradesco.setNumero(BANCO_ID);
			bancoBradesco.setNome("Banco Bradesco");
			bancoBradesco.setDeleted(false);
			
			bancoBradesco = em.persistAndFlush(bancoBradesco);
		}
		
		return bancoBradesco;
	}
	
	protected BancoLookupResult newBancoLookupResult(BancoEntity bancoEntity) {
		BancoLookupResult banco = new BancoLookupResult();
		
		banco.setId(bancoEntity.getId());
		banco.setNumero(bancoEntity.getNumero());
		banco.setNome(bancoEntity.getNome());
		
		return banco;
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
