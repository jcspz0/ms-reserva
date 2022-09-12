package com.diplo.application.msreserva.usecase.consumer.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.sharedkernel.integrationevents.IntegrationDeudaVencida;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaConfirmada;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaConfirmadaRollback;
import com.diplo.sharedkernel.mediator.IMediator;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ConsumerIntegrationReservaConfirmadaRollbackTest {

	@InjectMocks
	ConsumerIntegrationReservaConfirmadaRollback consumer;

	@Mock
	IMediator _mediator;

	@Test
	void testReservaConfirmadaRollback() {
		String reservaId = UUID.randomUUID().toString();
		String pagoId = UUID.randomUUID().toString();
		consumer.Consume(
			new IntegrationReservaConfirmadaRollback(reservaId, pagoId)
		);
		assertTrue(true);
	}
}
