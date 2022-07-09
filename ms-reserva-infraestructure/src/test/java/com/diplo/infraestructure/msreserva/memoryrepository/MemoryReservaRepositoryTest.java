package com.diplo.infraestructure.msreserva.memoryrepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.valueobjects.CantidadPasajero;
import com.diplo.msreserva.valueobjects.Monto;
import com.diplo.msreserva.valueobjects.NumeroReserva;

@ExtendWith(MockitoExtension.class)
class MemoryReservaRepositoryTest {
	
	@Mock
	MemoryDatabase _database;
	
	
	MemoryReservaRepository memoryReservaRepository;

	Reserva reservaTest;
	List<Reserva> lista;
	UUID reservaIdTest;
	
	@BeforeEach
	void init() throws Exception {
		reservaIdTest= UUID.randomUUID(); 
		NumeroReserva nroReservaTest= new NumeroReserva("Nro Reserva");
		UUID pasajeroTest = UUID.randomUUID();
		UUID vueloIdTest = UUID.randomUUID();
		Monto precioTest = new Monto(10);
		CantidadPasajero cantidadPasajeroTest = new CantidadPasajero(1);
		
		reservaTest = new Reserva(reservaIdTest, nroReservaTest, pasajeroTest, 
				vueloIdTest, precioTest, cantidadPasajeroTest);
		
		lista = new ArrayList();
		lista.add(reservaTest);
		memoryReservaRepository= new MemoryReservaRepository(_database);
		
	}
	
	@Test
	void testFindByIdAsync() throws Exception {
		when(_database.get_reservas()).thenReturn(lista);
		Future<Reserva> resultado = memoryReservaRepository.FindByIdAsync(reservaTest.getId());
		assertNotNull(resultado.get());
	}

	@Test
	void testCreateAsync() throws Exception{
		when(_database.get_reservas()).thenReturn(lista);
		Future<Reserva> resultado = memoryReservaRepository.CreateAsync(reservaTest);
		assertNotNull(resultado.get());
	}

	@Test
	void testUpdateAsync() throws Exception{
		Future<Reserva> resultado = memoryReservaRepository.UpdateAsync(reservaTest);
		assertNotNull(resultado);
	}

	@Test
	void testGetReservasByHoraAndEstado() throws Exception{
		Future<List<Reserva>> resultado = memoryReservaRepository.GetReservasByHoraAndEstado(LocalDateTime.now(), "VALIDO");
		assertNull(resultado);
	}

}
