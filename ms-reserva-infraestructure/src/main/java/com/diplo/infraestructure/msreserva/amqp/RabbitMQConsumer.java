package com.diplo.infraestructure.msreserva.amqp;

import com.diplo.sharedkernel.event.IListenerIntegrationConsumer;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.integrationevents.IntegrationCheckinCreado;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagada;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaVencida;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaConfirmada;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaConfirmadaRollback;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

	@Autowired
	private IListenerIntegrationConsumer consumer;

	@RabbitListener(queues = "pago.deudavencida.reserva.vencerreserva")
	public void deudaVencida(IntegrationDeudaVencida event) {
		System.out.println("Recieved Message From RabbitMQ: " + event);
		System.out.println(
			"Recieved Message ReservaId a vencer " + event.getReservaId()
		);
		consumer.consume(new IntegrationEvent(event, null));
	}

	@RabbitListener(queues = "pago.deudapagada.reserva.confirmarreserva")
	public void deudaPagada(IntegrationDeudaPagada event) {
		System.out.println("Recieved Message From RabbitMQ: " + event);
		System.out.println(
			"Recieved Message DeudaPagada de la reserva " + event.getReservaId()
		);
		consumer.consume(new IntegrationEvent(event, null));
	}

	@RabbitListener(
		queues = "checkin.reservaconfirmadarollback.reserva.reservaconfirmadarollback"
	)
	public void reservaConfirmadaRollback(
		IntegrationReservaConfirmadaRollback event
	) {
		System.out.println("Recieved Message From RabbitMQ: " + event);
		System.out.println(
			"Recieved Message reservaConfirmadaRollback de la reserva " +
			event.getReservaId()
		);
		consumer.consume(new IntegrationEvent(event, null));
	}

	//----TEST

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@RabbitListener(queues = "reserva.reservaconfirmada.checkin.crearcheckin")
	public void reservaConfirmada(IntegrationReservaConfirmada event) {
		System.out.println("Recieved Message From RabbitMQ: " + event);
		System.out.println(
			"Recieved Message reservaConfirmada de la reserva " +
			event.getReservaId()
		);
		//consumer.consume(new IntegrationEvent(event, null));
		///--- Confirmacion de checkin

		String exchange = "checkin.checkincreado.exchange";
		String routinkey = "checkin.chekincreado.pago.confirmarpago";
		IntegrationCheckinCreado newEvent = new IntegrationCheckinCreado(
			event.getReservaId()
		);
		rabbitTemplate.convertAndSend(exchange, routinkey, newEvent);
		System.out.println("Send msg = " + newEvent.getReservaId());
		////--- Prueba de rollback
		/*String exchange = "checkin.reservaconfirmadarollback.exchange";
		String routinkey="checkin.reservaconfirmadarollback.reserva.reservaconfirmadarollback";
		IntegrationReservaConfirmadaRollback newEvent = new IntegrationReservaConfirmadaRollback(event.getReservaId(), event.getPagoId());
		rabbitTemplate.convertAndSend(exchange, routinkey, newEvent);
		System.out.println("Send msg = " + newEvent.getReservaId());
		*/
	}
	//----

}
