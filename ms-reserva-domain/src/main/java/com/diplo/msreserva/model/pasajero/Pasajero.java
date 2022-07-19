package com.diplo.msreserva.model.pasajero;

import com.diplo.msreserva.valueobjects.DocumentoIdentidad;
import com.diplo.msreserva.valueobjects.NombreCompleto;
import com.diplo.sharekernel.core.AggregateRoot;
import java.util.UUID;

public class Pasajero extends AggregateRoot<UUID> {

	private NombreCompleto Nombre;
	private DocumentoIdentidad Documento;

	public Pasajero(NombreCompleto nombre, DocumentoIdentidad documento) {
		super();
		Id = UUID.randomUUID();
		Nombre = nombre;
		Documento = documento;
	}

	public Pasajero(
		UUID pasajeroID,
		NombreCompleto nombre,
		DocumentoIdentidad documento
	) {
		super();
		Id = pasajeroID;
		Nombre = nombre;
		Documento = documento;
	}

	public NombreCompleto getNombre() {
		return Nombre;
	}

	public DocumentoIdentidad getDocumento() {
		return Documento;
	}
}
