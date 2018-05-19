/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sb.demo.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author MyPC
 */
@Service
public class UserService {

    @Autowired
    private UserRespority userRespority;

    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        userRespority.findAll().forEach(new Consumer<User>() {
            @Override
            public void accept(User t) {
                result.add(t);
            }
        });
        return result;
    }

    public void addUser(User user) {
        userRespority.save(user);
    }

    public void deleteUser(User user) {
        userRespority.delete(user);
    }

    public User findUserById(Long id) {
        return userRespority.findById(id).get();
    }
}
