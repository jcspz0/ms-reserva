package com.diplo.application.msreserva.usecase.command.reserva.reservacompletadarollback;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.valueobjects.CantidadPasajero;
import com.diplo.msreserva.valueobjects.Monto;
import com.diplo.msreserva.valueobjects.NumeroReserva;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReservaCompletadaRollbackHandlerTest {

	@InjectMocks
	ReservaCompletadaRollbackHandler handler;

	@Mock
	IUnitOfWork _unitOfWork;

	@Mock
	IReservaRepository _reservaRepository;

	@Test
	void testReservaCompletadaRollbackHandler() {
		try {
			UUID reservaIdTest = UUID.randomUUID();
			NumeroReserva nroReservaTest = new NumeroReserva("Nro Reserva");
			UUID pasajeroTest = UUID.randomUUID();
			UUID vueloIdTest = UUID.randomUUID();
			Monto precioTest = new Monto(10);
			CantidadPasajero cantidadPasajeroTest = new CantidadPasajero(1);
			Reserva reservaTest = new Reserva(
				reservaIdTest,
				nroReservaTest,
				pasajeroTest,
				vueloIdTest,
				precioTest,
				cantidadPasajeroTest
			);

			when(_reservaRepository.FindByIdAsync(any()))
				.thenReturn(CompletableFuture.completedFuture(reservaTest));

			Future<UUID> resultado = handler.Handle(
				new ReservaCompletadaRollbackCommand(
					reservaIdTest.toString(),
					UUID.randomUUID().toString()
				)
			);

			assertEquals(reservaTest.getId(), resultado.get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
