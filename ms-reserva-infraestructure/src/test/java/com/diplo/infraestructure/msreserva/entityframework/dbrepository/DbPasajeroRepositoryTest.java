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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import com.diplo.application.msreserva.usecase.query.vuelo.getVuelosByDestino.GetVuelosByDestinoHandler;
import com.diplo.infraestructure.msreserva.entityframework.entity.PasajeroEntity;
import com.diplo.infraestructure.msreserva.entityframework.entity.ReservaEntity;
import com.diplo.infraestructure.msreserva.entityframework.entity.repository.PasajeroEntityRepository;
import com.diplo.infraestructure.msreserva.entityframework.entity.repository.ReservaEntityRepository;
import com.diplo.msreserva.model.pasajero.Pasajero;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.CantidadPasajero;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.DocumentoIdentidad;
import com.diplo.msreserva.valueobjects.Monto;
import com.diplo.msreserva.valueobjects.NombreCompleto;
import com.diplo.msreserva.valueobjects.NumeroReserva;
import com.diplo.msreserva.valueobjects.NumeroVuelo;

@ExtendWith(MockitoExtension.class)
class DbPasajeroRepositoryTest {
	
	@Mock PasajeroEntityRepository _databaseTest;
	@Mock ApplicationEventPublisher applicationEventPublisherTest;
	
	UUID pasajeroIDTest;
	NombreCompleto nombreTest;
	DocumentoIdentidad documentoTest;
	
	Pasajero pasajeroTest;
	PasajeroEntity pasajeroEntityTest;
	int nroDocTest;
	int tipoDocTest;
	
	List<PasajeroEntity> listaPasajeroTest;
	
	@InjectMocks
	DbPasajeroRepository dbPasajeroRepositoryTest;
	
	
	@BeforeEach
	void init() throws Exception {
		
		pasajeroIDTest = UUID.randomUUID();
		nombreTest = new NombreCompleto("Prueba", "Test", "Pruebas");
		documentoTest = new DocumentoIdentidad(123123, 1);
		
		pasajeroTest = new Pasajero(pasajeroIDTest, nombreTest, documentoTest);
		
		pasajeroEntityTest = new PasajeroEntity(pasajeroTest);
		
		listaPasajeroTest = new ArrayList<PasajeroEntity>();
		
		listaPasajeroTest.add(new PasajeroEntity(pasajeroTest));
	}

	@Test
	void FindByIdAsync() throws InterruptedException, ExecutionException {
		when(_databaseTest.findById(any())).thenReturn((Optional.of(pasajeroEntityTest)));
		
		Future<Pasajero> resultado = dbPasajeroRepositoryTest.FindByIdAsync(pasajeroIDTest);
		
		assertNotNull(resultado);
		assertEquals(pasajeroIDTest, resultado.get().getId());
	}

	@Test
	void CreateAsync() throws InterruptedException, ExecutionException {
		Future<Pasajero> resultado = dbPasajeroRepositoryTest.CreateAsync(pasajeroTest);
		
		assertNotNull(resultado);
		assertEquals(pasajeroTest.getId(), resultado.get().getId());
	}

	@Test
	void UpdateAsync() throws InterruptedException, ExecutionException {
		Future<Pasajero> resultado = dbPasajeroRepositoryTest.UpdateAsync(pasajeroTest);
		
		assertNotNull(resultado);
		assertEquals(pasajeroTest.getId(), resultado.get().getId());
	}

	@Test
	void GetPasajeroByNroDocAndTipoDoc() throws Exception {
		
		when(_databaseTest.GetPasajeroByNroDocAndTipoDoc(any(), any())).thenReturn(CompletableFuture.completedFuture(pasajeroEntityTest));
		
		Future<Pasajero> resultado = dbPasajeroRepositoryTest.GetPasajeroByNroDocAndTipoDoc(documentoTest.getNroDoc(), documentoTest.getTipoDoc());
		
		assertNotNull(resultado);
		assertEquals(pasajeroTest.getId(), resultado.get().getId());
	}

}
