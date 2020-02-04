package br.com.kerubin.api.financeiro.fluxocaixa.config;

import java.text.MessageFormat;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.kerubin.api.financeiro.fluxocaixa.FinanceiroFluxoCaixaConstants;
import br.com.kerubin.api.messaging.core.DomainEvent;

@ComponentScan({"br.com.kerubin.api.messaging.core"})
@Configuration
public class BrokerConfig {
	
	@Bean
	public Queue financeiroFluxoCaixaQueue() {
		// Default queue for this service.
		String queueName = MessageFormat.format("{0}_{1}_{2}", //
			DomainEvent.APPLICATION, //
			FinanceiroFluxoCaixaConstants.DOMAIN, //
			FinanceiroFluxoCaixaConstants.SERVICE);
		
		return new Queue(queueName, true);
	}

}
