package com.diplo.application.msreserva.usecase.command.vuelo.reducirdisponibilidad;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReducirDisponibilidadCommandTest {

	@Test
	void testReducirDisponibilidad() {
		String vueloId = UUID.randomUUID().toString();
		int cantidadPasajeros = 1;

		ReducirDisponibilidadCommand command = new ReducirDisponibilidadCommand();
		command.setCantidadPasajeros(cantidadPasajeros);
		command.setVueloId(vueloId);
		assertEquals(vueloId, command.getVueloId());
		assertEquals(cantidadPasajeros, command.getCantidadPasajeros());
		command = new ReducirDisponibilidadCommand(vueloId, cantidadPasajeros);
		assertEquals(vueloId, command.getVueloId());
		assertEquals(cantidadPasajeros, command.getCantidadPasajeros());
	}
}
