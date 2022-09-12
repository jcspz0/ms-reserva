package com.diplo.application.msreserva.dto.reserva;

import com.diplo.msreserva.model.reserva.Reserva;
import java.time.LocalDateTime;
import java.util.UUID;

public class ReservaDTO {

	private String ReservaId;
	private String NroReserva;
	private String PasajeroId;
	private String VueloId;
	private double Precio;
	private int CantidadPasajero;
	private String Hora;
	private String Estado;

	public ReservaDTO() {
		super();
	}

	public ReservaDTO(
		String id,
		String nroReserva,
		String pasajeroId,
		String vueloId,
		double precio,
		int cantidadPasajero,
		String hora,
		String estado
	) {
		super();
		Hora = hora;
		ReservaId = id;
		NroReserva = nroReserva;
		PasajeroId = pasajeroId;
		VueloId = vueloId;
		Precio = precio;
		CantidadPasajero = cantidadPasajero;
		Estado = estado;
	}

	public ReservaDTO(
		String nroReserva,
		String pasajeroId,
		String vueloId,
		double precio,
		int cantidadPasajero,
		String hora,
		String estado
	) {
		super();
		Hora = hora;
		ReservaId = "";
		PasajeroId = pasajeroId;
		VueloId = vueloId;
		Precio = precio;
		CantidadPasajero = cantidadPasajero;
		Estado = estado;
		this.NroReserva = nroReserva;
	}

	public ReservaDTO(Reserva aux) {
		Hora = aux.getHora().getHora().toString();
		ReservaId = aux.getId().toString();
		NroReserva = aux.getNroReserva().getValue();
		PasajeroId = aux.getPasajeroId().toString();
		VueloId = aux.getVueloId().toString();
		Precio = aux.getPrecio().getMonto();
		CantidadPasajero = aux.getCantidadPasajero().getCantidad();
		Estado = aux.getEstado();
	}

	public String getReservaId() {
		return ReservaId;
	}

	public void setReservaId(String reservaId) {
		ReservaId = reservaId;
	}

	public String getPasajeroId() {
		return PasajeroId;
	}

	public void setPasajeroId(String pasajeroId) {
		PasajeroId = pasajeroId;
	}

	public String getVueloId() {
		return VueloId;
	}

	public void setVueloId(String vueloId) {
		VueloId = vueloId;
	}

	public double getPrecio() {
		return Precio;
	}

	public void setPrecio(double precio) {
		Precio = precio;
	}

	public int getCantidadPasajero() {
		return CantidadPasajero;
	}

	public void setCantidadPasajero(int cantidadPasajero) {
		CantidadPasajero = cantidadPasajero;
	}

	public String getNroReserva() {
		return NroReserva;
	}

	public void setNroReserva(String nroReserva) {
		NroReserva = nroReserva;
	}

	public String getHora() {
		return Hora;
	}

	public void setHora(String hora) {
		Hora = hora;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}
}
