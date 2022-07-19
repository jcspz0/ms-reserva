package com.diplo.application.msreserva.usecase.query.vuelo.getVuelosByDestino;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GetVuelosByDestinoQueryTest {

	@Test
	void GetVuelosByDestinoQuery() {
		String destino = "santa";
		GetVuelosByDestinoQuery getVuelosByDestinoQuery = new GetVuelosByDestinoQuery();
		getVuelosByDestinoQuery.setDestino(destino);

		getVuelosByDestinoQuery = new GetVuelosByDestinoQuery(destino);

		assertEquals(destino, getVuelosByDestinoQuery.getDestino());
	}
}
