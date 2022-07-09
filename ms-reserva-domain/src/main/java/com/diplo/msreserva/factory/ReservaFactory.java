package com.diplo.msreserva.factory;

import java.util.UUID;

import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.valueobjects.CantidadPasajero;
import com.diplo.msreserva.valueobjects.Monto;
import com.diplo.msreserva.valueobjects.NumeroReserva;

public class ReservaFactory implements IReservaFactory {

	@Override
	public Reserva Create(String reservaId,String nroReserva ,String nroPasajer, String nroVuelo, double monto, int cantidadPasajero) throws Exception {
			return new Reserva(UUID.fromString(reservaId),new NumeroReserva(nroReserva) ,UUID.fromString(nroPasajer), UUID.fromString(nroVuelo), new Monto(monto), new CantidadPasajero(cantidadPasajero));
	}

}
