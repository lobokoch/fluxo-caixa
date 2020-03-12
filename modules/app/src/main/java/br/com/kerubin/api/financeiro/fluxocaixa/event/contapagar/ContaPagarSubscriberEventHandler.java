/**********************************************************************************************
Code generated with MKL Plug-in version: 5.0.3
Code generated at time stamp: 2019-06-24T22:01:00.414
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.event.contapagar;

import static br.com.kerubin.api.messaging.utils.Utils.isEmpty;
import static br.com.kerubin.api.messaging.utils.Utils.isNotEmpty;
import static br.com.kerubin.api.servicecore.util.CoreUtils.formatDateAndTime;
import static br.com.kerubin.api.servicecore.util.CoreUtils.formatMoney;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.kerubin.api.database.core.ServiceContext;
import br.com.kerubin.api.financeiro.fluxocaixa.FormaPagamento;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoFonteMovimento;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoLancamentoFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.core.CaixaGeral;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoService;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito.CartaoCreditoRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.fornecedor.FornecedorRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaRepository;
import br.com.kerubin.api.messaging.core.DomainEventEnvelope;

@Service
public class ContaPagarSubscriberEventHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ContaPagarSubscriberEventHandler.class);
	
	@Inject
	private CaixaLancamentoService caixaLancamentoService;
	
	@Inject
	private CaixaLancamentoRepository caixaLancamentoRepository;
	
	@Inject
	private PlanoContaRepository planoContasRepository;
	
	@Inject
	private ContaBancariaRepository contaBancariaRepository;
	
	@Inject
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	@Inject
	private FornecedorRepository fornecedorRepository;
	
	@Inject
	private CaixaGeral caixaGeral;
	
	@Transactional
	@RabbitListener(queues = "#{contaPagarQueue.name}")
	public void onReceiveEvent(DomainEventEnvelope<ContaPagarEvent> envelope) {
		try {
			switch (envelope.getPrimitive()) {
			case ContaPagarEvent.CONTA_PAGAR_CONTAPAGA:
			
				doContaPaga(envelope.getPayload());
				break;
				
			case ContaPagarEvent.CONTA_PAGAR_CONTAESTORNADA:
				
				doContaPagaEstornada(envelope.getPayload());
				break;
			
			default:
				log.warn("Unexpected entity event: {} for: {}.", envelope.getPrimitive(), "br.com.kerubin.api.financeiro.contaspagar.entity.contapagar.ContaPagar");
				break;
			}
		} catch(Exception e) {
			log.error("Error receiven event with envelope: " + envelope, e);
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}
	
	
	private void doContaPaga(ContaPagarEvent event) {
		doContaPagaOuEstornada(event, true);
	}
	
	private void doContaPagaEstornada(ContaPagarEvent event) {
		doContaPagaOuEstornada(event, false);
	}
	
	private void doContaPagaOuEstornada(ContaPagarEvent event, boolean isPaga) {
		log.info("Processando conta PAGA com id: {} e descrição: {} e vlaor: {} para registrar no caixa...", 
				event.getId(), event.getDescricao(), event.getValorPago());
		
		LocalDate dataMovimento = LocalDate.now(); // Para estorno usa a data atual.
		if (isPaga && isEmpty(event.getDataPagamento())) {
			throw new IllegalStateException("A data de pagamento é nula para o pagamento da conta.");
		}
		
		if (isPaga) {
			dataMovimento = event.getDataPagamento();
		}
		
		if (isEmpty(event.getValorPago())) {
			throw new IllegalStateException("O valor pago é nulo.");
		}
		
		CaixaLancamentoEntity caixaLancamentoEntity = new CaixaLancamentoEntity();
		
		caixaLancamentoEntity.setCaixaDiario(caixaGeral.getCaixaGeralDiarioAberto());
		
		String descricao = event.getDescricao();
		boolean isEstorno = !isPaga;
		
		CaixaLancamentoEntity lancamentoFonteMovimento = null;
		String estornoHistorico = null;
		if (isEstorno) {
			
			// Begin estorno
			List<CaixaLancamentoEntity> lancamentosFonteMovimento = caixaLancamentoRepository.findByIdFonteMovimentoAndEstornoIsNullOrIdFonteMovimentoAndEstornoIsFalse(event.getId(), event.getId());
			if (isEmpty(lancamentosFonteMovimento)) {
				throw new IllegalStateException(MessageFormat.format("Não foi possível achar o lançamento da fonte do movimento com IdFonteMovimento: {0} para efetuar o estorno. Evento do movimento: {1}.", 
						event.getId(), event)) ;
			}
			
			lancamentoFonteMovimento = lancamentosFonteMovimento.get(0);
			
			BigDecimal valor = TipoLancamentoFinanceiro.CREDITO.equals(lancamentoFonteMovimento.getTipoLancamentoFinanceiro()) ? 
					lancamentoFonteMovimento.getValorCredito() : lancamentoFonteMovimento.getValorDebito();
					
			if (isEmpty(valor)) {
				valor = event.getValorPago();
			}
			
			estornoHistorico = MessageFormat.format("Conta de valor: {0}, estornada em: {1}, pelo usuário: {2}.", 
					formatMoney(valor), formatDateAndTime(LocalDateTime.now()), ServiceContext.getUser());
			
			caixaLancamentoEntity.setEstorno(true);
			
			caixaLancamentoEntity.setHistConcBancaria(estornoHistorico);
			
			caixaLancamentoEntity.setEstornoLancamento(lancamentoFonteMovimento);
			
			// end estorno
			
			if (isNotEmpty(descricao)) {
				descricao = "(ESTORNO) - " + descricao;
			}
			else {
				descricao = "(ESTORNO)";
			}
		}
		
		caixaLancamentoEntity.setDescricao(descricao);
		
		caixaLancamentoEntity.setDataLancamento(dataMovimento);
		
		TipoLancamentoFinanceiro tipoLancamento = isPaga ? TipoLancamentoFinanceiro.DEBITO : TipoLancamentoFinanceiro.CREDITO;
		caixaLancamentoEntity.setTipoLancamentoFinanceiro(tipoLancamento);
		
		if (isPaga) {
			caixaLancamentoEntity.setValorDebito(event.getValorPago());
		}
		else {
			caixaLancamentoEntity.setValorCredito(event.getValorPago());
		}
		
		FormaPagamento formaPagamento = FormaPagamento.DINHEIRO;
		try {
			if (event != null && event.getFormaPagamento() != null) {
				formaPagamento = FormaPagamento.valueOf(event.getFormaPagamento().name());
			}
		} catch(Exception e) {
			log.error("Error converting FormaPagamento of: " + event.getFormaPagamento().name(), e);
		}
		
		caixaLancamentoEntity.setFormaPagamento(formaPagamento);
		
		if (event.getPlanoContas() != null) {
			caixaLancamentoEntity.setPlanoContas(planoContasRepository.findById(event.getPlanoContas()).orElse(null));
		}
		
		caixaLancamentoEntity.setTipoFonteMovimento(TipoFonteMovimento.CONTAS_PAGAR);
		
		if (event.getContaBancaria() != null) {
			caixaLancamentoEntity.setContaBancaria(contaBancariaRepository.findById(event.getContaBancaria()).orElse(null));
		}
		
		if (event.getCartaoCredito() != null) {
			caixaLancamentoEntity.setCartaoCredito(cartaoCreditoRepository.findById(event.getCartaoCredito()).orElse(null));
		}
		
		if (event.getFornecedor() != null) {
			caixaLancamentoEntity.setFornecedor(fornecedorRepository.findById(event.getFornecedor()).orElse(null));
		}
		
		
		caixaLancamentoEntity.setDocumento(event.getNumDocumento());
		caixaLancamentoEntity.setIdFonteMovimento(event.getId()); // Link entre a conta e o lançamento no caixa. Importante para estorno.
		log.info("Aplicou na entidade o IdFonteMovimento: {}, recebido do evento id: {}.", 
				caixaLancamentoEntity.getIdFonteMovimento(), event.getId());
		
		caixaLancamentoEntity = caixaLancamentoService.create(caixaLancamentoEntity);
		
		if (isEstorno) {
			lancamentoFonteMovimento.setEstorno(true);
			lancamentoFonteMovimento.setHistConcBancaria(estornoHistorico);
			lancamentoFonteMovimento.setEstornoLancamento(caixaLancamentoEntity);
			lancamentoFonteMovimento.setDescricao("(ESTORNO) - " + lancamentoFonteMovimento.getDescricao());
			caixaLancamentoRepository.save(lancamentoFonteMovimento);
		}
		
		log.info("Conta paga foi registrada no caixa com sucesso.");
	}
	
}
