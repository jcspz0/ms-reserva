package com.diplo.application.msreserva.usecase.command.vuelo.reducirdisponibilidad;

import com.diplo.application.msreserva.service.reserva.IReservaService;
import com.diplo.msreserva.factory.IReservaFactory;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.NumeroVuelo;
import com.diplo.sharedkernel.mediator.request.IRequestHandler;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ReducirDisponibilidadHandler
	implements IRequestHandler<ReducirDisponibilidadCommand, UUID> {

	private final IUnitOfWork _unitOfWork;
	private final IVueloRepository _vueloRepository;

	public ReducirDisponibilidadHandler(
		IVueloRepository _vueloRepository,
		IUnitOfWork _unitOfWork
	) {
		super();
		this._unitOfWork = _unitOfWork;
		this._vueloRepository = _vueloRepository;
	}

	@Override
	public Future<UUID> Handle(ReducirDisponibilidadCommand request) {
		try {
			Future<Vuelo> future = _vueloRepository.FindByIdAsync(
				UUID.fromString(request.getVueloId())
			);
			Vuelo vuelo = future.get();
			if (vuelo.reducirDisponibilidad(request.getCantidadPasajeros())) {
				_vueloRepository.UpdateAsync(vuelo);
				_unitOfWork.Commit();
				System.out.println(
					"Se redujo la capacidad, restante " +
					vuelo.getCantidadAsientoDisponible().getDisponibilidad()
				);
			}

			return CompletableFuture.completedFuture(vuelo.getId());
		} catch (Exception e) {
			System.out.println(
				"ReducirDisponibilidad->excepcion al reducirDisponibilidad:" + e
			);
			System.out.println(e);
			return CompletableFuture.completedFuture(null);
		}
	}
}
