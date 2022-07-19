package com.diplo.webapi.msreserva.controller;

import com.diplo.application.msreserva.dto.pasajero.PasajeroDTO;
import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.mediator.IMediator;
import com.diplo.application.msreserva.usecase.command.reserva.crearpasajero.CrearPasajeroCommand;
import com.diplo.application.msreserva.usecase.command.reserva.crearreserva.CrearReservaCommand;
import com.diplo.application.msreserva.usecase.command.reserva.crearvuelo.CrearVueloCommand;
import com.diplo.application.msreserva.usecase.query.reserva.getReservaById.GetReservaByIdQuery;
import com.diplo.application.msreserva.usecase.query.vuelo.getPasajeroByNroDocAndTipoDoc.GetPasajeroByNroDocAndTipoDocQuery;
import com.diplo.application.msreserva.usecase.query.vuelo.getVuelosByDestino.GetVuelosByDestinoQuery;
import com.diplo.infraestructure.msreserva.memoryrepository.MemoryReservaRepository;
import com.diplo.webapi.msreserva.service.MsReservaWebApiService;
import java.time.LocalDateTime;
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
@RequestMapping(path = "/pasajero")
@CrossOrigin(origins = "*")
public class PasajeroController {

	@Autowired
	MsReservaWebApiService _pasajeroService;

	@RequestMapping(value = "/crear", method = RequestMethod.POST)
	public PasajeroDTO CreatePasajero(@RequestBody PasajeroDTO pasajeroDTO) {
		try {
			System.out.println("invocado");
			UUID pasajeroId =
				this._pasajeroService.getMediator()
					.Send(
						new CrearPasajeroCommand(
							pasajeroDTO.getNroDoc(),
							pasajeroDTO.getTipoDoc(),
							pasajeroDTO.getNombre(),
							pasajeroDTO.getPrimerApellido(),
							pasajeroDTO.getSegundoApellido()
						)
					);
			PasajeroDTO result = new PasajeroDTO(
				pasajeroId.toString(),
				pasajeroDTO.getNroDoc(),
				pasajeroDTO.getTipoDoc(),
				pasajeroDTO.getNombre(),
				pasajeroDTO.getPrimerApellido(),
				pasajeroDTO.getSegundoApellido()
			);
			return result;
		} catch (Exception e) {
			System.out.println("Excepcion " + e);
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST,
				"Ocurrio un error"
			);
		}
	}

	@RequestMapping(value = "/buscarpasajero", method = RequestMethod.GET)
	public PasajeroDTO FindPasajeroByNroDocAndTipoDoc(
		@RequestParam int nrodoc,
		@RequestParam int tipodoc
	) {
		System.out.println("nrodoc:" + nrodoc);
		System.out.println("tipodoc:" + tipodoc);
		PasajeroDTO pasajeroDTO = null;
		try {
			pasajeroDTO =
				this._pasajeroService.getMediator()
					.Send(
						new GetPasajeroByNroDocAndTipoDocQuery(nrodoc, tipodoc)
					);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//if(pasajeroDTO == null) {
			throw new ResponseStatusException(
				HttpStatus.NOT_FOUND,
				"entity not found"
			);
			//}
		}
		return pasajeroDTO;
	}
}
