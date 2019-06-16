/**********************************************************************************************
Code generated with MKL Plug-in version: 3.10.14
Code generated at time stamp: 2019-06-16T09:08:50.464
Copyright: Kerubin - logokoch@gmail.com

WARNING: DO NOT CHANGE THIS CODE BECAUSE THE CHANGES WILL BE LOST IN THE NEXT CODE GENERATION.
***********************************************************************************************/

package br.com.kerubin.api.financeiro.fluxocaixa.entity.contabancaria;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.kerubin.api.cadastros.banco.entity.contabancaria.ContaBancariaEvent;

import br.com.kerubin.api.messaging.core.DomainEventEnvelope;

@Service
public class ContaBancariaSubscriberEventHandler {
	
	private static final Logger log = LoggerFactory.getLogger(ContaBancariaSubscriberEventHandler.class);
	
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	
	@Autowired
	private ContaBancariaService contaBancariaService;
	
	@Autowired
	ContaBancariaDTOConverter contaBancariaDTOConverter;
	
	@RabbitListener(queues = "#{contaBancariaQueue.name}")
	public void onReceiveEvent(DomainEventEnvelope<ContaBancariaEvent> envelope) {
		try {
			switch (envelope.getPrimitive()) {
			case ContaBancariaEvent.CONTA_BANCARIA_CREATED:
			case ContaBancariaEvent.CONTA_BANCARIA_UPDATED:
			
				saveContaBancaria(envelope.getPayload());
				break;
			
			case ContaBancariaEvent.CONTA_BANCARIA_DELETED:
				deleteContaBancaria(envelope.getPayload());
				break;
			
			default:
				log.warn("Unexpected entity event: {} for: {}.", envelope.getPrimitive(), "br.com.kerubin.api.cadastros.banco.entity.contabancaria.ContaBancaria");
				break;
			}
		} catch(Exception e) {
			log.error("Error receiven event with envelope: " + envelope, e);
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}
	
	private void saveContaBancaria(ContaBancariaEvent contaBancariaEvent) {
		saveContaBancaria(contaBancariaEvent, false);
	}
	
	private void saveContaBancaria(ContaBancariaEvent contaBancariaEvent, boolean isDeleted) {
		ContaBancariaEntity entity = buildContaBancariaEntity(contaBancariaEvent);
		Optional<ContaBancariaEntity> optionalEntity = contaBancariaRepository.findById(contaBancariaEvent.getId());
		if (optionalEntity.isPresent()) {
			if (isDeleted) {
				entity = optionalEntity.get();
				entity.setDeleted(true);
			}
			contaBancariaService.update(entity.getId(), entity);
		}
		else {
			contaBancariaService.create(entity);
		}
	}
	
	private void deleteContaBancaria(ContaBancariaEvent contaBancariaEvent) {
		Optional<ContaBancariaEntity> optionalEntity = contaBancariaRepository.findById(contaBancariaEvent.getId());
		if (optionalEntity.isPresent()) {
			try {
				contaBancariaService.delete(contaBancariaEvent.getId());
			} catch(DataIntegrityViolationException e) {
				saveContaBancaria(contaBancariaEvent, true);
			}
		}
	}

	private ContaBancariaEntity buildContaBancariaEntity(ContaBancariaEvent contaBancariaEvent) {
		ContaBancariaEntity entity = contaBancariaDTOConverter.convertDtoToEntity(contaBancariaEvent);
		return entity;
	}

}
