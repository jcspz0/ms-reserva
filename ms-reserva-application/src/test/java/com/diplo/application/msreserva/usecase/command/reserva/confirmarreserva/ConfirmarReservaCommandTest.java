package com.diplo.application.msreserva.usecase.command.reserva.confirmarreserva;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConfirmarReservaCommandTest {

	@Test
	void testConfirmarReservaCommand() {
		String reservaId = UUID.randomUUID().toString();
		String pagoId = UUID.randomUUID().toString();
		ConfirmarReservaCommand command = new ConfirmarReservaCommand();
		command.setReservaId(reservaId);
		command.setPagoId(pagoId);
		assertEquals(reservaId, command.getReservaId());
		assertEquals(pagoId, command.getPagoId());
		command = new ConfirmarReservaCommand(reservaId, pagoId);
		assertEquals(reservaId, command.getReservaId());
		assertEquals(pagoId, command.getPagoId());
	}
}
