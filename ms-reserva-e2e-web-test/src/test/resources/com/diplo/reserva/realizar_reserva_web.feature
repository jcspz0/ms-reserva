@tag
Feature: Realizar Reserva desde la web
	Se requiere realizar una reserva de un vuelo para un cliente desde la interfaz web

  @tag1
  Scenario Outline: Como operador del sistema quiero crear una reserva con los datos del cliente para entregarle su codigo id de reserva
    Given con el destino "<destino>", realizar la reserva al cliente "<nombre>" "<primer_apellido>" "<segundo_apellido>" con idenficacion <nrodoc>, <tipodoc> con el precio de <precio> y la cantidad de pasajeros <cantidad_pasajeros>
    When buscar vuelos para el destino solicitado en la web
    And obtener identificador del pasajero en el sistema en la web
    And crear reserva con los datos obtenidos en la web
    Then Se crea la reserva
		

    Examples: 
      | destino | nombre | primer_apellido | segundo_apellido | nrodoc | tipodoc | precio | cantidad_pasajeros | codigo_resp |
      | santa   | juan   | perez           | pereza           |     1	 |       1 |    100 |                  1 |         200 |
