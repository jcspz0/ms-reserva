@tag
Feature: Crear pasajero
  Se requiere crear un pasajero

  @tag1
  Scenario Outline: crear pasajero
    Given Se crea el pasajero con el numero de documento <nroDoc>, tipo de documento <tipoDoc>, nombre "<nombre>", apellido paterno "<apellido1>" y apellido materno "<apellido2>"
    When Crear el pasajero con los datos proporcionados
    Then Se obtiene una respuesta correcta con codigo <codigo_resp> de la creacion del pasajero

    Examples: 
      | nroDoc  | tipoDoc | nombre | apellido1 | apellido2 | codigo_resp |
      | 4731768 |       1 | Juan   | Perez     | Pereza    |         200 |
