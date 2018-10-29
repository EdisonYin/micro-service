package com.light.springboot.service.impl;

import org.springframework.stereotype.Service;

import com.light.springboot.model.User;
import com.light.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Override
	public User login(String login_id, String password) {
		return null;
	}

	@Override
	public boolean createUser(User user) {
		return false;
	}

}
