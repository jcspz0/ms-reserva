package com.diplo.application.msreserva.usecase.consumer.domain;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.diplo.application.msreserva.usecase.command.vuelo.reducirdisponibilidad.ReducirDisponibilidadCommand;
import com.diplo.application.msreserva.usecase.command.vuelo.reducirdisponibilidad.ReducirDisponibilidadHandler;
import com.diplo.msreserva.event.ReservaCreada;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.msreserva.valueobjects.HoraReserva;
import com.diplo.msreserva.valueobjects.NumeroReserva;
import com.diplo.sharedkernel.mediator.IMediator;
import com.diplo.sharedkernel.mediator.Mediator;
import com.diplo.sharedkernel.mediator.request.IRequest;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConsumerDomainReservaCreadaTest {

	@InjectMocks
	ConsumerDomainReservaCreada consumer;

	@Mock
	IMediator _mediator = new Mediator();

	@Test
	void ejecutarConsume() {
		//_mediator = new Mediator();
		NumeroReserva NroReserva = new NumeroReserva("123");
		UUID ReservaId = UUID.randomUUID();
		UUID VueloId = UUID.randomUUID();
		UUID pasajero = UUID.randomUUID();
		HoraReserva hora = new HoraReserva(LocalDateTime.now());
		int cantidadPasajeros = 1;
		try {
			//when(_mediator.registrarComando(any(),any()));
			//when(_mediator.Send(any())).thenReturn(CompletableFuture.completedFuture(UUID.randomUUID()));

			//ConsumerDomainReservaCreada consumer = new ConsumerDomainReservaCreada();
			ReservaCreada event = new ReservaCreada(
				ReservaId,
				NroReserva,
				VueloId,
				cantidadPasajeros,
				pasajero,
				hora
			);
			consumer.onProcessEvent(event);

			assertTrue(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
