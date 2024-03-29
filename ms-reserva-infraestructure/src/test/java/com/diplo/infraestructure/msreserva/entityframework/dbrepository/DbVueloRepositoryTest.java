package com.diplo.infraestructure.msreserva.entityframework.dbrepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.diplo.infraestructure.msreserva.entityframework.entity.PasajeroEntity;
import com.diplo.infraestructure.msreserva.entityframework.entity.VueloEntity;
import com.diplo.infraestructure.msreserva.entityframework.entity.repository.PasajeroEntityRepository;
import com.diplo.infraestructure.msreserva.entityframework.entity.repository.VueloEntityRepository;
import com.diplo.msreserva.model.pasajero.Pasajero;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.DocumentoIdentidad;
import com.diplo.msreserva.valueobjects.NombreCompleto;
import com.diplo.msreserva.valueobjects.NumeroVuelo;
import com.diplo.msreserva.valueobjects.Origen;
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

@ExtendWith(MockitoExtension.class)
class DbVueloRepositoryTest {

	@Mock
	VueloEntityRepository _databaseTest;

	@Mock
	ApplicationEventPublisher applicationEventPublisherTest;

	UUID vueloIdTest;
	NumeroVuelo numeroVueloTest;
	Destino _DestinoTest;
	Origen _OrigenTest;
	AsientoDisponible cantidadAsientoDisponibleTest;

	Vuelo vueloTest;
	VueloEntity vueloEntityTest;

	List<VueloEntity> listaVueloTest;

	@InjectMocks
	DbVueloRepository dbVueloRepositoryTest;

	@BeforeEach
	void init() throws Exception {
		vueloIdTest = UUID.randomUUID();
		numeroVueloTest = new NumeroVuelo("1");
		_DestinoTest = new Destino("Santa");
		_OrigenTest = new Origen("Santa");
		cantidadAsientoDisponibleTest = new AsientoDisponible(10);

		vueloTest =
			new Vuelo(
				vueloIdTest,
				numeroVueloTest,
				_OrigenTest,
				_DestinoTest,
				cantidadAsientoDisponibleTest
			);

		vueloEntityTest = new VueloEntity(vueloTest);

		listaVueloTest = new ArrayList<VueloEntity>();

		listaVueloTest.add(new VueloEntity(vueloTest));
	}

	@Test
	void FindByIdAsync() throws InterruptedException, ExecutionException {
		when(_databaseTest.findById(any()))
			.thenReturn((Optional.of(vueloEntityTest)));

		Future<Vuelo> resultado = dbVueloRepositoryTest.FindByIdAsync(
			vueloIdTest
		);

		assertNotNull(resultado);
		assertEquals(vueloIdTest, resultado.get().getId());
	}

	@Test
	void CreateAsync() throws InterruptedException, ExecutionException {
		Future<Vuelo> resultado = dbVueloRepositoryTest.CreateAsync(vueloTest);
		dbVueloRepositoryTest.commit();
		assertNotNull(resultado);
		assertEquals(vueloTest.getId(), resultado.get().getId());
	}

	@Test
	void UpdateAsync() throws InterruptedException, ExecutionException {
		Future<Vuelo> resultado = dbVueloRepositoryTest.UpdateAsync(vueloTest);
		dbVueloRepositoryTest.commit();
		assertNotNull(resultado);
		assertEquals(vueloTest.getId(), resultado.get().getId());
	}

	@Test
	void testGetVuelosByDestino()
		throws InterruptedException, ExecutionException {
		when(_databaseTest.GetVuelosByDestino(anyString(), anyString()))
			.thenReturn(listaVueloTest);

		Future<List<Vuelo>> resultado = dbVueloRepositoryTest.GetVuelosByDestino(
			_OrigenTest,
			_DestinoTest
		);

		assertNotNull(resultado);
		assertEquals(vueloTest.getId(), resultado.get().get(0).getId());
	}
}
