@tag
Feature: Buscar vuelos por destino
  Se requiere buscar los vuelos del destino solicitado

  @tag1
  Scenario Outline: obtener vuelos por destino
    Given El destino a buscar es "<destino>"
    When Busco los vuelos con por el destino
    Then Se debe de encontrar al menos un destino "<bandera>"
    And el codigo de respuesta es <codigo_resp> para la obtencion del vuelo

    Examples: 
      | destino | bandera | codigo_resp |
      | santa   | true    |         200 |
      | indigo  | false   |         200 |
