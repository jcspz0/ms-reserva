FROM openjdk:18-alpine

RUN mkdir /app
COPY ./target/ms-reserva-webapi-0.0.1-exec.jar /app/ms-reserva-webapi-0.0.1-exec.jar
WORKDIR /app
CMD "java" "-jar" "ms-reserva-webapi-0.0.1-exec.jar"


