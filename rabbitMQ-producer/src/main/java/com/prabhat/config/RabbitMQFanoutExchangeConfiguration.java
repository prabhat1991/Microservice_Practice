//package com.prabhat.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.FanoutExchange;
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
// * Fan-out Exchange routes messages to all the queues binded to the exchange
// * 
// */
//public class RabbitMQFanoutExchangeConfiguration {
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
//	public FanoutExchange exchange() {
//		return new FanoutExchange("exchange.fanout");
//	}
//	
//	@Bean
//	public Binding bindingA(Queue queueA, FanoutExchange exchange) {
//		return BindingBuilder.bind(queueA)
//				.to(exchange);
//	}
//	
//	@Bean
//	public Binding bindingB(Queue queueB, FanoutExchange exchange) {
//		return BindingBuilder.bind(queueB)
//				.to(exchange);
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
