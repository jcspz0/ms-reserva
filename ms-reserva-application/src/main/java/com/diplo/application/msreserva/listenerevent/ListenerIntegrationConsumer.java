package com.diplo.application.msreserva.listenerevent;

import com.diplo.sharedkernel.event.IListenerIntegrationConsumer;
import com.diplo.sharedkernel.event.IntegrationEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

@Service
public class ListenerIntegrationConsumer
	implements IListenerIntegrationConsumer, ApplicationEventPublisherAware {

	@Autowired
	private ApplicationEventPublisher publisher = null;

	@Override
	public void consume(IntegrationEvent event) {
		System.out.println(
			"ListenerIntegrationConsumer, llego evento " + event.getMessage()
		);
		this.publisher.publishEvent(event.getMessage());
	}

	@Override
	public void setApplicationEventPublisher(
		ApplicationEventPublisher applicationEventPublisher
	) {
		this.publisher = applicationEventPublisher;
	}
}
