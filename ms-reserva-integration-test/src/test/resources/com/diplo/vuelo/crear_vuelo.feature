@tag
Feature: Crear vuelo
  Se requiere crear un vuelo

  @tag1
  Scenario Outline: crear vuelo
    Given Se crea el vuelo con el numero de vuelo <nroVuelo>, cantidad de asientos disponibles <asientos> y el destino "<destino>"
    When Crear el vuelo con los datos proporcionados
    Then Se obtiene una respuesta correcta con codigo <codigo_resp> de la creacion del vuelo

    Examples: 
      | nroVuelo | asientos | destino | codigo_resp |
      |     1233 |       10 | santa   |         200 |
