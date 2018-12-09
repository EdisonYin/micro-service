package com.light.springboot.controller;

import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.light.springboot.dao.mapper.PersonMapper;
import com.light.springboot.model.User;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("/study")
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PersonMapper personMapper;
    
    @GetMapping("/testlogin")
    public String helloworld() {
		return"login";
    }
    
    @GetMapping("/testhelloworld")
    public List<User> helloworld_2() {
        return personMapper.selectAll();
    }

    @GetMapping("/optional")
    public String Optional() {
       // String str = "test" => logger.error(str);
        return null;
    }
}
