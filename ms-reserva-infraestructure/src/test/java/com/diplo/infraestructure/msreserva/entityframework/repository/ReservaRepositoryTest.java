package com.diplo.infraestructure.msreserva.entityframework.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.sharedkernel.core.Constant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReservaRepositoryTest {

	@InjectMocks
	ReservaRepository repository;

	@Test
	void testFindByIdAsync() throws Exception {
		Reserva reserva = new Reserva(
			UUID.randomUUID().toString(),
			UUID.randomUUID(),
			UUID.randomUUID(),
			100.0,
			10
		);
		Future<Reserva> resultado = repository.FindByIdAsync(reserva.getId());
		assertNotNull(resultado.get());
	}

	@Test
	void testCreateAsync() throws Exception {
		Reserva reserva = new Reserva(
			UUID.randomUUID().toString(),
			UUID.randomUUID(),
			UUID.randomUUID(),
			100.0,
			10
		);
		Future<Reserva> resultado = repository.CreateAsync(reserva);
		assertNotNull(resultado);
	}

	@Test
	void testUpdateAsync() throws Exception {
		Reserva reserva = new Reserva(
			UUID.randomUUID().toString(),
			UUID.randomUUID(),
			UUID.randomUUID(),
			100.0,
			10
		);
		Future<Reserva> resultado = repository.UpdateAsync(reserva);
		assertNotNull(resultado);
	}

	@Test
	void testGetReservasByHoraAndEstado() throws Exception {
		Reserva reserva = new Reserva(
			UUID.randomUUID().toString(),
			UUID.randomUUID(),
			UUID.randomUUID(),
			100.0,
			10
		);
		Future<List<Reserva>> resultado = repository.GetReservasByHoraAndEstado(
			LocalDateTime.now(),
			Constant.RESERVAESTADOCREADA
		);
		repository.commit();
		assertNotNull(resultado);
	}
}
