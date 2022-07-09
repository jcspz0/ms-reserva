package com.diplo.application.msreserva.service;

import org.springframework.stereotype.Service;

import com.diplo.application.msreserva.ReservaRepositoryImplTST;
import com.diplo.application.msreserva.UnitOfWorkImplTST;
import com.diplo.application.msreserva.mediator.IMediator;
import com.diplo.application.msreserva.mediator.Mediator;
import com.diplo.application.msreserva.service.reserva.IReservaService;
import com.diplo.application.msreserva.service.reserva.ReservaService;
import com.diplo.msreserva.factory.IReservaFactory;
import com.diplo.msreserva.factory.ReservaFactory;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.sharekernel.core.IApplicationService;

@Service
public class MsReservaApplicationService implements IMediatorApplicationService {

	private IMediator _mediator;
	private IReservaService _reservaService;
	private IReservaFactory _ReservaFactory;
	
	
	@Override
	public void AddApplication() {
		_mediator = new Mediator();
		_reservaService = new ReservaService();
		_ReservaFactory = new ReservaFactory();
	}

	

	public MsReservaApplicationService() {
		super();
		AddApplication();
	}

	public void AddApplication(IMediator _mediator, IReservaService _serverReservaService, IReservaFactory _ReservaFactory) {
		this._mediator = _mediator;
		this._reservaService = _serverReservaService;
		this._ReservaFactory = _ReservaFactory;
	}
	

	public MsReservaApplicationService(IMediator _mediator, IReservaService _serverReservaService, IReservaFactory _ReservaFactory) {
		super();
		this._mediator = _mediator;
		this._reservaService = _serverReservaService;
		this._ReservaFactory = _ReservaFactory;
	}


	@Override
	public IMediator getMediator() {
		return _mediator;
	}



	public void setMediator(IMediator _mediator) {
		this._mediator = _mediator;
	}



	public IReservaService getReservaService() {
		return _reservaService;
	}



	public void setReservaService(IReservaService _reservaService) {
		this._reservaService = _reservaService;
	}



	public IReservaFactory get_ReservaFactory() {
		return _ReservaFactory;
	}



	public void set_ReservaFactory(IReservaFactory _ReservaFactory) {
		this._ReservaFactory = _ReservaFactory;
	}


}
