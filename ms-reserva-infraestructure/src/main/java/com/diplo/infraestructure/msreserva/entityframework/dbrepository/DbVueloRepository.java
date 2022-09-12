package com.diplo.infraestructure.msreserva.entityframework.dbrepository;

import com.diplo.infraestructure.msreserva.entityframework.entity.ReservaEntity;
import com.diplo.infraestructure.msreserva.entityframework.entity.VueloEntity;
import com.diplo.infraestructure.msreserva.entityframework.entity.repository.ReservaEntityRepository;
import com.diplo.infraestructure.msreserva.entityframework.entity.repository.VueloEntityRepository;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.CantidadPasajero;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.NumeroVuelo;
import com.diplo.sharedkernel.core.Constant;
import com.diplo.sharedkernel.event.DomainEvent;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.event.IntegrationMessage;
import com.diplo.sharedkernel.event.MessageEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Primary
public class DbVueloRepository
	implements IVueloRepository, ApplicationEventPublisherAware {

	@Autowired
	private VueloEntityRepository _database;

	private Map<String, List<Vuelo>> context = null;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher = null;

	@Override
	public Future<Vuelo> FindByIdAsync(UUID id) {
		try {
			VueloEntity aux = _database.findById(id.toString()).get();
			Vuelo result = new Vuelo(
				UUID.fromString(aux.getVueloId()),
				new NumeroVuelo(aux.getNroVuelo()),
				new Destino(aux.getDestino()),
				new AsientoDisponible(aux.getCantidadAsientoDisponible())
			);
			return CompletableFuture.completedFuture(result);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(
				"DbVueloRepository->Excepcion al tratar de encontrar el Vuelo," +
				e
			);
			return CompletableFuture.completedFuture(null);
		}
	}

	@Override
	public Future<Vuelo> CreateAsync(Vuelo obj) {
		System.out.println("CreateAsync DBRepository");
		//_database.save(new VueloEntity(obj));
		register(obj, Constant.createAction);
		//this.applicationEventPublisher.publishEvent(new MessageEvent(obj,Constant.createAction));
		return CompletableFuture.completedFuture(obj);
	}

	@Override
	public Future<Vuelo> UpdateAsync(Vuelo obj) {
		System.out.println("UpdateAsync DBRepository");
		//_database.save(new VueloEntity(obj));
		register(obj, Constant.updateAction);
		//this.applicationEventPublisher.publishEvent(new MessageEvent(obj,Constant.updateAction));
		return CompletableFuture.completedFuture(obj);
	}

	@Override
	public Future<List<Vuelo>> GetVuelosByDestino(Destino destino) {
		System.out.println("GetVuelosByDestino DBRepository");
		try {
			List<VueloEntity> aux = _database.GetVuelosByDestino(
				destino.getNombreDestino()
			);
			List<Vuelo> listaVuelos = new ArrayList<Vuelo>();
			for (VueloEntity auxlist : aux) {
				listaVuelos.add(
					new Vuelo(
						UUID.fromString(auxlist.getVueloId()),
						new NumeroVuelo(auxlist.getNroVuelo()),
						new Destino(auxlist.getDestino()),
						new AsientoDisponible(
							auxlist.getCantidadAsientoDisponible()
						)
					)
				);
			}
			return CompletableFuture.completedFuture(listaVuelos);
		} catch (Exception e) {
			System.out.println(
				"DbVueloRepository->Excepcion al tratar de encontrar el Vuelo," +
				e
			);
			return CompletableFuture.completedFuture(null);
		}
	}

	@Override
	public void setApplicationEventPublisher(
		ApplicationEventPublisher applicationEventPublisher
	) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	private void register(Vuelo vuelo, String action) {
		if (context == null) {
			context = new HashMap<String, List<Vuelo>>();
		}
		ArrayList<Vuelo> ReservaToOperate = (ArrayList<Vuelo>) context.get(
			action
		);
		if (ReservaToOperate == null) {
			ReservaToOperate = new ArrayList<>();
		}
		ReservaToOperate.add(vuelo);
		context.put(action, ReservaToOperate);
		for (DomainEvent event : vuelo.getDomainEvents()) {
			this.applicationEventPublisher.publishEvent(
					new MessageEvent(event, action)
				);
		}
		for (IntegrationEvent event : vuelo.getIntegrationEvents()) {
			this.applicationEventPublisher.publishEvent(event);
		}
	}

	@Override
	public void commit() {
		if (context == null || context.size() == 0) {
			return;
		}
		if (context.containsKey(Constant.createAction)) {
			commitCreate();
		}
		if (context.containsKey(Constant.updateAction)) {
			commitUpdate();
		}
	}

	private void commitCreate() {
		List<Vuelo> VuelosToCreate = context.get(Constant.createAction);
		for (Vuelo reserva : VuelosToCreate) {
			_database.save(new VueloEntity(reserva));
		}
		context.remove(Constant.createAction);
	}

	private void commitUpdate() {
		List<Vuelo> VuelosToCreate = context.get(Constant.updateAction);
		for (Vuelo reserva : VuelosToCreate) {
			_database.save(new VueloEntity(reserva));
		}
		context.remove(Constant.updateAction);
	}
}
