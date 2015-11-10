package com.exadel.training.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomAuthentication extends UsernamePasswordAuthenticationToken implements Authentication {
    private Long userId;
    private String firstName;
    private String secondName;

    public CustomAuthentication(String username, String password,
                                Collection<? extends GrantedAuthority> authorities,
                                String firstName, String secondName, Long userId) {
        super(username, password, authorities);
        this.userId = userId;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
}
