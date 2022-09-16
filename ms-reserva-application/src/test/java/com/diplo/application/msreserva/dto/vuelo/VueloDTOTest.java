package com.diplo.application.msreserva.dto.vuelo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class VueloDTOTest {

	@Test
	void crearVueloDTO() {
		String id = UUID.randomUUID().toString();
		String nroVuelo = "123";
		int cantidadAsientoDisponible = 1;
		String destino = "Santa";

		VueloDTO vueloDTOTest = new VueloDTO();
		vueloDTOTest.setCantidadAsientoDisponible(cantidadAsientoDisponible);
		vueloDTOTest.setDestino(destino);
		vueloDTOTest.setNroVuelo(nroVuelo);
		vueloDTOTest.setVueloId(id);
		vueloDTOTest.setOrigen(destino);

		assertEquals(id, vueloDTOTest.getVueloId());
		assertEquals(nroVuelo, vueloDTOTest.getNroVuelo());
		assertEquals(destino, vueloDTOTest.getDestino());
		assertEquals(destino, vueloDTOTest.getOrigen());
		assertEquals(
			cantidadAsientoDisponible,
			vueloDTOTest.getCantidadAsientoDisponible()
		);

		vueloDTOTest =
			new VueloDTO(nroVuelo, cantidadAsientoDisponible, destino, destino);

		assertEquals(nroVuelo, vueloDTOTest.getNroVuelo());
		assertEquals(destino, vueloDTOTest.getDestino());
		assertEquals(destino, vueloDTOTest.getOrigen());
		assertEquals(
			cantidadAsientoDisponible,
			vueloDTOTest.getCantidadAsientoDisponible()
		);
	}
}
