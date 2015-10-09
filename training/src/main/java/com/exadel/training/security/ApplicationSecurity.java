package com.exadel.training.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@ComponentScan("com.exadel.training.security")
public class ApplicationSecurity extends WebSecurityConfigurerAdapter{
    @Autowired
    private CustomSuccessHandler customSuccessHandler;
    @Autowired
    private CustomFailureHandler customFailureHandler;
    @Autowired
    private CustomEntryPoint customEntryPoint;

    private PasswordEncoder encoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        if(encoder == null) {
            encoder = new BCryptPasswordEncoder();
        }
        return encoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/training/**").authenticated();
        http.csrf().disable();
        http.formLogin().loginPage("/login").permitAll();
        http.formLogin().successHandler(customSuccessHandler);
        http.formLogin().failureHandler(customFailureHandler);
        http.exceptionHandling().authenticationEntryPoint(customEntryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.inMemoryAuthentication().withUser("user").password("user").roles("USER").and().withUser("admin")
                .password("admin").roles("ADMIN");
        builder.use
    }
}
