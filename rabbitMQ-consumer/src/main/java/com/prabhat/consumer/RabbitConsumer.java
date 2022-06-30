package com.prabhat.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

import com.prabhat.model.Message;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class RabbitConsumer {

//	@RabbitListener(queues = "queue.A")
//	public void receiveMessageFromA(Message message) {
//		log.info("Message received From Queue A -> {}",message);
//	}
//	
//	@RabbitListener(queues = "queue.B")
//	public void receiveMessageFromB(Message message) {
//		log.info("Message received From Queue B -> {}",message);
//	}
//	
//	@RabbitListener(queues = "queue.All")
//	public void receiveMessageFromAllQueue(Message message) {
//		log.info("Message received From Queue All -> {}",message);
//	}
	
	//Below are specific to Headers Excahnge
	@RabbitListener(queues = "queue.A")
	public void receiveMessageFromA(String message) {
		log.info("Message received From Queue A -> {}",message);
	}
	
	@RabbitListener(queues = "queue.B")
	public void receiveMessageFromB(String message) {
		log.info("Message received From Queue B -> {}",message);
	}
	
	@RabbitListener(queues = "queue.All")
	public void receiveMessageFromAllQueue(String message) {
		log.info("Message received From Queue All -> {}",message);
	}
}
