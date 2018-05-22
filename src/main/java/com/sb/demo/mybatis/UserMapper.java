/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sb.demo.mybatis;

import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

/**
 *
 * @author MyPC
 */
@Mapper
public interface UserMapper {

    @Insert("INSERT INTO USER(user_name,PASSWORD,email,PROFILE,org_id) VALUES (#{userName},#{password},#{email},#{profile},#{org_id});")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    void insert(User user);

    @Select("SELECT * FROM user;")
    @Results(
            @Result(property = "userName", column = "user_name")
    )
    public List<User> findAll();

    @Select("SELECT * FROM USER LEFT JOIN org ON user.`org_id`=org.`id`;")
    @Results({
        @Result(property = "userName", column = "user_name")
        ,@Result(property = "org_name", column = "name")
    })
    public List<User> findAllUser();

    @Select("SELECT * FROM USER WHERE user_name=#{userName} OR email=#{userName};")
    @Results(
            @Result(property = "userName", column = "user_name")
    )
    User findUserByName(@Param("userName") String userName);
}
