//package com.prabhat.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
///*
// * Direct Exchange has one-one routingKey-queue mapping in the exchange
// * 
// */
//public class RabbitMQDirectExchangeConfiguration {
//
//	@Bean
//	public Queue queueA() {
//		return new Queue("queue.A", true);
//	}
//	
//	@Bean
//	public Queue queueB() {
//		return new Queue("queue.B", true);
//	}
//	
//	@Bean
//	public DirectExchange exchange() {
//		return new DirectExchange("exchange.direct");
//	}
//	
//	@Bean
//	public Binding bindingA(Queue queueA, DirectExchange exchange) {
//		return BindingBuilder.bind(queueA)
//				.to(exchange)
//				.with("routing.A");
//	}
//	
//	@Bean
//	public Binding bindingB(Queue queueB, DirectExchange exchange) {
//		return BindingBuilder.bind(queueB)
//				.to(exchange)
//				.with("routing.B");
//	}
//	
//	@Bean
//	public MessageConverter messageConverter() {
//		return new Jackson2JsonMessageConverter();
//	}
//	
//	@Bean
//	RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
//		RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
//		rabbitTemplate.setMessageConverter(messageConverter());
//		return rabbitTemplate;
//	}
//	
//}
