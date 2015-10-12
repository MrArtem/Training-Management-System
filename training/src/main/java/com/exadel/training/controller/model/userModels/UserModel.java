package com.exadel.training.controller.model.userModels;


import com.exadel.training.dao.domain.User;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by ayudovin on 12.10.2015.
 */

public class UserModel {
    private long id;
    private String firstName;
    private String lastname;
    private String phone;
    private String email;
    private Boolean isCoach;

    public UserModel(){
    }
    public UserModel(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastname = user.getLastName();
        this.phone = user.getPhone();
        this.email = user.getEmail();
    }
    public UserModel(User user, Boolean isCoach){
        this(user);

        this.isCoach = isCoach;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
