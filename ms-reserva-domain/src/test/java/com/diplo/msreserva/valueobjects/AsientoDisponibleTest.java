package com.diplo.msreserva.valueobjects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AsientoDisponibleTest {

	@Test
	void evitarCrearAsientoDisponibleNegativo() {
		
		Exception exception = assertThrows(Exception.class, () -> {
			new AsientoDisponible(-1);
	    });

	    String expectedMessage = "La disponibilidad no puede ser negativa";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void CrearAsientoDisponible() throws Exception {
		int cant=1;
		
		AsientoDisponible resultado = new AsientoDisponible(cant);
 
		assertEquals(cant, resultado.getDisponibilidad());
	}

}
