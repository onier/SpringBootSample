/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sb.demo;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author MyPC
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping("/hello")
    @PreAuthorize("hasAnyAuthority('All')")
    public String hello(Authentication authentication) {
        return "Hello!" + authentication.getAuthorities() + "   " + authentication.getAuthorities() + " " + authentication.getName();
    }
}
