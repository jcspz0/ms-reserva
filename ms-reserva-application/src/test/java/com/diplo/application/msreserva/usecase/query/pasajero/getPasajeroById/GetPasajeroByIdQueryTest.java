package com.diplo.application.msreserva.usecase.query.pasajero.getPasajeroById;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.application.msreserva.usecase.query.pasajero.getPasajeroByNroDocAndTipoDoc.GetPasajeroByNroDocAndTipoDocQuery;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class GetPasajeroByIdQueryTest {

	@Test
	void GetPasajeroByIdQuery() {
		UUID pasajeroId = UUID.randomUUID();

		GetPasajeroByIdQuery query = new GetPasajeroByIdQuery();
		query.setPasajeroId(pasajeroId);

		assertEquals(pasajeroId, query.getPasajeroId());

		query = new GetPasajeroByIdQuery(pasajeroId);

		assertEquals(pasajeroId, query.getPasajeroId());
	}
}
