package com.diplo.infraestructure.msreserva.entityframework.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class PasajeroEntityTest {

	@Test
	void testPasajeroEntity() {
		int nroDoc = 1;
		int tipoDoc = 1;
		String nombre = "nombre";
		String pasajeroId = UUID.randomUUID().toString();
		String primerApellido = "apellido";
		String segundoApellido = "segundo";

		PasajeroEntity pasajeroEntity = new PasajeroEntity();

		pasajeroEntity.setNroDoc(nroDoc);
		pasajeroEntity.setTipoDoc(tipoDoc);
		pasajeroEntity.setNombre(nombre);
		pasajeroEntity.setPasajeroId(pasajeroId);
		pasajeroEntity.setPrimerApellido(primerApellido);
		pasajeroEntity.setSegundoApellido(segundoApellido);

		assertEquals(nroDoc, pasajeroEntity.getNroDoc());
		assertEquals(tipoDoc, pasajeroEntity.getTipoDoc());
		assertEquals(nombre, pasajeroEntity.getNombre());
		assertEquals(primerApellido, pasajeroEntity.getPrimerApellido());
		assertEquals(segundoApellido, pasajeroEntity.getSegundoApellido());
		assertEquals(pasajeroId, pasajeroEntity.getPasajeroId());
	}
}
