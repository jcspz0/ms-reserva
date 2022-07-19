package com.diplo.pasajero;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.diplo.application.msreserva.dto.pasajero.PasajeroDTO;
import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Collections;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;
import reactor.core.publisher.Mono;

public class buscar_pasajero_por_datos {

	private int nroDoc;
	private int tipoDoc;
	WebClient client;
	Mono<PasajeroDTO> resultadoRest;
	int estatusCode;
	String nombre;

	@Given(
		"El nro de documento {int} y el tipo de documento {int} del pasajero"
	)
	public void given(int inputNroDoc, int inputTipoDoc) throws Throwable {
		nroDoc = inputNroDoc;
		tipoDoc = inputTipoDoc;
	}

	@When("Busco el pasajero por el numero y tipo de documento")
	public void when() throws Throwable {
		initClientRest();

		resultadoRest =
			client
				.get()
				.uri(
					"/pasajero/buscarpasajero?nrodoc={nroDoc}&tipodoc={tipoDoc}",
					nroDoc,
					tipoDoc
				)
				.retrieve()
				.onStatus(
					httpStatus -> httpStatus.is4xxClientError(),
					response -> {
						estatusCode = response.statusCode().value();
						return Mono.error(
							new HttpClientErrorException(response.statusCode())
						);
					}
				)
				.onStatus(
					httpStatus -> httpStatus.is5xxServerError(),
					response ->
						Mono.error(
							new HttpServerErrorException(response.statusCode())
						)
				)
				.bodyToMono(PasajeroDTO.class);

		try {
			resultadoRest.subscribe(resultado -> {
				nombre = resultado.getNombre();
			});
			resultadoRest.block();
		} catch (Exception e) {}
	}

	@Then("El nombre del pasajero obtenido es {string}")
	public void then(String nombreEsperado) throws Throwable {
		assertEquals(nombreEsperado, nombre);
	}

	@And("el codigo de respuesta es {int} para la obtencion del pasajero")
	public void and(int codigo) throws Throwable {
		assertEquals(codigo, estatusCode);
	}

	private void initClientRest() {
		client =
			WebClient
				.builder()
				.baseUrl("http://localhost:8080")
				//.defaultCookie("cookieKey", "cookieValue")
				//.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				//.defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				//.defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
				.build();
		estatusCode = 200;
	}
}
