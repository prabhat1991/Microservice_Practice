package com.prabhat.controllers;

import javax.websocket.server.PathParam;

import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prabhat.model.Message;

@RestController
public class TestController {
	
	@Autowired
	private RabbitTemplate template;
	
//	@Autowired
//	private DirectExchange exchange;
	
//	@Autowired
//	private FanoutExchange exchange;
	
//	@Autowired
//	private TopicExchange exchange;
	
	@Autowired
	private HeadersExchange exchange;

	@PostMapping("/send")
	public String sendMessage(@RequestBody Message messsgae) {
		for(int i = 0; i<10; i++) {
			template.convertAndSend(exchange.getName(), "routing.A", messsgae);
		}
		return "Message sent succcessfully";
	}
	
	@PostMapping("/sendHeader/{queueName}")
	public String sendHeadersMessage(@PathVariable(value = "queueName") String queueName) {
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setHeader("color", queueName);
		MessageConverter converter = new SimpleMessageConverter();
		org.springframework.amqp.core.Message finalmessage =  converter.toMessage(queueName, messageProperties);
		template.send(exchange.getName(),"", finalmessage);
		
		return "Message sent succcessfully";
	}
}
