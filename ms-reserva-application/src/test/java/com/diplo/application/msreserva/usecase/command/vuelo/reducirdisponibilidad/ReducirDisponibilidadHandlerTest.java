package com.diplo.application.msreserva.usecase.command.vuelo.reducirdisponibilidad;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.NumeroVuelo;
import com.diplo.msreserva.valueobjects.Origen;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReducirDisponibilidadHandlerTest {

	@InjectMocks
	ReducirDisponibilidadHandler handler;

	@Mock
	IUnitOfWork _unitOfWork;

	@Mock
	IVueloRepository _vueloRepository;

	@Test
	void testReducirDisponibilidadHandler() {
		try {
			NumeroVuelo NroVuelo = new NumeroVuelo("1");
			AsientoDisponible CantidadAsientoDisponible = new AsientoDisponible(
				1
			);
			Destino _Destino;
			_Destino = new Destino("santa");
			Origen _Origen = new Origen("santa");
			UUID vueloId = UUID.randomUUID();
			int cantidadPasajero = 1;
			Vuelo vueloTest = new Vuelo(
				vueloId,
				NroVuelo,
				_Origen,
				_Destino,
				CantidadAsientoDisponible
			);
			when(_vueloRepository.FindByIdAsync(any()))
				.thenReturn(CompletableFuture.completedFuture(vueloTest));
			Future<UUID> resultado = handler.Handle(
				new ReducirDisponibilidadCommand(
					vueloId.toString(),
					cantidadPasajero
				)
			);
			assertNotNull(resultado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
