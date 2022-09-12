package com.diplo.application.msreserva.usecase.command.reserva.confirmarreserva;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.diplo.application.msreserva.dto.pasajero.PasajeroDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.usecase.query.pasajero.getPasajeroById.GetPasajeroByIdHandler;
import com.diplo.application.msreserva.usecase.query.pasajero.getPasajeroById.GetPasajeroByIdQuery;
import com.diplo.application.msreserva.usecase.query.vuelo.getVueloById.GetVueloByIdHandler;
import com.diplo.application.msreserva.usecase.query.vuelo.getVueloById.GetVueloByIdQuery;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.repository.IPasajeroRepository;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.msreserva.valueobjects.CantidadPasajero;
import com.diplo.msreserva.valueobjects.Monto;
import com.diplo.msreserva.valueobjects.NumeroReserva;
import com.diplo.sharedkernel.mediator.IMediator;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConfirmarReservaHandlerTest {

	@InjectMocks
	ConfirmarReservaHandler handler;

	@Mock
	IUnitOfWork _unitOfWork;

	@Mock
	IReservaRepository _reservaRepository;

	@Mock
	IMediator _mediator;

	@Mock
	IVueloRepository _vueloRepository;

	@Mock
	IPasajeroRepository _pasajeroRepository;

	@Mock
	GetPasajeroByIdHandler getPasajeroByIdHandler;

	@Mock
	GetVueloByIdHandler getVueloByIdHandler;

	PasajeroDTO pasajeroTest;

	@Mock
	PasajeroDTO pasajeromock;

	VueloDTO vueloTest;

	@Test
	void testConfirmarReservaHandler() {
		try {
			UUID reservaIdTest = UUID.randomUUID();
			NumeroReserva nroReservaTest = new NumeroReserva("Nro Reserva");
			pasajeroTest =
				new PasajeroDTO(
					UUID.randomUUID().toString(),
					1,
					1,
					"nombre",
					"apellido",
					"segundo"
				);
			vueloTest =
				new VueloDTO(UUID.randomUUID().toString(), 123, 1, "santa");
			Monto precioTest = new Monto(10);
			CantidadPasajero cantidadPasajeroTest = new CantidadPasajero(1);
			Reserva reservaTest = new Reserva(
				reservaIdTest,
				nroReservaTest,
				UUID.fromString(pasajeroTest.getPasajeroId()),
				UUID.fromString(vueloTest.getVueloId()),
				precioTest,
				cantidadPasajeroTest
			);
			String pagoId = UUID.randomUUID().toString();

			when(_reservaRepository.FindByIdAsync(any()))
				.thenReturn(CompletableFuture.completedFuture(reservaTest));
			when(_mediator.Send(any(GetPasajeroByIdQuery.class)))
				.thenReturn(pasajeroTest);
			when(_mediator.Send(any(GetVueloByIdQuery.class)))
				.thenReturn(vueloTest);
			Future<UUID> resultado = handler.Handle(
				new ConfirmarReservaCommand(
					reservaTest.getId().toString(),
					pagoId
				)
			);
			assertNotNull(resultado.get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
