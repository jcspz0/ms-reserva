package com.diplo.msreserva.factory;

import com.diplo.msreserva.event.ReservaCreada;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.valueobjects.CantidadPasajero;
import com.diplo.msreserva.valueobjects.HoraReserva;
import com.diplo.msreserva.valueobjects.Monto;
import com.diplo.msreserva.valueobjects.NumeroReserva;
import com.diplo.sharedkernel.event.DomainEvent;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import java.time.LocalDateTime;
import java.util.UUID;

public class ReservaFactory implements IReservaFactory {

	@Override
	public Reserva Create(
		String reservaId,
		String nroReserva,
		String nroPasajer,
		String nroVuelo,
		double monto,
		int cantidadPasajero
	) throws Exception {
		Reserva objReserva = new Reserva(
			UUID.fromString(reservaId),
			new NumeroReserva(nroReserva),
			UUID.fromString(nroPasajer),
			UUID.fromString(nroVuelo),
			new Monto(monto),
			new CantidadPasajero(cantidadPasajero)
		);

		DomainEvent domainEvent = new ReservaCreada(
			objReserva.getId(),
			objReserva.getNroReserva(),
			objReserva.getVueloId(),
			objReserva.getCantidadPasajero().getCantidad(),
			objReserva.getPasajeroId(),
			objReserva.getHora()
		);
		IntegrationEvent integrationEvent = new IntegrationEvent(
			new IntegrationReservaCreada(
				objReserva.getId().toString(),
				objReserva.getNroReserva().getValue(),
				objReserva.getVueloId().toString(),
				objReserva.getCantidadPasajero().getCantidad(),
				objReserva.getPasajeroId().toString(),
				objReserva.getHora().getHora().toString(),
				objReserva.getPrecio().getMonto()
			),
			objReserva.getHora().toString()
		);

		System.out.println(
			"ReservaFactory -> creando el evento de integracion " +
			integrationEvent
		);
		System.out.println(
			"ReservaFactory -> con el mensaje " + integrationEvent.getMessage()
		);
		objReserva.AddDomainEvent(domainEvent);
		objReserva.AddIntegrationEvent(integrationEvent);

		return objReserva;
	}
}
