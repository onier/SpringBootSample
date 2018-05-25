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
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

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
@PropertySources({
    @PropertySource("classpath:/sys.properties")
})
public class DemoSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    UserMapper userMapper;
    @Autowired
    private Environment env;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/global/**");
    }

    public FormLoginConfigurer<HttpSecurity> loginConfig() {
        FormLoginConfigurer<HttpSecurity> loginConfig = new FormLoginConfigurer<HttpSecurity>();
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
        return loginConfig;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JdbcTokenRepositoryImpl jtri = new JdbcTokenRepositoryImpl();
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("mysql.dirver"));
        dataSource.setUrl(env.getProperty("mysql.jdbcUrl"));
        dataSource.setUsername(env.getProperty("mysql.username"));
        dataSource.setPassword(env.getProperty("mysql.password"));
        jtri.setDataSource(dataSource);
        //创建tooken表
//        jtri.getJdbcTemplate().execute(CREATE_TABLE_SQL);
        http.csrf().disable().authorizeRequests().antMatchers("/user/authentication").permitAll()//开放登录接口
                .and()
                .formLogin().loginProcessingUrl("/user/authentication").successHandler(new AuthenticationSuccessHandler() {
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
        })
                .and()
                .rememberMe().rememberMeParameter("rememberMe").tokenRepository(jtri)
                .and()
                .authorizeRequests().anyRequest().authenticated();//登录之外的接口全部要求验证

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userMapper.findUserByName(username);
                List<SimpleGrantedAuthority> grants = Arrays.asList(new SimpleGrantedAuthority("All"));
                org.springframework.security.core.userdetails.User u = new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grants);
                return u;
            }
        });
    }

}
