package com.diplo.application.msreserva.usecase.command.vuelo.reducirdisponibilidad;

import com.diplo.sharedkernel.mediator.request.IRequest;
import java.util.UUID;

public class ReducirDisponibilidadCommand implements IRequest<UUID> {

	public ReducirDisponibilidadCommand() {}

	private String vueloId;
	private int cantidadPasajeros;

	public ReducirDisponibilidadCommand(String vueloId, int cantidadPasajeros) {
		super();
		this.vueloId = vueloId;
		this.cantidadPasajeros = cantidadPasajeros;
	}

	public String getVueloId() {
		return vueloId;
	}

	public void setVueloId(String vueloId) {
		this.vueloId = vueloId;
	}

	public int getCantidadPasajeros() {
		return cantidadPasajeros;
	}

	public void setCantidadPasajeros(int cantidadPasajeros) {
		this.cantidadPasajeros = cantidadPasajeros;
	}
}
