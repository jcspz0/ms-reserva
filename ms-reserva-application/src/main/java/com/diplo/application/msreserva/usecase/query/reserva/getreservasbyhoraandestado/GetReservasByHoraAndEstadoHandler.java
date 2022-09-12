package com.diplo.application.msreserva.usecase.query.reserva.getreservasbyhoraandestado;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.usecase.query.reserva.getReservaById.GetReservaByIdQuery;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.sharedkernel.mediator.request.IRequestHandler;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class GetReservasByHoraAndEstadoHandler
	implements
		IRequestHandler<GetReservasByHoraAndEstadoQuery, List<ReservaDTO>> {

	private final IReservaRepository _reservaRepository;

	public GetReservasByHoraAndEstadoHandler(
		IReservaRepository _reservaRepository
	) {
		super();
		this._reservaRepository = _reservaRepository;
	}

	@Override
	public Future<List<ReservaDTO>> Handle(
		GetReservasByHoraAndEstadoQuery request
	) {
		try {
			String str = request.getHora();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
				"yyyy-MM-dd HH:mm:ss"
			);
			LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
			CompletableFuture<List<Reserva>> future = (CompletableFuture<List<Reserva>>) _reservaRepository.GetReservasByHoraAndEstado(
				dateTime,
				request.getEstado()
			);
			List<Reserva> auxListaReserva = future.get();
			if (auxListaReserva == null) {
				return CompletableFuture.completedFuture(null);
			}
			List<ReservaDTO> listaReservas = new ArrayList<ReservaDTO>();
			for (Reserva vuelo : auxListaReserva) {
				listaReservas.add(new ReservaDTO(vuelo));
			}
			return CompletableFuture.completedFuture(listaReservas);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return CompletableFuture.completedFuture(null);
		}
	}
}
