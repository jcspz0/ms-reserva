package com.diplo.application.msreserva.listenerevent;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.event.MessageEvent;
import com.diplo.sharedkernel.integrationevents.IntegrationDeudaPagada;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

@ExtendWith(MockitoExtension.class)
class ListenerIntegrationEventTrackerTest {

	@InjectMocks
	ListenerIntegrationEventTracker listener;

	@Mock
	ApplicationEventPublisher publisher;

	@Test
	void publish() {
		listener.publish(new IntegrationEvent(listener, null));
		assertTrue(true);
	}
}
