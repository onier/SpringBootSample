/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sb.demo.jpa;

import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MyPC
 */
@RestController
@RequestMapping(value = {"/user"}, method = {RequestMethod.GET, RequestMethod.POST})
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/list", "/listAll"})
    public List<User> getUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = {"/save"})
    public void save(User user) {
        System.out.println(ToStringBuilder.reflectionToString(user));
    }
}
