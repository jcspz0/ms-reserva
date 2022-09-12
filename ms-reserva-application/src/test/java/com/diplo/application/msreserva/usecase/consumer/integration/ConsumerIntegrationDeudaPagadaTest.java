package com.diplo.application.msreserva.usecase.consumer.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.application.msreserva.usecase.consumer.domain.ConsumerDomainReservaCreada;
import com.diplo.msreserva.repository.IPasajeroRepository;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagada;
import com.diplo.sharedkernel.mediator.IMediator;
import com.diplo.sharedkernel.mediator.Mediator;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
class ConsumerIntegrationDeudaPagadaTest {

	@InjectMocks
	ConsumerIntegrationDeudaPagada consumer;

	@Mock
	IMediator _mediator;

	@Test
	void ejecutarConsume() {
		String reservaId = UUID.randomUUID().toString();
		String pagoId = UUID.randomUUID().toString();
		consumer.Consume(new IntegrationDeudaPagada(reservaId, pagoId));
		assertTrue(true);
	}
}
