package com.diplo.application.msreserva.usecase.query.vuelo.getPasajeroByNroDocAndTipoDoc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.diplo.application.msreserva.dto.pasajero.PasajeroDTO;
import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.mediator.request.IRequestHandler;
import com.diplo.msreserva.model.pasajero.Pasajero;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IPasajeroRepository;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.msreserva.valueobjects.DocumentoIdentidad;

public class GetPasajeroByNroDocAndTipoDocHandler
		implements IRequestHandler<GetPasajeroByNroDocAndTipoDocQuery, PasajeroDTO> {

	private final IPasajeroRepository _pasajeroRepository;

	public GetPasajeroByNroDocAndTipoDocHandler(IPasajeroRepository pasajeroRepository) {
		super();
		this._pasajeroRepository = pasajeroRepository;
	}

	@Override
	public Future<PasajeroDTO> Handle(GetPasajeroByNroDocAndTipoDocQuery request) throws Exception{
			PasajeroDTO pasajeroDto = null;

			CompletableFuture<Pasajero> objPasajero = (CompletableFuture<Pasajero>) _pasajeroRepository.GetPasajeroByNroDocAndTipoDoc(request.getNroDoc(), request.getTipoDoc());

			Pasajero aux = objPasajero.get();
			if (aux == null) {
				return null;
			}
			pasajeroDto = new PasajeroDTO(aux);
			return CompletableFuture.completedFuture(pasajeroDto);

		
	}

}
