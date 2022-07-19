package com.diplo.application.msreserva.usecase.command.reserva.crearvuelo;

import com.diplo.application.msreserva.mediator.request.IRequest;
import java.util.UUID;

public class CrearVueloCommand implements IRequest<UUID> {

	public CrearVueloCommand() {}

	private String vueloId;
	private int nroVuelo;
	private int cantidadAsientoDisponible;
	private String destino;

	public CrearVueloCommand(
		String vueloId,
		int nroVuelo,
		int cantidadAsientoDisponible,
		String destino
	) {
		super();
		this.vueloId = vueloId;
		this.nroVuelo = nroVuelo;
		this.cantidadAsientoDisponible = cantidadAsientoDisponible;
		this.destino = destino;
	}

	public CrearVueloCommand(
		int nroVuelo,
		int cantidadAsientoDisponible,
		String destino
	) {
		super();
		this.nroVuelo = nroVuelo;
		this.cantidadAsientoDisponible = cantidadAsientoDisponible;
		this.destino = destino;
	}

	public int getNroVuelo() {
		return nroVuelo;
	}

	public void setNroVuelo(int nroVuelo) {
		this.nroVuelo = nroVuelo;
	}

	public int getCantidadAsientoDisponible() {
		return cantidadAsientoDisponible;
	}

	public void setCantidadAsientoDisponible(int cantidadAsientoDisponible) {
		this.cantidadAsientoDisponible = cantidadAsientoDisponible;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getVueloId() {
		return vueloId;
	}

	public void setVueloId(String vueloId) {
		this.vueloId = vueloId;
	}
}
