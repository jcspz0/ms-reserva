package com.diplo.infraestructure.msreserva.memoryrepository;

import java.util.ArrayList;
import java.util.List;

import com.diplo.msreserva.model.reserva.Reserva;

public class MemoryDatabase {

	private final List<Reserva> _reservas;

	public List<Reserva> get_reservas() {
		return _reservas;
	}

	public MemoryDatabase(List<Reserva> _reservas) {
		this._reservas = new ArrayList<Reserva>();
	}
	
	
	
}
