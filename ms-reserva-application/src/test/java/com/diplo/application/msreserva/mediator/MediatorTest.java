package com.diplo.application.msreserva.mediator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.diplo.application.msreserva.usecase.command.reserva.crearreserva.CrearReservaCommand;
import com.diplo.application.msreserva.usecase.command.reserva.crearreserva.CrearReservaHandler;
import com.diplo.application.msreserva.usecase.command.reserva.crearvuelo.CrearVueloCommand;
import com.diplo.application.msreserva.usecase.command.reserva.crearvuelo.CrearVueloHandler;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.NombreCompleto;
import com.diplo.msreserva.valueobjects.NumeroVuelo;
import com.diplo.sharedkernel.mediator.Mediator;
import com.diplo.sharedkernel.mediator.request.IRequest;
import com.diplo.sharedkernel.mediator.request.IRequestHandler;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MediatorTest {

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
	AsientoDisponible cantidadAsientoDisponibleTest;

	IRequestHandler requestHandler;

	@BeforeEach
	void init() throws Exception {
		vueloIdTest = UUID.randomUUID();
		numeroVueloTest = new NumeroVuelo(1);
		_DestinoTest = new Destino("destino");
		cantidadAsientoDisponibleTest = new AsientoDisponible(10);

		crearVueloHandlerTest =
			new CrearVueloHandler(_vueloRepository, _unitOfWork);

		//crearVueloCommandTest = new CrearVueloCommand();

		when(crearVueloCommandTest.getNroVuelo())
			.thenReturn(numeroVueloTest.getNumero());
		when(crearVueloCommandTest.getDestino())
			.thenReturn(_DestinoTest.getNombreDestino());
		when(crearVueloCommandTest.getCantidadAsientoDisponible())
			.thenReturn(cantidadAsientoDisponibleTest.getDisponibilidad());
	}

	/*
	@Test
	void ExcepcionMediatorNulo() throws Exception {
		requestHandler = crearVueloHandlerTest;
		when(requestHandler.Handle(any())).thenReturn(CompletableFuture.completedFuture());
		Exception exception = assertThrows(Exception.class, () -> {
			Mediator mediator = new Mediator();
			
			mediator.registrarComando(crearVueloCommandTest, crearVueloHandlerTest);
			
			mediator.Send(crearVueloCommandTest);
	    });

	    String expectedMessage = "Falla al tratar de procesar el request en el mediador";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
		
	}
	*/
	@Test
	void probarMediator() throws Exception {
		Map<Class<?>, IRequestHandler> _lista = null;

		Mediator mediator = new Mediator(_lista);
		mediator = new Mediator();

		mediator.registrarComando(crearVueloCommandTest, crearVueloHandlerTest);

		mediator.Send(crearVueloCommandTest);

		assertNotNull(mediator);
	}
}
