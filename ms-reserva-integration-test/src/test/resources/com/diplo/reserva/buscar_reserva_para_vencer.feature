@tag
Feature: Buscar reserva para vencer
  Se requiere buscar todas las reservas que hayan sido creadas hace mas de dos horas y que estén en un estado VALIDO

  @tag1
  Scenario Outline: obtener reservas creadas hace mas de 2 horas con estado VALIDO
    Given Dada la hora actual de "<horas>"
    When Buscar las reservas creadas mas antiguas de "<horas>"
    Then las reservas obtenidas deben de vencerse "<bandera>"
    And el codigo de respuesta ess <codigo_resp>

    Examples: 
      | horas               | bandera | codigo_resp |
      | 2022-04-25 18:22:41 | true    |         200 |
      | 2023-05-25 18:22:41 | false   |         200 |
