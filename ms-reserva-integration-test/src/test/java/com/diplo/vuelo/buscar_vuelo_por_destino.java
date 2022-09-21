package com.diplo.vuelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.diplo.application.msreserva.dto.pasajero.PasajeroDTO;
import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.util.Collections;
import java.util.List;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.RequestBodySpec;
import org.springframework.web.reactive.function.client.WebClient.UriSpec;
import reactor.core.publisher.Mono;

public class buscar_vuelo_por_destino {

	WebClient client;
	Mono<List<VueloDTO>> resultadoRest;
	List<VueloDTO> resultadoList;
	int estatusCode;
	String destino;

	@Given("El destino a buscar es {string}")
	public void given(String inputDestino) throws Throwable {
		destino = inputDestino;
	}

	@When("Busco los vuelos con por el destino")
	public void when() throws Throwable {
		initClientRest();

		resultadoRest =
			client
				.get()
				.uri(
					"/vuelo/buscarvuelos?origen={destino}&destino={destino}",
					destino,
					destino
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
				.bodyToMono(
					new ParameterizedTypeReference<List<VueloDTO>>() {}
				);

		try {
			resultadoRest.subscribe(resultado -> {});
			resultadoList = resultadoRest.block();
		} catch (Exception e) {}
	}

	@Then("Se debe de encontrar al menos un destino {string}")
	public void then(String bandera) throws Throwable {
		if (bandera.equalsIgnoreCase("true")) {
			assertNotEquals(0, resultadoList.size());
		} else {
			assertEquals(0, resultadoList.size());
		}
	}

	@And("el codigo de respuesta es {int} para la obtencion del vuelo")
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
