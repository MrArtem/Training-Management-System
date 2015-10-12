package com.exadel.training.configuration;

import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableAutoConfiguration
@EnableWebMvc
@ComponentScan({"com.exadel.training.dao"
        ,"com.exadel.training.service"
        ,"com.exadel.training.controller"})
public class WebApplicationStarter extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplicationStarter.class);
    }

    @Bean
    public SessionFactory sessionFactory(HibernateEntityManagerFactory hemf) {
        return hemf.getSessionFactory();
    }
}
