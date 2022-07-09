package com.diplo.application.msreserva.dto.vuelo;

import java.time.LocalDateTime;
import java.util.UUID;

import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;


public class VueloDTO {
	
	private String VueloId;
	private int NroVuelo;
	private int CantidadAsientoDisponible;
	private String Destino;
	
	public VueloDTO() {
		super();
	}

	
	public VueloDTO(String id, int nroVuelo, int cantidadAsientoDisponible, String destino) {
		super();
		this.VueloId = id;
		NroVuelo = nroVuelo;
		CantidadAsientoDisponible = cantidadAsientoDisponible;
		Destino = destino;
	}
	
	public VueloDTO(int nroVuelo, int cantidadAsientoDisponible, String destino) {
		super();
		NroVuelo = nroVuelo;
		CantidadAsientoDisponible = cantidadAsientoDisponible;
		Destino = destino;
	}



	public VueloDTO(Vuelo aux) {
		VueloId = aux.getId().toString();
		NroVuelo = aux.getNroVuelo().getNumero();
		CantidadAsientoDisponible = aux.getCantidadAsientoDisponible().getDisponibilidad();
		Destino = aux.getDestino().getNombreDestino();
	}


	public String getVueloId() {
		return VueloId;
	}


	public void setVueloId(String vueloId) {
		VueloId = vueloId;
	}


	public int getNroVuelo() {
		return NroVuelo;
	}


	public void setNroVuelo(int nroVuelo) {
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

	
	

}
