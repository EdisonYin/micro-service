package com.light.springboot.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import com.light.springboot.model.User;

public class UserDao {

    private JdbcTemplate jdbcTemplate;
    private final static String MATCH_COUNT_SQL =  "select count(*) from user"
            + " where user_name = ? and password=?";
    private final static String UPDATE_LOGININFO_SQL = "update user set "+
            " last_visit=?,last_ip=?,credits=? where user_id=?";
    @Autowired //自动注入JdbcTemplate的bean
    public void setJdbcTemplatedbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    public int getMatchCount(String userName,String password){
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL,new Object[]{userName,password}, Integer.class).intValue();
    }

    public User findUserByUserName(final String userName){
        String sqlStr = " SELECT user_id,user_name,credits "
                + " FROM t_user WHERE user_name =? ";
        final User user = new User();
        jdbcTemplate.query(sqlStr, new Object[]{userName},
                new RowCallbackHandler() {
                    public void processRow(ResultSet resultSet) throws SQLException {
                        user.setUserID(resultSet.getInt("user_id"));
                        user.setUsername(userName);
                        user.setCredits(resultSet.getInt("credits"));
                    }
                }
        );
        return user;
    }
}
