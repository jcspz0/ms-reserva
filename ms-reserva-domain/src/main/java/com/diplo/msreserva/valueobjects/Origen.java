package com.diplo.msreserva.valueobjects;

import com.diplo.sharedkernel.core.ValueObject;

public final class Origen extends ValueObject {

	private final String NombreOrigen;

	public String getNombreOrigen() {
		return NombreOrigen;
	}

	public Origen(String nombreOrigen) throws Exception {
		super();
		if (nombreOrigen == null || nombreOrigen.isEmpty()) {
			throw new Exception(
				"El nombre de origen no puede ser vacio o nulo"
			);
		}
		NombreOrigen = nombreOrigen;
	}
}
