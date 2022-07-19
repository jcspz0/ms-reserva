package com.diplo.application.msreserva.usecase.query.reserva.getreservasbyhoraandestado;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.mediator.request.IRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class GetReservasByHoraAndEstadoQuery
	implements IRequest<List<ReservaDTO>> {

	private String Hora;
	private String Estado;

	public GetReservasByHoraAndEstadoQuery() {}

	public GetReservasByHoraAndEstadoQuery(String hora, String estado) {
		super();
		Hora = hora;
		Estado = estado;
	}

	public String getHora() {
		return Hora;
	}

	public void setHora(String hora) {
		Hora = hora;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}
}
