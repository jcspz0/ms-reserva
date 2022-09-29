@tag
Feature: Realizar Reserva desde la web
	Se requiere realizar una reserva de un vuelo para un cliente desde la interfaz web

  @tag1
  Scenario Outline: Como operador del sistema quiero crear una reserva con los datos del cliente para entregarle su codigo id de reserva
    Given con el origen "<origen>" y el destino "<destino>", realizar la reserva al cliente "<nombre>" "<primer_apellido>" "<segundo_apellido>" con idenficacion <nrodoc>, <tipodoc> con el precio de <precio> y la cantidad de pasajeros <cantidad_pasajeros>
    When buscar vuelos para el destino solicitado en la web
    And obtener identificador del pasajero en el sistema en la web
    And crear reserva con los datos obtenidos en la web
    Then Se crea la reserva
		

    Examples: 
      | origen	| destino | nombre | primer_apellido | segundo_apellido | nrodoc | tipodoc | precio | cantidad_pasajeros | codigo_resp |
      | lapaz		| santa   | juan   | perez           | pereza           |     956|       1 |    100 |                  1 |         200 |
