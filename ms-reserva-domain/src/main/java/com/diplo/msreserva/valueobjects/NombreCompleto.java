package com.diplo.msreserva.valueobjects;

import com.diplo.sharedkernel.core.ValueObject;

public final class NombreCompleto extends ValueObject {

	private final String Nombre;
	private final String PrimerApellido;
	private final String SegundoApellido;

	public NombreCompleto(
		String nombre,
		String primerApellido,
		String segundoApellido
	) throws Exception {
		super();
		if (
			nombre == null ||
			nombre.isEmpty() ||
			primerApellido == null ||
			primerApellido.isEmpty()
		) {
			throw new Exception(
				"Se debe de ingresar obligatoriamente el nombre y el primer apellido"
			);
		}
		Nombre = nombre;
		PrimerApellido = primerApellido;
		if (segundoApellido == null || segundoApellido.isEmpty()) {
			SegundoApellido = "";
		} else {
			SegundoApellido = segundoApellido;
		}
	}

	public NombreCompleto(String nombre, String primerApellido)
		throws Exception {
		super();
		if (
			nombre == null ||
			nombre.isEmpty() ||
			primerApellido == null ||
			primerApellido.isEmpty()
		) {
			throw new Exception(
				"Se debe de ingresar obligatoriamente el nombre y el primer apellido"
			);
		}
		Nombre = nombre;
		PrimerApellido = primerApellido;
		SegundoApellido = " ";
	}

	public String getNombreCompleto() {
		return Nombre + " " + PrimerApellido + " " + SegundoApellido;
	}

	public String getNombre() {
		return Nombre;
	}

	public String getPrimerApellido() {
		return PrimerApellido;
	}

	public String getSegundoApellido() {
		return SegundoApellido;
	}
}
