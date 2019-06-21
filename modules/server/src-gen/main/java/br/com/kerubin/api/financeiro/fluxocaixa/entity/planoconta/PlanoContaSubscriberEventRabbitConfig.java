/**********************************************************************************************
Code generated with MKL Plug-in version: 3.17.1
Code generated at time stamp: 2019-06-20T23:36:05.212
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.planoconta;

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

import br.com.kerubin.api.financeiro.planocontas.FinanceiroPlanoContasConstants;

@ComponentScan({"br.com.kerubin.api.messaging.core"})
@Configuration
public class PlanoContaSubscriberEventRabbitConfig {
	
	private static final String ENTITY_NAME = "PlanoConta";
	private static final String ENTITY_KEY = "entity";
	
	@Bean
	public TopicExchange planoContaTopic() {
		String topicName = MessageFormat.format("{0}_{1}_{2}_{3}", 
			DomainEvent.APPLICATION, FinanceiroPlanoContasConstants.DOMAIN, 
			FinanceiroPlanoContasConstants.SERVICE, DomainEntityEventsPublisher.TOPIC_PREFFIX);
		
		return new TopicExchange(topicName);
	}
	
	@Bean
	public Queue planoContaQueue() {
		String queueName = MessageFormat.format("{0}_{1}_{2}_{3}_{4}", 
			DomainEvent.APPLICATION, FinanceiroPlanoContasConstants.DOMAIN, 
			FinanceiroPlanoContasConstants.SERVICE, ENTITY_KEY, ENTITY_NAME);
		
		return new Queue(queueName, true);
	}
	
	@Bean
	public Binding planoContaBinding(@Qualifier("planoContaTopic") TopicExchange topic, 
			@Qualifier("planoContaQueue") Queue queue) {
		
		String rountingKey = MessageFormat.format("{0}.{1}.{2}.{3}.{4}", 
				DomainEvent.APPLICATION, FinanceiroPlanoContasConstants.DOMAIN, 
				FinanceiroPlanoContasConstants.SERVICE, ENTITY_KEY, ENTITY_NAME);
		
		return BindingBuilder
				.bind(queue)
				.to(topic)
				.with(rountingKey);
	}

}
