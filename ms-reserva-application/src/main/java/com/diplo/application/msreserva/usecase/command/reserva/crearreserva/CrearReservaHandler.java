package com.diplo.application.msreserva.usecase.command.reserva.crearreserva;

import com.diplo.application.msreserva.service.reserva.IReservaService;
import com.diplo.msreserva.factory.IReservaFactory;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.NumeroVuelo;
import com.diplo.sharedkernel.mediator.request.IRequestHandler;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CrearReservaHandler
	implements IRequestHandler<CrearReservaCommand, UUID> {

	private final IReservaService _reservaService;
	private final IReservaFactory _reservaFactory;
	private final IUnitOfWork _unitOfWork;
	private final IReservaRepository _reservaRepository;

	public CrearReservaHandler(
		IReservaService _reservaService,
		IReservaFactory _reservaFactory,
		IReservaRepository _reservaRepository,
		IUnitOfWork _unitOfWork
	) {
		super();
		this._reservaService = _reservaService;
		this._reservaFactory = _reservaFactory;
		this._unitOfWork = _unitOfWork;
		this._reservaRepository = _reservaRepository;
	}

	@Override
	public Future<UUID> Handle(CrearReservaCommand request) {
		String reservaId = "";

		CompletableFuture<String> futuro = (CompletableFuture<String>) _reservaService.GenerarNroReservaAsync();
		try {
			reservaId = futuro.get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			System.out.println("Error al obtener el nroReserva " + e);
		}
		try {
			Reserva objReserva = _reservaFactory.Create(
				reservaId,
				request.getNroReserva(),
				request.getNroPasajero(),
				request.getVuelo().getVueloId(),
				request.getMonto(),
				request.getCantidadPasajero()
			);
			Vuelo auxVuelo = new Vuelo(
				UUID.fromString(request.getVuelo().getVueloId()),
				new NumeroVuelo(request.getVuelo().getNroVuelo()),
				new Destino(request.getVuelo().getDestino()),
				new AsientoDisponible(
					request.getVuelo().getCantidadAsientoDisponible()
				)
			);

			if (objReserva.RealizarReserva(auxVuelo)) {
				_reservaRepository.CreateAsync(objReserva);
				_unitOfWork.Commit();
				return CompletableFuture.completedFuture(objReserva.getId());
			}
		} catch (Exception e) {
			System.out.println("Error crear la reserva-> " + e);
		}

		return CompletableFuture.completedFuture(null);
	}
}
