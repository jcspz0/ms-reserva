package com.diplo.application.msreserva.usecase.query.vuelo.getVueloById;

import java.util.UUID;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.mediator.request.IRequest;

public class GetVueloByIdQuery implements IRequest<VueloDTO> {

	private UUID Id;

	public GetVueloByIdQuery(UUID id) {
		super();
		Id = id;
	}
	
	

	public GetVueloByIdQuery() {
		
	}



	public UUID getId() {
		return Id;
	}

	public void setId(UUID id) {
		Id = id;
	}
	
	
	
}
