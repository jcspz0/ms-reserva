package com.diplo.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

public class buscar_reserva_por_id {

	private String id;
	WebClient client;
	Mono<ReservaDTO> resultadoRest;
	int estatusCode;
	String nro;

	@Given("El id de la reserva es {string}")
	public void given(String reserva_id) throws Throwable {
		id = reserva_id;
	}

	@When("Busco la reserva por el id")
	public void when() throws Throwable {
		initClientRest();

		resultadoRest =
			client
				.get()
				.uri("/reserva/buscar?id={id}", id)
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
				.bodyToMono(ReservaDTO.class);

		try {
			resultadoRest.subscribe(resultado -> {
				nro = resultado.getNroReserva();
			});
			resultadoRest.block();
		} catch (Exception e) {}
	}

	@Then("El numero de reserva obtenido es {string}")
	public void then(String nro_reserva) throws Throwable {
		assertEquals(nro_reserva, nro);
	}

	@And("el codigo de respuesta es {int}")
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
		nro = "";
	}
}
