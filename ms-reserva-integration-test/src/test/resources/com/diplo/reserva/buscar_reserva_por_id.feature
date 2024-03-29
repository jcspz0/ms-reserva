@tag
Feature: Buscar reserva por id
  Se requiere buscar la reserve por el id

  @tag1
  Scenario Outline: obtener reserva del id
    Given El id de la reserva es "<reserva_id>"
    When Busco la reserva por el id
    Then El numero de reserva obtenido es "<nro_reserva>"
    And el codigo de respuesta es <codigo_resp>

    Examples: 
      | reserva_id                           | nro_reserva       | codigo_resp |
      | e0671023-d3be-4391-bf12-53702f304b21 | 123456						 |         200 |
      | 1b62fe13-ad01-4aca-b3e3-46ecc08b15fa |                   |         404 |
