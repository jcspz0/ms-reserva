package com.diplo.webapi.msreserva.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.diplo.application.msreserva.dto.pasajero.PasajeroDTO;
import com.diplo.application.msreserva.mediator.IMediator;
import com.diplo.application.msreserva.mediator.Mediator;
import com.diplo.webapi.msreserva.service.MsReservaWebApiService;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@ExtendWith(MockitoExtension.class)
class PasajeroControllerTest {

	@Mock
	MsReservaWebApiService webApiServiceTest;

	@Mock
	IMediator _mediatorTest;

	@InjectMocks
	PasajeroController pasajeroControllerTest;

	UUID pasajeroIdTest;
	PasajeroDTO PasajeroDTOTest;
	int nroDocTest;
	int tipoDocTest;
	String nombreTest;
	String primerApellidoTest;
	String segundoApellidoTest;

	@BeforeEach
	void init() {
		pasajeroIdTest = UUID.randomUUID();
		nroDocTest = 123456;
		tipoDocTest = 1;
		nombreTest = "Nombre";
		primerApellidoTest = "Apellido";
		segundoApellidoTest = "Segundo";

		PasajeroDTOTest =
			new PasajeroDTO(
				pasajeroIdTest.toString(),
				nroDocTest,
				tipoDocTest,
				nombreTest,
				primerApellidoTest,
				segundoApellidoTest
			);

		when(webApiServiceTest.getMediator()).thenReturn(_mediatorTest);
	}

	@Test
	void CrearPasajero() throws Exception {
		when(webApiServiceTest.getMediator().Send(any()))
			.thenReturn(pasajeroIdTest);

		PasajeroDTO resultado = pasajeroControllerTest.CreatePasajero(
			PasajeroDTOTest
		);

		assertNotNull(resultado);
		assertEquals(
			pasajeroIdTest.toString(),
			resultado.getPasajeroId().toString()
		);
	}

	@Test
	void EncontrarPasajeroPorNroDocYTipoDoc() throws Exception {
		int nrodocTest = 473176;
		int tipodocTest = 1;

		when(webApiServiceTest.getMediator().Send(any()))
			.thenReturn(PasajeroDTOTest);

		PasajeroDTO resultado = pasajeroControllerTest.FindPasajeroByNroDocAndTipoDoc(
			nrodocTest,
			tipodocTest
		);

		assertNotNull(resultado);
		assertEquals(
			pasajeroIdTest.toString(),
			resultado.getPasajeroId().toString()
		);
	}
}
