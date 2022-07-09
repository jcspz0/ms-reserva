package com.diplo.webapi.msreserva.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.diplo.application.msreserva.dto.pasajero.PasajeroDTO;
import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.mediator.IMediator;
import com.diplo.application.msreserva.usecase.command.reserva.crearreserva.CrearReservaCommand;
import com.diplo.application.msreserva.usecase.query.vuelo.getVueloById.GetVueloByIdQuery;
import com.diplo.webapi.msreserva.service.MsReservaWebApiService;

@ExtendWith(MockitoExtension.class)
class ReservaControllerTest {

	@Mock MsReservaWebApiService webApiServiceTest;
	
	@Mock IMediator _mediatorTest;
	
	@InjectMocks ReservaController reservaControllerTest;
	
	ReservaDTO reservaDTOTest;
	String reservaIdTest;
	String nroReservaTest;
	String pasajeroIdTest;
	String vueloIdTest;
	double precioTest;
	int cantidadPasajeroTest;
	String horaTest;
	String estadoTest;
	
	List<ReservaDTO> listaReservaDTO;
	
	VueloDTO vueloDTOTest;
	
	int nroVueloTest;
	int cantidadAsientoDisponibleTest;
	String destinoTest;
	
	@BeforeEach
	void init() {
		
		reservaIdTest = UUID.randomUUID().toString();
		nroReservaTest = "1";
		pasajeroIdTest = UUID.randomUUID().toString();
		vueloIdTest = UUID.randomUUID().toString();
		precioTest = 100;
		cantidadPasajeroTest = 1;
		horaTest = "2022-04-25 18:22:41";
		estadoTest = "VALIDO";
		
		reservaDTOTest = new ReservaDTO(reservaIdTest, nroReservaTest, pasajeroIdTest, vueloIdTest, precioTest, cantidadPasajeroTest, horaTest, estadoTest);
		
		listaReservaDTO = new ArrayList();
		listaReservaDTO.add(reservaDTOTest);
		
		nroVueloTest = 1;
		cantidadAsientoDisponibleTest = 10;
		destinoTest = "Santa";
		
		vueloDTOTest = new VueloDTO(vueloIdTest, nroVueloTest, cantidadAsientoDisponibleTest, destinoTest);
		
		when(webApiServiceTest.getMediator()).thenReturn(_mediatorTest);
	}
	
	@Test
	void CreateReserva() throws Exception {
		when(webApiServiceTest.getMediator().Send(any(GetVueloByIdQuery.class))).thenReturn(vueloDTOTest);
		
		when(webApiServiceTest.getMediator().Send(any(CrearReservaCommand.class))).thenReturn(UUID.fromString(reservaIdTest));
		
		
		ReservaDTO resultado = reservaControllerTest.CreateReserva(reservaDTOTest);
		
		assertNotNull(resultado);
		assertEquals(reservaIdTest.toString(), resultado.getReservaId().toString());
	}

	@Test
	void FindReservaById() throws Exception {
		
		when(webApiServiceTest.getMediator().Send(any())).thenReturn(reservaDTOTest);
		
		ReservaDTO resultado = reservaControllerTest.FindReservaById(reservaIdTest);
		
		assertNotNull(resultado);
		assertEquals(reservaIdTest, resultado.getReservaId());

	}

	@Test
	void FindReservasByHoraAndEstado() throws Exception {
		String hora = "2022-04-25 18:22:41";
		int cantidadEsperada = listaReservaDTO.size();
		
		when(webApiServiceTest.getMediator().Send(any())).thenReturn(listaReservaDTO);
	
		List<ReservaDTO> resultado = reservaControllerTest.FindReservasByHoraAndEstado(hora);
	
		assertNotNull(resultado);
		assertEquals(cantidadEsperada, resultado.size());
		assertEquals(reservaDTOTest.getReservaId(), resultado.get(0).getReservaId());

	}

}
