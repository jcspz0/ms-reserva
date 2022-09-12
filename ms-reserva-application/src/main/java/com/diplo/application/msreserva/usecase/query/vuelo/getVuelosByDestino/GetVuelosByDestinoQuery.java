package com.diplo.application.msreserva.usecase.query.vuelo.getVuelosByDestino;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.sharedkernel.mediator.request.IRequest;
import java.util.List;
import java.util.UUID;

public class GetVuelosByDestinoQuery implements IRequest<List<VueloDTO>> {

	private String Destino;

	public GetVuelosByDestinoQuery(String destino) {
		super();
		Destino = destino;
	}

	public GetVuelosByDestinoQuery() {}

	public String getDestino() {
		return Destino;
	}

	public void setDestino(String destino) {
		Destino = destino;
	}
}
