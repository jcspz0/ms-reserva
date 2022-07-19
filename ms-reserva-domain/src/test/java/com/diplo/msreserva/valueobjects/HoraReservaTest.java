package com.diplo.msreserva.valueobjects;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

class HoraReservaTest {

	@Test
	void CrearHoraReserva() {
		HoraReserva horaReservaTest = new HoraReserva(LocalDateTime.now());

		assertNotNull(horaReservaTest);
		assertEquals(LocalDateTime.class, horaReservaTest.getHora().getClass());
	}
}
