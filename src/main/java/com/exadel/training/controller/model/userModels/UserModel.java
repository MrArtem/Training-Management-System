package com.exadel.training.controller.model.userModels;


import com.exadel.training.dao.domain.User;
import com.exadel.training.validate.annotation.LegalID;

/**
 * Created by ayudovin on 12.10.2015.
 */

public class UserModel {

    private long id;
    private String username;
    private String phone;
    private String email;
    private Boolean isCoach;

    public UserModel() {
    }

    public UserModel(User user) {
        this.id = user.getId();
        this.username =  user.getLastName() + " " + user.getFirstName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
    }

    public UserModel(User user, Boolean isCoach) {
        this(user);

        this.isCoach = isCoach;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsCoach() {
        return isCoach;
    }

    public void setIsCoach(Boolean isCoach) {
        this.isCoach = isCoach;
    }
}
