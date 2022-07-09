package com.diplo.application.msreserva.usecase.query.vuelo.getVueloById;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.mediator.request.IRequestHandler;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IVueloRepository;

public class GetVueloByIdHandler implements IRequestHandler<GetVueloByIdQuery, VueloDTO>{

	
	private final IVueloRepository _vueloRepository;
	
	public GetVueloByIdHandler(IVueloRepository vueloRepository) {
		super();
		this._vueloRepository = vueloRepository;
	}

	@Override
	public Future<VueloDTO> Handle(GetVueloByIdQuery request) throws Exception {
		
		VueloDTO vueloDto = null;
		
		CompletableFuture<Vuelo> objReserva =  (CompletableFuture<Vuelo>) _vueloRepository.FindByIdAsync(request.getId());
		
		try {
			Vuelo aux = objReserva.get();
			if(aux == null) {
				return null;
			}
			vueloDto = new VueloDTO(aux);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return CompletableFuture.completedFuture(vueloDto);
	}



}
