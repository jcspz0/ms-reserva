package com.diplo.application.msreserva.usecase.command.reserva.crearvuelo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class CrearVueloCommandTest {

	@Test
	void CrearVueloCommand() {
		String vueloId = UUID.randomUUID().toString();
		String nroVuelo = "123";
		int cantidadAsientoDisponible = 10;
		String destino = "Santa";
		String origen = "Santa";

		CrearVueloCommand crearVueloCommand = new CrearVueloCommand();

		crearVueloCommand.setVueloId(vueloId);
		crearVueloCommand.setNroVuelo(nroVuelo);
		crearVueloCommand.setCantidadAsientoDisponible(
			cantidadAsientoDisponible
		);
		crearVueloCommand.setDestino(destino);
		crearVueloCommand.setOrigen(origen);

		assertEquals(vueloId, crearVueloCommand.getVueloId());
		assertEquals(nroVuelo, crearVueloCommand.getNroVuelo());
		assertEquals(
			cantidadAsientoDisponible,
			crearVueloCommand.getCantidadAsientoDisponible()
		);
		assertEquals(destino, crearVueloCommand.getDestino());
		assertEquals(origen, crearVueloCommand.getOrigen());

		crearVueloCommand =
			new CrearVueloCommand(
				vueloId,
				nroVuelo,
				cantidadAsientoDisponible,
				origen,
				destino
			);

		assertEquals(vueloId, crearVueloCommand.getVueloId());
		assertEquals(nroVuelo, crearVueloCommand.getNroVuelo());
		assertEquals(
			cantidadAsientoDisponible,
			crearVueloCommand.getCantidadAsientoDisponible()
		);
		assertEquals(destino, crearVueloCommand.getDestino());
		assertEquals(origen, crearVueloCommand.getOrigen());
	}
}
