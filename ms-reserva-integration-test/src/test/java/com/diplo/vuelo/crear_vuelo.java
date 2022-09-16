package com.diplo.vuelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.diplo.application.msreserva.dto.pasajero.PasajeroDTO;
import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.LocalDateTime;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class crear_vuelo {

	WebClient client;
	Mono<VueloDTO> resultadoRest;
	int estatusCode;
	VueloDTO request;
	VueloDTO response;

	String nroVuelo;
	int asientos;
	String destino;

	@Given(
		"Se crea el vuelo con el numero de vuelo {string}, cantidad de asientos disponibles {int} y el destino {string}"
	)
	public void given(
		String inputNroVuelo,
		int inputAsientos,
		String inputDestino
	) throws Throwable {
		nroVuelo = inputNroVuelo;
		asientos = inputAsientos;
		destino = inputDestino;

		request =
			new VueloDTO(
				inputNroVuelo,
				inputAsientos,
				inputDestino,
				inputDestino
			);
	}

	@When("Crear el vuelo con los datos proporcionados")
	public void when() throws Throwable {
		initClientRest();

		resultadoRest =
			client
				.post()
				.uri("/vuelo/crear")
				.body(Mono.just(request), VueloDTO.class)
				.retrieve()
				.onStatus(
					httpStatus -> httpStatus.is4xxClientError(),
					response -> {
						estatusCode = response.statusCode().value();
						System.out.println(
							"codigo de respuiesta " + estatusCode
						);
						return Mono.error(
							new HttpClientErrorException(response.statusCode())
						);
					}
				)
				.onStatus(
					httpStatus -> httpStatus.is5xxServerError(),
					response -> {
						estatusCode = response.statusCode().value();
						System.out.println(
							"codigo de respuiesta " + estatusCode
						);
						return Mono.error(
							new HttpServerErrorException(response.statusCode())
						);
					}
				)
				.bodyToMono(VueloDTO.class);

		try {
			response = resultadoRest.block();
		} catch (Exception e) {
			System.out.println("Entro en excepcion " + estatusCode);
		}
	}

	@Then(
		"Se obtiene una respuesta correcta con codigo {int} de la creacion del vuelo"
	)
	public void then(int codigo) throws Throwable {
		assertEquals(codigo, estatusCode);
	}

	private void initClientRest() {
		client =
			WebClient
				.builder()
				.baseUrl("http://localhost:8080")
				// .defaultCookie("cookieKey", "cookieValue")
				// .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				// .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				// .defaultUriVariables(Collections.singletonMap("url",
				// "http://localhost:8080"))
				.build();
		estatusCode = 200;
	}
}
