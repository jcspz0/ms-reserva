package com.diplo.msreserva.event;

import com.diplo.msreserva.valueobjects.HoraReserva;
import com.diplo.msreserva.valueobjects.NumeroReserva;
import com.diplo.sharedkernel.event.DomainEvent;
import java.time.LocalDateTime;
import java.util.UUID;

public final class ReservaCreada extends DomainEvent {

	private static final long serialVersionUID = 1L;
	private final NumeroReserva NroReserva;
	private final UUID ReservaId;
	private final UUID VueloId;
	private final UUID pasajero;
	private final HoraReserva hora;
	private final int cantidadPasajeros;

	public ReservaCreada(
		UUID reservaId,
		NumeroReserva nroReserva,
		UUID vueloId,
		int cantidadPasajeros,
		UUID pasajeroId,
		HoraReserva hora
	) {
		super("ReservaCreada", LocalDateTime.now());
		NroReserva = nroReserva;
		ReservaId = reservaId;
		VueloId = vueloId;
		this.pasajero = pasajeroId;
		this.hora = hora;
		this.cantidadPasajeros = cantidadPasajeros;
	}

	public NumeroReserva getNroReserva() {
		return NroReserva;
	}

	public UUID getReservaId() {
		return ReservaId;
	}

	public UUID getVueloId() {
		return VueloId;
	}

	public UUID getPasajero() {
		return pasajero;
	}

	public HoraReserva getHora() {
		return hora;
	}

	public int getCantidadPasajeros() {
		return cantidadPasajeros;
	}
}
