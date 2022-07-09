package com.diplo.application.msreserva.dto.pasajero;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

class PasajeroDTOTest {

	@Test
	void CrearPasajeroDTO() {
		
		String pasajeroIdTest = UUID.randomUUID().toString();
		int nroDoc = 1;
		int tipoDoc = 1;
		String nombre = "Nombre";
		String primerApellido = "apellido1";
		String segundoApellido = "apellido2";
		
		PasajeroDTO pasajeroDTO = new PasajeroDTO();
	
		pasajeroDTO.setPasajeroId(pasajeroIdTest);
		pasajeroDTO.setNroDoc(nroDoc);
		pasajeroDTO.setTipoDoc(tipoDoc);
		pasajeroDTO.setNombre(nombre);
		pasajeroDTO.setPrimerApellido(primerApellido);
		pasajeroDTO.setSegundoApellido(segundoApellido);
	
		assertEquals(pasajeroIdTest, pasajeroDTO.getPasajeroId());
		assertEquals(nroDoc, pasajeroDTO.getNroDoc());
		assertEquals(tipoDoc, pasajeroDTO.getTipoDoc());
		assertEquals(nombre, pasajeroDTO.getNombre());
		assertEquals(primerApellido, pasajeroDTO.getPrimerApellido());
		assertEquals(segundoApellido, pasajeroDTO.getSegundoApellido());
	
		
		pasajeroDTO = new PasajeroDTO(nroDoc, tipoDoc, nombre, primerApellido, segundoApellido);
	
		assertEquals(nroDoc, pasajeroDTO.getNroDoc());
		assertEquals(tipoDoc, pasajeroDTO.getTipoDoc());
		assertEquals(nombre, pasajeroDTO.getNombre());
		assertEquals(primerApellido, pasajeroDTO.getPrimerApellido());
		assertEquals(segundoApellido, pasajeroDTO.getSegundoApellido());
	
		pasajeroDTO = new PasajeroDTO(pasajeroIdTest, nroDoc, tipoDoc, nombre, primerApellido, segundoApellido);
		
		assertEquals(pasajeroIdTest, pasajeroDTO.getPasajeroId());
		assertEquals(nroDoc, pasajeroDTO.getNroDoc());
		assertEquals(tipoDoc, pasajeroDTO.getTipoDoc());
		assertEquals(nombre, pasajeroDTO.getNombre());
		assertEquals(primerApellido, pasajeroDTO.getPrimerApellido());
		assertEquals(segundoApellido, pasajeroDTO.getSegundoApellido());
		
	}

}
