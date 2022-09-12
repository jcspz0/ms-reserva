package com.diplo.application.msreserva.usecase.command.reserva.vencerreserva;

import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.sharedkernel.mediator.request.IRequestHandler;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public class VencerReservaHandler
	implements IRequestHandler<VencerReservaCommand, UUID> {

	private final IUnitOfWork _unitOfWork;
	private final IReservaRepository _reservaRepository;

	public VencerReservaHandler(
		IUnitOfWork _unitOfWork,
		IReservaRepository _reservaRepository
	) {
		super();
		this._unitOfWork = _unitOfWork;
		this._reservaRepository = _reservaRepository;
	}

	@Override
	public Future<UUID> Handle(VencerReservaCommand request) throws Exception {
		Future<Reserva> future = _reservaRepository.FindByIdAsync(
			UUID.fromString(request.getReservaId())
		);
		Reserva reserva = future.get();

		reserva.VencerReserva();
		_reservaRepository.UpdateAsync(reserva);
		_unitOfWork.Commit();
		System.out.println("Se venci� la reserva");

		return CompletableFuture.completedFuture(reserva.getId());
	}
}
