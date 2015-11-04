package com.exadel.training.configuration;

import com.exadel.training.security.ApplicationSecurity;
import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.hibernate.SessionFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.SocketUtils;

import javax.persistence.EntityManagerFactory;
import java.io.IOException;

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@Import({WebSecurityConfiguration.class, WebSocketConfiguration.class})
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
