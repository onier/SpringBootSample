/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sb.demo.util;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 *
 * @author MyPC
 */
public class CommonUtils {

    public static class Status {

        private int code;
        private String message;

        public Status() {
        }

        public Status(int code, String message) {
            this.code = code;
            this.message = message;
        }

        /**
         * @return the code
         */
        public int getCode() {
            return code;
        }

        /**
         * @param code the code to set
         */
        public void setCode(int code) {
            this.code = code;
        }

        /**
         * @return the message
         */
        public String getMessage() {
            return message;
        }

        /**
         * @param message the message to set
         */
        public void setMessage(String message) {
            this.message = message;
        }

    }

    public static Status createResponseStatus(int code, String message) {
        return new Status(code, message);
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        String password = passwordEncoder.encode("123456");
        System.out.println(password);
        System.out.println(passwordEncoder.matches("123456", password));
    }
}
