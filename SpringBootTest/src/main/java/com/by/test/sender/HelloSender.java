package com.by.test.sender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class HelloSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		String context = "hello " + Math.random();
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend("hello1", context);
	}

}