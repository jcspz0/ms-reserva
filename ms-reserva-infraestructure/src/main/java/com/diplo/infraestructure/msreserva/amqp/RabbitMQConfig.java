package com.diplo.infraestructure.msreserva.amqp;

import java.text.SimpleDateFormat;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class RabbitMQConfig {

	@Value("reserva.reservacreada.pago.creardeuda")
	private String reservaCreadaRoutingkey;

	@Value("reserva.reservaconfirmada.checkin.crearcheckin")
	private String reservaConfirmadaRoutingkey;

	@Value("reserva.deudapagadarollback.pago.deudapagadarollback")
	private String deudaPagadaRollbackRoutingkey;

	@Bean
	Queue reservaCreadaToPago() {
		return new Queue("reserva.reservacreada.pago.creardeuda", true);
	}

	@Bean
	Queue reservaConfirmadaToCheckin() {
		return new Queue(
			"reserva.reservaconfirmada.checkin.crearcheckin",
			true
		);
	}

	@Bean
	Queue deudaPagadaRollbackToPago() {
		return new Queue(
			"reserva.deudapagadarollback.pago.deudapagadarollback",
			true
		);
	}

	@Bean
	TopicExchange reservaCreadaExchange() {
		return new TopicExchange("reserva.reservacreada.exchange");
	}

	@Bean
	TopicExchange reservaConfirmadaExchange() {
		return new TopicExchange("reserva.reservaconfirmada.exchange");
	}

	@Bean
	TopicExchange deudaPagadaRollbackExchange() {
		return new TopicExchange("reserva.deudapagadarollback.exchange");
	}

	@Bean
	Binding reservaCreadaBinding(
		Queue reservaCreadaToPago,
		TopicExchange reservaCreadaExchange
	) {
		return BindingBuilder
			.bind(reservaCreadaToPago)
			.to(reservaCreadaExchange)
			.with(reservaCreadaRoutingkey);
	}

	@Bean
	Binding reservaConfirmadaBinding(
		Queue reservaConfirmadaToCheckin,
		TopicExchange reservaConfirmadaExchange
	) {
		return BindingBuilder
			.bind(reservaConfirmadaToCheckin)
			.to(reservaConfirmadaExchange)
			.with(reservaConfirmadaRoutingkey);
	}

	@Bean
	Binding deudaPagadaRollbackBinding(
		Queue deudaPagadaRollbackToPago,
		TopicExchange deudaPagadaRollbackExchange
	) {
		return BindingBuilder
			.bind(deudaPagadaRollbackToPago)
			.to(deudaPagadaRollbackExchange)
			.with(deudaPagadaRollbackRoutingkey);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public Jackson2JsonMessageConverter converter() {
		return new Jackson2JsonMessageConverter();
	}

	/*
	@Bean
	public Jackson2ObjectMapperBuilder jacksonBuilder() {
	    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
	    builder.indentOutput(true).dateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	    return builder;
	}
*/

	@Bean
	public AmqpTemplate myRabbitTemplate(ConnectionFactory connectionFactory) {
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(
			connectionFactory
		);
		//rabbitTemplate.setMessageConverter(jsonMessageConverter());
		rabbitTemplate.setMessageConverter(converter());
		return rabbitTemplate;
	}

	//---TESTING confirmar pago
	@Value("checkin.chekincreado.pago.confirmarpago")
	private String checkinCreadoRoutingkey;

	@Bean
	Queue checkinCreadoToPago() {
		return new Queue("checkin.chekincreado.pago.confirmarpago", false);
	}

	@Bean
	TopicExchange chekinCreadoExchange() {
		return new TopicExchange("checkin.checkincreado.exchange");
	}

	@Bean
	Binding checkinCreadoBinding(
		Queue checkinCreadoToPago,
		TopicExchange chekinCreadoExchange
	) {
		return BindingBuilder
			.bind(checkinCreadoToPago)
			.to(chekinCreadoExchange)
			.with(checkinCreadoRoutingkey);
	}

	//------------
	@Value(
		"checkin.reservaconfirmadarollback.reserva.reservaconfirmadarollback"
	)
	private String reservaConfirmadaRollbackRoutingkey;

	@Bean
	Queue reservaConfirmadaRollbackToReserva() {
		return new Queue(
			"checkin.reservaconfirmadarollback.reserva.reservaconfirmadarollback",
			false
		);
	}

	@Bean
	TopicExchange reservaConfirmadaRollbackExchange() {
		return new TopicExchange("checkin.reservaconfirmadarollback.exchange");
	}

	@Bean
	Binding reservaConfirmadaRollbackBinding(
		Queue reservaConfirmadaRollbackToReserva,
		TopicExchange reservaConfirmadaRollbackExchange
	) {
		return BindingBuilder
			.bind(reservaConfirmadaRollbackToReserva)
			.to(reservaConfirmadaRollbackExchange)
			.with(reservaConfirmadaRollbackRoutingkey);
	}
	//----------

}
