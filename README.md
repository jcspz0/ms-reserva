# MsReservaService

Proyecto de microservicio de reserva

# Pasos para levantar los servicios de reserva

Ejecutar el siguiente comando para crear la imagen
`
docker build -t jcspz0/ms-reserva-service:1.0 -f ./Dockerfile-back .
`
\n
Ejecutar el siguiente comando para iniciar el contenedor
`
 docker container run -p 8080:8080 -d --name ms-reserva-service jcspz0/ms-reserva-service:1.0
`

# Pasos para levantar la web de reserva

Ejecutar el siguiente comando para construir la imagen
`
docker build -t jcspz0/ms-reserva-web:1.0 -f ./Dockerfile-front .
`
Luego ejecutar el siguiente comando para iniciar el contenedor
`
docker container run -p 5000:5000 -d --name ms-reserva-web jcspz0/ms-reserva-web:1.0
`
Para ingresar a la web se debe de ir a la siguiente url
http://localhost:5000

# Pasos para levantar la aplicacion con docker-compose

Para ejecutar como docker-compose solo se debe de ejecutar el siguiente comando
`
docker-compose up
`
Nota: tomar en cuenta que el ms-reserva-service no funcionará hasta que la ms-reserva-db este lista (si no existe la BD aun tardará más)


Agregando gitflow;