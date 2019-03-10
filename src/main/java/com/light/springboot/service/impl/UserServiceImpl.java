package com.light.springboot.service.impl;

import com.light.springboot.dao.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.light.springboot.model.User;
import com.light.springboot.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(String login_id, String password) {
		User user =new User();
		try {
			user = userMapper.login(login_id,password);
			if (user == null) {
				System.out.println("user log in failed");
			}
		} catch(Exception e) {

		} finally {

		}
		return user;
	}

	@Override
	public boolean createUser(User user) {
		return false;
	}

}
