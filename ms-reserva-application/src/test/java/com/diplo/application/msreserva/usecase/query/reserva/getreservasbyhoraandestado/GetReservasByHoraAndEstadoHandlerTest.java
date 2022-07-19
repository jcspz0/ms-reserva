package com.diplo.application.msreserva.usecase.query.reserva.getreservasbyhoraandestado;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.usecase.query.reserva.getReservaById.GetReservaByIdHandler;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.valueobjects.CantidadPasajero;
import com.diplo.msreserva.valueobjects.Monto;
import com.diplo.msreserva.valueobjects.NumeroReserva;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetReservasByHoraAndEstadoHandlerTest {

	@Mock
	IReservaRepository _reservaRepositoryTest;

	@Mock
	GetReservasByHoraAndEstadoQuery getReservasByHoraAndEstadoQueryTest;

	GetReservasByHoraAndEstadoHandler getReservasByHoraAndEstadoHandlerTest;

	String HoraTest;
	String EstadoTest;
	List<Reserva> listaReservaTest;
	UUID reservaIdTest;
	NumeroReserva nroReservaTest;
	UUID pasajeroTest;
	UUID vueloIdTest;
	Monto precioTest;
	CantidadPasajero cantidadPasajeroTest;
	Reserva reservaTest;

	@BeforeEach
	void init() throws Exception {
		HoraTest = "2022-05-18 19:57:27";
		EstadoTest = "VALIDO";

		getReservasByHoraAndEstadoHandlerTest =
			new GetReservasByHoraAndEstadoHandler(_reservaRepositoryTest);

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

		listaReservaTest = new ArrayList<Reserva>();

		listaReservaTest.add(reservaTest);

		when(getReservasByHoraAndEstadoQueryTest.getHora())
			.thenReturn(HoraTest);
		when(_reservaRepositoryTest.GetReservasByHoraAndEstado(any(), any()))
			.thenReturn(CompletableFuture.completedFuture(listaReservaTest));
	}

	@Test
	void GetReservasByHoraAndEstado()
		throws InterruptedException, ExecutionException {
		Future<List<ReservaDTO>> resultado = getReservasByHoraAndEstadoHandlerTest.Handle(
			getReservasByHoraAndEstadoQueryTest
		);

		assertNotNull(resultado);
		assertEquals(
			resultado.get().get(0).getReservaId(),
			reservaIdTest.toString()
		);
	}
}
