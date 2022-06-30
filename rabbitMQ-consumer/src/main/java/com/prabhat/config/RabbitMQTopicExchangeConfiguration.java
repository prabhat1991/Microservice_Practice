//package com.prabhat.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.amqp.support.converter.MessageConverter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
///*
// * Topic/Partial Exchange sends message to all the queue based on partial matching of the routing queue
// * 
// */
//public class RabbitMQTopicExchangeConfiguration {
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
//	public Queue queueAll() {
//		return new Queue("queue.All", true);
//	}
//	
//	@Bean
//	public TopicExchange exchange() {
//		return new TopicExchange("exchange.topic");
//	}
//	
//	@Bean
//	public Binding bindingA(Queue queueA, TopicExchange exchange) {
//		return BindingBuilder.bind(queueA)
//				.to(exchange)
//				.with("routing.A");
//	}
//	
//	@Bean
//	public Binding bindingB(Queue queueB, TopicExchange exchange) {
//		return BindingBuilder.bind(queueB)
//				.to(exchange)
//				.with("routing.B");
//	}
//	
//	@Bean
//	public Binding bindingAll(Queue queueAll, TopicExchange exchange) {
//		return BindingBuilder.bind(queueAll)
//				.to(exchange)
//				.with("routing.*");
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
