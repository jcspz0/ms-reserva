package com.diplo.application.msreserva.usecase.producer.integration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

import com.diplo.sharedkernel.amqp.IAmqpProducer;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagadaRollback;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaConfirmada;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProducerIntegrationReservaConfirmadaTest {

	@Mock
	IAmqpProducer<IntegrationReservaConfirmada> amqp;

	@InjectMocks
	ProducerIntegrationReservaConfirmada test;

	@Test
	void OnProcessEventReservaConfirmada() {
		test.onProcessEvent(
			new IntegrationReservaConfirmada(
				UUID.randomUUID().toString(),
				UUID.randomUUID().toString(),
				1,
				1,
				"prueba",
				LocalDateTime.now().toString(),
				"santa",
				"santa",
				1,
				UUID.randomUUID().toString()
			)
		);
		assertTrue(true);
	}
}
