@tag
Feature: Buscar pasajero por id
  Se requiere buscar el pasajero por el id

  @tag1
  Scenario Outline: obtener pasajero con los datos proporcionados
    Given El nro de documento <nroDoc> y el tipo de documento <tipoDoc> del pasajero
    When Busco el pasajero por el numero y tipo de documento
    Then El nombre del pasajero obtenido es "<nombre>"
    And el codigo de respuesta es <codigo_resp> para la obtencion del pasajero

    Examples: 
      | nroDoc | tipoDoc | nombre | codigo_resp |
      |      1 |       1 | Juan   |         200 |
