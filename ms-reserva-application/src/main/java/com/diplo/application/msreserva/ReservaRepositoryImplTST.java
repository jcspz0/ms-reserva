package com.diplo.application.msreserva;

import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.repository.IReservaRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class ReservaRepositoryImplTST implements IReservaRepository {

	@Override
	public Future<Reserva> FindByIdAsync(UUID id) throws Exception {
		// TODO Auto-generated method stub
		Reserva reserva = new Reserva(
			id.toString(),
			UUID.randomUUID(),
			UUID.randomUUID(),
			100.0,
			10
		);

		System.out.println("ReservaRepository->FindByIdAsync");

		return CompletableFuture.completedFuture(reserva);
	}

	@Override
	public Future<Reserva> CreateAsync(Reserva obj) throws Exception {
		Reserva reserva = new Reserva(
			obj.getId().toString(),
			UUID.randomUUID(),
			UUID.randomUUID(),
			100.0,
			10
		);

		System.out.println("ReservaRepository->CreateAsync");

		return CompletableFuture.completedFuture(reserva);
	}

	@Override
	public Future<Reserva> UpdateAsync(Reserva obj) throws Exception {
		Reserva reserva = new Reserva(
			obj.getId().toString(),
			UUID.randomUUID(),
			UUID.randomUUID(),
			100.0,
			10
		);

		System.out.println("ReservaRepository->UpdateAsync");

		return CompletableFuture.completedFuture(reserva);
	}

	@Override
	public Future<List<Reserva>> GetReservasByHoraAndEstado(
		LocalDateTime hora,
		String estado
	) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void commit() {
		// TODO Auto-generated method stub
		return;
	}
}
