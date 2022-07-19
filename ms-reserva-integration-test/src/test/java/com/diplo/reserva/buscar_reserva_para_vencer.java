package com.diplo.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class buscar_reserva_para_vencer {

	int estatusCode;
	Mono<List<ReservaDTO>> resultadoRest;
	List<ReservaDTO> resultadoList;
	WebClient client;
	String hora;

	@Given("Dada la hora actual de {string}")
	public void given(String inputHora) throws Throwable {
		hora = inputHora;
	}

	@When("Buscar las reservas creadas mas antiguas de {string}")
	public void when(String inputHora) throws Throwable {
		initClientRest();

		resultadoRest =
			client
				.get()
				.uri("/reserva/buscarreservasantiguas?hora={hora}", hora)
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
					new ParameterizedTypeReference<List<ReservaDTO>>() {}
				);

		try {
			resultadoRest.subscribe(resultado -> {});
			resultadoList = resultadoRest.block();
			/*readers.stream()
					  //.map(Reader::getFavouriteBook)
					  .collect(Collectors.toList());*/
			System.out.println("cantidad " + resultadoList.size());
		} catch (Exception e) {}
	}

	@Then("las reservas obtenidas deben de vencerse {string}")
	public void then(String bandera) throws Throwable {
		if (bandera.equalsIgnoreCase("true")) {
			assertNotEquals(0, resultadoList.size());
		} else {
			assertEquals(0, resultadoList.size());
		}
	}

	@And("el codigo de respuesta ess {int}")
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
