package com.by.test.servicefeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eurekaClient")
public interface FeignService  {

	@RequestMapping(value = "/hi2",method = RequestMethod.GET)
	String getStr(@RequestParam(value = "name")String name);
}
