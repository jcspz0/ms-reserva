package com.diplo.application.msreserva.usecase.query.vuelo.getVuelosByDestino;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.usecase.query.vuelo.getVueloById.GetVueloByIdHandler;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.NumeroVuelo;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetVuelosByDestinoHandlerTest {

	@Mock
	IVueloRepository _vueloRepositoryTest;

	@Mock
	GetVuelosByDestinoQuery getVuelosByDestinoQueryTest;

	Vuelo vueloTest;

	List<Vuelo> listaVueloTest;

	UUID vueloIdTest;
	NumeroVuelo numeroVueloTest;
	Destino _DestinoTest;
	AsientoDisponible cantidadAsientoDisponibleTest;

	GetVuelosByDestinoHandler getVuelosByDestinoHandlerTest;

	@BeforeEach
	void init() throws Exception {
		getVuelosByDestinoHandlerTest =
			new GetVuelosByDestinoHandler(_vueloRepositoryTest);

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

		listaVueloTest = new ArrayList<Vuelo>();
		listaVueloTest.add(vueloTest);

		when(getVuelosByDestinoQueryTest.getDestino())
			.thenReturn(_DestinoTest.getNombreDestino());
		when(_vueloRepositoryTest.GetVuelosByDestino(any()))
			.thenReturn(CompletableFuture.completedFuture(listaVueloTest));
	}

	@Test
	void test() throws InterruptedException, ExecutionException {
		Future<List<VueloDTO>> resultado = getVuelosByDestinoHandlerTest.Handle(
			getVuelosByDestinoQueryTest
		);

		assertNotNull(resultado);
		assertEquals(
			vueloIdTest.toString(),
			resultado.get().get(0).getVueloId()
		);
	}
}
