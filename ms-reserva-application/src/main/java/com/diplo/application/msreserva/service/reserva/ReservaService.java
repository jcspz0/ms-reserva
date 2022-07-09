package com.diplo.application.msreserva.service.reserva;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class ReservaService implements IReservaService {

	@Override
	public Future<String> GenerarNroReservaAsync() {
		return CompletableFuture.supplyAsync(()-> {return UUID.randomUUID().toString();});
	}
	
	

	

}
