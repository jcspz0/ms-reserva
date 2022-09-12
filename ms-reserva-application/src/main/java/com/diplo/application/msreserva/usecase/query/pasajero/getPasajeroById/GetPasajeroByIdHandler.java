package com.diplo.application.msreserva.usecase.query.pasajero.getPasajeroById;

import com.diplo.application.msreserva.dto.pasajero.PasajeroDTO;
import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.msreserva.model.pasajero.Pasajero;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IPasajeroRepository;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.msreserva.valueobjects.DocumentoIdentidad;
import com.diplo.sharedkernel.mediator.request.IRequestHandler;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetPasajeroByIdHandler
	implements IRequestHandler<GetPasajeroByIdQuery, PasajeroDTO> {

	private final IPasajeroRepository _pasajeroRepository;

	public GetPasajeroByIdHandler(IPasajeroRepository pasajeroRepository) {
		super();
		this._pasajeroRepository = pasajeroRepository;
	}

	@Override
	public Future<PasajeroDTO> Handle(GetPasajeroByIdQuery request)
		throws Exception {
		PasajeroDTO pasajeroDto = null;

		CompletableFuture<Pasajero> objPasajero = (CompletableFuture<Pasajero>) _pasajeroRepository.FindByIdAsync(
			request.getPasajeroId()
		);

		Pasajero aux = objPasajero.get();
		if (aux == null) {
			return null;
		}
		pasajeroDto = new PasajeroDTO(aux);
		return CompletableFuture.completedFuture(pasajeroDto);
	}
}
