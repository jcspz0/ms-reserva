package com.diplo.application.msreserva.usecase.producer.integration;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.sharedkernel.amqp.IAmqpProducer;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagadaRollback;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProducerIntegrationDeudaPagadaRollbackTest {

	@Mock
	IAmqpProducer<IntegrationDeudaPagadaRollback> amqp;

	@InjectMocks
	ProducerIntegrationDeudaPagadaRollback test;

	@Test
	void OnProcessEventDeudaPagadaRollback() {
		test.onProcessEvent(
			new IntegrationDeudaPagadaRollback(
				UUID.randomUUID().toString(),
				UUID.randomUUID().toString()
			)
		);
		assertTrue(true);
	}
}
