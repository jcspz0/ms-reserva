package com.diplo.application.msreserva.usecase.consumer.domain;

import com.diplo.application.msreserva.service.MsReservaApplicationService;
import com.diplo.application.msreserva.usecase.command.vuelo.reducirdisponibilidad.ReducirDisponibilidadCommand;
import com.diplo.application.msreserva.usecase.command.vuelo.reducirdisponibilidad.ReducirDisponibilidadHandler;
import com.diplo.msreserva.event.ReservaCreada;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.msreserva.repository.IUnitOfWork;
import com.diplo.msreserva.repository.IVueloRepository;
import com.diplo.sharedkernel.event.DomainMessage;
import com.diplo.sharedkernel.event.IConsumer;
import com.diplo.sharedkernel.event.IEvent;
import com.diplo.sharedkernel.mediator.IMediator;
import com.diplo.sharedkernel.mediator.Mediator;
import java.awt.print.PrinterException;
import java.util.concurrent.Future;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerDomainReservaCreada implements IConsumer<ReservaCreada> {

	@Autowired
	private IMediator _mediator;

	@Autowired
	IUnitOfWork _unitOfWork;

	@Autowired
	IVueloRepository _vueloRepository;

	@Override
	public void onProcessEvent(ReservaCreada event) {
		System.out.println(
			"ConsumerDomainReservaCreada, llego evento de dominio " + event
		);
		try {
			System.out.println(
				"Se ha capturado un evento de dominio de reservaCreada"
			);
			//ReservaCreada reservaCreada = (ReservaCreada)event.getMessage();
			/*Future<Vuelo> future = vueloRepository.FindByIdAsync(event.getVueloId());
			Vuelo vuelo = future.get();
			if(vuelo.reducirDisponibilidad(event.getCantidadPasajeros())) {
				vueloRepository.UpdateAsync(vuelo);
				System.out.println("Se redujo la capacidad, restante "+vuelo.getCantidadAsientoDisponible().getDisponibilidad());
			}*/
			//_mediator = new Mediator();
			ReducirDisponibilidadCommand _reducirDisponibilidadCommand = new ReducirDisponibilidadCommand(
				event.getVueloId().toString(),
				event.getCantidadPasajeros()
			);
			ReducirDisponibilidadHandler _reducirDisponibilidadHandler = new ReducirDisponibilidadHandler(
				_vueloRepository,
				_unitOfWork
			);
			_mediator.registrarComando(
				_reducirDisponibilidadCommand,
				_reducirDisponibilidadHandler
			);

			_mediator.Send(_reducirDisponibilidadCommand);
		} catch (Exception e) {
			System.out.println(
				"ProcesorReservaCreada->Ocurrio un error al procesar el evento" +
				e
			);
			e.printStackTrace();
		}
	}
}
