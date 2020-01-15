/**********************************************************************************************
Code generated with MKL Plug-in version: 47.8.0
Code generated at time stamp: 2020-01-13T08:12:24.691
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.cartaocredito;

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

import br.com.kerubin.api.cadastros.banco.CadastrosBancoConstants;
import br.com.kerubin.api.financeiro.fluxocaixa.FinanceiroFluxoCaixaConstants;

@ComponentScan({"br.com.kerubin.api.messaging.core"})
@Configuration
public class CartaoCreditoSubscriberEventRabbitConfig {
	
	private static final String ENTITY_NAME = "CartaoCredito";
	private static final String ENTITY_KEY = "entity";
	
	@Bean
	public TopicExchange cartaoCreditoTopic() {
		String topicName = MessageFormat.format("{0}_{1}_{2}_{3}", //
			DomainEvent.APPLICATION, //
			CadastrosBancoConstants.DOMAIN, //
			CadastrosBancoConstants.SERVICE, //
			DomainEntityEventsPublisher.TOPIC_PREFFIX);
		
		return new TopicExchange(topicName);
	}
	
	@Bean
	public Queue cartaoCreditoQueue() {
		// This service queue name for subscribe to the entity owner exchange topic.
		String queueName = MessageFormat.format("{0}_{1}_{2}_{3}_{4}", //
			DomainEvent.APPLICATION, //
			FinanceiroFluxoCaixaConstants.DOMAIN, //
			FinanceiroFluxoCaixaConstants.SERVICE, //
			ENTITY_KEY, //
			ENTITY_NAME);
		
		return new Queue(queueName, true);
	}
	
	@Bean
	public Binding cartaoCreditoBinding(@Qualifier("cartaoCreditoTopic") TopicExchange topic, 
			@Qualifier("cartaoCreditoQueue") Queue queue) {
		
		String rountingKey = MessageFormat.format("{0}.{1}.{2}.{3}.{4}", //
				DomainEvent.APPLICATION, //
				CadastrosBancoConstants.DOMAIN, //
				CadastrosBancoConstants.SERVICE, //
				ENTITY_KEY, //
				ENTITY_NAME);
		
		return BindingBuilder
				.bind(queue) //
				.to(topic) //
				.with(rountingKey);
	}

}
