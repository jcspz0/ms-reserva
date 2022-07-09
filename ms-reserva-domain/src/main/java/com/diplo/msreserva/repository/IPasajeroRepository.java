package com.diplo.msreserva.repository;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

import com.diplo.msreserva.model.pasajero.Pasajero;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.DocumentoIdentidad;
import com.diplo.sharekernel.core.IRepository;

public interface IPasajeroRepository extends IRepository<Pasajero, UUID> {
	
	Future<Pasajero> UpdateAsync(Pasajero obj);
	
	Future<Pasajero> GetPasajeroByNroDocAndTipoDoc (int nroDoc, int tipoDoc) throws Exception;

}
