package com.diplo.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;

import com.diplo.application.msreserva.dto.pasajero.PasajeroDTO;
import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;
import reactor.core.publisher.Mono;

public class realizar_reserva {

	private String id;
	WebClient client;
	Mono<ReservaDTO> resultadoRestReserva;
	int estatusCode;
	ReservaDTO requestReserva;
	ReservaDTO responseReserva;
	PasajeroDTO requestPasajero;
	PasajeroDTO responsePasajero;
	Mono<PasajeroDTO> resultadoRestPasajero;
	Mono<PasajeroDTO> resultadoRestCrearPasajero;
	

	String nroReserva;
	String pasajeroId;
	String vueloId;
	
	double precio;
	int cantidadPasajero;
	String nombre;
	String primerApellido;
	String segundoApellido;
	int nroDoc;
	int tipoDoc;
	String destino;
	
	Mono<List<VueloDTO>> resultadoRestVuelos;
	List<VueloDTO> resultadoListVuelos;
	boolean buscarpasajero;
	

	@Given("dado el destino {string}, realizar la reserva al cliente {string} {string} {string} con idenficacion {int}, {int} con el precio de {double} y la cantidad de pasajeros {int}")
	public void obtenerDatos(String inputDestino, String inputNombre, String inputPrimerApellido, String inputSegundoApellido, int inputNroDoc, int inputTipoDoc, double inputPrecio,	int inputCantidadPasajero) throws Throwable {
		destino = inputDestino;
		nombre = inputNombre;
		primerApellido = inputPrimerApellido;
		segundoApellido = inputSegundoApellido;
		nroDoc = inputNroDoc;
		tipoDoc = inputTipoDoc;
		precio = inputPrecio;
		cantidadPasajero = inputCantidadPasajero;
		
	}

	@When("buscar vuelos para el destino solicitado")
	public void cargarDatosVuelo() throws Throwable {
		initClientRest();
		
		resultadoRestVuelos = client.get()
				.uri("/vuelo/buscarvuelos?destino={destino}",destino)
				.retrieve()
				.onStatus(httpStatus -> httpStatus.is4xxClientError(),
	                    response -> {
	                    	estatusCode = response.statusCode().value();
	                    	return Mono.error(new HttpClientErrorException(response.statusCode()));
	                    } )
	            .onStatus(httpStatus -> httpStatus.is5xxServerError(),
	                    response -> Mono.error(new HttpServerErrorException(response.statusCode())))
				.bodyToMono(new ParameterizedTypeReference<List<VueloDTO>>() {})
				;
		
				try {
					resultadoRestVuelos.subscribe(resultado -> {
						}
					);
					resultadoListVuelos = resultadoRestVuelos.block();
					for (VueloDTO vueloDTO : resultadoListVuelos) {
						if (vueloDTO.getDestino().equalsIgnoreCase(destino)) {
							vueloId = vueloDTO.getVueloId();
							break;
						}
					}
					System.out.println("Se encontro el vuelo "+vueloId);
				} catch (Exception e) {

				}

	}
	
	@And("obtener identificador del pasajero en el sistema")
	public void cargarDatosPasajero() {
		if(!buscarPasajero()) {
			crearPasajero();
		}
	}
	
	private boolean buscarPasajero() {
		initClientRest();
		
		buscarpasajero = false;
		
		resultadoRestPasajero = client.get()
				.uri("/pasajero/buscarpasajero?nrodoc={nroDoc}&tipodoc={tipoDoc}",nroDoc,tipoDoc)
				.retrieve()
				.onStatus(httpStatus -> httpStatus.is4xxClientError(),
	                    response -> {
	                    	estatusCode = response.statusCode().value();
	                    	return Mono.error(new HttpClientErrorException(response.statusCode()));
	                    } )
	            .onStatus(httpStatus -> httpStatus.is5xxServerError(),
	                    response -> Mono.error(new HttpServerErrorException(response.statusCode())))
				.bodyToMono(PasajeroDTO.class)
				;
		
				try {
					resultadoRestPasajero.subscribe(resultado -> {
						pasajeroId = resultado.getPasajeroId();
						responsePasajero = resultado;
						buscarpasajero = true;
						System.out.println("Se encontro el pasajero dentro del response"+pasajeroId);
					}

					);
					resultadoRestPasajero.block();
					System.out.println("Se encontro el pasajero "+pasajeroId);
				} catch (Exception e) {

				}
		return buscarpasajero;
	}
	
	private void crearPasajero() {
		initClientRest();
		requestPasajero = new PasajeroDTO(nroDoc, tipoDoc, nombre, primerApellido, segundoApellido);
		
		resultadoRestCrearPasajero = client.post().uri("/pasajero/crear")
				.body(Mono.just(requestPasajero), PasajeroDTO.class)
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
				.bodyToMono(PasajeroDTO.class);

			try {
				resultadoRestCrearPasajero.subscribe(resultado -> {
					pasajeroId = resultado.getPasajeroId();
					responsePasajero = resultado;
				}

				);
				resultadoRestCrearPasajero.block();
				System.out.println("Se creo el pasajero "+pasajeroId);
			} catch (Exception e) {
				System.out.println("Entro en excepcion "+estatusCode);
			}
	}
	
	@And("crear reserva con los datos obtenidos")
	public void crearReserva() {
		initClientRest();
		requestReserva = new ReservaDTO(nroReserva, pasajeroId, vueloId, precio, cantidadPasajero, null, null);
		
		resultadoRestReserva = client.post().uri("/reserva/crear")
				.body(Mono.just(requestReserva), ReservaDTO.class)
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
				resultadoRestReserva.subscribe(resultado -> {
					id = resultado.getReservaId();
							}
						);
				resultadoRestReserva.block();
				System.out.println("Se creo la reserva "+id + "codigo de respuesta "+estatusCode);
			} catch (Exception e) {
				System.out.println("Entro en excepcion "+estatusCode);
			}
	}

	@Then("Se crea la reserva y se obtiene el codigo de respuesta {int}")
	public void validarRespuesta(int codigo) throws Throwable {
		assertNotNull(id);
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
