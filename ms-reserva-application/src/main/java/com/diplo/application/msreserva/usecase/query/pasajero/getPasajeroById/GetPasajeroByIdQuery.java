package com.diplo.application.msreserva.usecase.query.pasajero.getPasajeroById;

import com.diplo.application.msreserva.dto.pasajero.PasajeroDTO;
import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.sharedkernel.mediator.request.IRequest;
import java.util.UUID;

public class GetPasajeroByIdQuery implements IRequest<PasajeroDTO> {

	private UUID pasajeroId;

	public GetPasajeroByIdQuery() {}

	public GetPasajeroByIdQuery(UUID pasjeroId) {
		super();
		this.pasajeroId = pasjeroId;
	}

	public UUID getPasajeroId() {
		return pasajeroId;
	}

	public void setPasajeroId(UUID pasajeroId) {
		this.pasajeroId = pasajeroId;
	}
}
