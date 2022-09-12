package com.diplo.infraestructure.msreserva.entityframework.tracker;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.infraestructure.msreserva.tracker.ListenerEventTrackerInfra;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.event.MessageEvent;
import org.junit.jupiter.api.Test;

class ListenerEventTrackerTest {

	@Test
	void OnApplicationEvent() {
		ListenerEventTrackerInfra listenerEventTracker = new ListenerEventTrackerInfra();

		listenerEventTracker.clearTracker();
		listenerEventTracker.publish(
			new MessageEvent(listenerEventTracker, "accion")
		);

		assertNotNull(listenerEventTracker);
		assertNotNull(listenerEventTracker.getTrackersCargados());
	}

	@Test
	void OnIntegrationEvent() {
		ListenerEventTrackerInfra listenerEventTracker = new ListenerEventTrackerInfra();

		listenerEventTracker.clearIntegrationTracker();
		listenerEventTracker.publish(
			new IntegrationEvent(listenerEventTracker, "accion")
		);

		assertNotNull(listenerEventTracker);
		assertNotNull(listenerEventTracker.getIntegrationTrackersCargados());
	}
}
