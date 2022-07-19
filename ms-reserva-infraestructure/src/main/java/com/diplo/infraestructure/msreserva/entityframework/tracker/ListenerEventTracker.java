package com.diplo.infraestructure.msreserva.entityframework.tracker;

import com.diplo.msreserva.model.reserva.Reserva;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerEventTracker {

	ArrayList<MessageEvent> lista = new ArrayList<MessageEvent>();

	@EventListener
	public void onApplicationEvent(MessageEvent event) {
		//Reserva aux = (Reserva) event.getMessage();
		//System.out.println("Se recibió el evento " + aux.getId() + " con elevento "+event.getMessage());
		lista.add(event);
		System.out.println("Cantidad en lista " + lista.size());
	}

	public ArrayList<MessageEvent> getTrackersCargados() {
		return lista;
	}

	public void clearTracker() {
		lista = new ArrayList<MessageEvent>();
	}
}
