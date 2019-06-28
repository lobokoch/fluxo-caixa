/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T19:57:39.460
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.event.contareceber;

import static br.com.kerubin.api.messaging.utils.Utils.isEmpty;
import static br.com.kerubin.api.messaging.utils.Utils.isNotEmpty;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.kerubin.api.financeiro.fluxocaixa.FormaPagamento;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoFonteMovimento;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoLancamentoFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.core.CaixaGeral;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoEntity;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoService;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito.CartaoCreditoRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.cliente.ClienteRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria.ContaBancariaRepository;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta.PlanoContaRepository;
import br.com.kerubin.api.messaging.core.DomainEventEnvelope;

@Service
public class ContaReceberSubscriberEventHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ContaReceberSubscriberEventHandler.class);
	
	@Inject
	private CaixaLancamentoService caixaLancamentoService;
	
	@Inject
	private PlanoContaRepository planoContasRepository;
	
	@Inject
	private ContaBancariaRepository contaBancariaRepository;
	
	@Inject
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	@Inject
	private ClienteRepository clienteRepository;
	
	@Inject
	private CaixaGeral caixaGeral;
	
	@Transactional
	@RabbitListener(queues = "#{contaReceberQueue.name}")
	public void onReceiveEvent(DomainEventEnvelope<ContaReceberEvent> envelope) {
		try {
			switch (envelope.getPrimitive()) {
			case ContaReceberEvent.CONTA_RECEBER_CONTAPAGA:
			
				doContaReceberPaga(envelope.getPayload());
				break;
				
			case ContaReceberEvent.CONTA_RECEBER_CONTAESTORNADA:
				
				doContaReceberEstornada(envelope.getPayload());
				break;
			
			default:
				log.warn("Unexpected entity event: {} for: {}.", envelope.getPrimitive(), "br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceber");
				break;
			}
		} catch(Exception e) {
			log.error("Error receiven event with envelope: " + envelope, e);
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}
	
	
	private void doContaReceberEstornada(ContaReceberEvent event) {
		doContaReceberPagaOuEstornda(event, false);
	}
	
	private void doContaReceberPaga(ContaReceberEvent event) {
		doContaReceberPagaOuEstornda(event, true);
	}
	
	private void doContaReceberPagaOuEstornda(ContaReceberEvent event, boolean isPaga) {
		log.info("Recebendo conta receber para registrar no caixa...");
		
		if (isEmpty(event.getDataPagamento())) {
			throw new IllegalStateException("A data de pagamento é nula.");
		}
		
		if (isEmpty(event.getValorPago())) {
			throw new IllegalStateException("O valor pago é nulo.");
		}
		
		CaixaLancamentoEntity caixaLancamentoEntity = new CaixaLancamentoEntity();
		
		caixaLancamentoEntity.setCaixaDiario(caixaGeral.getCaixaGeralDiarioAberto());
		
		String descricao = event.getDescricao();
		if (!isPaga) {
			if (isNotEmpty(descricao)) {
				descricao = "(ESTORNO) - " + descricao;
			}
			else {
				descricao = "(ESTORNO)";
			}
		}
		
		caixaLancamentoEntity.setDescricao(descricao);
		
		caixaLancamentoEntity.setDataLancamento(event.getDataPagamento());
		
		TipoLancamentoFinanceiro tipoLancamento = isPaga ? TipoLancamentoFinanceiro.CREDITO : TipoLancamentoFinanceiro.DEBITO;
		caixaLancamentoEntity.setTipoLancamentoFinanceiro(tipoLancamento);
		
		if (isPaga) {
			caixaLancamentoEntity.setValorCredito(event.getValorPago());
		}
		else {
			caixaLancamentoEntity.setValorDebito(event.getValorPago());
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
		
		caixaLancamentoEntity.setTipoFonteMovimento(TipoFonteMovimento.CONTAS_RECEBER);
		
		if (event.getContaBancaria() != null) {
			caixaLancamentoEntity.setContaBancaria(contaBancariaRepository.findById(event.getContaBancaria()).orElse(null));
		}
		
		if (event.getCartaoCredito() != null) {
			caixaLancamentoEntity.setCartaoCredito(cartaoCreditoRepository.findById(event.getCartaoCredito()).orElse(null));
		}
		
		if (event.getCliente() != null) {
			caixaLancamentoEntity.setCliente(clienteRepository.findById(event.getCliente()).orElse(null));
		}
		
		
		caixaLancamentoEntity.setDocumento(event.getNumDocumento());
		
		caixaLancamentoService.create(caixaLancamentoEntity);
		
		log.info("Conta receber paga foi registrada no caixa com sucesso.");
	}
	


}
