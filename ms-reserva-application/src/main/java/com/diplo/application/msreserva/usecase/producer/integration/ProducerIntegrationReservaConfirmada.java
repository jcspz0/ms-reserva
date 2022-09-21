package com.diplo.application.msreserva.usecase.producer.integration;

import com.diplo.msreserva.event.ReservaCreada;
import com.diplo.sharedkernel.amqp.AmqpMessage;
import com.diplo.sharedkernel.amqp.IAmqpMessage;
import com.diplo.sharedkernel.amqp.IAmqpProducer;
import com.diplo.sharedkernel.event.IProducer;
import com.diplo.sharedkernel.event.IntegrationMessage;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaConfirmada;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProducerIntegrationReservaConfirmada
	implements IProducer<IntegrationReservaConfirmada> {

	@Autowired
	private IAmqpProducer<IntegrationReservaConfirmada> amqp;

	@Value("reserva.reservaconfirmada.checkin.crearcheckin")
	String queueName;

	@Value("reserva.reservaconfirmada.exchange")
	String exchange;

	@Value("reserva.reservaconfirmada.checkin.crearcheckin")
	private String routingkey;

	@EventListener
	public void onProcessEvent(IntegrationReservaConfirmada event) {
		System.out.println(
			"ProducerIntegrationReservaConfirmada, llego evento de integracion " +
			event
		);
		amqp.sendMessage(
			new AmqpMessage(event),
			exchange,
			queueName,
			routingkey
		);
	}
}
