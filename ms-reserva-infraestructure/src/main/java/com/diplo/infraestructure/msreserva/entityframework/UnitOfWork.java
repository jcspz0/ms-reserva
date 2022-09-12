package com.diplo.infraestructure.msreserva.entityframework;

import com.diplo.infraestructure.msreserva.tracker.ListenerEventTrackerInfra;
import com.diplo.msreserva.repository.IReservaRepository;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.sharedkernel.core.Constant;
import com.diplo.sharedkernel.core.Entity;
import com.diplo.sharedkernel.event.DomainEvent;
import com.diplo.sharedkernel.event.DomainMessage;
import com.diplo.sharedkernel.event.IListenerIntegrationTracker;
import com.diplo.sharedkernel.event.IListenerTracker;
import com.diplo.sharedkernel.event.IntegrationEvent;
import com.diplo.sharedkernel.event.IntegrationMessage;
import com.diplo.sharedkernel.event.MessageEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Service
public class UnitOfWork implements IUnitOfWork, ApplicationEventPublisherAware {

	@Autowired
	private ListenerEventTrackerInfra tracker;

	@Autowired
	private ApplicationEventPublisher publisherDomain = null;

	@Autowired
	private IListenerTracker publisher;

	@Autowired
	private IListenerIntegrationTracker publisherIntegration;

	@Autowired
	IReservaRepository _reservaRepository;

	@Autowired
	IVueloRepository _vueloRepository;

	@Override
	public void setApplicationEventPublisher(
		ApplicationEventPublisher applicationEventPublisher
	) {
		this.publisherDomain = applicationEventPublisher;
	}

	@Override
	public Future<Void> Commit() {
		ArrayList<MessageEvent> TrackerDomainToExecute = new ArrayList<>(
			tracker.getTrackersCargados()
		);

		tracker.clearTracker();

		for (MessageEvent message : TrackerDomainToExecute) {
			System.out.println(
				"Entro un TrackerDomainToExecute " + message.getMessage()
			);
			Object domainEvent = message.getMessage();
			//this.publisherDomain.publishEvent(new DomainMessage(domainEvent, message.getAction()));
			publisher.publish(
				new MessageEvent(domainEvent, message.getAction())
			);
		}

		while (tracker.getTrackersCargados().size() > 0) {
			System.out.println(
				"Se ha capturado nuevos eventos de Dominio, cantidad=" +
				tracker.getTrackersCargados().size()
			);
			TrackerDomainToExecute =
				new ArrayList<>(tracker.getTrackersCargados());
			//TrackerIntegrationToExecute.addAll(TrackerDomainToExecute);
			tracker.clearTracker();

			for (MessageEvent message : TrackerDomainToExecute) {
				Object domainEvent = message.getMessage();
				//this.publisherDomain.publishEvent(new DomainMessage(domainEvent, message.getAction()));
				publisher.publish(
					new MessageEvent(domainEvent, message.getAction())
				);
			}
		}
		System.out.println("Unit of worl antes de commite");
		_reservaRepository.commit();
		_vueloRepository.commit();
		System.out.println("Unit of worl luego de commite");

		ArrayList<IntegrationEvent> TrackerIntegrationToExecute = new ArrayList<>(
			tracker.getIntegrationTrackersCargados()
		);
		tracker.clearIntegrationTracker();
		System.out.println(
			"Unit of worl lista de integrationEvent, cant=" +
			TrackerIntegrationToExecute.size()
		);
		for (IntegrationEvent message : TrackerIntegrationToExecute) {
			System.out.println(
				"evento a propagar de integracion en unitofwork ->" + message
			);
			//this.publisherIntegration.publishEvent(new IntegrationMessage(integrationEvent, message.getAction()));
			publisherIntegration.publish(
				new IntegrationEvent(
					message.getMessage(),
					Constant.EVENTOCONFIRMADO
				)
			);
		}

		return CompletableFuture.completedFuture(null);
	}
}
