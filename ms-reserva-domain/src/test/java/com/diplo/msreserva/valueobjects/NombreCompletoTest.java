package com.diplo.msreserva.valueobjects;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NombreCompletoTest {

	@Test
	void ExcepcionPorNombreCompletoVacioONulo() {
		Exception exception = assertThrows(Exception.class, () -> {
			new NombreCompleto(null,null);
	    });

	    String expectedMessage = "Se debe de ingresar obligatoriamente el nombre y el primer apellido";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	    
	}
	
	@Test
	void ExcepcionPorNombreCompletoVacioONuloConSegundoApellido() {
		Exception exception = assertThrows(Exception.class, () -> {
			new NombreCompleto("","","");
	    });

	    String expectedMessage = "Se debe de ingresar obligatoriamente el nombre y el primer apellido";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	    

	}
	
	@Test
	void CrearNombreCompleto() throws Exception {
		String nombre = "nombre";
		String apellido = "apellido";
		String segundo = "segundo";
		String mostrarNombreCompleto = nombre + " " + apellido + " " + segundo;
		
		NombreCompleto nombreCompleto = new NombreCompleto(nombre, apellido);
	
		assertEquals(nombre, nombreCompleto.getNombre());
		assertEquals(apellido, nombreCompleto.getPrimerApellido());
		assertNotNull(nombreCompleto.getSegundoApellido());
		
		nombreCompleto = new NombreCompleto(nombre, apellido,null);
		
		assertEquals(nombre, nombreCompleto.getNombre());
		assertEquals(apellido, nombreCompleto.getPrimerApellido());
	
		nombreCompleto = new NombreCompleto(nombre, apellido, segundo);
		assertEquals(nombre, nombreCompleto.getNombre());
		assertEquals(apellido, nombreCompleto.getPrimerApellido());
		assertEquals(segundo, nombreCompleto.getSegundoApellido());
		assertEquals(mostrarNombreCompleto, nombreCompleto.getNombreCompleto());
		
	}

}
