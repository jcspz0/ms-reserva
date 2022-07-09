package com.diplo.msreserva.model.vuelo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.NumeroVuelo;

class VueloTest {
	
	@Test
	void CantidadDeAsientoDisponible() throws Exception {
		UUID vueloIdTest = UUID.randomUUID();
		NumeroVuelo numeroVueloTest = new NumeroVuelo(10);
		Destino destinoTest = new Destino("Santa");
		AsientoDisponible cantidadAsientoDisponible = new AsientoDisponible(10);
		Vuelo vueloTest = new Vuelo(vueloIdTest, numeroVueloTest, destinoTest, cantidadAsientoDisponible);
	
		AsientoDisponible resultado = vueloTest.getCantidadAsientoDisponible();
	
		assertEquals(resultado.getDisponibilidad(), cantidadAsientoDisponible.getDisponibilidad());
	}

	@Test
	void ExisteDisponibilidad() throws Exception {
		UUID vueloIdTest = UUID.randomUUID();
		NumeroVuelo numeroVueloTest = new NumeroVuelo(10);
		Destino destinoTest = new Destino("Santa");
		AsientoDisponible cantidadAsientoDisponible = new AsientoDisponible(10);
		Vuelo vueloTest = new Vuelo(vueloIdTest, numeroVueloTest, destinoTest, cantidadAsientoDisponible);
	
		boolean resultado = vueloTest.ValidarDisponibilidad(5);
		
		assertTrue(resultado);
	}
	
	@Test
	void NoExisteDisponibilidad() throws Exception {
		UUID vueloIdTest = UUID.randomUUID();
		NumeroVuelo numeroVueloTest = new NumeroVuelo(10);
		Destino destinoTest = new Destino("Santa");
		AsientoDisponible cantidadAsientoDisponible = new AsientoDisponible(10);
		Vuelo vueloTest = new Vuelo(vueloIdTest, numeroVueloTest, destinoTest, cantidadAsientoDisponible);
	
		boolean resultado = vueloTest.ValidarDisponibilidad(11);
		
		assertFalse(resultado);
	}

	@Test
	void ReducirDisponibilidad() throws Exception {
		UUID vueloIdTest = UUID.randomUUID();
		NumeroVuelo numeroVueloTest = new NumeroVuelo(10);
		Destino destinoTest = new Destino("Santa");
		AsientoDisponible cantidadAsientoDisponible = new AsientoDisponible(10);
		int reduccionTest = 5;
		Vuelo vueloTest = new Vuelo(vueloIdTest, numeroVueloTest, destinoTest, cantidadAsientoDisponible);
	
		boolean resultado = vueloTest.reducirDisponibilidad(reduccionTest);
		
		assertTrue(resultado);
		assertEquals(cantidadAsientoDisponible.getDisponibilidad()-reduccionTest, vueloTest.getCantidadAsientoDisponible().getDisponibilidad());
	}
	
	@Test
	void ErrorAlReducirDisponibilidadPorFaltaDeDisponibilidad() throws Exception {
		UUID vueloIdTest = UUID.randomUUID();
		NumeroVuelo numeroVueloTest = new NumeroVuelo(10);
		Destino destinoTest = new Destino("Santa");
		AsientoDisponible cantidadAsientoDisponible = new AsientoDisponible(10);
		int reduccionTest = 15;
		Vuelo vueloTest = new Vuelo(vueloIdTest, numeroVueloTest, destinoTest, cantidadAsientoDisponible);
	
		boolean resultado = vueloTest.reducirDisponibilidad(reduccionTest);
		
		assertFalse(resultado);
		assertEquals(cantidadAsientoDisponible.getDisponibilidad(), vueloTest.getCantidadAsientoDisponible().getDisponibilidad());
	}
	
	@Test
	void probarConstructor() throws Exception {
		NumeroVuelo numeroVueloTest = new NumeroVuelo(10);
		Destino destinoTest = new Destino("Santa");
		AsientoDisponible cantidadAsientoDisponible = new AsientoDisponible(10);
		Vuelo vuelo = new Vuelo(numeroVueloTest, destinoTest, cantidadAsientoDisponible);
		
		assertEquals(numeroVueloTest, vuelo.getNroVuelo());
		assertEquals(destinoTest, vuelo.getDestino());
		
	}

}
