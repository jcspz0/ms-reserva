package com.diplo.infraestructure.msreserva;

import com.diplo.application.msreserva.ReservaRepositoryImplTST;
import com.diplo.application.msreserva.UnitOfWorkImplTST;
import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.service.MsReservaApplicationService;
import com.diplo.application.msreserva.service.reserva.IReservaService;
import com.diplo.application.msreserva.service.reserva.ReservaService;
import com.diplo.application.msreserva.usecase.command.reserva.crearpasajero.CrearPasajeroCommand;
import com.diplo.application.msreserva.usecase.command.reserva.crearpasajero.CrearPasajeroHandler;
import com.diplo.application.msreserva.usecase.command.reserva.crearreserva.CrearReservaCommand;
import com.diplo.application.msreserva.usecase.command.reserva.crearreserva.CrearReservaHandler;
import com.diplo.application.msreserva.usecase.query.reserva.getReservaById.GetReservaByIdHandler;
import com.diplo.application.msreserva.usecase.query.reserva.getReservaById.GetReservaByIdQuery;
import com.diplo.application.msreserva.usecase.query.vuelo.getVueloById.GetVueloByIdHandler;
import com.diplo.application.msreserva.usecase.query.vuelo.getVueloById.GetVueloByIdQuery;
import com.diplo.application.msreserva.usecase.query.vuelo.getVuelosByDestino.GetVuelosByDestinoHandler;
import com.diplo.application.msreserva.usecase.query.vuelo.getVuelosByDestino.GetVuelosByDestinoQuery;
import com.diplo.infraestructure.msreserva.entityframework.UnitOfWork;
import com.diplo.infraestructure.msreserva.entityframework.dbrepository.DbReservaRepository;
import com.diplo.infraestructure.msreserva.entityframework.dbrepository.DbVueloRepository;
import com.diplo.infraestructure.msreserva.entityframework.entity.repository.ReservaEntityRepository;
import com.diplo.infraestructure.msreserva.memoryrepository.MemoryDatabase;
import com.diplo.infraestructure.msreserva.memoryrepository.MemoryReservaRepository;
import com.diplo.infraestructure.msreserva.service.MsReservaInfraestructureService;
import com.diplo.msreserva.factory.IReservaFactory;
import com.diplo.msreserva.factory.ReservaFactory;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.sharedkernel.mediator.IMediator;
import com.diplo.sharedkernel.mediator.Mediator;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.diplo" })
public class MsReservaInfraestructureApplication { //implements CommandLineRunner {
	/*
	//@Autowired
	//DbReservaRepository reservaEntityRepository;
	
	@Autowired
	DbVueloRepository vueloEntityRepository;
	
	@Autowired
	MsReservaInfraestructureService _serviceInfra;
	
	@Autowired
	UnitOfWork _unitOfWork;
	
	
	public static void main(String[] args) {
		SpringApplication.run(MsReservaInfraestructureApplication.class, args);
		
	}
	
	@Override
    public void run(String... strings) throws Exception {
	//MsReservaInfraestructureService _serviceInfra = new MsReservaInfraestructureService(new MsReservaApplicationService(),reservaEntityRepository, new UnitOfWork());
	/*	
		_serviceInfra.setReservaRepository(reservaEntityRepository);
		_serviceInfra.set_unitOfWork(_unitOfWork);
		_serviceInfra.AddInfraestructure(new MsReservaApplicationService());
		
		IMediator _mediator = _serviceInfra.getService().getMediator();
		IReservaService _reservaService = _serviceInfra.getService().getReservaService();
		IReservaFactory _ReservaFactory = _serviceInfra.getService().get_ReservaFactory();
		IReservaRepository _ReservaRepository = _serviceInfra.getReservaRepository();
		IUnitOfWork _unitOfWork = _serviceInfra.get_unitOfWork();
		
		//----
		///Creación de reserva
		CrearReservaCommand _crearReservaCommand = new CrearReservaCommand();
		CrearReservaHandler _CrearReservaHandler = new CrearReservaHandler(_reservaService, _ReservaFactory,_ReservaRepository ,_unitOfWork);
		_mediator.registrarComando(_crearReservaCommand, _CrearReservaHandler);
		UUID reservaIdCreada = _mediator.Send(new CrearReservaCommand(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), 100.0, 1));
		UUID reservaIdCreada2 = _mediator.Send(new CrearReservaCommand(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), 200.0, 2));
		UUID reservaIdCreada3 = _mediator.Send(new CrearReservaCommand(UUID.randomUUID().toString(), UUID.randomUUID().toString(), UUID.randomUUID().toString(), 300.0, 3));
		System.out.println("main->CrearReserva, " + reservaIdCreada.toString());
		//---------
		//Busqueda de reserva
		GetReservaByIdQuery _getReservaByIdQuery = new GetReservaByIdQuery(reservaIdCreada2);
		GetReservaByIdHandler _getReservaByIdHandler = new GetReservaByIdHandler(_ReservaRepository);
		_mediator.registrarComando(new GetReservaByIdQuery(), _getReservaByIdHandler);
		ReservaDTO reservaEncontrada = _mediator.Send(_getReservaByIdQuery);
		System.out.println("main->GetReserva->id, "+reservaEncontrada.getReservaId());
		System.out.println("main->GetReserva->pasajeroid, "+reservaEncontrada.getPasajeroId());
		System.out.println("main->GetReserva->vueloid, "+reservaEncontrada.getVueloId());
		System.out.println("main->GetReserva->precio, "+reservaEncontrada.getPrecio());
		System.out.println("main->GetReserva->cantidad de pasajeros, "+reservaEncontrada.getCantidadPasajero());
		
		//Update reserva
		
	*/

