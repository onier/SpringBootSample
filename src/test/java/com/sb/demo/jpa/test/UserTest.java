/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sb.demo.jpa.test;

import com.sb.demo.jpa.UserController;
import com.sb.demo.mybatis.Organization;
import com.sb.demo.mybatis.OrganizationMapper;
import com.sb.demo.mybatis.User;
import com.sb.demo.mybatis.UserMapper;
import java.nio.charset.Charset;
import java.util.List;
import java.util.function.Consumer;
import org.apache.commons.lang.builder.ToStringBuilder;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author MyPC
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    UserController userController;
    @Autowired
    OrganizationMapper organizationMapper;
    @Autowired
    UserMapper userMapper;

    @Test
    public void contexLoads() throws Exception {
        System.out.println(Charset.defaultCharset());
        assertThat(userController).isNotNull();
    }

    @Test
    public void addUsers() {
//        System.out.println("addUsers");
//        User user = new User();
//        user.setEmail("282052309@qq.com");
//        user.setOrg_id(15);
//        user.setPassword("password");
//        user.setProfile("profle");
//        user.setUserName("username");
//        userMapper.insert(user);
//        System.out.println(user.getId());
    }

    @Test
    public void testListUsers() {
        List<User> users = userMapper.findAll();
        users.forEach(new Consumer<User>() {
            @Override
            public void accept(User t) {
                System.out.println(ToStringBuilder.reflectionToString(t));
            }
        });
        users = userMapper.findAllUser();
        users.forEach(new Consumer<User>() {
            @Override
            public void accept(User t) {
                System.out.println(ToStringBuilder.reflectionToString(t));
            }
        });
    }

//    @Test
//    public void addOrg() {
//        System.out.println(Charset.defaultCharset());
//        System.out.println("addOrg");
//        for (int i = 0; i < 10; i++) {
//            Organization org = new Organization();
//            org.setDescription("组织描述" + i);
//            org.setName("组织" + i);
//            organizationMapper.insert(org);
//            System.out.println(org.getId());
//        }
//    }
//
//    @Test
//    public void list() {
//        List<Organization> os = organizationMapper.findAll();
//        for (Organization o : os) {
//            System.out.println(ToStringBuilder.reflectionToString(o));
//        }
//    }
//
//    @Test
//    public void testQuery() {
//        System.out.println("testQuery");
//        Organization o = organizationMapper.findOrganization("1");
//        System.out.println(ToStringBuilder.reflectionToString(o));
//        o = organizationMapper.findOrganization("2");
//        System.out.println(ToStringBuilder.reflectionToString(o));
//    }
//
//    @Test
//    public void delete() {
//        organizationMapper.deleteOrganization("13");
//        organizationMapper.deleteOrganization("13");
//    }
//
//    @Test
//    public void testUpdate() {
//        Organization org = organizationMapper.findOrganizationBean("1");
//        org.setName("社会组织");
//        org.setDepth(0);
//        organizationMapper.update(org);
//    }
//
//    @Test
//    public void testfindOrganizationByParentID() {
//        System.out.println("testfindOrganizationByParentID");
//        List<Organization> orgs = organizationMapper.findOrganizationByParentID("1");
//        orgs.forEach(new Consumer<Organization>() {
//            @Override
//            public void accept(Organization t) {
//                System.out.println(ToStringBuilder.reflectionToString(t));
//            }
//        });
//        orgs = organizationMapper.findOrganizationByParentID("11");
//        orgs.forEach(new Consumer<Organization>() {
//            @Override
//            public void accept(Organization t) {
//                System.out.println(ToStringBuilder.reflectionToString(t));
//            }
//        });
//    }
}
