package com.light.springboot.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.light.springboot.dao.mapper.PersonMapper;
import com.light.springboot.model.User;

@Controller
public class TestController {

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
}
