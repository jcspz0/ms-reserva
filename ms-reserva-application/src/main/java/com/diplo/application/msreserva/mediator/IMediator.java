package com.diplo.application.msreserva.mediator;

import java.util.UUID;

import com.diplo.application.msreserva.mediator.request.IRequest;
import com.diplo.application.msreserva.mediator.request.IRequestHandler;

public interface IMediator {

	void registrarComando(IRequest request, IRequestHandler handler);
	public <I, T extends I> I Send(IRequest<I> request) throws Exception;
	
}
