package br.com.kerubin.api.financeiro.fluxocaixa.event.user;

import java.util.UUID;

import javax.inject.Inject;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.stereotype.Service;

import br.com.kerubin.api.financeiro.fluxocaixa.entity.caixa.CaixaRepository;
import br.com.kerubin.api.messaging.core.DomainMessage;
import br.com.kerubin.api.messaging.utils.DomainEventUtils;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SecurityAuthorizationEventHandler {
	
	@Inject
	private CaixaRepository caixaRepository;
	
	public void doHandleEvent(DomainMessage message) {
		try {
			switch (message.getPrimitive()) {
			case UserAccountConfirmedEvent.USER_ACCOUNT_CONFIRMED_EVENT:
				handleUserAccountConfirmedEvent((UserAccountConfirmedEvent) DomainEventUtils.bytesToDto(message.getPayload(), UserAccountConfirmedEvent.class));
				break;

			default:
				break;
			}
		} catch(Exception e) {
			log.error("Error receiven event with envelope: " + message, e);
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}

	private void handleUserAccountConfirmedEvent(UserAccountConfirmedEvent payload) {
		log.info("Handling event: {} with payload: {}", UserAccountConfirmedEvent.USER_ACCOUNT_CONFIRMED_EVENT, payload);
		
		try {
			// NOTE: this will force database migration very late to prepare it for users.
			boolean exists = caixaRepository.existsById(UUID.fromString("bd1e9cb7-e7f6-40da-af5c-1f461dac1d11"));
			log.info("Caixa entity id: \"bd1e9cb7-e7f6-40da-af5c-1f461dac1d11\", exists: {} in database.", exists);
		} catch(Exception e) {
			log.warn("Error finding Caixa entity with id: \"bd1e9cb7-e7f6-40da-af5c-1f461dac1d11\" in database, error: " + e.getMessage(), e);
		}
	}


}
