package com.diplo.msreserva.valueobjects;

import com.diplo.sharedkernel.core.ValueObject;
import java.time.LocalDateTime;

public final class HoraReserva extends ValueObject {

	private final LocalDateTime Hora;

	public HoraReserva(LocalDateTime hora) {
		super();
		Hora = hora;
	}

	public LocalDateTime getHora() {
		return Hora;
	}
}
