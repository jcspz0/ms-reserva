package com.diplo.application.msreserva.usecase.command.reserva.reservacompletadarollback;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReservaCompletadaRollbackCommandTest {

	@Test
	void testReservaCompletadaRollback() {
		String reservaId = UUID.randomUUID().toString();
		String pagoId = UUID.randomUUID().toString();
		ReservaCompletadaRollbackCommand command = new ReservaCompletadaRollbackCommand(
			reservaId,
			pagoId
		);
		assertEquals(reservaId, command.getReservaId());
		assertEquals(pagoId, command.getPagoId());
		command = new ReservaCompletadaRollbackCommand();
		command.setReservaId(reservaId);
		command.setPagoId(pagoId);
		assertEquals(reservaId, command.getReservaId());
		assertEquals(pagoId, command.getPagoId());
	}
}
