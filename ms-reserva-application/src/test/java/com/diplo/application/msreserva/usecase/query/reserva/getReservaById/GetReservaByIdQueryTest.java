package com.diplo.application.msreserva.usecase.query.reserva.getReservaById;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class GetReservaByIdQueryTest {

	@Test
	void GetReservaByIdQuery() {
		
		UUID id = UUID.randomUUID();
		
		GetReservaByIdQuery getReservaByIdQuery = new GetReservaByIdQuery();
		getReservaByIdQuery.setId(id);
		
		getReservaByIdQuery = new GetReservaByIdQuery(id);
		
		assertEquals(id, getReservaByIdQuery.getId());
	}

}
