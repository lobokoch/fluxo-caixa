package br.com.kerubin.api.financeiro.fluxocaixa.messaging;

import java.text.MessageFormat;

import javax.inject.Inject;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import br.com.kerubin.api.messaging.core.DomainMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * Starts handling all received messages from RabbitMQ for the queue financeiroFluxoCaixaQueue.
 * WARNING: The FinanceiroFluxocaixaEventHandler interface must be implemented by the implementation module.
 * */
@Slf4j
@Service
public class FinanceiroFluxocaixaListenerEventHandler {
	
	@Inject
	private FinanceiroFluxocaixaEventHandler financeiroFluxocaixaEventHandler;
	
	@RabbitListener(queues = "#{financeiroFluxoCaixaQueue.name}")
	public void onHandleEventListener(DomainMessage message) {
		log.info("Handling event listener received message from broker RabbitMQ: {}", message);
		try {
			financeiroFluxocaixaEventHandler.handleEvent(message);
		} catch(Exception e) {
			log.error(MessageFormat.format("Error: {0}, handling message: {1}", e.getMessage(), message), e);
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}
	
}
