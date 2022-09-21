package com.diplo.application.msreserva.usecase.query.vuelo.getVuelosByDestino;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.sharedkernel.mediator.request.IRequest;
import java.util.List;
import java.util.UUID;

public class GetVuelosByDestinoQuery implements IRequest<List<VueloDTO>> {

	private String Destino;
	private String Origen;

	public GetVuelosByDestinoQuery(String origen, String destino) {
		super();
		Destino = destino;
		Origen = origen;
	}

	public GetVuelosByDestinoQuery() {}

	public String getDestino() {
		return Destino;
	}

	public void setDestino(String destino) {
		Destino = destino;
	}

	public String getOrigen() {
		return Origen;
	}

	public void setOrigen(String origen) {
		Origen = origen;
	}
}
