package com.diplo.msreserva.model.pasajero;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.msreserva.valueobjects.DocumentoIdentidad;
import com.diplo.msreserva.valueobjects.NombreCompleto;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class PasajeroTest {

	@Test
	void crearPasajeroConIdGenerado() throws Exception {
		NombreCompleto nombreCompletTest = new NombreCompleto(
			"nombre",
			"apellido",
			"segundo fallido"
		);
		DocumentoIdentidad documentoIdentidadTest = new DocumentoIdentidad(
			1,
			1
		);
		Pasajero pasajeroTest = new Pasajero(
			nombreCompletTest,
			documentoIdentidadTest
		);

		assertEquals(nombreCompletTest, pasajeroTest.getNombre());
		assertEquals(documentoIdentidadTest, pasajeroTest.getDocumento());
	}

	@Test
	void crearPasajeroSinIdGenerado() throws Exception {
		NombreCompleto nombreCompletTest = new NombreCompleto(
			"nombre",
			"apellido",
			"segundo fallido"
		);
		DocumentoIdentidad documentoIdentidadTest = new DocumentoIdentidad(
			1,
			1
		);
		Pasajero pasajeroTest = new Pasajero(
			UUID.randomUUID(),
			nombreCompletTest,
			documentoIdentidadTest
		);

		assertEquals(nombreCompletTest, pasajeroTest.getNombre());
		assertEquals(documentoIdentidadTest, pasajeroTest.getDocumento());
	}
}
