package com.diplo.application.msreserva.usecase.producer.integration;

import com.diplo.msreserva.event.ReservaCreada;
import com.diplo.sharedkernel.amqp.AmqpMessage;
import com.diplo.sharedkernel.amqp.IAmqpMessage;
import com.diplo.sharedkernel.amqp.IAmqpProducer;
import com.diplo.sharedkernel.event.IProducer;
import com.diplo.sharedkernel.event.IntegrationMessage;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagadaRollback;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaConfirmada;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ProducerIntegrationDeudaPagadaRollback
	implements IProducer<IntegrationDeudaPagadaRollback> {

	@Autowired
	private IAmqpProducer<IntegrationDeudaPagadaRollback> amqp;

	@Value("reserva.deudapagadarollback.pago.deudapagadarollback")
	String queueName;

	@Value("reserva.deudapagadarollback.exchange")
	String exchange;

	@Value("reserva.deudapagadarollback.pago.deudapagadarollback")
	private String routingkey;

	@EventListener
	public void onProcessEvent(IntegrationDeudaPagadaRollback event) {
		System.out.println(
			"ProducerIntegrationDeudaPagadaRollback, llego evento de integracion " +
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
