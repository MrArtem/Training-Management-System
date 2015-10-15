package com.exadel.training.security.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {
    private Long userId;
    private String firstName;
    private String secondName;

    public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Long userId, String firstName, String secondName) {
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
