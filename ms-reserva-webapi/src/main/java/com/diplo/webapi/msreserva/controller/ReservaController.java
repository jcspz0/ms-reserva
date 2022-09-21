package com.diplo.webapi.msreserva.controller;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.usecase.command.reserva.crearreserva.CrearReservaCommand;
import com.diplo.application.msreserva.usecase.query.reserva.getReservaById.GetReservaByIdQuery;
import com.diplo.application.msreserva.usecase.query.reserva.getreservasbyhoraandestado.GetReservasByHoraAndEstadoQuery;
import com.diplo.application.msreserva.usecase.query.vuelo.getVueloById.GetVueloByIdQuery;
import com.diplo.application.msreserva.usecase.query.vuelo.getVuelosByDestino.GetVuelosByDestinoQuery;
import com.diplo.infraestructure.msreserva.amqp.RabbitMQReservaCreadaSender;
import com.diplo.infraestructure.msreserva.memoryrepository.MemoryReservaRepository;
import com.diplo.msreserva.model.vuelo.Vuelo;
import com.diplo.sharedkernel.core.Constant;
import com.diplo.sharedkernel.integrationevents.IntegrationReservaCreada;
import com.diplo.sharedkernel.mediator.IMediator;
import com.diplo.webapi.msreserva.service.MsReservaWebApiService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(path = "/reserva")
@CrossOrigin(origins = "*")
public class ReservaController {

	@Autowired
	MsReservaWebApiService _reservaService;

	@RequestMapping(value = "/crear", method = RequestMethod.POST)
	public ReservaDTO CreateReserva(@RequestBody ReservaDTO reserva)
		throws Exception {
		//try {
		VueloDTO vuelo =
			this._reservaService.getMediator()
				.Send(
					new GetVueloByIdQuery(UUID.fromString(reserva.getVueloId()))
				);
		if (vuelo == null) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST,
				"No existe vuelo"
			);
		}
		UUID reservaId =
			this._reservaService.getMediator()
				.Send(
					new CrearReservaCommand(
						reserva.getNroReserva(),
						reserva.getPasajeroId(),
						vuelo,
						reserva.getPrecio(),
						reserva.getCantidadPasajero()
					)
				);
		if (reservaId == null) {
			throw new ResponseStatusException(
				HttpStatus.CONFLICT,
				"problema al crear la reserva, revise los datos enviados y la disponibilidad del vuelo"
			);
		}
		ReservaDTO reservaDTO = new ReservaDTO(
			reservaId.toString(),
			reserva.getNroReserva(),
			reserva.getPasajeroId(),
			reserva.getVueloId(),
			reserva.getPrecio(),
			reserva.getCantidadPasajero(),
			LocalDateTime.now().toString(),
			reserva.getEstado()
		);
		if (reservaDTO == null) {
			throw new ResponseStatusException(
				HttpStatus.CONFLICT,
				"Problema al cargar la respuesta"
			);
		}

		return reservaDTO;
		/*} catch (Exception e) {
			System.out.println("Excepcion "+e);	
			throw new ResponseStatusException(HttpStatus.CONFLICT, "problema al crear la reserva");
		}*/
	}

	@RequestMapping(value = "/buscar", method = RequestMethod.GET)
	public ReservaDTO FindReservaById(@RequestParam String id) {
		ReservaDTO reservaDto = null;
		try {
			reservaDto =
				this._reservaService.getMediator()
					.Send(new GetReservaByIdQuery(UUID.fromString(id)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reservaDto == null) {
				throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"entity not found"
				);
			}
		}
		return reservaDto;
	}

	@RequestMapping(
		value = "/buscarreservasantiguas",
		method = RequestMethod.GET
	)
	public List<ReservaDTO> FindReservasByHoraAndEstado(
		@RequestParam String hora
	) {
		//String str = "2016-03-04 11:30";
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		//LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

		List<ReservaDTO> listadoDTO = null;
		try {
			listadoDTO =
				this._reservaService.getMediator()
					.Send(new GetReservasByHoraAndEstadoQuery(hora, "CREADA"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (listadoDTO == null) {
				throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"entity not found"
				);
			}
		}
		return listadoDTO;
	}

	@RequestMapping(
		value = "/buscarreservasparavencer",
		method = RequestMethod.GET
	)
	public List<ReservaDTO> FindReservasParaVencer() {
		//String str = "2016-03-04 11:30";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
			"yyyy-MM-dd HH:mm:ss"
		);
		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println("Hora actual " + dateTime.format(formatter));
		dateTime = dateTime.minusHours(2);
		System.out.println("Hora nueva " + dateTime.format(formatter));
		List<ReservaDTO> listadoDTO = null;
		try {
			listadoDTO =
				this._reservaService.getMediator()
					.Send(
						new GetReservasByHoraAndEstadoQuery(
							dateTime.format(formatter),
							Constant.RESERVAESTADOCREADA
						)
					);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (listadoDTO == null) {
				throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"entity not found"
				);
			}
		}
		return listadoDTO;
	}
}
