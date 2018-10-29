package com.light.springboot.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.light.springboot.model.User;

public interface PersonMapper {

    /**
     * 添加操作，返回新增元素的 ID
     *
     * @param User
     */
    @Insert("insert into user(log_id, name, age) values(#{name},#{age})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void insert(User User);

    /**
     * 更新操作
     *
     * @param User
     * @return 受影响的行数
     */
    @Update("update user set name=#{name},age=#{age} where id=#{id}")
    Long update(User User);

    /**
     * 删除操作
     *
     * @param id
     * @return 受影响的行数
     */
    @Delete("delete from user where id=#{id}")
    Long delete(@Param("id") Long id);

    /**
     * 查询所有
     *
     * @return
     */
    @Select("select id,name,age from user")
    List<User> selectAll();

    /**
     * 根据主键查询单个
     *
     * @param id
     * @return
     */
    @Select("select id,name,age from user where id=#{id}")
    User selectById(@Param("id") Long id);
}

