package com.diplo.application.msreserva;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.mediator.IMediator;
import com.diplo.application.msreserva.mediator.Mediator;
import com.diplo.application.msreserva.service.MsReservaApplicationService;
import com.diplo.application.msreserva.service.reserva.IReservaService;
import com.diplo.application.msreserva.service.reserva.ReservaService;
import com.diplo.application.msreserva.usecase.command.reserva.crearreserva.CrearReservaCommand;
import com.diplo.application.msreserva.usecase.command.reserva.crearreserva.CrearReservaHandler;
import com.diplo.application.msreserva.usecase.query.reserva.getReservaById.GetReservaByIdHandler;
import com.diplo.application.msreserva.usecase.query.reserva.getReservaById.GetReservaByIdQuery;
import com.diplo.msreserva.factory.IReservaFactory;
import com.diplo.msreserva.factory.ReservaFactory;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsReservaApplication {

	@Autowired
	static MsReservaApplicationService serviceApp;

	public static void main(String[] args) {
		SpringApplication.run(MsReservaApplication.class, args);
		/*serviceApp= new MsReservaApplicationService();
		serviceApp.AddApplication();
		
		IMediator _mediator = serviceApp.getMediator();
		IReservaService _serverReservaService = serviceApp.getReservaService();
		IReservaFactory _ReservaFactory = serviceApp.get_ReservaFactory();
		IReservaRepository _ReservaRepository = new ReservaRepositoryImplTST();
		IUnitOfWork _unitOfWork = new UnitOfWorkImplTST();
		
		///Creación de reserva
		CrearReservaCommand _crearReservaCommand = new CrearReservaCommand(UUID.randomUUID().toString(), UUID.randomUUID().toString(), 100.0, 1);
		CrearReservaHandler _CrearReservaHandler = new CrearReservaHandler(_serverReservaService, _ReservaFactory,_ReservaRepository ,_unitOfWork);
		_mediator.registrarComando(_crearReservaCommand, _CrearReservaHandler);
		UUID reservaIdCreada = _mediator.Send(_crearReservaCommand);
		System.out.println("main->CrearReserva, " + reservaIdCreada.toString());
		
		//Busqueda de reserva
		GetReservaByIdQuery _getReservaByIdQuery = new GetReservaByIdQuery(reservaIdCreada);
		GetReservaByIdHandler _getReservaByIdHandler = new GetReservaByIdHandler(_ReservaRepository);
		_mediator.registrarComando(new GetReservaByIdQuery(), _getReservaByIdHandler);
		ReservaDTO reservaEncontrada = _mediator.Send(_getReservaByIdQuery);
		System.out.println("main->GetReserva->id, "+reservaEncontrada.getReservaId());
		System.out.println("main->GetReserva->pasajeroid, "+reservaEncontrada.getPasajeroId());
		System.out.println("main->GetReserva->vueloid, "+reservaEncontrada.getVueloId());
		System.out.println("main->GetReserva->precio, "+reservaEncontrada.getPrecio());
		System.out.println("main->GetReserva->cantidad de pasajeros, "+reservaEncontrada.getCantidadPasajero());
		
		
		
		*/
	}
}
