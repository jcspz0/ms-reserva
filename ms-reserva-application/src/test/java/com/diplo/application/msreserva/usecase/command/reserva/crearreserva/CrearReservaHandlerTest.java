package com.diplo.application.msreserva.usecase.command.reserva.crearreserva;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.service.reserva.IReservaService;
import com.diplo.msreserva.factory.IReservaFactory;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.valueobjects.CantidadPasajero;
import com.diplo.msreserva.valueobjects.Monto;
import com.diplo.msreserva.valueobjects.NumeroReserva;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CrearReservaHandlerTest {

	@Mock
	IReservaService _reservaServiceTest;

	@Mock
	IReservaFactory _reservaFactoryTest;

	@Mock
	IUnitOfWork _unitOfWorkTest;

	@Mock
	IReservaRepository _reservaRepositoryTest;

	@Mock
	CrearReservaCommand crearReservaCommandTest;

	CrearReservaHandler crearReservaHandlerTest;

	UUID reservaIdTest;
	NumeroReserva nroReservaTest;
	UUID pasajeroTest;
	UUID vueloIdTest;
	Monto precioTest;
	CantidadPasajero cantidadPasajeroTest;
	Reserva reservaTest;
	VueloDTO vueloDTOTest;

	@BeforeEach
	void init() throws Exception {
		crearReservaHandlerTest =
			new CrearReservaHandler(
				_reservaServiceTest,
				_reservaFactoryTest,
				_reservaRepositoryTest,
				_unitOfWorkTest
			);

		reservaIdTest = UUID.randomUUID();
		nroReservaTest = new NumeroReserva("Nro Reserva");
		pasajeroTest = UUID.randomUUID();
		vueloIdTest = UUID.randomUUID();
		precioTest = new Monto(10);
		cantidadPasajeroTest = new CantidadPasajero(1);

		vueloDTOTest =
			new VueloDTO(UUID.randomUUID().toString(), 1, 1, "Santa");

		reservaTest =
			new Reserva(
				reservaIdTest,
				nroReservaTest,
				pasajeroTest,
				vueloIdTest,
				precioTest,
				cantidadPasajeroTest
			);

		when(_reservaServiceTest.GenerarNroReservaAsync())
			.thenReturn(
				CompletableFuture.supplyAsync(() -> {
					return UUID.randomUUID().toString();
				})
			);
		when(
			_reservaFactoryTest.Create(
				anyString(),
				anyString(),
				anyString(),
				anyString(),
				anyDouble(),
				anyInt()
			)
		)
			.thenReturn(reservaTest);
		when(crearReservaCommandTest.getCantidadPasajero())
			.thenReturn(cantidadPasajeroTest.getCantidad());
		when(crearReservaCommandTest.getMonto())
			.thenReturn(precioTest.getMonto());
		when(crearReservaCommandTest.getNroPasajero())
			.thenReturn(vueloDTOTest.getVueloId());
		when(crearReservaCommandTest.getNroReserva())
			.thenReturn(nroReservaTest.getValue());
		when(crearReservaCommandTest.getVuelo()).thenReturn(vueloDTOTest);
	}

	@Test
	void CrearReservaHandler() throws Exception {
		Future<UUID> result = crearReservaHandlerTest.Handle(
			crearReservaCommandTest
		);

		assertEquals(
			CompletableFuture.completedFuture(reservaIdTest).get(),
			result.get()
		);
	}
}
