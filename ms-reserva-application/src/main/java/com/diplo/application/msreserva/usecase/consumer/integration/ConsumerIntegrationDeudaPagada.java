package com.diplo.application.msreserva.usecase.consumer.integration;

import com.diplo.application.msreserva.usecase.command.reserva.confirmarreserva.ConfirmarReservaCommand;
import com.diplo.application.msreserva.usecase.command.reserva.confirmarreserva.ConfirmarReservaHandler;
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
import com.diplo.sharedkernel.mediator.IMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerIntegrationDeudaPagada
	implements IAmqpConsumer<IntegrationDeudaPagada> {

	@Autowired
	private IMediator _mediator;

	@Autowired
	private IUnitOfWork _unitOfWork;

	@Autowired
	private IReservaRepository _reservaRepository;

	@Autowired
	private IVueloRepository _vueloRepository;

	@Autowired
	private IPasajeroRepository _pasajeroRepository;

	@Override
	@EventListener
	public void Consume(IntegrationDeudaPagada message) {
		// TODO Auto-generated method stub
		try {
			System.out.println(
				"ConsumerIntegrationDeudaPagada, llego evento de integracion " +
				message
			);
			ConfirmarReservaCommand confirmarReservaCommand = new ConfirmarReservaCommand(
				message.getReservaId(),
				message.getPagoId()
			);
			ConfirmarReservaHandler confirmarReservaHandler = new ConfirmarReservaHandler(
				_unitOfWork,
				_reservaRepository,
				_vueloRepository,
				_pasajeroRepository,
				_mediator
			);
			_mediator.registrarComando(
				confirmarReservaCommand,
				confirmarReservaHandler
			);
			_mediator.Send(confirmarReservaCommand);
		} catch (Exception e) {
			e.printStackTrace();
			//aqui ejecutar el comando rollback SAGA
		}
	}
}
