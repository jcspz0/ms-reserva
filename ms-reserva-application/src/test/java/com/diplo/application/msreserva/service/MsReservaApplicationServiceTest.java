package com.diplo.application.msreserva.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.diplo.application.msreserva.mediator.Mediator;
import com.diplo.application.msreserva.service.reserva.ReservaService;
import com.diplo.msreserva.factory.ReservaFactory;

class MsReservaApplicationServiceTest {

	@Test
	void MsReservaApplicationService() {
		MsReservaApplicationService msReservaApplicationService = new MsReservaApplicationService();
		
		ReservaFactory reservaFactory = new ReservaFactory();
		Mediator mediator = new Mediator();
		ReservaService reservaService = new ReservaService();
		
		msReservaApplicationService.set_ReservaFactory(reservaFactory);
		msReservaApplicationService.setMediator(mediator);
		msReservaApplicationService.setReservaService(reservaService);
		
		assertEquals(reservaFactory, msReservaApplicationService.get_ReservaFactory());
		assertEquals(mediator, msReservaApplicationService.getMediator());
		assertEquals(reservaService, msReservaApplicationService.getReservaService());
		
		msReservaApplicationService = new MsReservaApplicationService(mediator, reservaService, reservaFactory);
		
		assertNotNull(msReservaApplicationService);
		
	}

}
