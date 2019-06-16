package br.com.kerubin.api.financeiro.fluxocaixa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import br.com.kerubin.api.database.core.ServiceContext;

@SpringBootApplication(
		exclude = { 
		        DataSourceAutoConfiguration.class,
		        HibernateJpaAutoConfiguration.class,
		        DataSourceTransactionManagerAutoConfiguration.class
		}
		, scanBasePackages = { "br.com.kerubin.api" }
)
@EnableEurekaClient
public class FinanceiroFluxoCaixaApplication {

	public static void main(String[] args) {
		init();
		SpringApplication.run(FinanceiroFluxoCaixaApplication.class, args);
	}
	
	private static void init() {
		ServiceContext.setDefaultDomain(FinanceiroFluxoCaixaConstants.DOMAIN);
		ServiceContext.setDefaultService(FinanceiroFluxoCaixaConstants.SERVICE);
		// ServiceConnectionProvider.INSTANCE.setMigrateDefaultTenant(true);
	}
}
