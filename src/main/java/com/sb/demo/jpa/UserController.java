/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sb.demo.jpa;

import com.sb.demo.mybatis.Organization;
import com.sb.demo.mybatis.OrganizationMapper;
import com.sb.demo.mybatis.UserMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MyPC
 */
@RestController
@RequestMapping(value = {"/user"}, method = {RequestMethod.GET, RequestMethod.POST})
@CrossOrigin
public class UserController {

//    @Autowired
//    private UserService userService;
    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = {"/list", "/listAll"})
    public List<User> getUsers() {
        return userMapper.findAll();
    }

    @RequestMapping(value = {"/save"})
    public void save(User user) {
        userMapper.insert(user);
    }

    public void save(Organization organization) {
        organizationMapper.insert(organization);
    }
}
