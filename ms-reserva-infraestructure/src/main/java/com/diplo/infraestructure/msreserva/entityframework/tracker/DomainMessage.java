package com.diplo.infraestructure.msreserva.entityframework.tracker;

import org.springframework.context.ApplicationEvent;

import com.diplo.msreserva.model.reserva.Reserva;

public class DomainMessage extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	Object message;
	String action;
	
	public DomainMessage(Object source, String action) {
		super(source);
		this.message = source;
		this.action = action;
	}

	public Object getMessage() {
		return this.message;
	}
	
	public String getAction() {
		return this.action;
	}
	
}
