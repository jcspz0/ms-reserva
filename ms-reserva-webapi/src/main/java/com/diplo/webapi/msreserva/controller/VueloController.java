package com.diplo.webapi.msreserva.controller;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import com.diplo.application.msreserva.usecase.command.reserva.crearpasajero.CrearPasajeroCommand;
import com.diplo.application.msreserva.usecase.command.reserva.crearreserva.CrearReservaCommand;
import com.diplo.application.msreserva.usecase.command.reserva.crearvuelo.CrearVueloCommand;
import com.diplo.application.msreserva.usecase.query.reserva.getReservaById.GetReservaByIdQuery;
import com.diplo.application.msreserva.usecase.query.vuelo.getVuelosByDestino.GetVuelosByDestinoQuery;
import com.diplo.infraestructure.msreserva.memoryrepository.MemoryReservaRepository;
import com.diplo.sharedkernel.mediator.IMediator;
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
@RequestMapping(path = "/vuelo")
@CrossOrigin(origins = "*")
public class VueloController {

	@Autowired
	MsReservaWebApiService _vueloService;

	@RequestMapping(value = "/crear", method = RequestMethod.POST)
	public VueloDTO CreateVuelo(@RequestBody VueloDTO vueloDTO) {
		try {
			System.out.println("destino:" + vueloDTO.getDestino());
			UUID vueloId =
				this._vueloService.getMediator()
					.Send(
						new CrearVueloCommand(
							vueloDTO.getNroVuelo(),
							vueloDTO.getCantidadAsientoDisponible(),
							vueloDTO.getDestino()
						)
					);
			VueloDTO result = new VueloDTO(
				vueloId.toString(),
				vueloDTO.getNroVuelo(),
				vueloDTO.getCantidadAsientoDisponible(),
				vueloDTO.getDestino()
			);
			return result;
		} catch (Exception e) {
			System.out.println("Excepcion " + e);
			return null;
		}
	}

	@RequestMapping(value = "/buscarvuelos", method = RequestMethod.GET)
	public List<VueloDTO> FindVueloByDestino(@RequestParam String destino) {
		List<VueloDTO> listadoDTO = null;
		try {
			listadoDTO =
				this._vueloService.getMediator()
					.Send(new GetVuelosByDestinoQuery(destino));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
