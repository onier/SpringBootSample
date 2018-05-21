package com.sb.demo;

import java.nio.charset.Charset;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        System.out.println(Charset.defaultCharset());
        SpringApplication.run(DemoApplication.class, args);
    }
}
