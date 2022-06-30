package com.prabhat.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
/*
 * Topic/Partial Exchange sends message to all the queue based on partial matching of the routing queue
 * 
 */
public class RabbitMQHeadersExchangeConfiguration {

	@Bean
	public Queue queueA() {
		return new Queue("queue.A", true);
	}
	
	@Bean
	public Queue queueB() {
		return new Queue("queue.B", true);
	}
	
	@Bean
	public Queue queueAll() {
		return new Queue("queue.All", true);
	}
	
	@Bean
	public HeadersExchange exchange() {
		return new HeadersExchange("exchange.headers");
	}
	
	@Bean
	public Binding bindingA(Queue queueA, HeadersExchange exchange) {
		return BindingBuilder.bind(queueA)
				.to(exchange)
				.where("color")
				.matches("queueA");
	}
	
	@Bean
	public Binding bindingB(Queue queueB, HeadersExchange exchange) {
		return BindingBuilder.bind(queueB)
				.to(exchange)
				.where("color")
				.matches("queueB");
	}
	
	@Bean
	public Binding bindingAll(Queue queueAll, HeadersExchange exchange) {
		return BindingBuilder.bind(queueAll)
				.to(exchange)
				.where("color")
				.matches("queueAll");
	}
	
	@Bean
	public MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	RabbitTemplate rabbitTemplate(ConnectionFactory factory) {
		RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
		rabbitTemplate.setMessageConverter(messageConverter());
		return rabbitTemplate;
	}
	
}
