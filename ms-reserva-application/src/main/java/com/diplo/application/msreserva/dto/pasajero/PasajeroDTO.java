package com.diplo.application.msreserva.dto.pasajero;

import java.time.LocalDateTime;
import java.util.UUID;

import com.diplo.msreserva.model.pasajero.Pasajero;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;

public class PasajeroDTO {

	private String PasajeroId;
	private int NroDoc;
	private int TipoDoc;
	private String Nombre;
	private String PrimerApellido;
	private String SegundoApellido;

	public PasajeroDTO() {
		super();
	}

	public PasajeroDTO(int nroDoc, int tipoDoc, String nombre, String primerApellido, String segundoApellido) {
		super();
		NroDoc = nroDoc;
		TipoDoc = tipoDoc;
		Nombre = nombre;
		PrimerApellido = primerApellido;
		SegundoApellido = segundoApellido;
	}
	
	public PasajeroDTO(String pasajeroId, int nroDoc, int tipoDoc, String nombre, String primerApellido, String segundoApellido) {
		super();
		PasajeroId = pasajeroId;
		NroDoc = nroDoc;
		TipoDoc = tipoDoc;
		Nombre = nombre;
		PrimerApellido = primerApellido;
		SegundoApellido = segundoApellido;
	}

	public PasajeroDTO(Pasajero aux) {
		PasajeroId = aux.getId().toString();
		NroDoc = aux.getDocumento().getNroDoc();
		TipoDoc = aux.getDocumento().getTipoDoc();
		Nombre = aux.getNombre().getNombre();
		PrimerApellido = aux.getNombre().getPrimerApellido();
		SegundoApellido = aux.getNombre().getSegundoApellido();
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

	public String getPasajeroId() {
		return PasajeroId;
	}

	public void setPasajeroId(String pasajeroId) {
		PasajeroId = pasajeroId;
	}
	

}
