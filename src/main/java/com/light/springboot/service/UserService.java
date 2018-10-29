package com.light.springboot.service;

import com.light.springboot.model.User;

public interface UserService {

	// User login service
	User login(String login_id, String password);
	
	// Create user
	boolean createUser(User user);
}
