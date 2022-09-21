package com.diplo.application.msreserva.usecase.consumer.integration;

import com.diplo.application.msreserva.usecase.command.reserva.confirmarreserva.ConfirmarReservaCommand;
import com.diplo.application.msreserva.usecase.command.reserva.confirmarreserva.ConfirmarReservaHandler;
import com.diplo.application.msreserva.usecase.command.reserva.crearvuelo.CrearVueloCommand;
import com.diplo.application.msreserva.usecase.command.reserva.crearvuelo.CrearVueloHandler;
import com.diplo.application.msreserva.usecase.command.reserva.vencerreserva.VencerReservaCommand;
import com.diplo.application.msreserva.usecase.command.reserva.vencerreserva.VencerReservaHandler;
import com.diplo.msreserva.repository.IPasajeroRepository;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.sharedkernel.amqp.IAmqpConsumer;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagada;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaVencida;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import com.diplo.sharedkernel.integrationevents.IntegrationVueloCreado;
import com.diplo.sharedkernel.mediator.IMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerIntegrationVueloCreado
	implements IAmqpConsumer<IntegrationVueloCreado> {

	@Autowired
	private IMediator _mediator;

	@Autowired
	private IUnitOfWork _unitOfWork;

	@Autowired
	private IVueloRepository _vueloRepository;

	@Override
	@EventListener
	public void Consume(IntegrationVueloCreado message) {
		// TODO Auto-generated method stub
		try {
			System.out.println(
				"ConsumerIntegrationVueloCreado, llego evento de integracion " +
				message
			);
			CrearVueloCommand command = new CrearVueloCommand(
				message.getIdVuelo(),
				message.getIdVuelo(),
				message.getCantidadAsientosDisponibles(),
				message.getOrigen(),
				message.getDestino()
			);
			CrearVueloHandler handler = new CrearVueloHandler(
				_vueloRepository,
				_unitOfWork
			);
			_mediator.registrarComando(command, handler);
			_mediator.Send(command);
		} catch (Exception e) {
			e.printStackTrace();
			//aqui ejecutar el comando rollback SAGA
		}
	}
}
