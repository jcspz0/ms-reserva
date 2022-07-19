package com.diplo.application.msreserva.usecase.query.reserva.getReservaById;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.mediator.request.IRequestHandler;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.repository.IReservaRepository;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetReservaByIdHandler
	implements IRequestHandler<GetReservaByIdQuery, ReservaDTO> {

	private final IReservaRepository _reservaRepository;

	public GetReservaByIdHandler(IReservaRepository reservaRepository) {
		super();
		this._reservaRepository = reservaRepository;
	}

	@Override
	public Future<ReservaDTO> Handle(GetReservaByIdQuery request)
		throws Exception {
		ReservaDTO reservaDto = null;

		CompletableFuture<Reserva> objReserva = (CompletableFuture<Reserva>) _reservaRepository.FindByIdAsync(
			request.getId()
		);

		try {
			Reserva aux = objReserva.get();
			if (aux == null) {
				return null;
			}
			reservaDto = new ReservaDTO(aux);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return CompletableFuture.completedFuture(reservaDto);
	}
}
