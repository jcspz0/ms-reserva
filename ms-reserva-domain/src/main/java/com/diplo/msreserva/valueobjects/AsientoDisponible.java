package com.diplo.msreserva.valueobjects;

import com.diplo.sharedkernel.core.ValueObject;

public final class AsientoDisponible extends ValueObject {

	private final int Disponibilidad;

	public int getDisponibilidad() {
		return Disponibilidad;
	}

	public AsientoDisponible(int disponibilidad) throws Exception {
		super();
		if (disponibilidad < 0) {
			throw new Exception("La disponibilidad no puede ser negativa");
		}
		Disponibilidad = disponibilidad;
	}
}
