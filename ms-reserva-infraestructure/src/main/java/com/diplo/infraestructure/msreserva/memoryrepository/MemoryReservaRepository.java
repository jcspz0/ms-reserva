package com.diplo.infraestructure.msreserva.memoryrepository;

import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.repository.IReservaRepository;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class MemoryReservaRepository implements IReservaRepository {

	private final MemoryDatabase _database;

	public MemoryReservaRepository(MemoryDatabase _database) {
		super();
		this._database = _database;
	}

	@Override
	public Future<Reserva> FindByIdAsync(UUID id) {
		Reserva result = null;
		for (Reserva _reserva : _database.get_reservas()) {
			if (_reserva.getId().toString().equals(id.toString())) {
				result = _reserva;
			}
		}
		return CompletableFuture.completedFuture(result);
	}

	@Override
	public Future<Reserva> CreateAsync(Reserva obj) {
		_database.get_reservas().add(obj);
		System.out.println(
			"MemoryReservaRepository->" + _database.get_reservas().size()
		);
		return CompletableFuture.completedFuture(obj);
	}

	@Override
	public Future<Reserva> UpdateAsync(Reserva obj) {
		return CompletableFuture.completedFuture(obj);
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
