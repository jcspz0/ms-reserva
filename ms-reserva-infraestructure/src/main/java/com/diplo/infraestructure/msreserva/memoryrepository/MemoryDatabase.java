package com.diplo.infraestructure.msreserva.memoryrepository;

import com.diplo.msreserva.model.reserva.Reserva;
import java.util.ArrayList;
import java.util.List;

public class MemoryDatabase {

	private final List<Reserva> _reservas;

	public List<Reserva> get_reservas() {
		return _reservas;
	}

	public MemoryDatabase(List<Reserva> _reservas) {
		this._reservas = new ArrayList<Reserva>();
	}
}
