package com.diplo.msreserva.repository;

import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.sharekernel.core.IRepository;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

public interface IVueloRepository extends IRepository<Vuelo, UUID> {
	Future<Vuelo> UpdateAsync(Vuelo obj);

	Future<List<Vuelo>> GetVuelosByDestino(Destino destino);
}
