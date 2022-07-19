package com.diplo.msreserva.valueobjects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NumeroReservaTest {

	@Test
	void CrearNumeroReserva() {
		String valor = "123";
		NumeroReserva numeroReservaTest = new NumeroReserva(valor);

		assertEquals(valor, numeroReservaTest.getValue());
	}
}
