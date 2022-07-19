package com.diplo.application.msreserva.service;

import com.diplo.application.msreserva.mediator.IMediator;
import com.diplo.sharekernel.core.IApplicationService;

public interface IMediatorApplicationService extends IApplicationService {
	IMediator getMediator();
}
