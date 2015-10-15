package com.exadel.training.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebMvcSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
    @Autowired
    private SimpleUrlAuthenticationSuccessHandler customSuccessHandler;
    @Autowired
    private SimpleUrlAuthenticationFailureHandler customFailureHandler;
    @Autowired
    private AuthenticationEntryPoint customEntryPoint;
    @Autowired
    private UserDetailsService customUserDetailsService;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/training/**").authenticated();
        http.csrf().disable();
        http.formLogin().loginPage("/api/login").permitAll();
        http.formLogin().successHandler(customSuccessHandler);
        http.formLogin().failureHandler(customFailureHandler);
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/api/logout")).logoutSuccessUrl("/login").permitAll();
        http.exceptionHandling().authenticationEntryPoint(customEntryPoint);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(customUserDetailsService)
                .passwordEncoder(encoder);
    }
}
