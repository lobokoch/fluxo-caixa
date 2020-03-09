package br.com.kerubin.api.financeiro.fluxocaixa.analytics;

import static br.com.kerubin.api.messaging.constants.MessagingConstants.HEADER_TENANT;
import static br.com.kerubin.api.messaging.constants.MessagingConstants.HEADER_USER;

import java.text.MessageFormat;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.kerubin.api.database.core.ServiceContext;
import br.com.kerubin.api.database.core.ServiceContextData;
import br.com.kerubin.api.financeiro.fluxocaixa.analytics.model.MonthlySumContas;
import br.com.kerubin.api.financeiro.fluxocaixa.analytics.model.MonthlySumContasPagar;
import br.com.kerubin.api.financeiro.fluxocaixa.analytics.model.MonthlySumContasReceber;
import br.com.kerubin.api.servicecore.util.stopwatch.StopWatch;
import br.com.kerubin.api.servicecore.util.stopwatch.TaskInfo;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ContasProviderImpl implements ContasProvider {
	
	private static final int MAX_RETRIES = 3;
	
	public static final String FINANCEIRO_CONTASPAGAR_SERVICE = "financeiro-contaspagar/financeiro/contas_pagar/";
	public static final String FINANCEIRO_CONTASRECEBER_SERVICE = "financeiro-contasreceber/financeiro/contas_receber/";
	//public static final String FINANCEIRO_FLUXOCAIXA_SERVICE = "financeiro-fluxocaixa/financeiro/fluxo_caixa/";
	//public static final String SECURITY_AUTHORIZATION_SERVICE = "security-authorization/security/authorization/";
	public static final String HTTP = "http://";
	public static final String DASHBOARD = "dashboard";
	
	@Inject
	private RestTemplate restTemplate;
	
	@Override
	public MonthlySumContas getMonthlySumContas() {
		final String taskName = "getMonthlySumContas";
		StopWatch stopWatch = new StopWatch();
		stopWatch.start(taskName);
		
		ServiceContextData serviceContext = ServiceContext.getServiceContextData();
		
		CompletableFuture<MonthlySumContasPagar> monthlySumContasPagarFuture = CompletableFuture.supplyAsync(() -> getMonthlySumContasPagar(serviceContext, stopWatch));
		CompletableFuture<MonthlySumContasReceber> monthlySumContasReceberFuture = CompletableFuture.supplyAsync(() -> getMonthlySumContasReceber(serviceContext, stopWatch));
		
		try {
			MonthlySumContasPagar monthlySumContasPagar = monthlySumContasPagarFuture.get();
			MonthlySumContasReceber MonthlySumContasReceber = monthlySumContasReceberFuture.get();
			
			MonthlySumContas result = new MonthlySumContas(monthlySumContasPagar, MonthlySumContasReceber);
			
			TaskInfo task = stopWatch.stop(taskName);
			log.info("stopWatch: {}, Tempo total millis: {}, em segundos: {}.", 
					task.getTaskName(), task.getTimeMillis(), task.getTimeSeconds());
			
			return result;
		} catch (Exception e) {
			log.error(MessageFormat.format("Error executing getMonthlySumContas: ", 
					e.getMessage()), e);
		}
		
		return null;
		
	}
	

	private MonthlySumContasReceber getMonthlySumContasReceber(ServiceContextData serviceContext, StopWatch stopWatch) {
		final String taskName = "getMonthlySumContasReceber";
		
		stopWatch.start(taskName);
		
		String tenant = serviceContext.getTenant();
		String username = serviceContext.getUsername();
		String url = HTTP + FINANCEIRO_CONTASRECEBER_SERVICE + "/" + DASHBOARD + "/getMonthlySumContasReceber";
		
		String logMsg = "Tenant: " + tenant + ", username: " + username + ", URL: " + url;
		
		log.info("Starting getMonthlySumContasReceber for: {}...", logMsg);
		
        HttpEntity<String> httpEntity = buildHttpHeaders(tenant, username);
        ResponseEntity<MonthlySumContasReceber> response = null;
        
        int attempts = 0;
		boolean success = false;
		while (!success && attempts < MAX_RETRIES) {
			attempts++;
			try {
				log.info("Trying {} attempts getting data from: {}...", attempts, logMsg);
				
				response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
						new ParameterizedTypeReference<MonthlySumContasReceber>() {
				});
				
				success = true;
				
			} catch (Exception e) {
				log.error(attempts + " errors getting data from: " + logMsg, e);
			}
		} // while
		
		if (!success) {
			log.warn("FAIL with " + attempts + " attempts getting data from: " + logMsg);
			return new MonthlySumContasReceber(); //empty
		}
		
		log.info("SUCCESS with " + attempts + " attempts getting data from: " + logMsg);
		
		MonthlySumContasReceber result = response.getBody();
		
		log.info("Result at getMonthlySumContasReceber for tenant: {} and user: {}, result: {}", tenant,  username, result);
		
		TaskInfo task = stopWatch.stop(taskName);
		log.info("stopWatch: {}, Tempo total millis: {}, em segundos: {}.", 
				task.getTaskName(), task.getTimeMillis(), task.getTimeSeconds());
		
		return result;
	}

	private MonthlySumContasPagar getMonthlySumContasPagar(ServiceContextData serviceContext, StopWatch stopWatch) {
		final String taskName = "getMonthlySumContasPagar"; 
		stopWatch.start(taskName);
		
		String tenant = serviceContext.getTenant();
		String username = serviceContext.getUsername();
		String url = HTTP + FINANCEIRO_CONTASPAGAR_SERVICE + "/" + DASHBOARD + "/getMonthlySumContasPagar";
		
		String logMsg = "Tenant: " + tenant + ", username: " + username + ", URL: " + url;
		
		log.info("Starting getMonthlySumContasPagar for: {}...", logMsg);
		
        HttpEntity<String> httpEntity = buildHttpHeaders(tenant, username);
        ResponseEntity<MonthlySumContasPagar> response = null;
        
        int attempts = 0;
		boolean success = false;
		while (!success && attempts < MAX_RETRIES) {
			attempts++;
			try {
				log.info("Trying {} attempts getting data from: {}...", attempts, logMsg);
				
				response = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
						new ParameterizedTypeReference<MonthlySumContasPagar>() {
				});
				
				success = true;
				
			} catch (Exception e) {
				log.error(attempts + " errors getting data from: " + logMsg, e);
			}
		} // while
		
		if (!success) {
			log.warn("FAIL with " + attempts + " attempts getting data from: " + logMsg);
			return new MonthlySumContasPagar(); //empty
		}
		
		log.info("SUCCESS with " + attempts + " attempts getting data from: " + logMsg);
		
		MonthlySumContasPagar result = response.getBody();
		
		log.info("Result at getMonthlySumContasPagar for tenant: {} and user: {}, result: {}", tenant,  username, result);
		
		TaskInfo task = stopWatch.stop(taskName);
		log.info("stopWatch: {}, Tempo total millis: {}, em segundos: {}.", 
				task.getTaskName(), task.getTimeMillis(), task.getTimeSeconds());
		
		return result;
	}

	
	private HttpEntity<String> buildHttpHeaders(String tenant, String username) {
		HttpHeaders headers = new HttpHeaders();
		headers.set(HEADER_TENANT, tenant);
        headers.set(HEADER_USER, username);
        
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
        return httpEntity;
	}

	
}
