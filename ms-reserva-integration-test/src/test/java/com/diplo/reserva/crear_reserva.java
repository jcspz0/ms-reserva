package com.diplo.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;

import com.diplo.application.msreserva.dto.reserva.ReservaDTO;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import reactor.core.publisher.Mono;

public class crear_reserva {

	private String id;
	WebClient client;
	Mono<ReservaDTO> resultadoRest;
	int estatusCode;
	ReservaDTO request;
	ReservaDTO response;

	String nroReserva;
	String pasajeroId;
	String vueloId;
	double precio;
	int cantidadPasajero;

	@Given("Se crea la reserva con el nro de reserva {string}, pasajero {string}, vuelo {string}, precio de {double} y cantidad de pasajeros {int}")
	public void given(String inputNroReserva, String inputPasajeroId, String inputVueloId, double inputPrecio,
			int inputCantidadPasajero) throws Throwable {
		nroReserva = inputNroReserva;
		pasajeroId = inputPasajeroId;
		vueloId = inputVueloId;
		precio = inputPrecio;
		cantidadPasajero = inputCantidadPasajero;
		
		request = new ReservaDTO(inputNroReserva, inputPasajeroId, inputVueloId, inputPrecio, inputCantidadPasajero, LocalDateTime.now().toString(), "VALIDO");
		
	}

	@When("Crear la reserva con los datos proporcionados")
	public void when() throws Throwable {
		initClientRest();

		resultadoRest = client.post().uri("/reserva/crear")
				.body(Mono.just(request), ReservaDTO.class)
				.retrieve()
				.onStatus(httpStatus -> httpStatus.is4xxClientError(), response -> {
					
					estatusCode = response.statusCode().value();
					System.out.println("codigo de respuiesta "+estatusCode);
					return Mono.error(new HttpClientErrorException(response.statusCode()));
				}).onStatus(httpStatus -> httpStatus.is5xxServerError(), response -> {
					estatusCode = response.statusCode().value();
					System.out.println("codigo de respuiesta "+estatusCode);
					return Mono.error(new HttpServerErrorException(response.statusCode()));
				})
				.bodyToMono(ReservaDTO.class);

			try {
				response = resultadoRest.block();
			} catch (Exception e) {
				System.out.println("Entro en excepcion "+estatusCode);
			}

		

	}

	@Then("Se obtiene una respuesta correcta con codigo {int}")
	public void then(int codigo) throws Throwable {
		assertEquals(codigo, estatusCode);
	}

	private void initClientRest() {
		client = WebClient.builder().baseUrl("http://localhost:8080")
				// .defaultCookie("cookieKey", "cookieValue")
				// .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				// .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
				// .defaultUriVariables(Collections.singletonMap("url",
				// "http://localhost:8080"))
				.build();
		estatusCode = 200;
	}

}
