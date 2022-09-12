package com.diplo.infraestructure.msreserva.amqp;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.sharedkernel.amqp.IAmqpMessage;
import com.diplo.sharedkernel.amqp.IAmqpProducer;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagadaRollback;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaConfirmada;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQDeudaPagadaRollbackSender
	implements IAmqpProducer<IntegrationDeudaPagadaRollback> {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Override
	public void sendMessage(
		IAmqpMessage message,
		String exchange,
		String queue,
		String routingkey
	) {
		//rabbitTemplate.convertAndSend(this.exchange, "", message.getMessage());
		rabbitTemplate.convertAndSend(
			exchange,
			routingkey,
			message.getMessage()
		);
		System.out.println("Send msg = " + message.getMessage());
	}
}
