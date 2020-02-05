package br.com.kerubin.api.financeiro.fluxocaixa.messaging;

public class EventMessageNotHandledException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EventMessageNotHandledException(String message) {
		super(message);
	}

}
