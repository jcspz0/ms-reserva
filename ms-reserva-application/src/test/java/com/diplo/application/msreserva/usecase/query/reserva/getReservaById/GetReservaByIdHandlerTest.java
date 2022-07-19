package com.diplo.application.msreserva.usecase.query.reserva.getReservaById;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.usecase.command.reserva.crearvuelo.CrearVueloHandler;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.CantidadPasajero;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.Monto;
import com.diplo.msreserva.valueobjects.NumeroReserva;
import com.diplo.msreserva.valueobjects.NumeroVuelo;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetReservaByIdHandlerTest {

	@Mock
	IReservaRepository _reservaRepositoryTest;

	@Mock
	GetReservaByIdQuery getReservaByIdQueryTest;

	UUID reservaIdTest;
	NumeroReserva nroReservaTest;
	UUID pasajeroTest;
	UUID vueloIdTest;
	Monto precioTest;
	CantidadPasajero cantidadPasajeroTest;
	Reserva reservaTest;

	GetReservaByIdHandler getReservaByIdHandlerTest;

	@BeforeEach
	void init() throws Exception {
		reservaIdTest = UUID.randomUUID();
		nroReservaTest = new NumeroReserva("Nro Reserva");
		pasajeroTest = UUID.randomUUID();
		vueloIdTest = UUID.randomUUID();
		precioTest = new Monto(10);
		cantidadPasajeroTest = new CantidadPasajero(1);

		reservaTest =
			new Reserva(
				reservaIdTest,
				nroReservaTest,
				pasajeroTest,
				vueloIdTest,
				precioTest,
				cantidadPasajeroTest
			);

		getReservaByIdHandlerTest =
			new GetReservaByIdHandler(_reservaRepositoryTest);

		when(_reservaRepositoryTest.FindByIdAsync(any()))
			.thenReturn(CompletableFuture.completedFuture(reservaTest));
		when(getReservaByIdQueryTest.getId()).thenReturn(reservaIdTest);
	}

	@Test
	void GetReservaByIdHandler() throws Exception {
		Future<ReservaDTO> resultado = getReservaByIdHandlerTest.Handle(
			getReservaByIdQueryTest
		);

		assertNotNull(resultado);
		assertEquals(
			reservaTest.getId().toString(),
			resultado.get().getReservaId()
		);
	}
}
