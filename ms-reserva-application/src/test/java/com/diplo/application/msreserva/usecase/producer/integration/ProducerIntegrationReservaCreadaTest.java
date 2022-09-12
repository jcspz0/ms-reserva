package com.diplo.application.msreserva.usecase.producer.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.sharedkernel.amqp.IAmqpProducer;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaConfirmada;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProducerIntegrationReservaCreadaTest {

	@Mock
	IAmqpProducer<IntegrationReservaCreada> amqp;

	@InjectMocks
	ProducerIntegrationReservaCreada test;

	@Test
	void OnProcessEventReservaCreada() {
		test.onProcessEvent(
			new IntegrationReservaCreada(
				UUID.randomUUID().toString(),
				"1",
				UUID.randomUUID().toString(),
				1,
				UUID.randomUUID().toString(),
				LocalDateTime.now().toString(),
				100
			)
		);
		assertTrue(true);
	}
}
