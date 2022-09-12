package com.diplo.application.msreserva.usecase.command.reserva.reservacompletadarollback;

import com.diplo.sharedkernel.mediator.request.IRequest;
import java.util.UUID;

public class ReservaCompletadaRollbackCommand implements IRequest<UUID> {

	private String reservaId;
	private String pagoId;

	public ReservaCompletadaRollbackCommand() {}

	public ReservaCompletadaRollbackCommand(String reservaId, String pagoId) {
		this.reservaId = reservaId;
		this.pagoId = pagoId;
	}

	public String getReservaId() {
		return reservaId;
	}

	public void setReservaId(String reservaId) {
		this.reservaId = reservaId;
	}

	public String getPagoId() {
		return pagoId;
	}

	public void setPagoId(String pagoId) {
		this.pagoId = pagoId;
	}
}
