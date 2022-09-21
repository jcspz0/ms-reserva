package com.diplo.application.msreserva.usecase.consumer.integration;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.usecase.command.reserva.reservacompletadarollback.ReservaCompletadaRollbackCommand;
import com.diplo.application.msreserva.usecase.command.reserva.reservacompletadarollback.ReservaCompletadaRollbackHandler;
import com.diplo.application.msreserva.usecase.command.reserva.vencerreserva.VencerReservaCommand;
import com.diplo.application.msreserva.usecase.command.reserva.vencerreserva.VencerReservaHandler;
import com.diplo.application.msreserva.usecase.command.vuelo.reducirdisponibilidad.ReducirDisponibilidadCommand;
import com.diplo.application.msreserva.usecase.command.vuelo.reducirdisponibilidad.ReducirDisponibilidadHandler;
import com.diplo.application.msreserva.usecase.query.reserva.getReservaById.GetReservaByIdHandler;
import com.diplo.application.msreserva.usecase.query.reserva.getReservaById.GetReservaByIdQuery;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.sharedkernel.amqp.IAmqpConsumer;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaVencida;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaConfirmadaRollback;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import com.diplo.sharedkernel.mediator.IMediator;
import java.util.UUID;
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

	@Autowired
	IVueloRepository _vueloRepository;

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
			GetReservaByIdQuery getReservaByIdQuery = new GetReservaByIdQuery(
				UUID.fromString(message.getReservaId())
			);

			GetReservaByIdHandler getReservaByIdHandler = new GetReservaByIdHandler(
				_reservaRepository
			);
			_mediator.registrarComando(
				getReservaByIdQuery,
				getReservaByIdHandler
			);
			ReservaDTO reserva = _mediator.Send(getReservaByIdQuery);
			ReducirDisponibilidadCommand _reducirDisponibilidadCommand = new ReducirDisponibilidadCommand(
				reserva.getVueloId().toString(),
				reserva.getCantidadPasajero() * (-1)
			);

			ReducirDisponibilidadHandler _reducirDisponibilidadHandler = new ReducirDisponibilidadHandler(
				_vueloRepository,
				_unitOfWork
			);
			_mediator.registrarComando(
				_reducirDisponibilidadCommand,
				_reducirDisponibilidadHandler
			);
			_mediator.Send(_reducirDisponibilidadCommand);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
