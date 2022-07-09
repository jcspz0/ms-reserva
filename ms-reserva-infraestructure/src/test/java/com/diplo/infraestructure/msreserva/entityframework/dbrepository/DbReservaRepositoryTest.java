package com.diplo.infraestructure.msreserva.entityframework.dbrepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import com.diplo.infraestructure.msreserva.entityframework.entity.ReservaEntity;
import com.diplo.infraestructure.msreserva.entityframework.entity.repository.ReservaEntityRepository;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.valueobjects.CantidadPasajero;
import com.diplo.msreserva.valueobjects.Monto;
import com.diplo.msreserva.valueobjects.NumeroReserva;

@ExtendWith(MockitoExtension.class)
class DbReservaRepositoryTest {
	
	@Mock ReservaEntityRepository _databaseTest;
	@Mock ApplicationEventPublisher applicationEventPublisherTest;
	
	UUID reservaIdTest; 
	NumeroReserva nroReservaTest;
	UUID pasajeroTest;
	UUID vueloIdTest;
	Monto precioTest;
	CantidadPasajero cantidadPasajeroTest;
	Reserva reservaTest;
	ReservaEntity reservaEntityTest;
	
	String HoraTest;
	String EstadoTest;
	List<ReservaEntity> listaReservaTest;
	
	@InjectMocks
	DbReservaRepository dbReservaRepositoryTest;
	
	@BeforeEach
	void init() throws Exception {
		
		
		reservaIdTest=UUID.randomUUID();
		nroReservaTest= new NumeroReserva("Nro Reserva");
		pasajeroTest = UUID.randomUUID();
		vueloIdTest = UUID.randomUUID();
		precioTest = new Monto(10);
		cantidadPasajeroTest = new CantidadPasajero(1);
		
		reservaTest = new Reserva(reservaIdTest, nroReservaTest, pasajeroTest, 
				vueloIdTest, precioTest, cantidadPasajeroTest);
		
		reservaEntityTest = new ReservaEntity(reservaTest);
		
		listaReservaTest = new ArrayList<ReservaEntity>();
		
		listaReservaTest.add(new ReservaEntity(reservaTest));
	}

	@Test
	void FindByIdAsync() throws InterruptedException, ExecutionException {
		when(_databaseTest.findById(any())).thenReturn((Optional.of(reservaEntityTest)));
		
		Future<Reserva> resultado = dbReservaRepositoryTest.FindByIdAsync(reservaIdTest);
		
		assertNotNull(resultado);
		assertEquals(reservaIdTest, resultado.get().getId());
	}

	@Test
	void CreateAsync() throws InterruptedException, ExecutionException {
Future<Reserva> resultado = dbReservaRepositoryTest.CreateAsync(reservaTest);
		
		assertNotNull(resultado);
		assertEquals(reservaTest.getId(), resultado.get().getId());
	}

	@Test
	void UpdateAsync() throws InterruptedException, ExecutionException {
Future<Reserva> resultado = dbReservaRepositoryTest.UpdateAsync(reservaTest);
		
		assertNotNull(resultado);
		assertEquals(reservaTest.getId(), resultado.get().getId());
	}

	@Test
	void GetReservasByHoraAndEstado() throws InterruptedException, ExecutionException {
		HoraTest = "2022-05-18 19:57:27";
		EstadoTest = "VALIDO";
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime dateTime = LocalDateTime.parse(HoraTest, formatter);
		
		when(_databaseTest.GetReservasByHoraAndEstado(any(), any())).thenReturn(listaReservaTest);
		
		Future<List<Reserva>> resultado = dbReservaRepositoryTest.GetReservasByHoraAndEstado(dateTime, EstadoTest);
		
		assertNotNull(resultado);
		assertEquals(reservaTest.getId(), resultado.get().get(0).getId());
	}

}
