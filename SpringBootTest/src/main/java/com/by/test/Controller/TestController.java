package com.by.test.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by root on 2018/3/4.
 */
@RestController
public class TestController {
    @GetMapping("/helloworld")
    public String helloworld() {
        return "helloworld";
    }

    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView();
    }

    public static void main(String[] args) {
        System.out.println(System.getProperty("sun.boot.class.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.class.path"));
    }
}
