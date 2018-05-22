/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sb.demo.mybatis;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;

/**
 *
 * @author MyPC
 */
@Mapper
public interface OrganizationMapper {

    /**
     * 插入新数据
     *
     * @param organization
     */
    @Insert("INSERT INTO org(name,description,depth,parent_id,org_path) VALUES(#{name}, #{description}, #{depth}, #{parent_id}, #{org_path})")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "id", before = false, resultType = int.class)
    void insert(Organization organization);

    @Update("UPDATE org AS o SET  o.`name`=#{name},o.`description`=#{description},o.`depth`=#{depth},o.`parent_id`=#{parent_id},o.`org_path`=#{org_path} WHERE o.`id`=#{id};")
    void update(Organization organization);

    /**
     * 查询所有数据，并将parentName组合起来
     *
     * @return
     */
//    @Select("select * from org")
    @Select("SELECT	a.id,a.`name`,a.`description`,a.`depth`,a.`parent_id`,a.`org_path`,b.`name` AS parentName FROM org AS a LEFT JOIN org AS b ON a.`parent_id`=b.`id`;")
    List<Organization> findAll();

    /**
     * 查询单数据，并将parentName组合起来
     *
     * @param id
     * @return
     */
    @Select("SELECT	a.id,a.`name`,a.`description`,a.`depth`,a.`parent_id`,a.`org_path`,b.`name` AS parentName \n"
            + "FROM org AS a LEFT JOIN org AS b ON a.`parent_id`=b.`id`\n"
            + "WHERE a.`id`=#{id};")
    Organization findOrganization(@Param("id") String id);

    /**
     * 删除数据
     *
     * @param id
     */
    @Delete("DELETE parent FROM org AS parent LEFT JOIN org AS child ON parent.`id`=child.`parent_id` \n"
            + "WHERE parent.`id`=#{id} AND child.`parent_id` IS NULL ;")
    void deleteOrganization(@Param("id") String id);

    /**
     * 查找子组织
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM org WHERE org.`parent_id`=#{id}")
    List<Organization> findOrganizationByParentID(@Param("id") String id);

    @Select("SELECT * FROM org WHERE id=#{id};")
    Organization findOrganizationBean(@Param("id") String id);
}
