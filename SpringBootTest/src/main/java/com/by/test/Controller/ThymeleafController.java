package com.by.test.Controller;

import com.by.test.entity.Person;
import com.by.test.sender.HelloSender;
import com.by.test.server.WebSocketServer;
import com.by.test.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

/**
 * Created by root on 2018/3/5.
 */
@Controller
@RequestMapping("thymeleaf")
public class ThymeleafController {

    @Autowired
    private PersonService personService;

    @Autowired
    private HelloSender helloSender;

    @RequestMapping("hello")
    public String hello(Map<String,Object> map) {
        map.put("msg", "Hello Thymeleaf");
        return "hello";
    }

    @RequestMapping("webSocket")
    public String webSocket(Map<String,Object> map) {
        return "webSocket";
    }

    @RequestMapping(value = "person",method = RequestMethod.POST)
    @ResponseBody
    public int add(@ModelAttribute("person") Person person){

//        helloSender.send();
        final Set<WebSocketServer> allConn = WebSocketServer.getAllConn();
        return allConn.size();
    }

    @RequestMapping(value = "person/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public int delete(@PathVariable("id") Integer id){
        return personService.delete(id);
    }
}
