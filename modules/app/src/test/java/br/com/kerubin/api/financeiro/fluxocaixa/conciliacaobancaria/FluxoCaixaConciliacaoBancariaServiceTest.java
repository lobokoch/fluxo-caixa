/**********************************************************************************************
Code generated with MKL Plug-in version: 30.0.1
Code generated at time stamp: 2019-11-10T19:32:00.072
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.conciliacaobancaria;

import static br.com.kerubin.api.servicecore.util.CoreUtils.format;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.util.ArrayList;
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
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaLookupResult;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioListFilterPredicate;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioListFilterPredicateImpl;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixadiario.CaixaDiarioLookupResult;
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
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente.ClienteEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente.ClienteLookupResult;
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
public class FluxoCaixaConciliacaoBancariaServiceTest extends FinanceiroFluxoCaixaBaseEntityTest {
	
	private static final String BANCO_ID = "237";
	private static final String AGENDIA_ID = "12345";
	private static final String CONTA_BANCARIA_ID = "98765";
	
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
	
	// BEGIN CREATE TESTS
	
	@Test
	public void testAplicarConciliacaoBancariaComSucesso() {
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		ContaBancariaEntity contaBancaria = conciliacaoBancariaHelper.findContaBancaria(BANCO_ID, AGENDIA_ID, CONTA_BANCARIA_ID);
		
		BigDecimal valor1 = BigDecimal.valueOf(100.55);
		LocalDate data1 = LocalDate.now().minusDays(1);
		
		BigDecimal valor2 = BigDecimal.valueOf(181.95);
		LocalDate data2 = LocalDate.now().minusDays(2);
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(valor1)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(data1)
				.tituloConciliadoId(null)
				.trnDocumento("012345")
				.trnHistorico("Teste de conciliação 012345")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(valor2)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(data2)
				.tituloConciliadoId(null)
				.trnDocumento("98765")
				.trnHistorico("Teste de conciliação 98765")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		// Valida os lançamentos criados a partir das transações bancárias consolidades.
		PlanoContaEntity planoContas1 = conciliacaoBancariaHelper.findPlanoContaPelaTransacaoBancaria(t1);
		PlanoContaEntity planoContas2 = conciliacaoBancariaHelper.findPlanoContaPelaTransacaoBancaria(t2);
		
		List<CaixaLancamentoEntity> lancamentos = caixaLancamentoRepository.findAll();
		assertThat(lancamentos).hasSize(2)
		.extracting(CaixaLancamentoEntity::getDescricao,
				CaixaLancamentoEntity::getDataLancamento,
				CaixaLancamentoEntity::getTipoLancamentoFinanceiro,
				CaixaLancamentoEntity::getValorCredito,
				CaixaLancamentoEntity::getValorDebito,
				CaixaLancamentoEntity::getFormaPagamento,
				CaixaLancamentoEntity::getPlanoContas,
				CaixaLancamentoEntity::getTipoFonteMovimento,
				CaixaLancamentoEntity::getContaBancaria,
				CaixaLancamentoEntity::getDocumento,
				CaixaLancamentoEntity::getNumDocConcBancaria,
				CaixaLancamentoEntity::getHistConcBancaria)
		.contains(
				tuple(t1.getTrnHistorico(), 
						t1.getTrnData(), 
						TipoLancamentoFinanceiro.DEBITO, 
						null, t1.getTrnValor(), 
						FormaPagamento.CONTA_BANCARIA, 
						planoContas1, 
						TipoFonteMovimento.LANCEMENTO_CAIXA, 
						contaBancaria, 
						t1.getTrnDocumento(), 
						t1.getTrnDocumento(), 
						t1.getTrnHistorico()),
				
				tuple(t2.getTrnHistorico(),
						t2.getTrnData(),
						TipoLancamentoFinanceiro.CREDITO,
						t2.getTrnValor(),
						null,
						FormaPagamento.CONTA_BANCARIA,
						planoContas2,
						TipoFonteMovimento.LANCEMENTO_CAIXA,
						contaBancaria,
						t2.getTrnDocumento(),
						t2.getTrnDocumento(),
						t2.getTrnHistorico())
				);
		
		
		// Valida se os itens das transações de consoldiação foram alterados corretamente e refletem os lançamentos criados.
		CaixaLancamentoEntity l1 = lancamentos.get(0);
		CaixaLancamentoEntity l2 = lancamentos.get(1);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		assertThat(transacoes).hasSize(2)
		.extracting(
				ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(
				tuple(
						l1.getId(),
						l1.getDescricao(),
						LocalDate.now(),
						false,
						"Sucesso",
						SituacaoConciliacaoTrn.CONCILIADO_CAIXA
						),
				tuple(
						l2.getId(),
						l2.getDescricao(),
						LocalDate.now(),
						false,
						"Sucesso",
						SituacaoConciliacaoTrn.CONCILIADO_CAIXA
						)
				);
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroBancoNaoEncontrado() {
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID + "x");
		
		BigDecimal valor1 = BigDecimal.valueOf(100.55);
		LocalDate data1 = LocalDate.now().minusDays(1);
		
		BigDecimal valor2 = BigDecimal.valueOf(181.95);
		LocalDate data2 = LocalDate.now().minusDays(2);
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(valor1)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(data1)
				.tituloConciliadoId(null)
				.trnDocumento("012345")
				.trnHistorico("Teste de conciliação 012345")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(valor2)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(data2)
				.tituloConciliadoId(null)
				.trnDocumento("98765")
				.trnHistorico("Teste de conciliação 98765")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		List<CaixaLancamentoEntity> lancamentos = caixaLancamentoRepository.findAll();
		
		// Nenhum lançamento foi gerado.
		assertThat(lancamentos).hasSize(0);
		
		String msg = MessageFormat.format("Conta bancária não encontrada para bancoId:{0}, agenciaId:{1}, contaBancariaId:{2}", BANCO_ID, AGENDIA_ID, CONTA_BANCARIA_ID + "x");
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		assertThat(transacoes).hasSize(2)
		.extracting(
				ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(
				tuple(
						null,
						null,
						null,
						true,
						msg,
						SituacaoConciliacaoTrn.CONCILIAR_CAIXA
						),
				tuple(
						null,
						null,
						null,
						true,
						msg,
						SituacaoConciliacaoTrn.CONCILIAR_CAIXA
						)
				);
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroJaTemTituloConciliadoId() {
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		ContaBancariaEntity contaBancaria = conciliacaoBancariaHelper.findContaBancaria(BANCO_ID, AGENDIA_ID, CONTA_BANCARIA_ID);
		
		BigDecimal valor1 = BigDecimal.valueOf(100.55);
		LocalDate data1 = LocalDate.now().minusDays(1);
		
		BigDecimal valor2 = BigDecimal.valueOf(181.95);
		LocalDate data2 = LocalDate.now().minusDays(2);
		
		UUID fakeTituloConciliadoId1 = UUID.randomUUID();
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(valor1)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(data1)
				.tituloConciliadoId(fakeTituloConciliadoId1)
				.trnDocumento("012345")
				.trnHistorico("Teste de conciliação 012345")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(valor2)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(data2)
				.tituloConciliadoId(null)
				.trnDocumento("98765")
				.trnHistorico("Teste de conciliação 98765")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		// Valida os lançamentos criados a partir das transações bancárias consolidades.
		PlanoContaEntity planoContas2 = conciliacaoBancariaHelper.findPlanoContaPelaTransacaoBancaria(t2);
		
		List<CaixaLancamentoEntity> lancamentos = caixaLancamentoRepository.findAll();
		assertThat(lancamentos).hasSize(1)
		.extracting(CaixaLancamentoEntity::getDescricao,
				CaixaLancamentoEntity::getDataLancamento,
				CaixaLancamentoEntity::getTipoLancamentoFinanceiro,
				CaixaLancamentoEntity::getValorCredito,
				CaixaLancamentoEntity::getValorDebito,
				CaixaLancamentoEntity::getFormaPagamento,
				CaixaLancamentoEntity::getPlanoContas,
				CaixaLancamentoEntity::getTipoFonteMovimento,
				CaixaLancamentoEntity::getContaBancaria,
				CaixaLancamentoEntity::getDocumento,
				CaixaLancamentoEntity::getNumDocConcBancaria,
				CaixaLancamentoEntity::getHistConcBancaria)
		.contains(
				tuple(t2.getTrnHistorico(),
						t2.getTrnData(),
						TipoLancamentoFinanceiro.CREDITO,
						t2.getTrnValor(),
						null,
						FormaPagamento.CONTA_BANCARIA,
						planoContas2,
						TipoFonteMovimento.LANCEMENTO_CAIXA,
						contaBancaria,
						t2.getTrnDocumento(),
						t2.getTrnDocumento(),
						t2.getTrnHistorico())
				);
		
		
		// Valida se os itens das transações de consoldiação foram alterados corretamente e refletem os lançamentos criados.
		String msg = format("A transação já possui id ({0}) de título de conciliação", fakeTituloConciliadoId1);
		CaixaLancamentoEntity l2 = lancamentos.get(0);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		assertThat(transacoes).hasSize(2)
		.extracting(
				ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(
				tuple(
						fakeTituloConciliadoId1,
						null,
						null,
						true,
						msg,
						SituacaoConciliacaoTrn.CONCILIAR_CAIXA
						),
				tuple(
						l2.getId(),
						l2.getDescricao(),
						LocalDate.now(),
						false,
						"Sucesso",
						SituacaoConciliacaoTrn.CONCILIADO_CAIXA
						)
				);
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroDataVazia() {
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		ContaBancariaEntity contaBancaria = conciliacaoBancariaHelper.findContaBancaria(BANCO_ID, AGENDIA_ID, CONTA_BANCARIA_ID);
		
		BigDecimal valor1 = BigDecimal.valueOf(100.55);
		LocalDate data1 = null;
		
		BigDecimal valor2 = BigDecimal.valueOf(181.95);
		LocalDate data2 = LocalDate.now().minusDays(2);
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(valor1)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(data1)
				.tituloConciliadoId(null)
				.trnDocumento("012345")
				.trnHistorico("Teste de conciliação 012345")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(valor2)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(data2)
				.tituloConciliadoId(null)
				.trnDocumento("98765")
				.trnHistorico("Teste de conciliação 98765")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		// Valida os lançamentos criados a partir das transações bancárias consolidades.
		PlanoContaEntity planoContas2 = conciliacaoBancariaHelper.findPlanoContaPelaTransacaoBancaria(t2);
		
		List<CaixaLancamentoEntity> lancamentos = caixaLancamentoRepository.findAll();
		assertThat(lancamentos).hasSize(1)
		.extracting(CaixaLancamentoEntity::getDescricao,
				CaixaLancamentoEntity::getDataLancamento,
				CaixaLancamentoEntity::getTipoLancamentoFinanceiro,
				CaixaLancamentoEntity::getValorCredito,
				CaixaLancamentoEntity::getValorDebito,
				CaixaLancamentoEntity::getFormaPagamento,
				CaixaLancamentoEntity::getPlanoContas,
				CaixaLancamentoEntity::getTipoFonteMovimento,
				CaixaLancamentoEntity::getContaBancaria,
				CaixaLancamentoEntity::getDocumento,
				CaixaLancamentoEntity::getNumDocConcBancaria,
				CaixaLancamentoEntity::getHistConcBancaria)
		.contains(
				tuple(t2.getTrnHistorico(),
						t2.getTrnData(),
						TipoLancamentoFinanceiro.CREDITO,
						t2.getTrnValor(),
						null,
						FormaPagamento.CONTA_BANCARIA,
						planoContas2,
						TipoFonteMovimento.LANCEMENTO_CAIXA,
						contaBancaria,
						t2.getTrnDocumento(),
						t2.getTrnDocumento(),
						t2.getTrnHistorico())
				);
		
		
		// Valida se os itens das transações de consoldiação foram alterados corretamente e refletem os lançamentos criados.
		String msg = "Data da transação está vazia ao baixar conta via conciliação";
		CaixaLancamentoEntity l2 = lancamentos.get(0);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		assertThat(transacoes).hasSize(2)
		.extracting(
				ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(
				tuple(
						null,
						null,
						null,
						true,
						msg,
						SituacaoConciliacaoTrn.CONCILIAR_CAIXA
						),
				tuple(
						l2.getId(),
						l2.getDescricao(),
						LocalDate.now(),
						false,
						"Sucesso",
						SituacaoConciliacaoTrn.CONCILIADO_CAIXA
						)
				);
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroDocumentoVazio() {
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		ContaBancariaEntity contaBancaria = conciliacaoBancariaHelper.findContaBancaria(BANCO_ID, AGENDIA_ID, CONTA_BANCARIA_ID);
		
		BigDecimal valor1 = BigDecimal.valueOf(100.55);
		LocalDate data1 = LocalDate.now();
		
		BigDecimal valor2 = BigDecimal.valueOf(181.95);
		LocalDate data2 = LocalDate.now().minusDays(2);
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(valor1)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(data1)
				.tituloConciliadoId(null)
				.trnDocumento(null)
				.trnHistorico("Teste de conciliação 012345")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(valor2)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(data2)
				.tituloConciliadoId(null)
				.trnDocumento("98765")
				.trnHistorico("Teste de conciliação 98765")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		// Valida os lançamentos criados a partir das transações bancárias consolidades.
		PlanoContaEntity planoContas2 = conciliacaoBancariaHelper.findPlanoContaPelaTransacaoBancaria(t2);
		
		List<CaixaLancamentoEntity> lancamentos = caixaLancamentoRepository.findAll();
		assertThat(lancamentos).hasSize(1)
		.extracting(CaixaLancamentoEntity::getDescricao,
				CaixaLancamentoEntity::getDataLancamento,
				CaixaLancamentoEntity::getTipoLancamentoFinanceiro,
				CaixaLancamentoEntity::getValorCredito,
				CaixaLancamentoEntity::getValorDebito,
				CaixaLancamentoEntity::getFormaPagamento,
				CaixaLancamentoEntity::getPlanoContas,
				CaixaLancamentoEntity::getTipoFonteMovimento,
				CaixaLancamentoEntity::getContaBancaria,
				CaixaLancamentoEntity::getDocumento,
				CaixaLancamentoEntity::getNumDocConcBancaria,
				CaixaLancamentoEntity::getHistConcBancaria)
		.contains(
				tuple(t2.getTrnHistorico(),
						t2.getTrnData(),
						TipoLancamentoFinanceiro.CREDITO,
						t2.getTrnValor(),
						null,
						FormaPagamento.CONTA_BANCARIA,
						planoContas2,
						TipoFonteMovimento.LANCEMENTO_CAIXA,
						contaBancaria,
						t2.getTrnDocumento(),
						t2.getTrnDocumento(),
						t2.getTrnHistorico())
				);
		
		
		// Valida se os itens das transações de consoldiação foram alterados corretamente e refletem os lançamentos criados.
		String msg = "Documento da transação está vazio ao baixar conta via conciliação";
		CaixaLancamentoEntity l2 = lancamentos.get(0);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		assertThat(transacoes).hasSize(2)
		.extracting(
				ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(
				tuple(
						null,
						null,
						null,
						true,
						msg,
						SituacaoConciliacaoTrn.CONCILIAR_CAIXA
						),
				tuple(
						l2.getId(),
						l2.getDescricao(),
						LocalDate.now(),
						false,
						"Sucesso",
						SituacaoConciliacaoTrn.CONCILIADO_CAIXA
						)
				);
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroValorVazio() {
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		ContaBancariaEntity contaBancaria = conciliacaoBancariaHelper.findContaBancaria(BANCO_ID, AGENDIA_ID, CONTA_BANCARIA_ID);
		
		BigDecimal valor1 = null;
		LocalDate data1 = LocalDate.now();
		
		BigDecimal valor2 = BigDecimal.valueOf(181.95);
		LocalDate data2 = LocalDate.now().minusDays(2);
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(valor1)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(data1)
				.tituloConciliadoId(null)
				.trnDocumento(null)
				.trnHistorico("Teste de conciliação 012345")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(valor2)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(data2)
				.tituloConciliadoId(null)
				.trnDocumento("98765")
				.trnHistorico("Teste de conciliação 98765")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		// Valida os lançamentos criados a partir das transações bancárias consolidades.
		PlanoContaEntity planoContas2 = conciliacaoBancariaHelper.findPlanoContaPelaTransacaoBancaria(t2);
		
		List<CaixaLancamentoEntity> lancamentos = caixaLancamentoRepository.findAll();
		assertThat(lancamentos).hasSize(1)
		.extracting(CaixaLancamentoEntity::getDescricao,
				CaixaLancamentoEntity::getDataLancamento,
				CaixaLancamentoEntity::getTipoLancamentoFinanceiro,
				CaixaLancamentoEntity::getValorCredito,
				CaixaLancamentoEntity::getValorDebito,
				CaixaLancamentoEntity::getFormaPagamento,
				CaixaLancamentoEntity::getPlanoContas,
				CaixaLancamentoEntity::getTipoFonteMovimento,
				CaixaLancamentoEntity::getContaBancaria,
				CaixaLancamentoEntity::getDocumento,
				CaixaLancamentoEntity::getNumDocConcBancaria,
				CaixaLancamentoEntity::getHistConcBancaria)
		.contains(
				tuple(t2.getTrnHistorico(),
						t2.getTrnData(),
						TipoLancamentoFinanceiro.CREDITO,
						t2.getTrnValor(),
						null,
						FormaPagamento.CONTA_BANCARIA,
						planoContas2,
						TipoFonteMovimento.LANCEMENTO_CAIXA,
						contaBancaria,
						t2.getTrnDocumento(),
						t2.getTrnDocumento(),
						t2.getTrnHistorico())
				);
		
		
		// Valida se os itens das transações de consoldiação foram alterados corretamente e refletem os lançamentos criados.
		String msg = "Valor da transação está vazio ao baixar conta via conciliação";
		CaixaLancamentoEntity l2 = lancamentos.get(0);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		assertThat(transacoes).hasSize(2)
		.extracting(
				ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(
				tuple(
						null,
						null,
						null,
						true,
						msg,
						SituacaoConciliacaoTrn.CONCILIAR_CAIXA
						),
				tuple(
						l2.getId(),
						l2.getDescricao(),
						LocalDate.now(),
						false,
						"Sucesso",
						SituacaoConciliacaoTrn.CONCILIADO_CAIXA
						)
				);
	}
	
	@Test
	public void testAplicarConciliacaoBancariaComErroLancamentoJaRealizado() {
		CaixaLancamentoEntity lancamento = newCaixaLancamentoEntity();
		
		ConciliacaoBancariaDTO conciliacaoBancariaDTO = new ConciliacaoBancariaDTO();
		conciliacaoBancariaDTO.setBancoId(BANCO_ID);
		conciliacaoBancariaDTO.setAgenciaId(AGENDIA_ID);
		conciliacaoBancariaDTO.setContaId(CONTA_BANCARIA_ID);
		
		ContaBancariaEntity contaBancaria = conciliacaoBancariaHelper.findContaBancaria(BANCO_ID, AGENDIA_ID, CONTA_BANCARIA_ID);
		
		BigDecimal valor1 = lancamento.getValorDebito();
		LocalDate data1 = lancamento.getDataLancamento();
		
		BigDecimal valor2 = BigDecimal.valueOf(181.95);
		LocalDate data2 = LocalDate.now();
		
		
		List<ConciliacaoTransacaoDTO> transacoes = new ArrayList<>();
		ConciliacaoTransacaoDTO t1 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(valor1)
				.trnTipo(TipoTransacao.DEBITO)
				.trnData(data1)
				.tituloConciliadoId(null)
				.trnDocumento("012345")
				.trnHistorico("Teste de conciliação 012345")
				.build();
		
		transacoes.add(t1);
		
		ConciliacaoTransacaoDTO t2 = ConciliacaoTransacaoDTO.builder()
				.id(UUID.randomUUID())
				.trnValor(valor2)
				.trnTipo(TipoTransacao.CREDITO)
				.trnData(data2)
				.tituloConciliadoId(null)
				.trnDocumento("98765")
				.trnHistorico("Teste de conciliação 98765")
				.build();
		
		transacoes.add(t2);
		
		
		conciliacaoBancariaDTO.setTransacoes(transacoes);
		
		conciliacaoBancariaDTO = conciliacaoBancariaService.aplicarConciliacaoBancaria(conciliacaoBancariaDTO);
		
		// Valida os lançamentos criados a partir das transações bancárias consolidades.
		PlanoContaEntity planoContas2 = conciliacaoBancariaHelper.findPlanoContaPelaTransacaoBancaria(t2);
		
		List<CaixaLancamentoEntity> lancamentos = caixaLancamentoRepository.findAll();
		assertThat(lancamentos).hasSize(2);
		lancamentos.remove(lancamento);
		
		assertThat(lancamentos).hasSize(1)
		.extracting(CaixaLancamentoEntity::getDescricao,
				CaixaLancamentoEntity::getDataLancamento,
				CaixaLancamentoEntity::getTipoLancamentoFinanceiro,
				CaixaLancamentoEntity::getValorCredito,
				CaixaLancamentoEntity::getValorDebito,
				CaixaLancamentoEntity::getFormaPagamento,
				CaixaLancamentoEntity::getPlanoContas,
				CaixaLancamentoEntity::getTipoFonteMovimento,
				CaixaLancamentoEntity::getContaBancaria,
				CaixaLancamentoEntity::getDocumento,
				CaixaLancamentoEntity::getNumDocConcBancaria,
				CaixaLancamentoEntity::getHistConcBancaria)
		.contains(
				tuple(t2.getTrnHistorico(),
						t2.getTrnData(),
						TipoLancamentoFinanceiro.CREDITO,
						t2.getTrnValor(),
						null,
						FormaPagamento.CONTA_BANCARIA,
						planoContas2,
						TipoFonteMovimento.LANCEMENTO_CAIXA,
						contaBancaria,
						t2.getTrnDocumento(),
						t2.getTrnDocumento(),
						t2.getTrnHistorico())
				);
		
		
		// Valida se os itens das transações de consoldiação foram alterados corretamente e refletem os lançamentos criados.
		String msg = format("Transação já baixada no lançamento id: {0}, data: {1}, descrição: {2}", lancamento.getId(), lancamento.getDataLancamento(), lancamento.getDescricao());
		CaixaLancamentoEntity l2 = lancamentos.get(0);
		
		transacoes = conciliacaoBancariaDTO.getTransacoes();
		assertThat(transacoes).hasSize(2)
		.extracting(
				ConciliacaoTransacaoDTO::getTituloConciliadoId,
				ConciliacaoTransacaoDTO::getTituloConciliadoDesc,
				ConciliacaoTransacaoDTO::getDataConciliacao,
				ConciliacaoTransacaoDTO::getConciliadoComErro,
				ConciliacaoTransacaoDTO::getConciliadoMsg,
				ConciliacaoTransacaoDTO::getSituacaoConciliacaoTrn)
		.contains(
				tuple(
						lancamento.getId(),
						lancamento.getDescricao(),
						null,
						true,
						msg,
						SituacaoConciliacaoTrn.CAIXA_BAIXADO_SEM_CONCILIACAO
						),
				tuple(
						l2.getId(),
						l2.getDescricao(),
						LocalDate.now(),
						false,
						"Sucesso",
						SituacaoConciliacaoTrn.CONCILIADO_CAIXA
						)
				);
	}
	
	// BEGIN TESTS DEPENDENCIES
	
	protected CaixaLancamentoEntity newCaixaLancamentoEntity() {
		CaixaLancamentoEntity caixaLancamentoEntity = new CaixaLancamentoEntity();
		
		caixaLancamentoEntity.setCaixaDiario(newCaixaDiarioEntity());
		caixaLancamentoEntity.setDescricao("Lançamento para testes 1");
		caixaLancamentoEntity.setDataLancamento(LocalDate.now());
		caixaLancamentoEntity.setTipoLancamentoFinanceiro(TipoLancamentoFinanceiro.DEBITO);
		caixaLancamentoEntity.setValorCredito(null);
		caixaLancamentoEntity.setValorDebito(BigDecimal.valueOf(181.16));
		caixaLancamentoEntity.setFormaPagamento(FormaPagamento.DINHEIRO);
		caixaLancamentoEntity.setContaBancaria(newContaBancariaEntity());
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
		
		return caixaLancamentoEntity;
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
		caixaEntity.setSaldo(new java.math.BigDecimal("26429.18625"));
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
		cartaoCreditoEntity.setValorLimite(new java.math.BigDecimal("28028.18682"));
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
