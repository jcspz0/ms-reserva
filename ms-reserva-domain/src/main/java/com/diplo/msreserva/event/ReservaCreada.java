package com.diplo.msreserva.event;

import com.diplo.msreserva.valueobjects.HoraReserva;
import com.diplo.msreserva.valueobjects.NumeroReserva;
import com.diplo.sharekernel.core.DomainEvent;
import java.time.LocalDateTime;
import java.util.UUID;

public final class ReservaCreada extends DomainEvent {

	private final NumeroReserva NroReserva;
	private final UUID ReservaId;
	private final UUID VueloId;
	private final UUID pasajero;
	private final HoraReserva hora;

	public ReservaCreada(
		UUID reservaId,
		NumeroReserva nroReserva,
		UUID vueloId,
		UUID pasajeroId,
		HoraReserva hora
	) {
		super(LocalDateTime.now());
		NroReserva = nroReserva;
		ReservaId = reservaId;
		VueloId = vueloId;
		this.pasajero = pasajeroId;
		this.hora = hora;
	}
}
