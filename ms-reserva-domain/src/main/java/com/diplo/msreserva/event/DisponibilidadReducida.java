package com.diplo.msreserva.event;

import com.diplo.sharedkernel.event.DomainEvent;
import java.time.LocalDateTime;
import java.util.UUID;

public final class DisponibilidadReducida extends DomainEvent {

	private static final long serialVersionUID = 1L;
	private UUID VueloId;
	private int CantidadAsientoDisponible;

	public DisponibilidadReducida(UUID vueloId, int cantidadAsientoDisponible) {
		super("DisponibilidadReducida", LocalDateTime.now());
		this.VueloId = vueloId;
		this.CantidadAsientoDisponible = cantidadAsientoDisponible;
	}

	public UUID getVueloId() {
		return VueloId;
	}

	public int getCantidadAsientoDisponible() {
		return CantidadAsientoDisponible;
	}
}
