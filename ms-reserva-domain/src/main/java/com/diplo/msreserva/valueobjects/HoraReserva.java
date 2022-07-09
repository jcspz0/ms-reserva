package com.diplo.msreserva.valueobjects;

import java.time.LocalDateTime;

import com.diplo.sharekernel.core.ValueObject;

public final class HoraReserva extends ValueObject{
	
	private final LocalDateTime Hora;

	public HoraReserva(LocalDateTime hora) {
		super();
		Hora = hora;
	}

	public LocalDateTime getHora() {
		return Hora;
	}
	
	

}
