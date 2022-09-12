package com.diplo.application.msreserva.usecase.query.vuelo.getVueloById;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.sharedkernel.mediator.request.IRequest;
import java.util.UUID;

public class GetVueloByIdQuery implements IRequest<VueloDTO> {

	private UUID Id;

	public GetVueloByIdQuery(UUID id) {
		super();
		Id = id;
	}

	public GetVueloByIdQuery() {}

	public UUID getId() {
		return Id;
	}

	public void setId(UUID id) {
		Id = id;
	}
}
