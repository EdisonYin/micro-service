package com.light.springboot.service;

import com.light.springboot.model.User;

import java.util.List;

public interface UserService {

	// User login service
	User login(String login_id, String password);
	
	// Create user
	boolean createUser(User user);

	List<User> selectAll();
}
