package com.diplo.application.msreserva.usecase.query.reserva.getreservasbyhoraandestado;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GetReservasByHoraAndEstadoQueryTest {

	@Test
	void GetReservasByHoraAndEstadoQuery() {
		String hora = "2022-04-25 18:22:41";
		String estado = "VALIDO";
		GetReservasByHoraAndEstadoQuery getReservasByHoraAndEstadoQuery = new GetReservasByHoraAndEstadoQuery();

		getReservasByHoraAndEstadoQuery.setEstado(estado);
		getReservasByHoraAndEstadoQuery.setHora(hora);

		getReservasByHoraAndEstadoQuery =
			new GetReservasByHoraAndEstadoQuery(hora, estado);

		assertEquals(estado, getReservasByHoraAndEstadoQuery.getEstado());
		assertEquals(hora, getReservasByHoraAndEstadoQuery.getHora());
	}
}
