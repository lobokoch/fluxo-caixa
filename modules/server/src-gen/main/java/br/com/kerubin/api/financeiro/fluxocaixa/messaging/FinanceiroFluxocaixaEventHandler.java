package br.com.kerubin.api.financeiro.fluxocaixa.messaging;

import br.com.kerubin.api.messaging.core.DomainMessage;

public interface FinanceiroFluxocaixaEventHandler {

	void handleEvent(DomainMessage message);

}
