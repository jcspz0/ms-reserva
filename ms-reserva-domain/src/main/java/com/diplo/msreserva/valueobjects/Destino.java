package com.diplo.msreserva.valueobjects;

import com.diplo.sharedkernel.core.ValueObject;

public final class Destino extends ValueObject {

	private final String NombreDestino;

	public String getNombreDestino() {
		return NombreDestino;
	}

	public Destino(String nombreDestino) throws Exception {
		super();
		if (nombreDestino == null || nombreDestino.isEmpty()) {
			throw new Exception(
				"El nombre de destino no puede ser vacio o nulo"
			);
		}
		NombreDestino = nombreDestino;
	}
}
