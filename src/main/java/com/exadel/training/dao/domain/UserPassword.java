package com.exadel.training.dao.domain;

import javax.persistence.*;

@Entity
public class UserPassword {

    @Id
    @GeneratedValue
    private Long id;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public UserPassword() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
