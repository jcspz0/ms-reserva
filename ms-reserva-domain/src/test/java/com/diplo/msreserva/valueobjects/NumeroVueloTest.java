package com.diplo.msreserva.valueobjects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumeroVueloTest {

	@Test
	void crearNumeroVuelo() {
		
		int numeroTest = 1;
		NumeroVuelo numeroVueloTest = new NumeroVuelo(numeroTest);
		
		assertEquals(numeroTest, numeroVueloTest.getNumero());
	}

}
