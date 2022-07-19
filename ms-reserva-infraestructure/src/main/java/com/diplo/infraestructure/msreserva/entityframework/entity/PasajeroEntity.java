package com.diplo.infraestructure.msreserva.entityframework.entity;

import com.diplo.msreserva.model.pasajero.Pasajero;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import java.time.LocalDateTime;
import javax.persistence.Column;
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
@Table(name = "pasajero")
public class PasajeroEntity {

	@Id
	private String PasajeroId;

	@Column(name = "nrodoc")
	private int NroDoc;

	@Column(name = "tipodoc")
	private int TipoDoc;

	private String Nombre;
	private String PrimerApellido;
	private String SegundoApellido;

	public PasajeroEntity() {}

	public PasajeroEntity(Pasajero aux) {
		super();
		PasajeroId = aux.getId().toString();
		NroDoc = aux.getDocumento().getNroDoc();
		TipoDoc = aux.getDocumento().getTipoDoc();
		Nombre = aux.getNombre().getNombre();
		PrimerApellido = aux.getNombre().getPrimerApellido();
		SegundoApellido = aux.getNombre().getSegundoApellido();
	}

	public String getPasajeroId() {
		return PasajeroId;
	}

	public void setPasajeroId(String pasajeroId) {
		PasajeroId = pasajeroId;
	}

	public int getNroDoc() {
		return NroDoc;
	}

	public void setNroDoc(int nroDoc) {
		NroDoc = nroDoc;
	}

	public int getTipoDoc() {
		return TipoDoc;
	}

	public void setTipoDoc(int tipoDoc) {
		TipoDoc = tipoDoc;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getPrimerApellido() {
		return PrimerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		PrimerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return SegundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		SegundoApellido = segundoApellido;
	}
}
