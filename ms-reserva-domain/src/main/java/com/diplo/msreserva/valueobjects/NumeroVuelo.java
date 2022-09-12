package com.diplo.msreserva.valueobjects;

import com.diplo.sharedkernel.core.ValueObject;

public final class NumeroVuelo extends ValueObject {

	private final int Numero;

	public int getNumero() {
		return Numero;
	}

	public NumeroVuelo(int numero) {
		super();
		Numero = numero;
	}
}
