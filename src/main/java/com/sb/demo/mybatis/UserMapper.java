/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sb.demo.mybatis;

import com.sb.demo.jpa.User;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author MyPC
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user(userName,password，organization） value(#{userName},#{password},#{organization})")
    void insert(User user);

    @Select("SELECT	* FROM org AS a LEFT JOIN org AS b ON a.`parent_id`=b.`id`;")
    public List<User> findAll();
}
