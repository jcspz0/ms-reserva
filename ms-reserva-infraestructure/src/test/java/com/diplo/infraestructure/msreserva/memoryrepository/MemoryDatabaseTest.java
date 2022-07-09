package com.diplo.infraestructure.msreserva.memoryrepository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.diplo.msreserva.model.reserva.Reserva;

class MemoryDatabaseTest {

	@Test
	void probarMemoryDatabase() {
		List<Reserva> lista = new ArrayList();
		MemoryDatabase memoryDatabase = new MemoryDatabase(lista);
		assertEquals(lista, memoryDatabase.get_reservas());
	}

}
