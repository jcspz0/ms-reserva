package com.diplo.application.msreserva.dto.vuelo;

import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import java.time.LocalDateTime;
import java.util.UUID;

public class VueloDTO {

	private String VueloId;
	private String NroVuelo;
	private int CantidadAsientoDisponible;
	private String Destino;
	private String Origen;

	public VueloDTO() {
		super();
	}

	public VueloDTO(
		String id,
		String nroVuelo,
		int cantidadAsientoDisponible,
		String origen,
		String destino
	) {
		super();
		this.VueloId = id;
		NroVuelo = nroVuelo;
		CantidadAsientoDisponible = cantidadAsientoDisponible;
		Destino = destino;
		Origen = origen;
	}

	public VueloDTO(
		String nroVuelo,
		int cantidadAsientoDisponible,
		String origen,
		String destino
	) {
		super();
		NroVuelo = nroVuelo;
		CantidadAsientoDisponible = cantidadAsientoDisponible;
		Destino = destino;
		Origen = origen;
	}

	public VueloDTO(Vuelo aux) {
		VueloId = aux.getId().toString();
		NroVuelo = aux.getNroVuelo().getNumero();
		CantidadAsientoDisponible =
			aux.getCantidadAsientoDisponible().getDisponibilidad();
		Destino = aux.getDestino().getNombreDestino();
		Origen = aux.getOrigen().getNombreOrigen();
	}

	public String getVueloId() {
		return VueloId;
	}

	public void setVueloId(String vueloId) {
		VueloId = vueloId;
	}

	public String getNroVuelo() {
		return NroVuelo;
	}

	public void setNroVuelo(String nroVuelo) {
		NroVuelo = nroVuelo;
	}

	public int getCantidadAsientoDisponible() {
		return CantidadAsientoDisponible;
	}

	public void setCantidadAsientoDisponible(int cantidadAsientoDisponible) {
		CantidadAsientoDisponible = cantidadAsientoDisponible;
	}

	public String getDestino() {
		return Destino;
	}

	public void setDestino(String destino) {
		Destino = destino;
	}

	public String getOrigen() {
		return Origen;
	}

	public void setOrigen(String origen) {
		Origen = origen;
	}
}
