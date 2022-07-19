package com.diplo.msreserva.factory;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.msreserva.model.reserva.Reserva;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class ReservaFactoryTest {

	@Test
	void Create() throws Exception {
		ReservaFactory reservaFactoryRest = new ReservaFactory();
		Reserva resultado = reservaFactoryRest.Create(
			UUID.randomUUID().toString(),
			"123",
			UUID.randomUUID().toString(),
			UUID.randomUUID().toString(),
			100,
			1
		);

		assertNotNull(resultado);
	}
}
