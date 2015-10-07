package com.exadel.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by ayudovin on 01.10.2015.
 */

@EnableAutoConfiguration
@RestController
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);


    }
}
