/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sb.demo.jpa.test;

import com.sb.demo.jpa.User;
import com.sb.demo.jpa.UserController;
import com.sb.demo.mybatis.Organization;
import com.sb.demo.mybatis.OrganizationMapper;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    @Test
    public void contexLoads() throws Exception {
        System.out.println(Charset.defaultCharset());
        assertThat(userController).isNotNull();
    }

    @Test
    public void addUsers() {
        System.out.println("addUsers");
    }

//    @Test
//    public void addOrg() {
//        System.out.println(Charset.defaultCharset());
//        System.out.println("addOrg");
//        for (int i = 0; i < 10; i++) {
//            Organization org = new Organization();
//            org.setDescription("组织描述" + i);
//            org.setName("组织" + i);
//            organizationMapper.insert(org);;
//        }
//    }
    @Test
    public void list() {
        List<Organization> os = organizationMapper.findAll();
        for (Organization o : os) {
            System.out.println(ToStringBuilder.reflectionToString(o));
        }
    }

    @Test
    public void testQuery() {
        Organization o = organizationMapper.findOrganization(1);
        System.out.println(ToStringBuilder.reflectionToString(o));
        o = organizationMapper.findOrganization(2);
        System.out.println(ToStringBuilder.reflectionToString(o));
    }
}
