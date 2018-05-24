/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sb.demo.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sb.demo.mybatis.User;
import com.sb.demo.mybatis.UserMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author MyPC
 */
@Configuration
@EnableWebSecurity
@EnableWebMvc
@ComponentScan
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)
public class DemoSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    UserMapper userMapper;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/global/**");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/hello/**").permitAll()
                .antMatchers("/user/**").authenticated();
        // Custom login form configurer to allow for non-standard HTTP-methods (eg. LOCK)
        CustomFormLoginConfig<HttpSecurity> loginConfig = new CustomFormLoginConfig<HttpSecurity>();
        loginConfig.loginProcessingUrl("/user/authentication")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest hsr, HttpServletResponse hsr1, Authentication a) throws IOException, ServletException {
                        hsr1.setStatus(HttpServletResponse.SC_OK);
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.writeValue(hsr1.getOutputStream(), a.getPrincipal());
                    }
                }).failureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest hsr, HttpServletResponse hsr1, AuthenticationException ae) throws IOException, ServletException {
                hsr1.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }).usernameParameter("j_username")
                .passwordParameter("j_password")
                .permitAll();

        http.apply(loginConfig);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userMapper.findUserByName(username);
                List<SimpleGrantedAuthority> grants = Arrays.asList(new SimpleGrantedAuthority("All"));
                org.springframework.security.core.userdetails.User u = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grants);
                return u;
            }
        });
        auth.authenticationProvider(daoAuthenticationProvider);
        RememberMeAuthenticationProvider rememberMeAuthenticationProvider = new RememberMeAuthenticationProvider("123456");
        auth.authenticationProvider(rememberMeAuthenticationProvider);
    }

}
