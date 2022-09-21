package com.diplo.infraestructure.msreserva.entityframework.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class VueloEntityTest {

	@Test
	void VueloEntity() {
		String VueloId = UUID.randomUUID().toString();
		String NroVuelo = "123";
		int CantidadAsientoDisponible = 1;
		String Destino = "Santa";
		String Origen = "Santa";
		VueloEntity vueloEntity = new VueloEntity();
		vueloEntity.setCantidadAsientoDisponible(CantidadAsientoDisponible);
		vueloEntity.setDestino(Destino);
		vueloEntity.setNroVuelo(NroVuelo);
		vueloEntity.setVueloId(VueloId);
		vueloEntity.setOrigen(Origen);

		assertEquals(VueloId, vueloEntity.getVueloId());
		assertEquals(NroVuelo, vueloEntity.getNroVuelo());
		assertEquals(
			CantidadAsientoDisponible,
			vueloEntity.getCantidadAsientoDisponible()
		);
		assertEquals(Destino, vueloEntity.getDestino());
		assertEquals(Origen, vueloEntity.getOrigen());
	}
}
