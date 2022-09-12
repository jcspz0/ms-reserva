package com.diplo.application.msreserva.usecase.consumer.integration;

import com.diplo.application.msreserva.usecase.command.reserva.reservacompletadarollback.ReservaCompletadaRollbackCommand;
import com.diplo.application.msreserva.usecase.command.reserva.reservacompletadarollback.ReservaCompletadaRollbackHandler;
import com.diplo.application.msreserva.usecase.command.reserva.vencerreserva.VencerReservaCommand;
import com.diplo.application.msreserva.usecase.command.reserva.vencerreserva.VencerReservaHandler;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.sharedkernel.amqp.IAmqpConsumer;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaVencida;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaConfirmadaRollback;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import com.diplo.sharedkernel.mediator.IMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerIntegrationReservaConfirmadaRollback
	implements IAmqpConsumer<IntegrationReservaConfirmadaRollback> {

	@Autowired
	private IMediator _mediator;

	@Autowired
	private IUnitOfWork _unitOfWork;

	@Autowired
	private IReservaRepository _reservaRepository;

	@Override
	@EventListener
	public void Consume(IntegrationReservaConfirmadaRollback message) {
		// TODO Auto-generated method stub
		try {
			System.out.println(
				"ConsumerIntegrationReservaConfirmadaRollback, llego evento de integracion " +
				message
			);
			ReservaCompletadaRollbackCommand reservaCompletadaRollbackCommand = new ReservaCompletadaRollbackCommand(
				message.getReservaId(),
				message.getPagoId()
			);
			ReservaCompletadaRollbackHandler reservaCompletadaRollbackHandler = new ReservaCompletadaRollbackHandler(
				_unitOfWork,
				_reservaRepository
			);
			_mediator.registrarComando(
				reservaCompletadaRollbackCommand,
				reservaCompletadaRollbackHandler
			);
			_mediator.Send(reservaCompletadaRollbackCommand);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
