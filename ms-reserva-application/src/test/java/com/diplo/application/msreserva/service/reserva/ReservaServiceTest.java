package com.diplo.application.msreserva.service.reserva;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.Future;

import org.junit.jupiter.api.Test;

class ReservaServiceTest {

	@Test
	void GenerarNroReservaAsync() {
		ReservaService reservaService = new ReservaService();
		Future<String> resultado = reservaService.GenerarNroReservaAsync();
		
		assertNotNull(resultado);
	}

}
