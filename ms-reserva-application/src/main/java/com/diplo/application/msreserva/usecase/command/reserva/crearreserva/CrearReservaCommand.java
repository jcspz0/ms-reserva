package com.diplo.application.msreserva.usecase.command.reserva.crearreserva;

import java.util.UUID;

import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.mediator.request.IRequest;
import com.diplo.msreserva.model.vuelo.Vuelo;

public class CrearReservaCommand implements IRequest<UUID> {

	public CrearReservaCommand() {
	}
	
	private String reservaId;
	private String nroReserva;
	private String nroPasajero; 
	private double monto;
	private int cantidadPasajero;
	private VueloDTO vueloDTO;
	
	public CrearReservaCommand(String nroReserva, String nroPasajero, VueloDTO vueloDTO, double monto,
			int cantidadPasajero) {
		super();
		this.nroReserva = nroReserva;
		this.nroPasajero = nroPasajero;
		this.vueloDTO = vueloDTO;
		this.monto = monto;
		this.cantidadPasajero = cantidadPasajero;
	}
	
	public CrearReservaCommand(String reservaId, String nroReserva, String nroPasajero, VueloDTO vueloDTO, double monto,
			int cantidadPasajero) {
		super();
		this.reservaId = reservaId;
		this.nroReserva = nroReserva;
		this.nroPasajero = nroPasajero;
		this.vueloDTO = vueloDTO;
		this.monto = monto;
		this.cantidadPasajero = cantidadPasajero;
	}
	
	public String getNroReserva() {
		return nroReserva;
	}
	public void setNroReserva(String nroReserva) {
		this.nroReserva = nroReserva;
	}
	public String getNroPasajero() {
		return nroPasajero;
	}
	public void setNroPasajero(String nroPasajero) {
		this.nroPasajero = nroPasajero;
	}
	public VueloDTO getVuelo() {
		return vueloDTO;
	}
	public void setVuelo(VueloDTO nroVuelo) {
		this.vueloDTO = nroVuelo;
	}
	public double getMonto() {
		return monto;
	}
	public void setMonto(double monto) {
		this.monto = monto;
	}
	public int getCantidadPasajero() {
		return cantidadPasajero;
	}
	public void setCantidadPasajero(int cantidadPasajero) {
		this.cantidadPasajero = cantidadPasajero;
	}
	
	
	
	

}
