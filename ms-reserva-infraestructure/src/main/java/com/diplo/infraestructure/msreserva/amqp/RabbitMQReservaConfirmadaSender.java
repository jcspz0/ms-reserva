package com.diplo.infraestructure.msreserva.amqp;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.sharedkernel.amqp.IAmqpMessage;
import com.diplo.sharedkernel.amqp.IAmqpProducer;
import com.diplo.sharedkernel.amqp.MasstransitEvent;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaConfirmada;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.HashMap;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQReservaConfirmadaSender
	implements IAmqpProducer<IntegrationReservaConfirmada> {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	ObjectMapper Obj = new ObjectMapper();

	/*	
	@Value("reserva.crearreserva.exchange")
	private String exchange;
	
	@Value("reserva.reservacreada.pago.creardeuda")
	private String routingkey;	
*/
	@Override
	public void sendMessage(
		IAmqpMessage message,
		String exchange,
		String queue,
		String routingkey
	) {
		//rabbitTemplate.convertAndSend(this.exchange, "", message.getMessage());
		try {
			MasstransitEvent masstransitEvent = new MasstransitEvent();
			masstransitEvent.setMessageType(
				Arrays.asList(
					"urn:message:ShareKernel.IntegrationEvents:ReservaCreado"
				)
			);
			IntegrationReservaConfirmada data = (IntegrationReservaConfirmada) message.getMessage();
			HashMap<String, Object> mapData = new HashMap<>();
			mapData.put("reservaId", data.getReservaId());
			mapData.put("cantidadPasajeros", data.getCantidadPasajeros());
			mapData.put("hora", data.getHora());
			mapData.put("vueloId", data.getVueloId());
			mapData.put("destino", data.getDestino());
			mapData.put("origen", data.getOrigen());
			mapData.put("nroDoc", data.getNroDoc());
			mapData.put("tipoDoc", data.getTipoDoc());
			mapData.put(
				"nombreCompletoPasajero",
				data.getNombreCompletoPasajero()
			);
			mapData.put("pagoId", data.getPagoId());

			masstransitEvent.setMessage(mapData);
			//rabbitTemplate.convertAndSend(exchange, routingkey, Obj.writeValueAsString((IntegrationReservaConfirmada) message.getMessage()));
			rabbitTemplate.convertAndSend(
				exchange,
				routingkey,
				Obj.writeValueAsString(masstransitEvent)
			);
			System.out.println(
				"Send msg = " +
				Obj.writeValueAsString(
					(IntegrationReservaConfirmada) message.getMessage()
				)
			);
		} catch (AmqpException | JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
