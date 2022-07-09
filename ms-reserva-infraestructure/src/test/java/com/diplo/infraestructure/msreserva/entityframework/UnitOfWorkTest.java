package com.diplo.infraestructure.msreserva.entityframework;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import com.diplo.infraestructure.msreserva.entityframework.tracker.ListenerEventTracker;

@ExtendWith(MockitoExtension.class)
class UnitOfWorkTest {

	@InjectMocks
	UnitOfWork unitOfWork;
	
	@Mock
	private ListenerEventTracker tracker;
	
	@Mock
	private ApplicationEventPublisher publisherDomain;
	
	@Test
	void Commit() {
		unitOfWork.Commit();
		assertNotNull(unitOfWork);
	}

}
