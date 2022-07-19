package com.diplo.pasajero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.diplo.application.msreserva.dto.pasajero.PasajeroDTO;
import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.LocalDateTime;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class crear_pasajero {

	WebClient client;
	Mono<PasajeroDTO> resultadoRest;
	int estatusCode;
	PasajeroDTO request;
	PasajeroDTO response;

	int nroDoc;
	int tipoDoc;
	String nombre;
	String primerApellido;
	String segundoApellido;

	@Given(
		"Se crea el pasajero con el numero de documento {int}, tipo de documento {int}, nombre {string}, apellido paterno {string} y apellido materno {string}"
	)
	public void given(
		int inputNroDoc,
		int inputTipoDoc,
		String inputNombre,
		String inputPrimerApellido,
		String inputSegundoApellido
	) throws Throwable {
		nroDoc = inputNroDoc;
		tipoDoc = inputTipoDoc;
		nombre = inputNombre;
		primerApellido = inputPrimerApellido;
		segundoApellido = inputSegundoApellido;

		request =
			new PasajeroDTO(
				nroDoc,
				tipoDoc,
				nombre,
				primerApellido,
				segundoApellido
			);
	}

	@When("Crear el pasajero con los datos proporcionados")
	public void when() throws Throwable {
		initClientRest();

		resultadoRest =
			client
				.post()
				.uri("/pasajero/crear")
				.body(Mono.just(request), PasajeroDTO.class)
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
				.bodyToMono(PasajeroDTO.class);

		try {
			response = resultadoRest.block();
		} catch (Exception e) {
			System.out.println("Entro en excepcion " + estatusCode);
		}
	}

	@Then(
		"Se obtiene una respuesta correcta con codigo {int} de la creacion del pasajero"
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
