
package br.com.kerubin.api.financeiro.fluxocaixa.event.user;

import java.text.MessageFormat;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.kerubin.api.financeiro.fluxocaixa.FinanceiroFluxoCaixaConstants;
import br.com.kerubin.api.messaging.core.DomainEvent;
import br.com.kerubin.api.messaging.utils.DomainEventUtils;

@ComponentScan({"br.com.kerubin.api.messaging.core"})
@Configuration
public class SecurityAuthorizationSubscriberEventRabbitConfig {
	
	@Bean
	public TopicExchange securityAuthorizationTopic() {
		String topicName = DomainEventUtils.mountTopicName(SecurityAuthorizationConstants.DOMAIN, 
				SecurityAuthorizationConstants.SERVICE);
		
		return new TopicExchange(topicName);
	}
	
	@Bean
	public Queue securityAuthorizationQueue() {
		// This service queue name for subscribe to the entity owner exchange topic.
		String queueName = MessageFormat.format("{0}_{1}_{2}_for_{3}_{4}", //
			DomainEvent.APPLICATION, //
			FinanceiroFluxoCaixaConstants.DOMAIN, //
			FinanceiroFluxoCaixaConstants.SERVICE, //
			SecurityAuthorizationConstants.DOMAIN, //
			SecurityAuthorizationConstants.SERVICE);
		
		return new Queue(queueName, true);
	}
	
	@Bean
	public Binding securityAuthorizationQueueBinding(@Qualifier("securityAuthorizationTopic") TopicExchange topic, 
			@Qualifier("securityAuthorizationQueue") Queue queue) {
		
		String rountingKey = DomainEventUtils.mountRoutingKey(SecurityAuthorizationConstants.DOMAIN, //
				SecurityAuthorizationConstants.SERVICE, UserAccountConfirmedEvent.USER_ACCOUNT_CONFIRMED_EVENT);
		
		return BindingBuilder //
				.bind(queue) //
				.to(topic) //
				.with(rountingKey);
	}
	
	

}
