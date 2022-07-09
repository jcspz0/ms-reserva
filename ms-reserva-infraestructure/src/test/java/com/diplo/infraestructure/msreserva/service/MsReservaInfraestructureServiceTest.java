package com.diplo.infraestructure.msreserva.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.diplo.application.msreserva.service.MsReservaApplicationService;
import com.diplo.msreserva.repository.IPasajeroRepository;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.repository.IVueloRepository;

@ExtendWith(MockitoExtension.class)
class MsReservaInfraestructureServiceTest {

	@Mock
	MsReservaApplicationService service;
	@Mock
	IReservaRepository _reservaRepository;
	@Mock
	IVueloRepository _vueloRepository;
	
	@Mock
	IPasajeroRepository _pasajeroRepository;
	
	@Mock
	IUnitOfWork _unitOfWork;
	
	@Test
	void testMsReservaInfraestructureService() {
		MsReservaInfraestructureService msReservaInfraestructureService = new MsReservaInfraestructureService(service, _reservaRepository, _vueloRepository, _pasajeroRepository, _unitOfWork);
		msReservaInfraestructureService.set_unitOfWork(_unitOfWork);
		msReservaInfraestructureService.setPasajeroRepository(_pasajeroRepository);
		msReservaInfraestructureService.setReservaRepository(_reservaRepository);
		msReservaInfraestructureService.setService(service);
		msReservaInfraestructureService.setVueloRepository(_vueloRepository);
		
		assertEquals(service, msReservaInfraestructureService.getService());
		assertEquals(_reservaRepository, msReservaInfraestructureService.getReservaRepository());
		assertEquals(_vueloRepository, msReservaInfraestructureService.getVueloRepository());
		assertEquals(_pasajeroRepository, msReservaInfraestructureService.getPasajeroRepository());
		assertEquals(_unitOfWork, msReservaInfraestructureService.get_unitOfWork());
		
		
	}

}
