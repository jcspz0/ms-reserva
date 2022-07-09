package com.diplo.msreserva.valueobjects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DestinoTest {

	@Test
	void ExcepcionPorDestinoVacioONulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			new Destino("");
	    });

	    String expectedMessage = "El nombre de destino no puede ser vacio o nulo";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void ValidarDestinoCreado() throws Exception {
		String destinoTest = "Santa";
		Destino destino = new Destino(destinoTest);
		
		assertEquals(destinoTest, destino.getNombreDestino());

	}

}
