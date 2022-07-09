@tag
Feature: Realizar Reserva
	Se requiere realizar una reserva de un vuelo para un cliente

  @tag1
  Scenario Outline: Como operador del sistema quiero crear una reserva con los datos del cliente para entregarle su codigo identificador de reserva
    Given dado el destino "<destino>", realizar la reserva al cliente "<nombre>" "<primer_apellido>" "<segundo_apellido>" con idenficacion <nrodoc>, <tipodoc> con el precio de <precio> y la cantidad de pasajeros <cantidad_pasajeros>
    When buscar vuelos para el destino solicitado
    And obtener identificador del pasajero en el sistema
    And crear reserva con los datos obtenidos
    Then Se crea la reserva y se obtiene el codigo de respuesta <codigo_resp>
		

    Examples: 
      | destino | nombre | primer_apellido | segundo_apellido | nrodoc | tipodoc | precio | cantidad_pasajeros | codigo_resp |
      | santa   | juan   | perez           | pereza           |     10 |       1 |    100 |                  1 |         200 |
