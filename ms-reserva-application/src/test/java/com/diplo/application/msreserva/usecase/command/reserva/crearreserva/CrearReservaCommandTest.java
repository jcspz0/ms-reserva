package com.diplo.application.msreserva.usecase.command.reserva.crearreserva;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.diplo.application.msreserva.dto.vuelo.VueloDTO;

class CrearReservaCommandTest {

	@Test
	void testCrearReservaCommand() {
		
		String reservaId = UUID.randomUUID().toString();
		String nroReserva = "123";
		String nroPasajero = UUID.randomUUID().toString(); 
		VueloDTO vueloDTO = new VueloDTO(1, 1, "santa");
		double monto = 10;
		int cantidadPasajero = 1;
		
		
		CrearReservaCommand crearReservaCommand = new CrearReservaCommand();
		crearReservaCommand.setCantidadPasajero(cantidadPasajero);
		crearReservaCommand.setMonto(monto);
		crearReservaCommand.setNroPasajero(nroPasajero);
		crearReservaCommand.setNroReserva(nroReserva);
		crearReservaCommand.setVuelo(vueloDTO);
	
		assertEquals(nroReserva, crearReservaCommand.getNroReserva());
		assertEquals(nroPasajero, crearReservaCommand.getNroPasajero());
		assertEquals(vueloDTO, crearReservaCommand.getVuelo());
		assertEquals(monto, crearReservaCommand.getMonto());
		assertEquals(cantidadPasajero, crearReservaCommand.getCantidadPasajero());
		
		crearReservaCommand = new CrearReservaCommand(nroReserva, nroPasajero, vueloDTO, monto, cantidadPasajero);
		
		assertEquals(nroReserva, crearReservaCommand.getNroReserva());
		assertEquals(nroPasajero, crearReservaCommand.getNroPasajero());
		assertEquals(vueloDTO, crearReservaCommand.getVuelo());
		assertEquals(monto, crearReservaCommand.getMonto());
		assertEquals(cantidadPasajero, crearReservaCommand.getCantidadPasajero());
		
		crearReservaCommand = new CrearReservaCommand(reservaId, nroReserva, nroPasajero, vueloDTO, monto, cantidadPasajero);
		
		assertEquals(nroReserva, crearReservaCommand.getNroReserva());
		assertEquals(nroPasajero, crearReservaCommand.getNroPasajero());
		assertEquals(vueloDTO, crearReservaCommand.getVuelo());
		assertEquals(monto, crearReservaCommand.getMonto());
		assertEquals(cantidadPasajero, crearReservaCommand.getCantidadPasajero());
		
	}

}
