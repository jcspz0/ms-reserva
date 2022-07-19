package com.diplo.infraestructure.msreserva.entityframework.entity;

import com.diplo.msreserva.model.reserva.Reserva;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

@Entity
@Table(name = "reserva")
public class ReservaEntity {

	@Id
	private String ReservaId;

	private String nroReserva;
	private String PasajeroId;
	private String VueloId;
	private double Precio;
	private int CantidadPasajero;
	private LocalDateTime Hora;
	private String Estado;

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

	public LocalDateTime getHora() {
		return Hora;
	}

	public void setHora(LocalDateTime hora) {
		Hora = hora;
	}

	public ReservaEntity(Reserva reserva) {
		super();
		ReservaId = reserva.getId().toString();
		nroReserva = reserva.getNroReserva().getValue();
		PasajeroId = reserva.getPasajeroId().toString();
		VueloId = reserva.getVueloId().toString();
		Precio = reserva.getPrecio().getMonto();
		CantidadPasajero = reserva.getCantidadPasajero().getCantidad();
		Hora = reserva.getHora().getHora();
		Estado = reserva.getEstado();
	}

	public String getNroReserva() {
		return nroReserva;
	}

	public void setNroReserva(String nroReserva) {
		this.nroReserva = nroReserva;
	}

	public ReservaEntity() {}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}
}
