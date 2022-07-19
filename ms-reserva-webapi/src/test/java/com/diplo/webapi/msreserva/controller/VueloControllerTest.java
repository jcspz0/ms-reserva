package com.diplo.webapi.msreserva.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.mediator.IMediator;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.webapi.msreserva.service.MsReservaWebApiService;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class VueloControllerTest {

	@Mock
	MsReservaWebApiService webApiServiceTest;

	@Mock
	IMediator _mediatorTest;

	@InjectMocks
	VueloController vueloControllerTest;

	VueloDTO vueloDTOTest;
	String vueloIdTest;
	int nroVueloTest;
	int cantidadAsientoDisponibleTest;
	String destinoTest;

	List<VueloDTO> listaVueloDTO;

	@BeforeEach
	void init() {
		vueloIdTest = UUID.randomUUID().toString();
		nroVueloTest = 1;
		cantidadAsientoDisponibleTest = 10;
		destinoTest = "Santa";

		vueloDTOTest =
			new VueloDTO(
				vueloIdTest,
				nroVueloTest,
				cantidadAsientoDisponibleTest,
				destinoTest
			);

		listaVueloDTO = new ArrayList();
		listaVueloDTO.add(vueloDTOTest);

		when(webApiServiceTest.getMediator()).thenReturn(_mediatorTest);
	}

	@Test
	void CreateVuelo() throws Exception {
		when(webApiServiceTest.getMediator().Send(any()))
			.thenReturn(UUID.fromString(vueloIdTest));

		VueloDTO resultado = vueloControllerTest.CreateVuelo(vueloDTOTest);

		assertNotNull(resultado);
		assertEquals(vueloIdTest, resultado.getVueloId());
	}

	@Test
	void FindVueloByDestino() throws Exception {
		String destinoAEncontrar = "Santa";
		int cantidadEsperada = listaVueloDTO.size();

		when(webApiServiceTest.getMediator().Send(any()))
			.thenReturn(listaVueloDTO);

		List<VueloDTO> resultado = vueloControllerTest.FindVueloByDestino(
			destinoAEncontrar
		);

		assertNotNull(resultado);
		assertEquals(cantidadEsperada, resultado.size());
		assertEquals(vueloDTOTest.getVueloId(), resultado.get(0).getVueloId());
	}
}
