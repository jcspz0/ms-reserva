package com.diplo.application.msreserva.usecase.query.vuelo.getVuelosByDestino;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.usecase.query.reserva.getReservaById.GetReservaByIdQuery;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.sharedkernel.mediator.request.IRequestHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetVuelosByDestinoHandler
	implements IRequestHandler<GetVuelosByDestinoQuery, List<VueloDTO>> {

	private final IVueloRepository _vueloRepository;

	public GetVuelosByDestinoHandler(IVueloRepository _vueloRepository) {
		super();
		this._vueloRepository = _vueloRepository;
	}

	@Override
	public Future<List<VueloDTO>> Handle(GetVuelosByDestinoQuery request) {
		try {
			CompletableFuture<List<Vuelo>> future = (CompletableFuture<List<Vuelo>>) _vueloRepository.GetVuelosByDestino(
				new Destino(request.getDestino())
			);
			List<Vuelo> auxListaVuelos = future.get();
			if (auxListaVuelos == null) {
				return CompletableFuture.completedFuture(null);
			}
			List<VueloDTO> listaVuelos = new ArrayList<VueloDTO>();
			for (Vuelo vuelo : auxListaVuelos) {
				listaVuelos.add(new VueloDTO(vuelo));
			}
			return CompletableFuture.completedFuture(listaVuelos);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return CompletableFuture.completedFuture(null);
		}
	}
}
