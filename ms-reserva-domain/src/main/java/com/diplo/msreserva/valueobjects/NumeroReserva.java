package com.diplo.msreserva.valueobjects;

import com.diplo.sharedkernel.core.ValueObject;

public class NumeroReserva extends ValueObject {

	private final String value;

	public NumeroReserva(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
