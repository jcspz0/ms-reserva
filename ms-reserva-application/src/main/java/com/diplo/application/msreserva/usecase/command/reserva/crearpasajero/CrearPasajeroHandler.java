package com.diplo.application.msreserva.usecase.command.reserva.crearpasajero;

import com.diplo.application.msreserva.mediator.request.IRequestHandler;
import com.diplo.application.msreserva.service.reserva.IReservaService;
import com.diplo.msreserva.factory.IReservaFactory;
import com.diplo.msreserva.model.pasajero.Pasajero;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IPasajeroRepository;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.DocumentoIdentidad;
import com.diplo.msreserva.valueobjects.NombreCompleto;
import com.diplo.msreserva.valueobjects.NumeroVuelo;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class CrearPasajeroHandler
	implements IRequestHandler<CrearPasajeroCommand, UUID> {

	private final IUnitOfWork _unitOfWork;
	private final IPasajeroRepository _pasajeroRepository;

	public CrearPasajeroHandler(
		IPasajeroRepository _pasajeroRepository,
		IUnitOfWork _unitOfWork
	) {
		super();
		this._unitOfWork = _unitOfWork;
		this._pasajeroRepository = _pasajeroRepository;
	}

	@Override
	public Future<UUID> Handle(CrearPasajeroCommand request) {
		try {
			Pasajero objPasajero = new Pasajero(
				UUID.randomUUID(),
				new NombreCompleto(
					request.getNombre(),
					request.getPrimerApellido(),
					request.getSegundoApellido()
				),
				new DocumentoIdentidad(
					request.getNroDoc(),
					request.getTipoDoc()
				)
			);
			_pasajeroRepository.CreateAsync(objPasajero);
			_unitOfWork.Commit();

			return CompletableFuture.completedFuture(objPasajero.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return CompletableFuture.completedFuture(null);
		}
	}
}
