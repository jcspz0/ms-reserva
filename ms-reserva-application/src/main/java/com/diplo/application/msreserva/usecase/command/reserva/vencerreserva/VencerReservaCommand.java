package com.diplo.application.msreserva.usecase.command.reserva.vencerreserva;

import com.diplo.sharedkernel.mediator.request.IRequest;
import java.util.UUID;

public class VencerReservaCommand implements IRequest<UUID> {

	private String reservaId;

	public VencerReservaCommand() {}

	public VencerReservaCommand(String reservaId) {
		this.reservaId = reservaId;
	}

	public String getReservaId() {
		return reservaId;
	}

	public void setReservaId(String reservaId) {
		this.reservaId = reservaId;
	}
}
