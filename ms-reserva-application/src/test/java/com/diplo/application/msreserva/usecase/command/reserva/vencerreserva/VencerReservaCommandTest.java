package com.diplo.application.msreserva.usecase.command.reserva.vencerreserva;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VencerReservaCommandTest {

	@Test
	void vencerReserva() {
		String reservaId = UUID.randomUUID().toString();
		VencerReservaCommand command = new VencerReservaCommand();
		command.setReservaId(reservaId);
		assertEquals(reservaId, command.getReservaId());
		command = new VencerReservaCommand(reservaId);
		assertEquals(reservaId, command.getReservaId());
	}
}
