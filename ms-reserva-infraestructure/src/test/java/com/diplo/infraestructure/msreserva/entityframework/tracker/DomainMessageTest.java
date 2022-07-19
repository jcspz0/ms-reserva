package com.diplo.infraestructure.msreserva.entityframework.tracker;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DomainMessageTest {

	@Test
	void probarDomainMessage() {
		Object message = new Object();
		String action = "accion";
		DomainMessage domainMessage = new DomainMessage(message, action);
		assertEquals(message, domainMessage.getMessage());
		assertEquals(action, domainMessage.getAction());
	}
}
