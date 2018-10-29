package com.light.springboot.dao.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.light.springboot.model.User;

@Mapper
public interface UserMapper {

    /**
     * Get all user.
     * 
     * @return
     */
    List<User> getUsers();

    /**
     * update user info.
     * 
     * @param user
     */
    void update(User user);

    /**
     * Delete a user.
     * 
     * @param id用户id
     */
    void del(@Param("id")int id);

    /**
     * Add a user.
     * 
     * @param user
     */
    void save(User user);
    
    /**
     * User Login
     * 
     * @param login_id
     * @param password
     */
    User login(String login_id, String password);

}