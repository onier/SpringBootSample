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
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author MyPC
 */
@Mapper
public interface OrganizationMapper {

    @Insert("INSERT INTO org(name,description,depth,parent_id,org_path) VALUES(#{name}, #{description}, #{depth}, #{parent_id}, #{org_path})")
//    @SelectKey(statement = "call identity()", keyProperty = "id", before = false, resultType = Long.class)
    void insert(Organization organization);
//    Organization findUserByID(@Param("id") String id);

//    @Select("select * from org")
    @Select("SELECT	a.id,a.`name`,a.`description`,a.`depth`,a.`parent_id`,a.`org_path`,b.`name` AS parentName FROM org AS a LEFT JOIN org AS b ON a.`parent_id`=b.`id`;")
    List<Organization> findAll();

    @Select("SELECT	a.id,a.`name`,a.`description`,a.`depth`,a.`parent_id`,a.`org_path`,b.`name` AS parentName \n"
            + "FROM org AS a LEFT JOIN org AS b ON a.`parent_id`=b.`id`\n"
            + "WHERE a.`id`=#{id};")
    Organization findOrganization(@Param("id") long id);
}
