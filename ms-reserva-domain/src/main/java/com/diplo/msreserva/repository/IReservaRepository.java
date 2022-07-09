package com.diplo.msreserva.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.sharekernel.core.IRepository;

public interface IReservaRepository extends IRepository<Reserva, UUID> {
	
	Future<Reserva> UpdateAsync(Reserva obj) throws Exception;
	
	Future<List<Reserva>> GetReservasByHoraAndEstado (LocalDateTime hora, String estado);

}
