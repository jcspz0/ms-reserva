package com.diplo.infraestructure.msreserva.entityframework;

import com.diplo.infraestructure.msreserva.entityframework.tracker.DomainMessage;
import com.diplo.infraestructure.msreserva.entityframework.tracker.ListenerEventTracker;
import com.diplo.infraestructure.msreserva.entityframework.tracker.MessageEvent;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.sharekernel.core.DomainEvent;
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
	private ListenerEventTracker tracker;

	@Autowired
	private ApplicationEventPublisher publisherDomain = null;

	@Override
	public void setApplicationEventPublisher(
		ApplicationEventPublisher applicationEventPublisher
	) {
		this.publisherDomain = applicationEventPublisher;
	}

	@Override
	public Future<Void> Commit() {
		for (MessageEvent message : tracker.getTrackersCargados()) {
			Object domainEvent = message.getMessage();
			this.publisherDomain.publishEvent(
					new DomainMessage(domainEvent, message.getAction())
				);
		}

		tracker.clearTracker();

		return CompletableFuture.completedFuture(null);
	}
}
