package com.diplo.application.msreserva.usecase.command.reserva.crearpasajero;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.UUID;
import java.util.concurrent.Future;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.diplo.msreserva.repository.IPasajeroRepository;
import com.diplo.msreserva.repository.IUnitOfWork;

@ExtendWith(MockitoExtension.class)
class CrearPasajeroHandlerTest {
	
	CrearPasajeroHandler crearPasajeroHandlerTest;
	
	@Mock CrearPasajeroCommand crearPasajeroCommandTest;
	@Mock IUnitOfWork _unitOfWork;
	@Mock IPasajeroRepository _pasajeroRepository;
	
	int NroDocTest;
	int TipoDocTest;
	String NombreTest;
	String PrimerApellidoTest;
	String SegundoApellidoTest;
	
	@BeforeEach
	void init() throws Exception {
		
		NroDocTest=12345;
		TipoDocTest=1;
		NombreTest="Test";
		PrimerApellidoTest="prueba";
		SegundoApellidoTest="mock";
		
		crearPasajeroHandlerTest = new CrearPasajeroHandler(_pasajeroRepository, _unitOfWork);
		
		when(crearPasajeroCommandTest.getNombre()).thenReturn(NombreTest);
		when(crearPasajeroCommandTest.getPrimerApellido()).thenReturn(PrimerApellidoTest);
		when(crearPasajeroCommandTest.getSegundoApellido()).thenReturn(SegundoApellidoTest);
		when(crearPasajeroCommandTest.getNroDoc()).thenReturn(NroDocTest);
		when(crearPasajeroCommandTest.getTipoDoc()).thenReturn(TipoDocTest);
		
	}
	
	@Test
	void CrearPasajeroHandler() throws Exception {
		
		Future<UUID> result = crearPasajeroHandlerTest.Handle(crearPasajeroCommandTest);
		
		assertNotNull(result.get());
	}

}
