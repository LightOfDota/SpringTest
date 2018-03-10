package com.by.test.servicefeign;

import com.by.test.servicefeign.service.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {

	@Autowired
	public FeignService feignService;

	@RequestMapping(value = "/hi",method = RequestMethod.GET)
	public String sayHi(@RequestParam String name){
		System.out.println("feign!!!!!!!!!!!!!!");
		return feignService.getStr(name);
	}
}
