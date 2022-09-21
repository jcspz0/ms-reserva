package com.diplo.application.msreserva.usecase.query.vuelo.getVuelosByDestino;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GetVuelosByDestinoQueryTest {

	@Test
	void GetVuelosByDestinoQuery() {
		String destino = "santa";
		String origen = "lapaz";
		GetVuelosByDestinoQuery getVuelosByDestinoQuery = new GetVuelosByDestinoQuery();
		getVuelosByDestinoQuery.setDestino(destino);
		getVuelosByDestinoQuery.setOrigen(origen);

		getVuelosByDestinoQuery = new GetVuelosByDestinoQuery(origen, destino);

		assertEquals(destino, getVuelosByDestinoQuery.getDestino());
		assertEquals(origen, getVuelosByDestinoQuery.getOrigen());
	}
}