	///-----------crear vuelo
	/*	
		_serviceInfra.set_unitOfWork(_unitOfWork);
		_serviceInfra.AddInfraestructure(new MsReservaApplicationService());
		
		IMediator _mediator = _serviceInfra.getService().getMediator();
		IVueloRepository _vueloRepository = _serviceInfra.getVueloRepository();
		IUnitOfWork _unitOfWork = _serviceInfra.get_unitOfWork();
		
		CrearVueloCommand _crearVueloComman = new CrearVueloCommand();
		CrearVueloHandler _crearVueloHandler = new CrearVueloHandler(_vueloRepository,_unitOfWork);
		_mediator.registrarComando(_crearVueloComman, _crearVueloHandler);
		UUID vueloID = _mediator.Send(new CrearVueloCommand(1, 10, "santa"));
		
		GetVueloByIdQuery _GetVueloByIdQuery = new GetVueloByIdQuery();
		GetVueloByIdHandler _GetVueloByIdQueryHandler = new GetVueloByIdHandler(_serviceInfra.getVueloRepository());
		_mediator.registrarComando(_GetVueloByIdQuery, _GetVueloByIdQueryHandler);
		VueloDTO aux = _mediator.Send(new GetVueloByIdQuery(vueloID));
		System.out.println("cantidad:"+aux.getVueloId());
		
		GetVuelosByDestinoQuery _getVuelosByDestinoQuery = new GetVuelosByDestinoQuery();
		GetVuelosByDestinoHandler _getVuelosByDestinoHandler = new GetVuelosByDestinoHandler(_vueloRepository);
		_mediator.registrarComando(_getVuelosByDestinoQuery, _getVuelosByDestinoHandler);
		List<VueloDTO> listaVuelo = _mediator.Send(new GetVuelosByDestinoQuery("santa"));
		
		for (VueloDTO vueloDTO : listaVuelo) {
			System.out.println("vueloId:"+vueloDTO.getVueloId());
		}
		System.out.println("cantidad:"+listaVuelo.size());
		
		//----------------
		*/
	//  }

}
