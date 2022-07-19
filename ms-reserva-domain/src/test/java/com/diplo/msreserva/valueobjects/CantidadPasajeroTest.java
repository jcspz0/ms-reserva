package com.diplo.msreserva.valueobjects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CantidadPasajeroTest {

	@Test
	void generarExcepcionPorCantidadNegativa() {
		Exception exception = assertThrows(
			Exception.class,
			() -> {
				new CantidadPasajero(-1);
			}
		);

		String expectedMessage = "La cantidad no puede ser negativa";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void CrearCantidadPasajero() throws Exception {
		int cant = 1;

		CantidadPasajero resultado = new CantidadPasajero(cant);

		assertEquals(cant, resultado.getCantidad());
	}
}
