package com.by.test.serviceribbon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

	@Autowired
	public RestTemplate restTemplate;

	public String hiService(String name) {
		System.out.println("ribbon!!!!!!!!!!!!!!!!!!!!!!!!!");
		return restTemplate.getForObject("http://eurekaClient/hi?name="+name,String.class);
	}
}
