@tag
Feature: Crear reserva
  Se requiere crear una reserva

  @tag1
  Scenario Outline: crear reserva
    Given Se crea la reserva con el nro de reserva "<nroReserva>", pasajero "<pasajeroId>", vuelo "<vueloId>", precio de <precio> y cantidad de pasajeros <cantidadPasajero>
    When Crear la reserva con los datos proporcionados
    Then Se obtiene una respuesta correcta con codigo <codigo_resp>

    Examples: 
      | nroReserva | pasajeroId                           | vueloId                              | precio | cantidadPasajero | codigo_resp |
      |        123 | 7b777a32-4d9e-4ecb-bd31-faa7902b0994 | 4f67286f-cf1f-4b0d-bab1-2a17d61d91bb |    100 |                1 |         200 |
      |        123 | 7b777a32-4d9e-4ecb-bd31-faa7902b0994 | 7b83c7db-4ce1-436d-825f-396e8a1cb261 |    100 |                1 |         409 |
      |        123 | 7b777a32-4d9e-4ecb-bd31-faa7902b0994 | 7b83c7db-4ce1-436d-825f-999999999999 |    100 |                1 |         400 |
