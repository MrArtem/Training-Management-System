package com.exadel.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by ayudovin on 01.10.2015.
 */
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
