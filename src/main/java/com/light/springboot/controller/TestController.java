package com.light.springboot.controller;

import javax.xml.ws.ResponseWrapper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {

    @GetMapping("/login")
    public String helloworld() {
		return"login";
    }
    
    @GetMapping("/helloworld2")
    public String helloworld_2() {
        return "helloworld2";
    }
}
