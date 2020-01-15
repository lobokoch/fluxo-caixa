package br.com.kerubin.api.financeiro.fluxocaixa;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import br.com.kerubin.api.financeiro.fluxocaixa.custom.TestVisitorImpl;

@TestConfiguration
public class CustomTestConfig {
	
	@Bean
	public TestVisitor testVisitor() {
		return new TestVisitorImpl();
	}
	

}
