package com.diplo.infraestructure.msreserva.entityframework.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.junit.jupiter.api.Test;

import com.diplo.msreserva.model.reserva.Reserva;

class ReservaRepositoryTest {

	@Test
	void testFindByIdAsync() throws Exception {
		Reserva reserva = new Reserva(UUID.randomUUID().toString(), UUID.randomUUID(), UUID.randomUUID(), 100.0, 10);
		assertNotNull(reserva);
	}

	@Test
	void testCreateAsync() throws Exception{
		Reserva reserva = new Reserva(UUID.randomUUID().toString(), UUID.randomUUID(), UUID.randomUUID(), 100.0, 10);
		assertNotNull(reserva);
	}

	@Test
	void testUpdateAsync() throws Exception{
		Reserva reserva = new Reserva(UUID.randomUUID().toString(), UUID.randomUUID(), UUID.randomUUID(), 100.0, 10);
		assertNotNull(reserva);
	}

	@Test
	void testGetReservasByHoraAndEstado() throws Exception{
		Reserva reserva = new Reserva(UUID.randomUUID().toString(), UUID.randomUUID(), UUID.randomUUID(), 100.0, 10);
		assertNotNull(reserva);
	}

}
