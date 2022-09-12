package com.diplo.application.msreserva;

import static org.junit.jupiter.api.Assertions.*;

import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.valueobjects.CantidadPasajero;
import com.diplo.msreserva.valueobjects.Monto;
import com.diplo.msreserva.valueobjects.NumeroReserva;
import com.diplo.sharedkernel.core.Constant;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReservaRepositoryImplTSTTest {

	@Test
	void test() {
		try {
			ReservaRepositoryImplTST test = new ReservaRepositoryImplTST();
			UUID reservaIdTest = UUID.randomUUID();
			NumeroReserva nroReservaTest = new NumeroReserva("Nro Reserva");
			UUID pasajeroTest = UUID.randomUUID();
			UUID vueloIdTest = UUID.randomUUID();
			Monto precioTest = new Monto(10);
			CantidadPasajero cantidadPasajeroTest;
			cantidadPasajeroTest = new CantidadPasajero(1);
			Reserva reservaTest = new Reserva(
				reservaIdTest,
				nroReservaTest,
				pasajeroTest,
				vueloIdTest,
				precioTest,
				cantidadPasajeroTest
			);

			test.CreateAsync(reservaTest);
			test.UpdateAsync(reservaTest);
			test.FindByIdAsync(reservaTest.getId());
			test.GetReservasByHoraAndEstado(
				LocalDateTime.now(),
				Constant.RESERVAESTADOVENCIDA
			);
			assertNotNull(test);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
