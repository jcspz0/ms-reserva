package com.diplo.infraestructure.msreserva.entityframework.tracker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ListenerEventTrackerTest {

	@Test
	void OnApplicationEvent() {
		ListenerEventTracker listenerEventTracker = new ListenerEventTracker();
		
		listenerEventTracker.clearTracker();
		listenerEventTracker.onApplicationEvent(new MessageEvent(listenerEventTracker, "accion"));
		
		assertNotNull(listenerEventTracker);
		assertNotNull(listenerEventTracker.getTrackersCargados());
		
	}

}
