package com.diplo.reserva;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.diplo.application.msreserva.dto.pasajero.PasajeroDTO;
import com.diplo.application.msreserva.dto.reserva.ReservaDTO;
import com.diplo.application.msreserva.dto.vuelo.VueloDTO;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class RealizarReservaWeb {

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
	String origen;

	Mono<List<VueloDTO>> resultadoRestVuelos;
	List<VueloDTO> resultadoListVuelos;
	boolean buscarpasajero;
	boolean vueloEncontrado;
	boolean pasajeroEncontrado;
	boolean reservaCreada;

	private WebDriver driver;

	@AfterEach
	void teardown() {
		driver.quit();
	}

	@Before
	public void setUp() {
		System.setProperty(
			"webdriver.gecko.driver",
			"E:\\jc\\postgrado\\programas\\geckodriver"
		);
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}

	@Given(
		"con el origen {string} y el destino {string}, realizar la reserva al cliente {string} {string} {string} con idenficacion {int}, {int} con el precio de {double} y la cantidad de pasajeros {int}"
	)
	public void obtenerDatosParaWeb(
		String inputOrigen,
		String inputDestino,
		String inputNombre,
		String inputPrimerApellido,
		String inputSegundoApellido,
		int inputNroDoc,
		int inputTipoDoc,
		double inputPrecio,
		int inputCantidadPasajero
	) throws Throwable {
		destino = inputDestino;
		origen = inputOrigen;
		nombre = inputNombre;
		primerApellido = inputPrimerApellido;
		segundoApellido = inputSegundoApellido;
		nroDoc = inputNroDoc;
		tipoDoc = inputTipoDoc;
		precio = inputPrecio;
		cantidadPasajero = inputCantidadPasajero;
		vueloEncontrado = false;
		pasajeroEncontrado = false;
		reservaCreada = false;
		nroReserva = destino + "-" + nroDoc + "-" + nombre;
	}

	@When("buscar vuelos para el destino solicitado en la web")
	public void cargarDatosVueloParaWeb() throws Throwable {
		//driver.get("http://localhost:5000/vuelo");
		driver.get("http://localhost:4200/modulos/reserva/vuelo");
		// Thread.sleep(2000);
		WebElement searchBoxOrigen = driver.findElement(By.id("origen"));
		searchBoxOrigen.sendKeys(origen);
		WebElement searchBoxDestino = driver.findElement(By.id("destino"));
		searchBoxDestino.sendKeys(destino);
		Thread.sleep(2000);
		WebElement botonBuscarVuelo = driver.findElement(
			By.id("botonBuscarVuelo")
		);
		botonBuscarVuelo.click();
		Thread.sleep(5000);
		List<WebElement> tablaVuelos = driver.findElements(
			By.id("tbodyVuelos")
		);
		List<WebElement> filas = tablaVuelos
			.get(0)
			.findElements(By.tagName("tr"));
		WebElement botonSeleccionar;
		WebElement cantidadAsientos;
		for (WebElement fila : filas) {
			cantidadAsientos = fila.findElement(By.id("tablaasientos"));
			if (
				Integer.valueOf(cantidadAsientos.getText()) >= cantidadPasajero
			) {
				botonSeleccionar =
					fila.findElement(By.id("tablabotonseleccionar"));
				botonSeleccionar.click();
				vueloEncontrado = true;
				break;
			}
		}
		Thread.sleep(2000);
	}

	@And("obtener identificador del pasajero en el sistema en la web")
	public void cargarDatosPasajero() throws InterruptedException {
		if (vueloEncontrado) {
			//WebElement ventanapasajero = driver.findElement(By.id("paginapasajero"));
			WebElement ventanapasajero = driver.findElement(
				By.linkText("gestion de pasajero")
			);
			ventanapasajero.click();
			Thread.sleep(2000);
			WebElement buscarnrodoc = driver.findElement(By.id("inputNroDoc"));
			buscarnrodoc.sendKeys(String.valueOf(nroDoc));
			/*Select buscartipodoc = new Select(driver.findElement(By.id("inputbuscartipodoc")));
			buscartipodoc.selectByValue(String.valueOf(tipoDoc));*/
			//---------
			WebElement buscartipodoc = driver.findElement(
				By.id("inputbuscartipodoc")
			);
			buscartipodoc.click();
			//WebElement seleccionartipodoc = driver.findElement(By.id("tipodoc"+tipoDoc));
			WebElement seleccionartipodoc = driver.findElement(
				By.id("nb-option-" + (tipoDoc - 1))
			);
			seleccionartipodoc.click();
			//---------
			WebElement botonbuscarpasajero = driver.findElement(
				By.id("botonbuscarpasajero")
			);
			botonbuscarpasajero.click();
			// ----------
			List<WebElement> tablapasajero = driver.findElements(
				By.id("tbodypasajero")
			);
			List<WebElement> filas = tablapasajero
				.get(0)
				.findElements(By.tagName("tr"));
			WebElement botonSeleccionar;
			for (WebElement fila : filas) {
				if (
					!fila
						.findElements(By.id("tablabotonseleccionarpasajero"))
						.isEmpty()
				) {
					botonSeleccionar =
						fila.findElement(
							By.id("tablabotonseleccionarpasajero")
						);
					botonSeleccionar.click();
					pasajeroEncontrado = true;
					System.out.println("pasajero encontrado");
					break;
				}
			}
			Thread.sleep(2000);
			if (!pasajeroEncontrado) {
				WebElement habilitarcrearpasajero = driver.findElement(
					By.id("habilitarcrearpasajero")
				);
				habilitarcrearpasajero.click();
				Thread.sleep(2000);
				WebElement inputCrearNroDoc = driver.findElement(
					By.id("inputCrearNroDoc")
				);
				inputCrearNroDoc.sendKeys(String.valueOf(nroDoc));
				/*Select inputcreartipodocvalue = new Select(driver.findElement(By.id("inputcreartipodocvalue")));
				inputcreartipodocvalue.selectByValue(String.valueOf(tipoDoc));*/
				//-------------------
				WebElement inputcreartipodocvalue = driver.findElement(
					By.id("inputcreartipodocvalue")
				);
				inputcreartipodocvalue.click();
				//WebElement creartipodoc = driver.findElement(By.id("creartipodoc"+tipoDoc));
				WebElement creartipodoc = driver.findElement(
					By.id("nb-option-" + (tipoDoc + 1))
				);
				creartipodoc.click();
				//-------------------
				WebElement inputCrearNombre = driver.findElement(
					By.id("inputCrearNombre")
				);
				inputCrearNombre.sendKeys(nombre);
				WebElement inputCrearPrimerApellido = driver.findElement(
					By.id("inputCrearPrimerApellido")
				);
				inputCrearPrimerApellido.sendKeys(primerApellido);
				WebElement inputcrearsegundoapellido = driver.findElement(
					By.id("inputCrearSegundoApellido")
				);
				inputcrearsegundoapellido.sendKeys(segundoApellido);
				Thread.sleep(2000);
				WebElement botoncrear = driver.findElement(By.id("botoncrear"));
				botoncrear.click();
				Thread.sleep(5000);
				driver.switchTo().alert().accept();
				// ------------
				Thread.sleep(5000);
				buscarnrodoc.clear();
				buscarnrodoc.sendKeys(String.valueOf(nroDoc));
				/*buscartipodoc.selectByValue(String.valueOf(tipoDoc));*/
				//------------
				buscartipodoc = driver.findElement(By.id("inputbuscartipodoc"));
				buscartipodoc.click();
				//seleccionartipodoc = driver.findElement(By.id("tipodoc"+tipoDoc));
				seleccionartipodoc =
					driver.findElement(By.id("nb-option-" + (tipoDoc - 1)));
				seleccionartipodoc.click();
				//-----------------
				buscartipodoc.click();
				Thread.sleep(2000);
				botonbuscarpasajero.click();
				Thread.sleep(2000);
				tablapasajero = driver.findElements(By.id("tbodypasajero"));
				filas = tablapasajero.get(0).findElements(By.tagName("tr"));
				pasajeroEncontrado = false;
				for (WebElement fila : filas) {
					if (
						!fila
							.findElements(
								By.id("tablabotonseleccionarpasajero")
							)
							.isEmpty()
					) {
						botonSeleccionar =
							fila.findElement(
								By.id("tablabotonseleccionarpasajero")
							);
						botonSeleccionar.click();
						pasajeroEncontrado = true;
						System.out.println("pasajero encontrado");
						break;
					}
				}
				Thread.sleep(2000);
			}
		}
	}

	@And("crear reserva con los datos obtenidos en la web")
	public void crearReservaWeb() throws Exception {
		if (pasajeroEncontrado) {
			//WebElement paginareserva = driver.findElement(By.id("paginareserva"));
			WebElement paginareserva = driver.findElement(
				By.linkText("Registro de reserva")
			);
			paginareserva.click();
			WebElement inputCrearNroReserva = driver.findElement(
				By.id("inputCrearNroReserva")
			);
			inputCrearNroReserva.sendKeys(nroReserva);
			WebElement inputCrearPrecio = driver.findElement(
				By.id("inputCrearPrecio")
			);
			inputCrearPrecio.sendKeys(String.valueOf(precio));
			WebElement inputCrearCantidadPasajero = driver.findElement(
				By.id("inputCrearCantidadPasajero")
			);
			inputCrearCantidadPasajero.sendKeys(
				String.valueOf(cantidadPasajero)
			);
			WebElement botoncrearreserva = driver.findElement(
				By.id("botoncrearreserva")
			);
			Thread.sleep(2000);
			botoncrearreserva.click();
			String popup = waitForAlert();
			driver.switchTo().alert().accept();
			System.out.println(popup);
			if (popup.contains("reserva creada correctamente")) {
				reservaCreada = true;
			}
			Thread.sleep(2000);
		}
	}

	private String waitForAlert() throws Exception {
		String popup = "";
		int i = 0;
		while (i++ < 5) {
			try {
				Alert alert = driver.switchTo().alert();
				popup = alert.getText();
				break;
			} catch (NoAlertPresentException e) {
				Thread.sleep(2000);
				System.out.println("Esperando alerta");
				continue;
			}
		}
		return popup;
	}

	@Then("Se crea la reserva")
	public void validarCreacionReserva() throws Throwable {
		assertTrue(vueloEncontrado);
		assertTrue(pasajeroEncontrado);
		assertTrue(reservaCreada);
		driver.quit();
	}
}
