package com.diplo.application.msreserva.service.reserva;

import java.util.concurrent.Future;

public interface IReservaService {

	Future<String> GenerarNroReservaAsync();
	
}
