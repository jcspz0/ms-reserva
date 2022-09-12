package com.diplo.application.msreserva.usecase.producer.integration;

import com.diplo.msreserva.event.ReservaCreada;
import com.diplo.sharedkernel.amqp.AmqpMessage;
import com.diplo.sharedkernel.amqp.IAmqpMessage;
import com.diplo.sharedkernel.amqp.IAmqpProducer;
import com.diplo.sharedkernel.event.IProducer;
import com.diplo.sharedkernel.event.IntegrationMessage;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProducerIntegrationReservaCreada
	implements IProducer<IntegrationReservaCreada> {

	@Autowired
	private IAmqpProducer<IntegrationReservaCreada> amqp;

	@Value("reserva.reservacreada.pago.creardeuda")
	String queueName;

	@Value("reserva.reservacreada.exchange")
	String exchange;

	@Value("reserva.reservacreada.pago.creardeuda")
	private String routingkey;

	@EventListener
	public void onProcessEvent(IntegrationReservaCreada event) {
		System.out.println(
			"ProducerIntegrationReservaCreada, llego evento de integracion " +
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
