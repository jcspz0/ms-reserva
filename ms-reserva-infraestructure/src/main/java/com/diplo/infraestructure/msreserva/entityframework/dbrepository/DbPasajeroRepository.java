package com.diplo.infraestructure.msreserva.entityframework.dbrepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.diplo.infraestructure.msreserva.entityframework.entity.PasajeroEntity;
import com.diplo.infraestructure.msreserva.entityframework.entity.ReservaEntity;
import com.diplo.infraestructure.msreserva.entityframework.entity.VueloEntity;
import com.diplo.infraestructure.msreserva.entityframework.entity.repository.PasajeroEntityRepository;
import com.diplo.infraestructure.msreserva.entityframework.entity.repository.ReservaEntityRepository;
import com.diplo.infraestructure.msreserva.entityframework.entity.repository.VueloEntityRepository;
import com.diplo.infraestructure.msreserva.entityframework.tracker.MessageEvent;
import com.diplo.msreserva.model.pasajero.Pasajero;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IPasajeroRepository;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.CantidadPasajero;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.DocumentoIdentidad;
import com.diplo.msreserva.valueobjects.NombreCompleto;
import com.diplo.msreserva.valueobjects.NumeroVuelo;

@Service
@Primary
public class DbPasajeroRepository implements IPasajeroRepository, ApplicationEventPublisherAware{
	
	@Autowired
	private PasajeroEntityRepository _database;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher = null;

	@Override
	public Future<Pasajero> FindByIdAsync(UUID id) {
		try {
			PasajeroEntity aux = _database.findById(id).get();
			Pasajero result = new Pasajero(UUID.fromString(aux.getPasajeroId()),new NombreCompleto(aux.getNombre(), aux.getPrimerApellido(), aux.getSegundoApellido()), new DocumentoIdentidad(aux.getNroDoc(), aux.getTipoDoc()));
			return CompletableFuture.completedFuture(result);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("DbVueloRepository->Excepcion al tratar de encontrar el Vuelo,"+e);
			return CompletableFuture.completedFuture(null);
		}
	}

	@Override
	public Future<Pasajero> CreateAsync(Pasajero obj) {
		System.out.println("CreateAsync DBRepository");
		_database.save(new PasajeroEntity(obj));
		//this.applicationEventPublisher.publishEvent(new MessageEvent(obj,"create"));
		return CompletableFuture.completedFuture(obj);
	}

	@Override
	public Future<Pasajero> UpdateAsync(Pasajero obj) {
		System.out.println("UpdateAsync DBRepository");
		_database.save(new PasajeroEntity(obj));
		//this.applicationEventPublisher.publishEvent(new MessageEvent(obj,"update"));
		return CompletableFuture.completedFuture(obj);
	}

	@Override
	public Future<Pasajero> GetPasajeroByNroDocAndTipoDoc(int nroDoc, int tipoDoc) throws Exception {
			PasajeroEntity aux = _database.GetPasajeroByNroDocAndTipoDoc(nroDoc, tipoDoc).get();
			if(aux == null) {
				throw new Exception("No se encontro pasajero");
			}
			Pasajero result = new Pasajero(UUID.fromString(aux.getPasajeroId()),new NombreCompleto(aux.getNombre(), aux.getPrimerApellido(), aux.getSegundoApellido()), new DocumentoIdentidad(aux.getNroDoc(), aux.getTipoDoc()));
			return CompletableFuture.completedFuture(result);
	}
	
	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	

}
