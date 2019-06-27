/**********************************************************************************************
Code generated with MKL Plug-in version: 5.3.2
Code generated at time stamp: 2019-06-27T19:57:39.460
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.event.contareceber;

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
			
			default:
				log.warn("Unexpected entity event: {} for: {}.", envelope.getPrimitive(), "br.com.kerubin.api.financeiro.contasreceber.entity.contareceber.ContaReceber");
				break;
			}
		} catch(Exception e) {
			log.error("Error receiven event with envelope: " + envelope, e);
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}
	
	
	private void doContaReceberPaga(ContaReceberEvent contaReceberEvent) {
		log.info("Recebendo conta receber paga para registrar no caixa...");
		CaixaLancamentoEntity caixaLancamentoEntity = new CaixaLancamentoEntity();
		
		caixaLancamentoEntity.setCaixaDiario(caixaGeral.getCaixaGeralDiarioAberto());
		caixaLancamentoEntity.setDescricao(contaReceberEvent.getDescricao());
		caixaLancamentoEntity.setDataLancamento(contaReceberEvent.getDataPagamento());
		caixaLancamentoEntity.setTipoLancamentoFinanceiro(TipoLancamentoFinanceiro.CREDITO);
		caixaLancamentoEntity.setValorCredito(contaReceberEvent.getValorPago());
		
		FormaPagamento formaPagamento = FormaPagamento.DINHEIRO;
		try {
			if (contaReceberEvent != null && contaReceberEvent.getFormaPagamento() != null) {
				formaPagamento = FormaPagamento.valueOf(contaReceberEvent.getFormaPagamento().name());
			}
		} catch(Exception e) {
			log.error("Error converting FormaPagamento of: " + contaReceberEvent.getFormaPagamento().name(), e);
		}
		
		caixaLancamentoEntity.setFormaPagamento(formaPagamento);
		
		if (contaReceberEvent.getPlanoContas() != null) {
			caixaLancamentoEntity.setPlanoContas(planoContasRepository.findById(contaReceberEvent.getPlanoContas()).orElse(null));
		}
		
		caixaLancamentoEntity.setTipoFonteMovimento(TipoFonteMovimento.CONTAS_RECEBER);
		
		if (contaReceberEvent.getContaBancaria() != null) {
			caixaLancamentoEntity.setContaBancaria(contaBancariaRepository.findById(contaReceberEvent.getContaBancaria()).orElse(null));
		}
		
		if (contaReceberEvent.getCartaoCredito() != null) {
			caixaLancamentoEntity.setCartaoCredito(cartaoCreditoRepository.findById(contaReceberEvent.getCartaoCredito()).orElse(null));
		}
		
		if (contaReceberEvent.getCliente() != null) {
			caixaLancamentoEntity.setCliente(clienteRepository.findById(contaReceberEvent.getCliente()).orElse(null));
		}
		
		
		caixaLancamentoEntity.setDocumento(contaReceberEvent.getNumDocumento());
		
		caixaLancamentoService.create(caixaLancamentoEntity);
		
		log.info("Conta receber paga foi registrada no caixa com sucesso.");
	}
	


}
