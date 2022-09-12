package com.diplo.application.msreserva.usecase.consumer.integration;

import com.diplo.application.msreserva.usecase.command.reserva.vencerreserva.VencerReservaCommand;
import com.diplo.application.msreserva.usecase.command.reserva.vencerreserva.VencerReservaHandler;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.sharedkernel.amqp.IAmqpConsumer;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaVencida;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import com.diplo.sharedkernel.mediator.IMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerIntegrationDeudaVencida
	implements IAmqpConsumer<IntegrationDeudaVencida> {

	@Autowired
	private IMediator _mediator;

	@Autowired
	private IUnitOfWork _unitOfWork;

	@Autowired
	private IReservaRepository _reservaRepository;

	@Override
	@EventListener
	public void Consume(IntegrationDeudaVencida message) {
		// TODO Auto-generated method stub
		try {
			System.out.println(
				"ConsumerIntegrationDeudaVencida, llego evento de integracion " +
				message
			);
			VencerReservaCommand vencerReservaCommand = new VencerReservaCommand(
				message.getReservaId()
			);
			VencerReservaHandler vencerReservaHandler = new VencerReservaHandler(
				_unitOfWork,
				_reservaRepository
			);
			_mediator.registrarComando(
				vencerReservaCommand,
				vencerReservaHandler
			);
			_mediator.Send(vencerReservaCommand);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
