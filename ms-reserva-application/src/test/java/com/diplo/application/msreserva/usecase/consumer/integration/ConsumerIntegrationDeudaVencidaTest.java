package com.diplo.application.msreserva.usecase.consumer.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.sharedkernel.integrationevents.IntegrationDeudaVencida;
import com.diplo.sharedkernel.mediator.IMediator;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConsumerIntegrationDeudaVencidaTest {

	@InjectMocks
	ConsumerIntegrationDeudaVencida consumer;

	@Mock
	IMediator _mediator;

	@Test
	void ejecutarConsume() {
		String reserveId = UUID.randomUUID().toString();
		consumer.Consume(new IntegrationDeudaVencida(reserveId));
		assertTrue(true);
	}
}
