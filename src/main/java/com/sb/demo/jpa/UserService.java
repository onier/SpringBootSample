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

    public List<UserEntity> findAll() {
        List<UserEntity> result = new ArrayList<>();
        userRespority.findAll().forEach(new Consumer<UserEntity>() {
            @Override
            public void accept(UserEntity t) {
                result.add(t);
            }
        });
        return result;
    }

    public void addUser(UserEntity user) {
        userRespority.save(user);
    }

    public void deleteUser(UserEntity user) {
        userRespority.delete(user);
    }

    public UserEntity findUserById(Long id) {
        return userRespority.findById(id).get();
    }
}
