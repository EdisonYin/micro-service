package com.light.springboot.controller;

import java.util.List;
import java.util.Optional;


import com.light.springboot.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.light.springboot.dao.mapper.PersonMapper;
import com.light.springboot.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PersonMapper personMapper;
    @Autowired
    private UserService userService;
    
    @GetMapping("/search-users")
    public String helloworld() {
		return"login";
    }
    
    @GetMapping("/all-users")
    public List<User> getAllUsers() {
        return userService.selectAll();
    }

    @GetMapping("/optional")
    public String Optional() {
        String str = "test";
        return "ss";
    }
}
