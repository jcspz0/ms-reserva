package com.diplo.msreserva.model.vuelo;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.NumeroVuelo;
import com.diplo.msreserva.valueobjects.Origen;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class VueloTest {

	@Test
	void CantidadDeAsientoDisponible() throws Exception {
		UUID vueloIdTest = UUID.randomUUID();
		NumeroVuelo numeroVueloTest = new NumeroVuelo("10");
		Destino destinoTest = new Destino("Santa");
		Origen origenTest = new Origen("santa");
		AsientoDisponible cantidadAsientoDisponible = new AsientoDisponible(10);
		Vuelo vueloTest = new Vuelo(
			vueloIdTest,
			numeroVueloTest,
			origenTest,
			destinoTest,
			cantidadAsientoDisponible
		);

		AsientoDisponible resultado = vueloTest.getCantidadAsientoDisponible();

		assertEquals(
			resultado.getDisponibilidad(),
			cantidadAsientoDisponible.getDisponibilidad()
		);
	}

	@Test
	void ExisteDisponibilidad() throws Exception {
		UUID vueloIdTest = UUID.randomUUID();
		NumeroVuelo numeroVueloTest = new NumeroVuelo("10");
		Destino destinoTest = new Destino("Santa");
		Origen origenTest = new Origen("santa");
		AsientoDisponible cantidadAsientoDisponible = new AsientoDisponible(10);
		Vuelo vueloTest = new Vuelo(
			vueloIdTest,
			numeroVueloTest,
			origenTest,
			destinoTest,
			cantidadAsientoDisponible
		);

		boolean resultado = vueloTest.ValidarDisponibilidad(5);

		assertTrue(resultado);
	}

	@Test
	void NoExisteDisponibilidad() throws Exception {
		UUID vueloIdTest = UUID.randomUUID();
		NumeroVuelo numeroVueloTest = new NumeroVuelo("10");
		Destino destinoTest = new Destino("Santa");
		Origen origenTest = new Origen("santa");
		AsientoDisponible cantidadAsientoDisponible = new AsientoDisponible(10);
		Vuelo vueloTest = new Vuelo(
			vueloIdTest,
			numeroVueloTest,
			origenTest,
			destinoTest,
			cantidadAsientoDisponible
		);

		boolean resultado = vueloTest.ValidarDisponibilidad(11);

		assertFalse(resultado);
	}

	@Test
	void ReducirDisponibilidad() throws Exception {
		UUID vueloIdTest = UUID.randomUUID();
		NumeroVuelo numeroVueloTest = new NumeroVuelo("10");
		Destino destinoTest = new Destino("Santa");
		AsientoDisponible cantidadAsientoDisponible = new AsientoDisponible(10);
		int reduccionTest = 5;
		Origen origenTest = new Origen("santa");
		Vuelo vueloTest = new Vuelo(
			vueloIdTest,
			numeroVueloTest,
			origenTest,
			destinoTest,
			cantidadAsientoDisponible
		);

		boolean resultado = vueloTest.reducirDisponibilidad(reduccionTest);

		assertTrue(resultado);
		assertEquals(
			cantidadAsientoDisponible.getDisponibilidad() - reduccionTest,
			vueloTest.getCantidadAsientoDisponible().getDisponibilidad()
		);
	}

	@Test
	void ErrorAlReducirDisponibilidadPorFaltaDeDisponibilidad()
		throws Exception {
		UUID vueloIdTest = UUID.randomUUID();
		NumeroVuelo numeroVueloTest = new NumeroVuelo("10");
		Destino destinoTest = new Destino("Santa");
		AsientoDisponible cantidadAsientoDisponible = new AsientoDisponible(10);
		int reduccionTest = 15;
		Origen origenTest = new Origen("santa");
		Vuelo vueloTest = new Vuelo(
			vueloIdTest,
			numeroVueloTest,
			origenTest,
			destinoTest,
			cantidadAsientoDisponible
		);

		boolean resultado = vueloTest.reducirDisponibilidad(reduccionTest);

		assertFalse(resultado);
		assertEquals(
			cantidadAsientoDisponible.getDisponibilidad(),
			vueloTest.getCantidadAsientoDisponible().getDisponibilidad()
		);
	}

	@Test
	void probarConstructor() throws Exception {
		NumeroVuelo numeroVueloTest = new NumeroVuelo("10");
		Destino destinoTest = new Destino("Santa");
		AsientoDisponible cantidadAsientoDisponible = new AsientoDisponible(10);
		Origen origenTest = new Origen("santa");
		Vuelo vuelo = new Vuelo(
			numeroVueloTest,
			origenTest,
			destinoTest,
			cantidadAsientoDisponible
		);

		assertEquals(numeroVueloTest, vuelo.getNroVuelo());
		assertEquals(destinoTest, vuelo.getDestino());
		assertEquals(origenTest, vuelo.getOrigen());
	}
}
