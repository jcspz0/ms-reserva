package com.diplo.msreserva.model.reserva;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.CantidadPasajero;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.Monto;
import com.diplo.msreserva.valueobjects.NumeroReserva;
import com.diplo.msreserva.valueobjects.NumeroVuelo;
import com.diplo.sharedkernel.core.Constant;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class ReservaTest {

	@Test
	void RealizarReservaConDisponibilidad() throws Exception {
		Vuelo vueloTest = new Vuelo(
			UUID.randomUUID(),
			new NumeroVuelo(1),
			new Destino("Santa"),
			new AsientoDisponible(5)
		);
		Reserva reservaTest = new Reserva(
			UUID.randomUUID(),
			new NumeroReserva("10"),
			UUID.randomUUID(),
			vueloTest.getId(),
			new Monto(100),
			new CantidadPasajero(5)
		);

		boolean result = reservaTest.RealizarReserva(vueloTest);

		assertTrue(result);
	}

	@Test
	void NoRealizarReservaPorDisponibilidad() throws Exception {
		Vuelo vueloTest = new Vuelo(
			UUID.randomUUID(),
			new NumeroVuelo(1),
			new Destino("Santa"),
			new AsientoDisponible(5)
		);
		Reserva reservaTest = new Reserva(
			UUID.randomUUID(),
			new NumeroReserva("10"),
			UUID.randomUUID(),
			vueloTest.getId(),
			new Monto(100),
			new CantidadPasajero(8)
		);

		boolean result = reservaTest.RealizarReserva(vueloTest);

		assertFalse(result);
	}

	@Test
	void VencerReserva() throws Exception {
		Vuelo vueloTest = new Vuelo(
			UUID.randomUUID(),
			new NumeroVuelo(1),
			new Destino("Santa"),
			new AsientoDisponible(5)
		);
		Reserva reservaTest = new Reserva(
			UUID.randomUUID(),
			new NumeroReserva("10"),
			UUID.randomUUID(),
			vueloTest.getId(),
			new Monto(100),
			new CantidadPasajero(8)
		);

		reservaTest.VencerReserva();

		assertEquals(Constant.RESERVAESTADOVENCIDA, reservaTest.getEstado());
	}

	@Test
	void VencerReservaConError() throws Exception {
		Vuelo vueloTest = new Vuelo(
			UUID.randomUUID(),
			new NumeroVuelo(1),
			new Destino("Santa"),
			new AsientoDisponible(5)
		);
		Reserva reservaTest = new Reserva(
			UUID.randomUUID(),
			new NumeroReserva("10"),
			UUID.randomUUID(),
			vueloTest.getId(),
			new Monto(100),
			new CantidadPasajero(8),
			Constant.RESERVAESTADOCONFIRMADA
		);

		Exception exception = assertThrows(
			Exception.class,
			() -> {
				reservaTest.VencerReserva();
			}
		);
		//

		assertEquals(
			"La reserva no se puede cancelar, su estado no es " +
			Constant.RESERVAESTADOCREADA,
			exception.getMessage()
		);
	}

	@Test
	void DeshacerConfirmacion() throws Exception {
		Vuelo vueloTest = new Vuelo(
			UUID.randomUUID(),
			new NumeroVuelo(1),
			new Destino("Santa"),
			new AsientoDisponible(5)
		);
		Reserva reservaTest = new Reserva(
			UUID.randomUUID(),
			new NumeroReserva("10"),
			UUID.randomUUID(),
			vueloTest.getId(),
			new Monto(100),
			new CantidadPasajero(8)
		);

		reservaTest.DeshacerConfirmacion();

		assertEquals(Constant.RESERVAESTADOCREADA, reservaTest.getEstado());
	}

	@Test
	void ConfirmarReserva() throws Exception {
		Vuelo vueloTest = new Vuelo(
			UUID.randomUUID(),
			new NumeroVuelo(1),
			new Destino("Santa"),
			new AsientoDisponible(5)
		);
		Reserva reservaTest = new Reserva(
			UUID.randomUUID(),
			new NumeroReserva("10"),
			UUID.randomUUID(),
			vueloTest.getId(),
			new Monto(100),
			new CantidadPasajero(8)
		);

		reservaTest.ConfirmarReserva(
			UUID.randomUUID().toString(),
			UUID.randomUUID().toString(),
			"Santa",
			1,
			1,
			"Juan"
		);

		assertEquals(Constant.RESERVAESTADOCONFIRMADA, reservaTest.getEstado());
	}

	@Test
	void ConfirmarReservaConError() throws Exception {
		Vuelo vueloTest = new Vuelo(
			UUID.randomUUID(),
			new NumeroVuelo(1),
			new Destino("Santa"),
			new AsientoDisponible(5)
		);
		Reserva reservaTest = new Reserva(
			UUID.randomUUID(),
			new NumeroReserva("10"),
			UUID.randomUUID(),
			vueloTest.getId(),
			new Monto(100),
			new CantidadPasajero(8)
		);

		Exception exception = assertThrows(
			Exception.class,
			() -> {
				reservaTest.VencerReserva();
				reservaTest.ConfirmarReserva(
					UUID.randomUUID().toString(),
					UUID.randomUUID().toString(),
					"Santa",
					1,
					1,
					"Juan"
				);
			}
		);
		assertEquals(
			"No se puede confirmar una reserva vencida",
			exception.getMessage()
		);
	}

	@Test
	void verReserva() throws Exception {
		UUID reservaIdTest = UUID.randomUUID();
		NumeroReserva nroReservaTest = new NumeroReserva("Nro Reserva");
		UUID pasajeroTest = UUID.randomUUID();
		UUID vueloIdTest = UUID.randomUUID();
		Monto precioTest = new Monto(10);
		CantidadPasajero cantidadPasajeroTest = new CantidadPasajero(1);

		Reserva reservaTest = new Reserva(
			reservaIdTest,
			nroReservaTest,
			pasajeroTest,
			vueloIdTest,
			precioTest,
			cantidadPasajeroTest
		);

		String esperado =
			"reserva: " +
			reservaIdTest +
			" , pasajero: " +
			pasajeroTest +
			" , vuelo: " +
			vueloIdTest +
			", cantidad de pasajeros " +
			cantidadPasajeroTest.getCantidadPasajero() +
			", al precio de " +
			precioTest.getMonto();

		assertEquals(esperado, reservaTest.verReserva());
	}

	@Test
	void crearReserva() throws Exception {
		UUID reservaIdTest = UUID.randomUUID();
		NumeroReserva nroReservaTest = new NumeroReserva("Nro Reserva");
		UUID pasajeroTest = UUID.randomUUID();
		UUID vueloIdTest = UUID.randomUUID();
		Monto precioTest = new Monto(10);
		CantidadPasajero cantidadPasajeroTest = new CantidadPasajero(1);

		Reserva reservaTest = new Reserva(
			reservaIdTest,
			nroReservaTest,
			pasajeroTest,
			vueloIdTest,
			precioTest,
			cantidadPasajeroTest
		);

		assertNotNull(reservaTest.getHora());
		assertEquals(precioTest.getMonto(), reservaTest.getPrecio().getMonto());
		assertEquals(
			nroReservaTest.getValue(),
			reservaTest.getNroReserva().getValue()
		);
		assertEquals(Constant.RESERVAESTADOCREADA, reservaTest.getEstado());

		reservaTest =
			new Reserva(
				reservaIdTest,
				nroReservaTest,
				pasajeroTest,
				vueloIdTest,
				precioTest,
				cantidadPasajeroTest,
				Constant.RESERVAESTADOCREADA
			);

		assertNotNull(reservaTest.getHora());
		assertEquals(precioTest.getMonto(), reservaTest.getPrecio().getMonto());
		assertEquals(
			nroReservaTest.getValue(),
			reservaTest.getNroReserva().getValue()
		);
		assertEquals(Constant.RESERVAESTADOCREADA, reservaTest.getEstado());

		reservaTest =
			new Reserva(
				nroReservaTest,
				pasajeroTest,
				vueloIdTest,
				precioTest,
				cantidadPasajeroTest
			);

		assertNotNull(reservaTest.getHora());
		assertEquals(precioTest.getMonto(), reservaTest.getPrecio().getMonto());
		assertEquals(
			nroReservaTest.getValue(),
			reservaTest.getNroReserva().getValue()
		);
		assertEquals(Constant.RESERVAESTADOCREADA, reservaTest.getEstado());

		reservaTest =
			new Reserva(
				nroReservaTest.getValue(),
				pasajeroTest,
				vueloIdTest,
				precioTest.getMonto(),
				cantidadPasajeroTest.getCantidad()
			);

		assertNotNull(reservaTest.getHora());
		assertEquals(precioTest.getMonto(), reservaTest.getPrecio().getMonto());
		assertEquals(
			nroReservaTest.getValue(),
			reservaTest.getNroReserva().getValue()
		);
		assertEquals(Constant.RESERVAESTADOCREADA, reservaTest.getEstado());

		reservaTest =
			new Reserva(
				reservaIdTest.toString(),
				nroReservaTest.getValue(),
				pasajeroTest.toString(),
				vueloIdTest.toString(),
				precioTest.getMonto(),
				cantidadPasajeroTest.getCantidad()
			);

		assertNotNull(reservaTest.getHora());
		assertEquals(precioTest.getMonto(), reservaTest.getPrecio().getMonto());
		assertEquals(
			nroReservaTest.getValue(),
			reservaTest.getNroReserva().getValue()
		);
		assertEquals(Constant.RESERVAESTADOCREADA, reservaTest.getEstado());
	}
}
