package br.com.kerubin.api.financeiro.fluxocaixa.messaging.handler;

import java.text.MessageFormat;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.com.kerubin.api.financeiro.fluxocaixa.event.user.SecurityAuthorizationConstants;
import br.com.kerubin.api.financeiro.fluxocaixa.event.user.SecurityAuthorizationEventHandler;
import br.com.kerubin.api.financeiro.fluxocaixa.messaging.EventMessageNotHandledException;
import br.com.kerubin.api.financeiro.fluxocaixa.messaging.FinanceiroFluxoCaixaEventHandler;
import br.com.kerubin.api.messaging.core.DomainMessage;


@Service
public class FinanceiroFluxocaixaEventHandlerImpl implements FinanceiroFluxoCaixaEventHandler {
	
	@Inject
	private SecurityAuthorizationEventHandler securityAuthorizationEventHandler;
	
	@Override
	public void handleEvent(DomainMessage message) {
		String domain = message.getDomain();
		String service = message.getService();
		
		if (SecurityAuthorizationConstants.DOMAIN.equals(domain) && SecurityAuthorizationConstants.SERVICE.equals(service)) {
			securityAuthorizationEventHandler.doHandleEvent(message);
			return;
		}
		
		
		throw new EventMessageNotHandledException(MessageFormat.format("Event message not handled yet: {0}", message));
	}

}
