package com.exadel.training;

import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.impl.TrainingDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

/**
 * Created by ayudovin on 01.10.2015.
 */

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@RestController
public class Main {

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);


    }
}
