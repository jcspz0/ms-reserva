package com.diplo.application.msreserva.usecase.query.reserva.getReservaById;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.sharedkernel.mediator.request.IRequest;
import java.util.UUID;

public class GetReservaByIdQuery implements IRequest<ReservaDTO> {

	private UUID Id;

	public GetReservaByIdQuery(UUID id) {
		super();
		Id = id;
	}

	public GetReservaByIdQuery() {}

	public UUID getId() {
		return Id;
	}

	public void setId(UUID id) {
		Id = id;
	}
}
