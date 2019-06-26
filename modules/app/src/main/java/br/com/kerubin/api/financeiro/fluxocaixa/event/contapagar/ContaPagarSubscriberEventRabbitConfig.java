/**********************************************************************************************
Code generated with MKL Plug-in version: 5.0.3
Code generated at time stamp: 2019-06-24T22:01:00.414
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.event.contapagar;

import java.text.MessageFormat;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.kerubin.api.messaging.core.DomainEntityEventsPublisher;
import br.com.kerubin.api.messaging.core.DomainEvent;


@ComponentScan({"br.com.kerubin.api.messaging.core"})
@Configuration
public class ContaPagarSubscriberEventRabbitConfig {
	
	private static final String ENTITY_NAME = "ContaPagar";
	private static final String ENTITY_KEY = "entity";
	
	@Bean
	public TopicExchange contaPagarTopic() {
		String topicName = MessageFormat.format("{0}_{1}_{2}_{3}", 
			DomainEvent.APPLICATION, FinanceiroContasPagarConstants.DOMAIN, 
			FinanceiroContasPagarConstants.SERVICE, DomainEntityEventsPublisher.TOPIC_PREFFIX);
		
		return new TopicExchange(topicName);
	}
	
	@Bean
	public Queue contaPagarQueue() {
		String queueName = MessageFormat.format("{0}_{1}_{2}_{3}_{4}", 
			DomainEvent.APPLICATION, FinanceiroContasPagarConstants.DOMAIN, 
			FinanceiroContasPagarConstants.SERVICE, ENTITY_KEY, ENTITY_NAME);
		
		return new Queue(queueName, true);
	}
	
	@Bean
	public Binding contaPagarBinding(@Qualifier("contaPagarTopic") TopicExchange topic, 
			@Qualifier("contaPagarQueue") Queue queue) {
		
		String rountingKey = MessageFormat.format("{0}.{1}.{2}.{3}.{4}", 
				DomainEvent.APPLICATION, FinanceiroContasPagarConstants.DOMAIN, 
				FinanceiroContasPagarConstants.SERVICE, ENTITY_KEY, ENTITY_NAME);
		
		return BindingBuilder
				.bind(queue)
				.to(topic)
				.with(rountingKey);
	}

}
