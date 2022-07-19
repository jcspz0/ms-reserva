package com.diplo.application.msreserva.usecase.command.reserva.crearpasajero;

import com.diplo.application.msreserva.mediator.request.IRequest;
import java.util.UUID;

public class CrearPasajeroCommand implements IRequest<UUID> {

	public CrearPasajeroCommand() {}

	private int NroDoc;
	private int TipoDoc;
	private String Nombre;
	private String PrimerApellido;
	private String SegundoApellido;

	public CrearPasajeroCommand(
		int nroDoc,
		int tipoDoc,
		String nombre,
		String primerApellido,
		String segundoApellido
	) {
		super();
		NroDoc = nroDoc;
		TipoDoc = tipoDoc;
		Nombre = nombre;
		PrimerApellido = primerApellido;
		SegundoApellido = segundoApellido;
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
