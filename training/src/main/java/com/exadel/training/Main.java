package com.exadel.training;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by ayudovin on 01.10.2015.
 */
@ComponentScan("com.exadel.training.configuration")
public class Main {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
}
