package com.diplo.msreserva;

import com.diplo.msreserva.model.pasajero.Pasajero;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.CantidadPasajero;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.DocumentoIdentidad;
import com.diplo.msreserva.valueobjects.Monto;
import com.diplo.msreserva.valueobjects.NombreCompleto;
import com.diplo.msreserva.valueobjects.NumeroReserva;
import com.diplo.msreserva.valueobjects.NumeroVuelo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsReservaDomainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsReservaDomainApplication.class, args);

		try {
			Vuelo objVuelo1 = new Vuelo(
				new NumeroVuelo(1),
				new Destino("Santa Cruz"),
				new AsientoDisponible(10)
			);
			Vuelo objVuelo2 = new Vuelo(
				new NumeroVuelo(1),
				new Destino("La paz"),
				new AsientoDisponible(5)
			);

			Pasajero objPasajero = new Pasajero(
				new NombreCompleto("Juan", "Perez"),
				new DocumentoIdentidad(123456, 1)
			);

			Reserva objReserva1 = new Reserva(
				new NumeroReserva("1"),
				objPasajero.getId(),
				objVuelo1.getId(),
				new Monto(100),
				new CantidadPasajero(3)
			);

			System.out.println(objReserva1.verReserva());
			///---------
			/*Deuda objDeuda1 = new Deuda(objReserva1.getId(), objReserva1.getPrecio());
			System.out.println("deuda: "+objDeuda1.consultarDeuda() + ", estado: " + objDeuda1.getEstado());
			
			objDeuda1.RealizarPago(new Pago(new Monto(40), "pago de 40"));
			System.out.println("deuda: "+objDeuda1.consultarDeuda() + ", estado: " + objDeuda1.getEstado());
			
			objDeuda1.RealizarPago(new Pago(new Monto(40), "pago de 40"));
			System.out.println("deuda: "+objDeuda1.consultarDeuda() + ", estado: " + objDeuda1.getEstado());
			
			//objDeuda1.ImprimirFactura("pago de viaje", 123456);
			
			objDeuda1.RealizarPago(new Pago(new Monto(20), "pago de 40"));
			System.out.println("deuda: "+objDeuda1.consultarDeuda() + ", estado: " + objDeuda1.getEstado());
			
			objDeuda1.ImprimirFactura("pago de viaje", 123456);*/
			//----------
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
