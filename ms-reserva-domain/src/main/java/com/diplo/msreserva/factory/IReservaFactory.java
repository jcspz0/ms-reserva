package com.diplo.msreserva.factory;

import com.diplo.msreserva.model.reserva.Reserva;

public interface IReservaFactory {
	Reserva Create(
		String reservaId,
		String nroReserva,
		String nroPasajer,
		String nroVuelo,
		double monto,
		int cantidadPasajero
	) throws Exception;
}
