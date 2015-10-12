package com.exadel.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


/**
 * Created by ayudovin on 01.10.2015.
 */
@EnableAutoConfiguration
@EnableWebMvc
@RestController
@SpringBootApplication
public class Main {

    @RequestMapping("/")
    public String cntr() {
        return "hey";
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
}
