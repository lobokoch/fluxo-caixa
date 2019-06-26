/**********************************************************************************************
Code generated with MKL Plug-in version: 5.0.3
Code generated at time stamp: 2019-06-24T22:01:00.414
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.event.contapagar;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import br.com.kerubin.api.financeiro.fluxocaixa.FormaPagamento;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoFonteMovimento;
import br.com.kerubin.api.financeiro.fluxocaixa.TipoLancamentoFinanceiro;
import br.com.kerubin.api.financeiro.fluxocaixa.core.CaixaGeral;
import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixalancamento.CaixaLancamentoEntity;
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
	private PlanoContaRepository planoContasRepository;
	
	@Inject
	private ContaBancariaRepository contaBancariaRepository;
	
	@Inject
	private CartaoCreditoRepository cartaoCreditoRepository;
	
	@Inject
	private FornecedorRepository fornecedorRepository;
	
	@Inject
	private CaixaGeral caixaGeral;
	
	@RabbitListener(queues = "#{contaPagarQueue.name}")
	public void onReceiveEvent(DomainEventEnvelope<ContaPagarEvent> envelope) {
		try {
			switch (envelope.getPrimitive()) {
			case ContaPagarEvent.CONTA_PAGAR_CONTAPAGA:
			
				doContaPaga(envelope.getPayload());
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
	
	
	private void doContaPaga(ContaPagarEvent contaPagarEvent) {
		log.info("Recebendo conta paga para registrar no caixa...");
		CaixaLancamentoEntity caixaLancamentoEntity = new CaixaLancamentoEntity();
		
		caixaLancamentoEntity.setCaixaDiario(caixaGeral.getCaixaGeralDiarioAberto());
		caixaLancamentoEntity.setDescricao(contaPagarEvent.getDescricao());
		caixaLancamentoEntity.setDataLancamento(contaPagarEvent.getDataPagamento());
		caixaLancamentoEntity.setTipoLancamentoFinanceiro(TipoLancamentoFinanceiro.DEBITO);
		caixaLancamentoEntity.setValorDebito(contaPagarEvent.getValorPago());
		
		FormaPagamento formaPagamento = FormaPagamento.DINHEIRO;
		try {
			if (contaPagarEvent != null && contaPagarEvent.getFormaPagamento() != null) {
				formaPagamento = FormaPagamento.valueOf(contaPagarEvent.getFormaPagamento().name());
			}
		} catch(Exception e) {
			log.error("Error converting FormaPagamento of: " + contaPagarEvent.getFormaPagamento().name(), e);
		}
		
		caixaLancamentoEntity.setFormaPagamento(formaPagamento);
		
		if (contaPagarEvent.getPlanoContas() != null) {
			caixaLancamentoEntity.setPlanoContas(planoContasRepository.findById(contaPagarEvent.getPlanoContas()).orElse(null));
		}
		
		caixaLancamentoEntity.setTipoFonteMovimento(TipoFonteMovimento.CONTAS_PAGAR);
		
		if (contaPagarEvent.getContaBancaria() != null) {
			caixaLancamentoEntity.setContaBancaria(contaBancariaRepository.findById(contaPagarEvent.getContaBancaria()).orElse(null));
		}
		
		if (contaPagarEvent.getCartaoCredito() != null) {
			caixaLancamentoEntity.setCartaoCredito(cartaoCreditoRepository.findById(contaPagarEvent.getCartaoCredito()).orElse(null));
		}
		
		if (contaPagarEvent.getFornecedor() != null) {
			caixaLancamentoEntity.setFornecedor(fornecedorRepository.findById(contaPagarEvent.getFornecedor()).orElse(null));
		}
		
		
		caixaLancamentoEntity.setDocumento(contaPagarEvent.getNumDocumento());
		
		caixaLancamentoService.create(caixaLancamentoEntity);
		
		log.info("Conta paga foi registrada no caixa com sucesso.");
	}
	
}
