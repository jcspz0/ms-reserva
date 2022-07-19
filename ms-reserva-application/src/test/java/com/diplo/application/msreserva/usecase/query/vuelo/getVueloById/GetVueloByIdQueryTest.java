package com.diplo.application.msreserva.usecase.query.vuelo.getVueloById;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class GetVueloByIdQueryTest {

	@Test
	void GetVueloByIdQuery() {
		UUID id = UUID.randomUUID();
		GetVueloByIdQuery getVueloByIdQuery = new GetVueloByIdQuery();
		getVueloByIdQuery.setId(id);
		getVueloByIdQuery = new GetVueloByIdQuery(id);

		assertEquals(id, getVueloByIdQuery.getId());
	}
}
