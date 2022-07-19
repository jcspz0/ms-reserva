package com.diplo.application.msreserva.usecase.query.vuelo.getVueloById;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.usecase.query.vuelo.getPasajeroByNroDocAndTipoDoc.GetPasajeroByNroDocAndTipoDocHandler;
import com.diplo.msreserva.model.pasajero.Pasajero;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.DocumentoIdentidad;
import com.diplo.msreserva.valueobjects.NombreCompleto;
import com.diplo.msreserva.valueobjects.NumeroVuelo;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetVueloByIdHandlerTest {

	@Mock
	IVueloRepository _vueloRepositoryTest;

	@Mock
	GetVueloByIdQuery getVueloByIdQueryTest;

	GetVueloByIdHandler getVueloByIdHandler;

	Vuelo vueloTest;

	UUID vueloIdTest;
	NumeroVuelo numeroVueloTest;
	Destino _DestinoTest;
	AsientoDisponible cantidadAsientoDisponibleTest;

	@BeforeEach
	void init() throws Exception {
		getVueloByIdHandler = new GetVueloByIdHandler(_vueloRepositoryTest);

		vueloIdTest = UUID.randomUUID();
		numeroVueloTest = new NumeroVuelo(1);
		_DestinoTest = new Destino("Santa");
		cantidadAsientoDisponibleTest = new AsientoDisponible(10);

		vueloTest =
			new Vuelo(
				vueloIdTest,
				numeroVueloTest,
				_DestinoTest,
				cantidadAsientoDisponibleTest
			);

		when(getVueloByIdQueryTest.getId()).thenReturn(vueloIdTest);
		when(_vueloRepositoryTest.FindByIdAsync(any()))
			.thenReturn(CompletableFuture.completedFuture(vueloTest));
	}

	@Test
	void GetVueloById() throws Exception {
		Future<VueloDTO> resultado = getVueloByIdHandler.Handle(
			getVueloByIdQueryTest
		);

		assertNotNull(resultado);
		assertEquals(vueloIdTest.toString(), resultado.get().getVueloId());
	}
}
