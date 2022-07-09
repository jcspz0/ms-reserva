package com.diplo.application.msreserva.usecase.command.reserva.crearvuelo;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.diplo.application.msreserva.mediator.request.IRequestHandler;
import com.diplo.application.msreserva.service.reserva.IReservaService;
import com.diplo.msreserva.factory.IReservaFactory;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.NumeroVuelo;



public class CrearVueloHandler implements IRequestHandler<CrearVueloCommand, UUID>{

	private final IUnitOfWork _unitOfWork;
	private final IVueloRepository _vueloRepository;
	
	public CrearVueloHandler(IVueloRepository _vueloRepository, IUnitOfWork _unitOfWork) {
		super();
		this._unitOfWork = _unitOfWork;
		this._vueloRepository = _vueloRepository;
	}

	@Override
	public Future<UUID> Handle(CrearVueloCommand request) {
		try {
			Vuelo objVuelo = new Vuelo(UUID.randomUUID(), new NumeroVuelo(request.getNroVuelo()), new Destino(request.getDestino()), new AsientoDisponible(request.getCantidadAsientoDisponible())); 
			System.out.println("CrearVueloHandler->objVuelo Creado");
			_vueloRepository.CreateAsync(objVuelo);
			_unitOfWork.Commit();
			
			return CompletableFuture.completedFuture(objVuelo.getId());
		} catch (Exception e) {
			System.out.println("CrearVueloHandler->excepcion al crear vuelo:"+e);
			System.out.println(e);
			return CompletableFuture.completedFuture(null);
		}
		
	}

}
