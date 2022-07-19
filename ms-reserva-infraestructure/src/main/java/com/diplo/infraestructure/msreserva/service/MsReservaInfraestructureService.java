package com.diplo.infraestructure.msreserva.service;

import com.diplo.application.msreserva.mediator.IMediator;
import com.diplo.application.msreserva.mediator.Mediator;
import com.diplo.application.msreserva.service.IMediatorApplicationService;
import com.diplo.application.msreserva.service.MsReservaApplicationService;
import com.diplo.application.msreserva.service.reserva.IReservaService;
import com.diplo.application.msreserva.service.reserva.ReservaService;
import com.diplo.infraestructure.msreserva.entityframework.UnitOfWork;
import com.diplo.infraestructure.msreserva.entityframework.dbrepository.DbReservaRepository;
import com.diplo.infraestructure.msreserva.entityframework.entity.repository.ReservaEntityRepository;
import com.diplo.infraestructure.msreserva.memoryrepository.MemoryDatabase;
import com.diplo.infraestructure.msreserva.memoryrepository.MemoryReservaRepository;
import com.diplo.msreserva.factory.IReservaFactory;
import com.diplo.msreserva.factory.ReservaFactory;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.repository.IPasajeroRepository;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.sharekernel.core.IApplicationService;
import com.diplo.sharekernel.core.IInfraestructureService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MsReservaInfraestructureService
	implements IInfraestructureService {

	@Autowired
	MsReservaApplicationService service;

	@Autowired
	IReservaRepository _reservaRepository;

	@Autowired
	IVueloRepository _vueloRepository;

	@Autowired
	IPasajeroRepository _pasajeroRepository;

	@Autowired
	IUnitOfWork _unitOfWork;

	//-----

	@Override
	public void AddInfraestructure(IApplicationService appService) {
		service = (MsReservaApplicationService) appService;
		service.AddApplication();
		//_reservaRepository = new MemoryReservaRepository(new MemoryDatabase(new ArrayList<Reserva>()));
		//this._reservaRepository = _reservaRepository;
		//_unitOfWork = new UnitOfWork();
	}

	public MsReservaInfraestructureService(
		IMediatorApplicationService service,
		IReservaRepository _reservaRepository,
		IVueloRepository _vueloRepository,
		IPasajeroRepository _pasajeroRepository,
		IUnitOfWork _unitOfWork
	) {
		this.service = (MsReservaApplicationService) service;
		this._reservaRepository = _reservaRepository;
		this._vueloRepository = _vueloRepository;
		this._pasajeroRepository = _pasajeroRepository;
		this._unitOfWork = _unitOfWork;
		this.service.AddApplication();
	}

	public MsReservaInfraestructureService() {
		super();
	}

	public MsReservaApplicationService getService() {
		return service;
	}

	public void setService(MsReservaApplicationService service) {
		this.service = service;
	}

	public IReservaRepository getReservaRepository() {
		return _reservaRepository;
	}

	public void setReservaRepository(IReservaRepository _reservaRepository) {
		this._reservaRepository = _reservaRepository;
	}

	public IUnitOfWork get_unitOfWork() {
		return _unitOfWork;
	}

	public void set_unitOfWork(IUnitOfWork _unitOfWork) {
		this._unitOfWork = _unitOfWork;
	}

	public IVueloRepository getVueloRepository() {
		return _vueloRepository;
	}

	public void setVueloRepository(IVueloRepository _vueloRepository) {
		this._vueloRepository = _vueloRepository;
	}

	public IPasajeroRepository getPasajeroRepository() {
		return _pasajeroRepository;
	}

	public void setPasajeroRepository(IPasajeroRepository _pasajeroRepository) {
		this._pasajeroRepository = _pasajeroRepository;
	}
}
