package com.diplo.application.msreserva.dto.reserva;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class ReservaDTOTest {

	@Test
	void crearReservaDTO() {
		String id = UUID.randomUUID().toString();
		String nroReserva = "123";
		String pasajeroId = UUID.randomUUID().toString();
		String vueloId = UUID.randomUUID().toString();
		double precio = 10;
		int cantidadPasajero = 1;
		String hora = "2022-04-25 18:22:41";
		String estado = "VALIDO";

		ReservaDTO reservaDTOTest = new ReservaDTO();
		reservaDTOTest.setReservaId(id);
		reservaDTOTest.setNroReserva(nroReserva);
		reservaDTOTest.setPasajeroId(pasajeroId);
		reservaDTOTest.setVueloId(vueloId);
		reservaDTOTest.setPrecio(precio);
		reservaDTOTest.setCantidadPasajero(cantidadPasajero);
		reservaDTOTest.setHora(hora);
		reservaDTOTest.setEstado(estado);

		assertEquals(id, reservaDTOTest.getReservaId());
		assertEquals(nroReserva, reservaDTOTest.getNroReserva());
		assertEquals(pasajeroId, reservaDTOTest.getPasajeroId());
		assertEquals(vueloId, reservaDTOTest.getVueloId());
		assertEquals(precio, reservaDTOTest.getPrecio());
		assertEquals(cantidadPasajero, reservaDTOTest.getCantidadPasajero());
		assertEquals(hora, reservaDTOTest.getHora());
		assertEquals(estado, reservaDTOTest.getEstado());

		reservaDTOTest =
			new ReservaDTO(
				nroReserva,
				pasajeroId,
				vueloId,
				precio,
				cantidadPasajero,
				hora,
				estado
			);

		assertEquals(pasajeroId, reservaDTOTest.getPasajeroId());
		assertEquals(vueloId, reservaDTOTest.getVueloId());
		assertEquals(precio, reservaDTOTest.getPrecio());
		assertEquals(cantidadPasajero, reservaDTOTest.getCantidadPasajero());
		assertEquals(hora, reservaDTOTest.getHora());
		assertEquals(estado, reservaDTOTest.getEstado());

		reservaDTOTest =
			new ReservaDTO(
				id,
				nroReserva,
				pasajeroId,
				vueloId,
				precio,
				cantidadPasajero,
				hora,
				estado
			);

		assertEquals(id, reservaDTOTest.getReservaId());
		assertEquals(nroReserva, reservaDTOTest.getNroReserva());
		assertEquals(pasajeroId, reservaDTOTest.getPasajeroId());
		assertEquals(vueloId, reservaDTOTest.getVueloId());
		assertEquals(precio, reservaDTOTest.getPrecio());
		assertEquals(cantidadPasajero, reservaDTOTest.getCantidadPasajero());
		assertEquals(hora, reservaDTOTest.getHora());
		assertEquals(estado, reservaDTOTest.getEstado());
	}
}
