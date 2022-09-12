package com.diplo.application.msreserva.usecase.query.pasajero.getPasajeroById;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.diplo.application.msreserva.dto.pasajero.PasajeroDTO;
import com.diplo.application.msreserva.usecase.query.pasajero.getPasajeroByNroDocAndTipoDoc.GetPasajeroByNroDocAndTipoDocHandler;
import com.diplo.application.msreserva.usecase.query.pasajero.getPasajeroByNroDocAndTipoDoc.GetPasajeroByNroDocAndTipoDocQuery;
import com.diplo.msreserva.model.pasajero.Pasajero;
import com.diplo.msreserva.repository.IPasajeroRepository;
import com.diplo.msreserva.valueobjects.DocumentoIdentidad;
import com.diplo.msreserva.valueobjects.NombreCompleto;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.Any;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetPasajeroByIdHandlerTest {

	@Mock
	IPasajeroRepository repository;

	@Mock
	GetPasajeroByIdQuery query;

	UUID pasajeroIDTest;

	Pasajero pasajeroTest;

	GetPasajeroByIdHandler handler;

	@BeforeEach
	void init() throws Exception {
		handler = new GetPasajeroByIdHandler(repository);

		pasajeroIDTest = UUID.randomUUID();
		NombreCompleto nombreTest = new NombreCompleto(
			"Test",
			"Prueba",
			"Pruebita"
		);
		DocumentoIdentidad documentoTest = new DocumentoIdentidad(1, 1);

		pasajeroTest = new Pasajero(pasajeroIDTest, nombreTest, documentoTest);

		when(query.getPasajeroId()).thenReturn(pasajeroIDTest);
		when(repository.FindByIdAsync(any()))
			.thenReturn(CompletableFuture.completedFuture(pasajeroTest));
	}

	@Test
	void GetPasajeroById() throws Exception {
		Future<PasajeroDTO> resultado = handler.Handle(query);

		assertNotNull(resultado);
		assertEquals(
			resultado.get().getPasajeroId(),
			pasajeroTest.getId().toString()
		);
	}
}
