package com.diplo.msreserva.model.vuelo;

import com.diplo.msreserva.event.DisponibilidadReducida;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.NumeroVuelo;
import com.diplo.msreserva.valueobjects.Origen;
import com.diplo.sharedkernel.core.AggregateRoot;
import java.util.UUID;

public class Vuelo extends AggregateRoot<UUID> {

	private NumeroVuelo NroVuelo;
	private AsientoDisponible CantidadAsientoDisponible;
	private Destino Destino;
	private Origen Origen;

	public Vuelo(
		NumeroVuelo numeroVuelo,
		Origen _origen,
		Destino _Destino,
		AsientoDisponible cantidadAsientoDisponible
	) {
		super();
		Id = UUID.randomUUID();
		this.CantidadAsientoDisponible = cantidadAsientoDisponible;
		this.Destino = _Destino;
		this.NroVuelo = numeroVuelo;
		this.Origen = _origen;
	}

	public Vuelo(
		UUID vueloId,
		NumeroVuelo numeroVuelo,
		Origen _origen,
		Destino _Destino,
		AsientoDisponible cantidadAsientoDisponible
	) {
		super();
		Id = vueloId;
		this.CantidadAsientoDisponible = cantidadAsientoDisponible;
		this.Destino = _Destino;
		this.NroVuelo = numeroVuelo;
		this.Origen = _origen;
	}

	public AsientoDisponible getCantidadAsientoDisponible() {
		return CantidadAsientoDisponible;
	}

	public NumeroVuelo getNroVuelo() {
		return NroVuelo;
	}

	public Destino getDestino() {
		return Destino;
	}

	public Origen getOrigen() {
		return Origen;
	}

	public boolean ValidarDisponibilidad(int cantidad) {
		if (CantidadAsientoDisponible.getDisponibilidad() - cantidad >= 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean reducirDisponibilidad(int cantidad) throws Exception {
		if (ValidarDisponibilidad(cantidad)) {
			System.out.println("reducirDiponibilidad en->" + cantidad);
			this.CantidadAsientoDisponible =
				new AsientoDisponible(
					this.CantidadAsientoDisponible.getDisponibilidad() -
					cantidad
				);
			AddDomainEvent(
				new DisponibilidadReducida(
					this.Id,
					this.CantidadAsientoDisponible.getDisponibilidad()
				)
			);
			return true;
		}
		System.out.println(
			"no se pudo reducir la disponibilidad en->" +
			cantidad +
			", la disponibilidad actual es:" +
			this.CantidadAsientoDisponible.getDisponibilidad()
		);
		return false;
	}
}
