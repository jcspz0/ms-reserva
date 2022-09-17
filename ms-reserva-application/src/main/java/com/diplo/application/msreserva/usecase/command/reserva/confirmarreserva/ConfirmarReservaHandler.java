package com.diplo.application.msreserva.usecase.command.reserva.confirmarreserva;

import com.diplo.application.msreserva.dto.pasajero.PasajeroDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.usecase.query.pasajero.getPasajeroById.GetPasajeroByIdHandler;
import com.diplo.application.msreserva.usecase.query.pasajero.getPasajeroById.GetPasajeroByIdQuery;
import com.diplo.application.msreserva.usecase.query.vuelo.getVueloById.GetVueloByIdHandler;
import com.diplo.application.msreserva.usecase.query.vuelo.getVueloById.GetVueloByIdQuery;
import com.diplo.msreserva.model.pasajero.Pasajero;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IPasajeroRepository;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.NumeroVuelo;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagadaRollback;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaConfirmada;
import com.diplo.sharedkernel.mediator.IMediator;
import com.diplo.sharedkernel.mediator.request.IRequestHandler;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;

public class ConfirmarReservaHandler
	implements IRequestHandler<ConfirmarReservaCommand, UUID> {

	private final IUnitOfWork _unitOfWork;
	private final IReservaRepository _reservaRepository;
	private final IMediator _mediator;
	private final IVueloRepository _vueloRepository;
	private final IPasajeroRepository _pasajeroRepository;

	public ConfirmarReservaHandler(
		IUnitOfWork _unitOfWork,
		IReservaRepository _reservaRepository,
		IVueloRepository _vueIVueloRepository,
		IPasajeroRepository _pasajeroRepository,
		IMediator _mediator
	) {
		super();
		this._unitOfWork = _unitOfWork;
		this._reservaRepository = _reservaRepository;
		this._mediator = _mediator;
		this._vueloRepository = _vueIVueloRepository;
		this._pasajeroRepository = _pasajeroRepository;
	}

	@Override
	public Future<UUID> Handle(ConfirmarReservaCommand request)
		throws Exception {
		Future<Reserva> future = _reservaRepository.FindByIdAsync(
			UUID.fromString(request.getReservaId())
		);
		Reserva reserva = future.get();

		VueloDTO vuelo = getVueloById(reserva.getVueloId());
		PasajeroDTO pasajero = getPasajeroById(reserva.getPasajeroId());
		String nombreCompletoPasajero =
			pasajero.getNombre() +
			" " +
			pasajero.getPrimerApellido() +
			" " +
			pasajero.getSegundoApellido();

		try {
			reserva.ConfirmarReserva(
				request.getPagoId(),
				vuelo.getVueloId(),
				vuelo.getDestino(),
				pasajero.getNroDoc(),
				pasajero.getTipoDoc(),
				nombreCompletoPasajero
			);
			reserva.AddIntegrationEvent(
				new IntegrationEvent(
					new IntegrationReservaConfirmada(
						reserva.getId().toString(),
						reserva.getVueloId().toString(),
						pasajero.getTipoDoc(),
						pasajero.getNroDoc(),
						nombreCompletoPasajero,
						reserva.getHora().getHora().toString(),
						vuelo.getOrigen(),
						vuelo.getDestino(),
						vuelo.getCantidadAsientoDisponible(),
						request.getPagoId()
					),
					LocalDateTime.now().toString()
				)
			);
			//Prueba de rollback
			//reserva.AddIntegrationEvent(new IntegrationEvent(new IntegrationDeudaPagadaRollback(request.getReservaId(), request.getPagoId()), LocalDateTime.now().toString()));

		} catch (Exception e) {
			// TODO: handle exception
			reserva.AddIntegrationEvent(
				new IntegrationEvent(
					new IntegrationDeudaPagadaRollback(
						request.getReservaId(),
						request.getPagoId()
					),
					LocalDateTime.now().toString()
				)
			);
		} finally {
			_reservaRepository.UpdateAsync(reserva);
			_unitOfWork.Commit();
			System.out.println("Se confirmo la reserva");
		}
		return CompletableFuture.completedFuture(reserva.getId());
	}

	private VueloDTO getVueloById(UUID vueloId) throws Exception {
		GetVueloByIdQuery _getVueloByIdQuery = new GetVueloByIdQuery(vueloId);
		GetVueloByIdHandler _getVueloByIdHandler = new GetVueloByIdHandler(
			_vueloRepository
		);
		_mediator.registrarComando(_getVueloByIdQuery, _getVueloByIdHandler);
		VueloDTO vueloDTO = _mediator.Send(_getVueloByIdQuery);
		//Vuelo vuelo = new Vuelo(UUID.fromString(vueloDTO.getVueloId()), new NumeroVuelo(vueloDTO.getNroVuelo()), new Destino(vueloDTO.getDestino()), new AsientoDisponible(vueloDTO.getCantidadAsientoDisponible()));
		return vueloDTO;
	}

	private PasajeroDTO getPasajeroById(UUID pasajeroId) throws Exception {
		System.out.println("Pasajero a buscar " + pasajeroId.toString());
		GetPasajeroByIdQuery _getPasajeroByIdQuery = new GetPasajeroByIdQuery(
			pasajeroId
		);
		GetPasajeroByIdHandler _getPasajeroByIdHandler = new GetPasajeroByIdHandler(
			_pasajeroRepository
		);
		_mediator.registrarComando(
			_getPasajeroByIdQuery,
			_getPasajeroByIdHandler
		);
		PasajeroDTO pasajeroDTO = _mediator.Send(_getPasajeroByIdQuery);
		return pasajeroDTO;
	}
}
