package com.diplo.application.msreserva.usecase.query.vuelo.getPasajeroByNroDocAndTipoDoc;

import java.util.UUID;

import com.diplo.application.msreserva.dto.pasajero.PasajeroDTO;
import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.mediator.request.IRequest;

public class GetPasajeroByNroDocAndTipoDocQuery implements IRequest<PasajeroDTO> {

	private int NroDoc;
	private int TipoDoc;

	public GetPasajeroByNroDocAndTipoDocQuery() {

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

	public GetPasajeroByNroDocAndTipoDocQuery(int nroDoc, int tipoDoc) {
		super();
		NroDoc = nroDoc;
		TipoDoc = tipoDoc;
	}

}
