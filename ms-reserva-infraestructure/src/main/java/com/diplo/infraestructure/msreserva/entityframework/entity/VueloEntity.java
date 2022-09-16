package com.diplo.infraestructure.msreserva.entityframework.entity;

import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
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
@Table(name = "vuelo")
public class VueloEntity {

	@Id
	private String VueloId;

	private String NroVuelo;
	private int CantidadAsientoDisponible;
	private String Origen;
	private String Destino;

	public VueloEntity() {}

	public VueloEntity(Vuelo aux) {
		super();
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
