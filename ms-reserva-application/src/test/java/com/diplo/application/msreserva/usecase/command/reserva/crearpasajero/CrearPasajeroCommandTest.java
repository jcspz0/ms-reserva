package com.diplo.application.msreserva.usecase.command.reserva.crearpasajero;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CrearPasajeroCommandTest {

	@Test
	void CrearPasajeroCommand() {
		
		CrearPasajeroCommand crearPasajeroCommand = new CrearPasajeroCommand();
		
		int nroDoc=1;
		int tipoDoc=1;
		String nombre="nombre";
		String primerApellido="apellido";
		String segundoApellido = "apellido2";
		
		crearPasajeroCommand.setNroDoc(nroDoc);
		crearPasajeroCommand.setTipoDoc(tipoDoc);
		crearPasajeroCommand.setNombre(nombre);
		crearPasajeroCommand.setPrimerApellido(primerApellido);
		crearPasajeroCommand.setSegundoApellido(segundoApellido);

		assertEquals(nroDoc, crearPasajeroCommand.getNroDoc());
		assertEquals(tipoDoc, crearPasajeroCommand.getTipoDoc());
		assertEquals(nombre, crearPasajeroCommand.getNombre());
		assertEquals(primerApellido, crearPasajeroCommand.getPrimerApellido());
		assertEquals(segundoApellido, crearPasajeroCommand.getSegundoApellido());

		crearPasajeroCommand = new CrearPasajeroCommand(nroDoc, tipoDoc, nombre, primerApellido, segundoApellido);
	
		assertEquals(nroDoc, crearPasajeroCommand.getNroDoc());
		assertEquals(tipoDoc, crearPasajeroCommand.getTipoDoc());
		assertEquals(nombre, crearPasajeroCommand.getNombre());
		assertEquals(primerApellido, crearPasajeroCommand.getPrimerApellido());
		assertEquals(segundoApellido, crearPasajeroCommand.getSegundoApellido());
	}

}
