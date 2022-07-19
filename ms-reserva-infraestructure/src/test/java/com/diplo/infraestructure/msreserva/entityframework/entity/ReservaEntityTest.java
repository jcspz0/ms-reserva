package com.diplo.infraestructure.msreserva.entityframework.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class ReservaEntityTest {

	@Test
	void ReservaEntity() {
		String ReservaId = UUID.randomUUID().toString();
		String nroReserva = "123";
		String PasajeroId = UUID.randomUUID().toString();
		String VueloId = UUID.randomUUID().toString();
		double Precio = 10;
		int CantidadPasajero = 1;
		LocalDateTime Hora = LocalDateTime.now();
		String Estado = "VALIDO";
		ReservaEntity reservaEntity = new ReservaEntity();
		reservaEntity.setCantidadPasajero(CantidadPasajero);
		reservaEntity.setEstado(Estado);
		reservaEntity.setHora(Hora);
		reservaEntity.setNroReserva(nroReserva);
		reservaEntity.setPasajeroId(PasajeroId);
		reservaEntity.setPrecio(Precio);
		reservaEntity.setReservaId(ReservaId);
		reservaEntity.setVueloId(VueloId);

		assertEquals(ReservaId, reservaEntity.getReservaId());
		assertEquals(nroReserva, reservaEntity.getNroReserva());
		assertEquals(PasajeroId, reservaEntity.getPasajeroId());
		assertEquals(VueloId, reservaEntity.getVueloId());
		assertEquals(Precio, reservaEntity.getPrecio());
		assertEquals(CantidadPasajero, reservaEntity.getCantidadPasajero());
		assertEquals(Hora, reservaEntity.getHora());
		assertEquals(Estado, reservaEntity.getEstado());
	}
}
