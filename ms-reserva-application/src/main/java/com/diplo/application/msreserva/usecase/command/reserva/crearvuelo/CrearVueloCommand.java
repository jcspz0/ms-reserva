package com.diplo.application.msreserva.usecase.command.reserva.crearvuelo;

import com.diplo.sharedkernel.mediator.request.IRequest;
import java.util.UUID;

public class CrearVueloCommand implements IRequest<UUID> {

	public CrearVueloCommand() {}

	private String vueloId;
	private String nroVuelo;
	private int cantidadAsientoDisponible;
	private String destino;
	private String origen;

	public CrearVueloCommand(
		String vueloId,
		String nroVuelo,
		int cantidadAsientoDisponible,
		String origen,
		String destino
	) {
		super();
		this.vueloId = vueloId;
		this.nroVuelo = nroVuelo;
		this.cantidadAsientoDisponible = cantidadAsientoDisponible;
		this.destino = destino;
		this.origen = origen;
	}

	public CrearVueloCommand(
		String nroVuelo,
		int cantidadAsientoDisponible,
		String origen,
		String destino
	) {
		super();
		this.nroVuelo = nroVuelo;
		this.cantidadAsientoDisponible = cantidadAsientoDisponible;
		this.destino = destino;
		this.origen = origen;
	}

	public String getNroVuelo() {
		return nroVuelo;
	}

	public void setNroVuelo(String nroVuelo) {
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

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}
}
