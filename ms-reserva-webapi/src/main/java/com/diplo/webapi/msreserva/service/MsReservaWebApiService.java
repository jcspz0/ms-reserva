package com.diplo.webapi.msreserva.service;

import com.diplo.application.msreserva.mediator.IMediator;
import com.diplo.application.msreserva.mediator.Mediator;
import com.diplo.application.msreserva.service.MsReservaApplicationService;
import com.diplo.application.msreserva.service.reserva.IReservaService;
import com.diplo.application.msreserva.service.reserva.ReservaService;
import com.diplo.application.msreserva.usecase.command.reserva.crearpasajero.CrearPasajeroCommand;
import com.diplo.application.msreserva.usecase.command.reserva.crearpasajero.CrearPasajeroHandler;
import com.diplo.application.msreserva.usecase.command.reserva.crearreserva.CrearReservaCommand;
import com.diplo.application.msreserva.usecase.command.reserva.crearreserva.CrearReservaHandler;
import com.diplo.application.msreserva.usecase.command.reserva.crearvuelo.CrearVueloCommand;
import com.diplo.application.msreserva.usecase.command.reserva.crearvuelo.CrearVueloHandler;
import com.diplo.application.msreserva.usecase.query.reserva.getReservaById.GetReservaByIdHandler;
import com.diplo.application.msreserva.usecase.query.reserva.getReservaById.GetReservaByIdQuery;
import com.diplo.application.msreserva.usecase.query.reserva.getreservasbyhoraandestado.GetReservasByHoraAndEstadoHandler;
import com.diplo.application.msreserva.usecase.query.reserva.getreservasbyhoraandestado.GetReservasByHoraAndEstadoQuery;
import com.diplo.application.msreserva.usecase.query.vuelo.getPasajeroByNroDocAndTipoDoc.GetPasajeroByNroDocAndTipoDocHandler;
import com.diplo.application.msreserva.usecase.query.vuelo.getPasajeroByNroDocAndTipoDoc.GetPasajeroByNroDocAndTipoDocQuery;
import com.diplo.application.msreserva.usecase.query.vuelo.getVueloById.GetVueloByIdHandler;
import com.diplo.application.msreserva.usecase.query.vuelo.getVueloById.GetVueloByIdQuery;
import com.diplo.application.msreserva.usecase.query.vuelo.getVuelosByDestino.GetVuelosByDestinoHandler;
import com.diplo.application.msreserva.usecase.query.vuelo.getVuelosByDestino.GetVuelosByDestinoQuery;
import com.diplo.infraestructure.msreserva.entityframework.UnitOfWork;
import com.diplo.infraestructure.msreserva.memoryrepository.MemoryDatabase;
import com.diplo.infraestructure.msreserva.memoryrepository.MemoryReservaRepository;
import com.diplo.infraestructure.msreserva.service.MsReservaInfraestructureService;
import com.diplo.msreserva.factory.IReservaFactory;
import com.diplo.msreserva.factory.ReservaFactory;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MsReservaWebApiService {

	@Autowired
	MsReservaInfraestructureService _serviceInfra;

	IMediator _mediator;

	@PostConstruct
	private void postConstruct() {
		AddWebApi();
	}

	private void AddWebApi() {
		//_serviceInfra = new MsReservaInfraestructureService();
		_serviceInfra.AddInfraestructure(new MsReservaApplicationService());
		_mediator = _serviceInfra.getService().getMediator();
		registrarHandlers();
	}

	private void registrarHandlers() {
		CrearReservaHandler _CrearReservaHandler = new CrearReservaHandler(
			_serviceInfra.getService().getReservaService(),
			_serviceInfra.getService().get_ReservaFactory(),
			_serviceInfra.getReservaRepository(),
			_serviceInfra.get_unitOfWork()
		);
		_mediator.registrarComando(
			new CrearReservaCommand(),
			_CrearReservaHandler
		);
		GetReservaByIdHandler _getReservaByIdHandler = new GetReservaByIdHandler(
			_serviceInfra.getReservaRepository()
		);
		_mediator.registrarComando(
			new GetReservaByIdQuery(),
			_getReservaByIdHandler
		);

		CrearVueloCommand _crearVueloComman = new CrearVueloCommand();
		CrearVueloHandler _crearVueloHandler = new CrearVueloHandler(
			_serviceInfra.getVueloRepository(),
			_serviceInfra.get_unitOfWork()
		);
		_mediator.registrarComando(_crearVueloComman, _crearVueloHandler);

		GetVuelosByDestinoQuery _getVuelosByDestinoQuery = new GetVuelosByDestinoQuery();
		GetVuelosByDestinoHandler _getVuelosByDestinoHandler = new GetVuelosByDestinoHandler(
			_serviceInfra.getVueloRepository()
		);
		_mediator.registrarComando(
			_getVuelosByDestinoQuery,
			_getVuelosByDestinoHandler
		);

		GetVueloByIdQuery _getVueloByIdQuery = new GetVueloByIdQuery();
		GetVueloByIdHandler _getVueloByIdHandler = new GetVueloByIdHandler(
			_serviceInfra.getVueloRepository()
		);
		_mediator.registrarComando(_getVueloByIdQuery, _getVueloByIdHandler);

		GetReservasByHoraAndEstadoQuery _GetReservasByHoraAndEstadoQuery = new GetReservasByHoraAndEstadoQuery();
		GetReservasByHoraAndEstadoHandler _GetReservasByHoraAndEstadoHandler = new GetReservasByHoraAndEstadoHandler(
			_serviceInfra.getReservaRepository()
		);
		_mediator.registrarComando(
			_GetReservasByHoraAndEstadoQuery,
			_GetReservasByHoraAndEstadoHandler
		);

		CrearPasajeroCommand _CrearPasajeroCommand = new CrearPasajeroCommand();
		CrearPasajeroHandler _CrearPasajeroHandler = new CrearPasajeroHandler(
			_serviceInfra.getPasajeroRepository(),
			_serviceInfra.get_unitOfWork()
		);
		_mediator.registrarComando(
			_CrearPasajeroCommand,
			_CrearPasajeroHandler
		);

		GetPasajeroByNroDocAndTipoDocQuery _GetPasajeroByNroDocAndTipoDocQuery = new GetPasajeroByNroDocAndTipoDocQuery();
		GetPasajeroByNroDocAndTipoDocHandler _GetPasajeroByNroDocAndTipoDocHandler = new GetPasajeroByNroDocAndTipoDocHandler(
			_serviceInfra.getPasajeroRepository()
		);
		_mediator.registrarComando(
			_GetPasajeroByNroDocAndTipoDocQuery,
			_GetPasajeroByNroDocAndTipoDocHandler
		);
	}

	public IMediator getMediator() {
		//comentario
		return _mediator;
	}
}
