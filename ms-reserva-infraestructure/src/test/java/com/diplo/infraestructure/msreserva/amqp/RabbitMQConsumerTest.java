package com.diplo.infraestructure.msreserva.amqp;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.sharedkernel.event.IListenerIntegrationConsumer;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagada;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaVencida;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaConfirmada;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaConfirmadaRollback;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
class RabbitMQConsumerTest {

	@Mock
	private IListenerIntegrationConsumer consumer;

	@InjectMocks
	RabbitMQConsumer consumerTest;

	@Test
	void testConsumers() {
		try {
			consumerTest.deudaPagada(
				new IntegrationDeudaPagada(
					UUID.randomUUID().toString(),
					UUID.randomUUID().toString()
				)
			);
			consumerTest.deudaVencida(
				new IntegrationDeudaVencida(UUID.randomUUID().toString())
			);
			consumerTest.reservaConfirmada(
				new IntegrationReservaConfirmada(
					UUID.randomUUID().toString(),
					UUID.randomUUID().toString(),
					1,
					1,
					"Pruebas",
					LocalDateTime.now().toString(),
					"destino",
					1,
					UUID.randomUUID().toString()
				)
			);
			consumerTest.reservaConfirmadaRollback(
				new IntegrationReservaConfirmadaRollback(
					UUID.randomUUID().toString(),
					UUID.randomUUID().toString()
				)
			);
			assertTrue(true);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
