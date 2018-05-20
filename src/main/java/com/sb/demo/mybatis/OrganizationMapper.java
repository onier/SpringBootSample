/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sb.demo.mybatis;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectKey;

/**
 *
 * @author MyPC
 */
@Mapper
public interface OrganizationMapper {

    @Insert("INSERT INTO org(name,description) VALUES(#{name}, #{description})")
//    @SelectKey(statement = "call identity()", keyProperty = "id", before = false, resultType = Long.class)
    void insert(Organization organization);
//    Organization findUserByID(@Param("id") String id);
}
