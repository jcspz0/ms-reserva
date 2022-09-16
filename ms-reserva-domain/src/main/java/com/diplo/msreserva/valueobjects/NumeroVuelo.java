package com.diplo.msreserva.valueobjects;

import com.diplo.sharedkernel.core.ValueObject;

public final class NumeroVuelo extends ValueObject {

	private final String Numero;

	public String getNumero() {
		return Numero;
	}

	public NumeroVuelo(String numero) {
		super();
		Numero = numero;
	}
}
