package com.diplo.infraestructure.msreserva.entityframework.event.reserva;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.h2.engine.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.diplo.infraestructure.msreserva.entityframework.dbrepository.DbVueloRepository;
import com.diplo.infraestructure.msreserva.entityframework.entity.VueloEntity;
import com.diplo.infraestructure.msreserva.entityframework.tracker.DomainMessage;
import com.diplo.infraestructure.msreserva.entityframework.tracker.MessageEvent;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;

@Component
public class VueloListenerEvent {
	
	@Autowired
	DbVueloRepository vueloEntityRepository;

	@EventListener
	public void onApplicationEvent(DomainMessage event) {
		if(event.getMessage() instanceof Reserva) {
			try {
				System.out.println("Se ha creado un evento de dominio de reserva");
				Reserva reserva = (Reserva)event.getMessage();
				Future<Vuelo> future = vueloEntityRepository.FindByIdAsync(reserva.getVueloId());
				Vuelo vuelo = future.get();
				if(vuelo.reducirDisponibilidad(reserva.getCantidadPasajero().getCantidad())) {
					vueloEntityRepository.UpdateAsync(vuelo);
					System.out.println("Se redujo la capacidad,");
				}
			} catch (Exception e) {
				System.out.println("VueloListenerEvent->Ocurrio un error al procesar el evento"+e);
			}
		}
	}
	
}
