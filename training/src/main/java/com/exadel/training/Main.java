package com.exadel.training;

import com.exadel.training.dao.LessonDAO;
import com.exadel.training.dao.TrainingDAO;
import com.exadel.training.dao.domain.Lesson;
import com.exadel.training.dao.domain.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ayudovin on 01.10.2015.
 */

@Configuration
@EnableAutoConfiguration
@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
}
