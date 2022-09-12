package com.diplo.infraestructure.msreserva.entityframework.dbrepository;

import com.diplo.infraestructure.msreserva.entityframework.entity.ReservaEntity;
import com.diplo.infraestructure.msreserva.entityframework.entity.VueloEntity;
import com.diplo.infraestructure.msreserva.entityframework.entity.repository.ReservaEntityRepository;
import com.diplo.msreserva.model.reserva.Reserva;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.valueobjects.AsientoDisponible;
import com.diplo.msreserva.valueobjects.CantidadPasajero;
import com.diplo.msreserva.valueobjects.Destino;
import com.diplo.msreserva.valueobjects.Monto;
import com.diplo.msreserva.valueobjects.NumeroReserva;
import com.diplo.msreserva.valueobjects.NumeroVuelo;
import com.diplo.sharedkernel.core.Constant;
import com.diplo.sharedkernel.event.DomainEvent;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.event.IntegrationMessage;
import com.diplo.sharedkernel.event.MessageEvent;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import java.time.LocalDateTime;
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
public class DbReservaRepository
	implements IReservaRepository, ApplicationEventPublisherAware {

	@Autowired
	private ReservaEntityRepository _database;

	private Map<String, List<Reserva>> context = null;

	@Autowired
	private ApplicationEventPublisher applicationEventPublisher = null;

	@Override
	public Future<Reserva> FindByIdAsync(UUID id) {
		ReservaEntity aux = _database.findById(id.toString()).get();
		Reserva result = new Reserva(
			aux.getReservaId(),
			aux.getNroReserva(),
			aux.getPasajeroId(),
			aux.getVueloId(),
			aux.getPrecio(),
			aux.getCantidadPasajero()
		);
		return CompletableFuture.completedFuture(result);
	}

	@Override
	public Future<Reserva> CreateAsync(Reserva obj) {
		System.out.println("CreateAsync DBRepository");
		// _database.save(new ReservaEntity(obj));
		register(obj, Constant.createAction);

		return CompletableFuture.completedFuture(obj);
	}

	@Override
	public Future<Reserva> UpdateAsync(Reserva obj) {
		System.out.println("UpdateAsync DBRepository");
		// _database.save(new ReservaEntity(obj));
		register(obj, Constant.updateAction);
		//this.applicationEventPublisher.publishEvent(new MessageEvent(obj,Constant.updateAction));
		return CompletableFuture.completedFuture(obj);
	}

	@Override
	public void setApplicationEventPublisher(
		ApplicationEventPublisher applicationEventPublisher
	) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public Future<List<Reserva>> GetReservasByHoraAndEstado(
		LocalDateTime hora,
		String estado
	) {
		System.out.println("GetReservasByHoraAndEstado DBRepository");
		try {
			List<ReservaEntity> aux = _database.GetReservasByHoraAndEstado(
				hora,
				estado
			);
			List<Reserva> listaReservas = new ArrayList<Reserva>();
			for (ReservaEntity auxlist : aux) {
				listaReservas.add(
					new Reserva(
						UUID.fromString(auxlist.getReservaId()),
						new NumeroReserva(auxlist.getNroReserva()),
						UUID.fromString(auxlist.getPasajeroId()),
						UUID.fromString(auxlist.getVueloId()),
						new Monto(auxlist.getPrecio()),
						new CantidadPasajero(auxlist.getCantidadPasajero()),
						auxlist.getEstado()
					)
				);
			}
			return CompletableFuture.completedFuture(listaReservas);
		} catch (Exception e) {
			System.out.println(
				"DbVueloRepository->Excepcion al tratar de encontrar el Vuelo," +
				e
			);
			return CompletableFuture.completedFuture(null);
		}
	}

	private void register(Reserva reserva, String action) {
		if (context == null) {
			context = new HashMap<String, List<Reserva>>();
		}
		ArrayList<Reserva> ReservaToOperate = (ArrayList<Reserva>) context.get(
			action
		);
		if (ReservaToOperate == null) {
			ReservaToOperate = new ArrayList<>();
		}
		ReservaToOperate.add(reserva);
		context.put(action, ReservaToOperate);
		for (DomainEvent event : reserva.getDomainEvents()) {
			this.applicationEventPublisher.publishEvent(
					new MessageEvent(event, action)
				);
		}
		for (IntegrationEvent event : reserva.getIntegrationEvents()) {
			System.out.println(
				"DbReservaRepository -> en for de eventos " + event
			);
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
		List<Reserva> ReservasToCreate = context.get(Constant.createAction);
		for (Reserva reserva : ReservasToCreate) {
			_database.save(new ReservaEntity(reserva));
		}
		context.remove(Constant.createAction);
	}

	private void commitUpdate() {
		List<Reserva> ReservasToCreate = context.get(Constant.updateAction);
		for (Reserva reserva : ReservasToCreate) {
			_database.save(new ReservaEntity(reserva));
		}
		context.remove(Constant.updateAction);
	}
}
