package com.diplo.application.msreserva.usecase.query.vuelo.getPasajeroByNroDocAndTipoDoc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import com.diplo.application.msreserva.dto.pasajero.PasajeroDTO;
import com.diplo.application.msreserva.usecase.query.reserva.getreservasbyhoraandestado.GetReservasByHoraAndEstadoHandler;
import com.diplo.msreserva.model.pasajero.Pasajero;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.repository.IPasajeroRepository;
import com.diplo.msreserva.valueobjects.CantidadPasajero;
import com.diplo.msreserva.valueobjects.DocumentoIdentidad;
import com.diplo.msreserva.valueobjects.Monto;
import com.diplo.msreserva.valueobjects.NombreCompleto;
import com.diplo.msreserva.valueobjects.NumeroReserva;
import java.util.ArrayList;
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
class GetPasajeroByNroDocAndTipoDocHandlerTest {

	@Mock
	IPasajeroRepository _pasajeroRepository;

	@Mock
	GetPasajeroByNroDocAndTipoDocQuery getPasajeroByNroDocAndTipoDocQuery;

	int NroDocTest;
	int TipoDocTest;

	UUID pasajeroIDTest;
	NombreCompleto nombreTest;
	DocumentoIdentidad documentoTest;

	Pasajero pasajeroTest;

	GetPasajeroByNroDocAndTipoDocHandler getPasajeroByNroDocAndTipoDocHandler;

	@BeforeEach
	void init() throws Exception {
		getPasajeroByNroDocAndTipoDocHandler =
			new GetPasajeroByNroDocAndTipoDocHandler(_pasajeroRepository);

		NroDocTest = 123456;
		TipoDocTest = 1;
		pasajeroIDTest = UUID.randomUUID();
		nombreTest = new NombreCompleto("Test", "Prueba", "Pruebita");
		documentoTest = new DocumentoIdentidad(NroDocTest, TipoDocTest);

		pasajeroTest = new Pasajero(pasajeroIDTest, nombreTest, documentoTest);

		when(getPasajeroByNroDocAndTipoDocQuery.getNroDoc())
			.thenReturn(NroDocTest);
		when(getPasajeroByNroDocAndTipoDocQuery.getTipoDoc())
			.thenReturn(TipoDocTest);
		when(
			_pasajeroRepository.GetPasajeroByNroDocAndTipoDoc(
				anyInt(),
				anyInt()
			)
		)
			.thenReturn(CompletableFuture.completedFuture(pasajeroTest));
	}

	@Test
	void GetPasajeroByNroDocAndTipoDoc() throws Exception {
		Future<PasajeroDTO> resultado = getPasajeroByNroDocAndTipoDocHandler.Handle(
			getPasajeroByNroDocAndTipoDocQuery
		);

		assertNotNull(resultado);
		assertEquals(
			resultado.get().getNroDoc(),
			pasajeroTest.getDocumento().getNroDoc()
		);
	}
}
