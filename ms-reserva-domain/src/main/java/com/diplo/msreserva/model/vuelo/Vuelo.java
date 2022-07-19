package com.diplo.msreserva.model.vuelo;

import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.NumeroVuelo;
import com.diplo.sharekernel.core.AggregateRoot;
import java.util.UUID;

public class Vuelo extends AggregateRoot<UUID> {

	private NumeroVuelo NroVuelo;
	private AsientoDisponible CantidadAsientoDisponible;
	private Destino _Destino;

	public Vuelo(
		NumeroVuelo numeroVuelo,
		Destino _Destino,
		AsientoDisponible cantidadAsientoDisponible
	) {
		super();
		Id = UUID.randomUUID();
		this.CantidadAsientoDisponible = cantidadAsientoDisponible;
		this._Destino = _Destino;
		this.NroVuelo = numeroVuelo;
	}

	public Vuelo(
		UUID vueloId,
		NumeroVuelo numeroVuelo,
		Destino _Destino,
		AsientoDisponible cantidadAsientoDisponible
	) {
		super();
		Id = vueloId;
		this.CantidadAsientoDisponible = cantidadAsientoDisponible;
		this._Destino = _Destino;
		this.NroVuelo = numeroVuelo;
	}

	public AsientoDisponible getCantidadAsientoDisponible() {
		return CantidadAsientoDisponible;
	}

	public NumeroVuelo getNroVuelo() {
		return NroVuelo;
	}

	public Destino getDestino() {
		return _Destino;
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
