package com.diplo.application.msreserva.usecase.command.reserva.vencerreserva;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
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
class VencerReservaHandlerTest {

	@InjectMocks
	VencerReservaHandler handlerTest;

	@Mock
	IUnitOfWork _unitOfWork;

	@Mock
	IReservaRepository _reservaRepository;

	@Test
	void vencerReserva() {
		try {
			UUID reservaIdTest = UUID.randomUUID();
			NumeroReserva nroReservaTest = new NumeroReserva("Nro Reserva");
			UUID pasajeroTest = UUID.randomUUID();
			UUID vueloIdTest = UUID.randomUUID();
			Monto precioTest = new Monto(10);
			CantidadPasajero cantidadPasajeroTest = new CantidadPasajero(1);

			//VueloDTO vueloDTOTest = new VueloDTO(UUID.randomUUID().toString(), 1, 1, "Santa");

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
			VencerReservaCommand comand = new VencerReservaCommand(
				reservaTest.getId().toString()
			);

			Future<UUID> resultado = handlerTest.Handle(comand);
			assertNotNull(resultado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
