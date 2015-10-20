package com.exadel.training.configuration;

import com.exadel.training.security.ApplicationSecurity;
import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@Import(WebSecurityConfiguration.class)
public class WebApplicationStarter extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebApplicationStarter.class);
    }

    @Bean
    public SessionFactory sessionFactory(EntityManagerFactory hemf) {
        return hemf.unwrap(SessionFactory.class);
    }

    @Bean
    public WebSecurityConfigurerAdapter webSecurityConfigurerAdapter() {
        return new ApplicationSecurity();
    }
}
