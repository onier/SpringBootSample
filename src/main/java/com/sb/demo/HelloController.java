/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sb.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MyPC
 */
@RestController
@RequestMapping("/api")
public class HelloController {

    @Value("${server.port}")
    private String port; 

    @RequestMapping("/hello")
//    @PreAuthorize("hasAnyAuthority('All')")
    public String hello(Authentication authentication) {
        return "Hello!" + authentication.getAuthorities() + "   " + authentication.getAuthorities() + " " + authentication.getName()+"  "+port;
    }

    @RequestMapping("/hello2")
    public String hello2(@AuthenticationPrincipal User user) {
        return "Hello1";
    }

    @RequestMapping("/hello1")
    public String hello1() {
        return "Hello1";
    }
}
