package com.diplo.application.msreserva.usecase.command.reserva.crearvuelo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.diplo.application.msreserva.usecase.command.reserva.crearvuelo.CrearVueloCommand;
import com.diplo.application.msreserva.usecase.command.reserva.crearvuelo.CrearVueloHandler;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.NumeroVuelo;
import com.diplo.msreserva.valueobjects.Origen;
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
class CrearVueloHandlerTest {

	CrearVueloHandler crearVueloHandlerTest;

	@Mock
	CrearVueloCommand crearVueloCommandTest;

	@Mock
	IUnitOfWork _unitOfWork;

	@Mock
	IVueloRepository _vueloRepository;

	UUID vueloIdTest;
	NumeroVuelo numeroVueloTest;
	Destino _DestinoTest;
	Origen OrigenTest;
	AsientoDisponible cantidadAsientoDisponibleTest;

	@BeforeEach
	void init() throws Exception {
		vueloIdTest = UUID.randomUUID();
		numeroVueloTest = new NumeroVuelo("1");
		_DestinoTest = new Destino("destino");
		OrigenTest = new Origen("Santa");
		cantidadAsientoDisponibleTest = new AsientoDisponible(10);

		crearVueloHandlerTest =
			new CrearVueloHandler(_vueloRepository, _unitOfWork);

		when(crearVueloCommandTest.getNroVuelo())
			.thenReturn(numeroVueloTest.getNumero());
		when(crearVueloCommandTest.getDestino())
			.thenReturn(_DestinoTest.getNombreDestino());
		when(crearVueloCommandTest.getOrigen())
			.thenReturn(OrigenTest.getNombreOrigen());
		when(crearVueloCommandTest.getCantidadAsientoDisponible())
			.thenReturn(cantidadAsientoDisponibleTest.getDisponibilidad());
	}

	@Test
	void CrearVueloHandler() throws Exception {
		Future<UUID> resulto = crearVueloHandlerTest.Handle(
			crearVueloCommandTest
		);

		assertNotNull(resulto.get());
	}
}
