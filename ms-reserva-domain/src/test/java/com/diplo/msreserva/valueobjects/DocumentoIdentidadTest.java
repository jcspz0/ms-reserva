package com.diplo.msreserva.valueobjects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DocumentoIdentidadTest {

	@Test
	void CrearDocumentoIdentidadCI() throws Exception {
		int nro = 123;
		int tipo = 1;
		String mostrarDocumentoIdentidad =
			"Numero de documento: " +
			nro +
			" , Tipo de documento: Carnet de identidad";

		DocumentoIdentidad documentoIdentidad = new DocumentoIdentidad(
			nro,
			tipo
		);
		assertEquals(nro, documentoIdentidad.getNroDoc());
		assertEquals(tipo, documentoIdentidad.getTipoDoc());
		assertEquals(
			mostrarDocumentoIdentidad,
			documentoIdentidad.getDocumentoIdentidad()
		);
	}

	@Test
	void CrearDocumentoIdentidadPasaporte() throws Exception {
		int nro = 123;
		int tipo = 2;
		String mostrarDocumentoIdentidad =
			"Numero de documento: " + nro + " , Tipo de documento: Pasaporte";

		DocumentoIdentidad documentoIdentidad = new DocumentoIdentidad(
			nro,
			tipo
		);
		assertEquals(nro, documentoIdentidad.getNroDoc());
		assertEquals(tipo, documentoIdentidad.getTipoDoc());
		assertEquals(
			mostrarDocumentoIdentidad,
			documentoIdentidad.getDocumentoIdentidad()
		);
	}

	@Test
	void ExcepcionTipoDocumentoInvalido() {
		Exception exception = assertThrows(
			Exception.class,
			() -> {
				new DocumentoIdentidad(123, 0);
			}
		);

		String expectedMessage = "Tipo de documento no valido";
		String actualMessage = exception.getMessage();

		assertTrue(actualMessage.contains(expectedMessage));
	}
}
