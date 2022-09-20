package com.diplo.infraestructure.msreserva.amqp;

import com.diplo.sharedkernel.event.IListenerIntegrationConsumer;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.integrationevents.IntegrationCheckinCreado;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagada;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaVencida;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaConfirmada;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaConfirmadaRollback;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import com.diplo.sharedkernel.integrationevents.IntegrationVueloCreado;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

	@Autowired
	private IListenerIntegrationConsumer consumer;

	ObjectMapper mapper = new ObjectMapper();

	@RabbitListener(queues = "pago.deudavencida.reserva.vencerreserva")
	public void deudaVencida(String event) {
		IntegrationDeudaVencida message;
		try {
			message = mapper.readValue(event, IntegrationDeudaVencida.class);
			System.out.println("Recieved Message From RabbitMQ: " + event);
			System.out.println(
				"Recieved Message ReservaId a vencer " + message.getReservaId()
			);
			consumer.consume(new IntegrationEvent(message, null));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RabbitListener(queues = "pago.deudapagada.reserva.confirmarreserva")
	public void deudaPagada(String event) {
		IntegrationDeudaPagada message;
		try {
			message = mapper.readValue(event, IntegrationDeudaPagada.class);
			System.out.println("Recieved Message From RabbitMQ: " + event);
			System.out.println(
				"Recieved Message DeudaPagada de la reserva " +
				message.getReservaId()
			);
			consumer.consume(new IntegrationEvent(message, null));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RabbitListener(
		queues = "checkin.reservaconfirmadarollback.reserva.reservaconfirmadarollback"
	)
	public void reservaConfirmadaRollback(String event) {
		IntegrationReservaConfirmadaRollback message;
		try {
			message =
				mapper.readValue(
					event,
					IntegrationReservaConfirmadaRollback.class
				);
			System.out.println("Recieved Message From RabbitMQ: " + event);
			System.out.println(
				"Recieved Message reservaConfirmadaRollback de la reserva " +
				message.getReservaId()
			);
			consumer.consume(new IntegrationEvent(message, null));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RabbitListener(queues = "vuelo.VueloCreatedEvent.pago.crearVuelo")
	public void vueloCreado(String event) {
		IntegrationVueloCreado message;
		try {
			message = mapper.readValue(event, IntegrationVueloCreado.class);
			System.out.println("Recieved Message From RabbitMQ: " + event);
			System.out.println(
				"Recieved Message vueloCreado IdVuelo = " + message.getIdVuelo()
			);
			System.out.println(
				"Recieved Message vueloCreado destino " + message.getDestino()
			);
			System.out.println(
				"Recieved Message vueloCreado cantidadAsientos " +
				message.getCantidadAsientosDisponibles()
			);
			consumer.consume(new IntegrationEvent(message, null));
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//----TEST

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@RabbitListener(queues = "reserva.reservaconfirmada.checkin.crearcheckin")
	public void reservaConfirmada(String event) {
		try {
			IntegrationReservaConfirmada message;
			message =
				mapper.readValue(event, IntegrationReservaConfirmada.class);

			System.out.println("Recieved Message From RabbitMQ: " + event);
			System.out.println(
				"Recieved Message reservaConfirmada de la reserva " +
				message.getReservaId()
			);
			//consumer.consume(new IntegrationEvent(message, null));
			///--- Confirmacion de checkin
			/*
			String exchange = "checkin.checkincreado.exchange";
			String routinkey="checkin.chekincreado.pago.confirmarpago";
			IntegrationCheckinCreado newEvent = new IntegrationCheckinCreado(message.getReservaId());
			rabbitTemplate.convertAndSend(exchange, routinkey, mapper.writeValueAsString(newEvent));
			System.out.println("Send msg = " + newEvent.getReservaId());
			*/
			////--- Prueba de rollback
			String exchange = "checkin.reservaconfirmadarollback.exchange";
			String routinkey =
				"checkin.reservaconfirmadarollback.reserva.reservaconfirmadarollback";
			IntegrationReservaConfirmadaRollback newEvent = new IntegrationReservaConfirmadaRollback(
				message.getReservaId(),
				message.getPagoId()
			);
			rabbitTemplate.convertAndSend(
				exchange,
				routinkey,
				mapper.writeValueAsString(newEvent)
			);
			System.out.println("Send msg = " + newEvent.getReservaId());
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//----

}
