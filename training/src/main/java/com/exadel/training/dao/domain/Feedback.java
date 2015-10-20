package com.exadel.training.dao.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by ayudovin on 05.10.2015.
 */
@Entity
@Table
public class Feedback {

    @Id
    @GeneratedValue
    private long id;

    private boolean attendance;

    private boolean attitude;

    private boolean commSkills;

    private boolean questions;

    private boolean motivation;

    private boolean focusOnResult;

    private String other;

    @NotNull
    private Date date;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Training training;

    public Feedback(){
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    public boolean isAttitude() {
        return attitude;
    }

    public void setAttitude(boolean attitude) {
        this.attitude = attitude;
    }

    public boolean isCommSkills() {
        return commSkills;
    }

    public void setCommSkills(boolean commSkills) {
        this.commSkills = commSkills;
    }

    public boolean isQuestions() {
        return questions;
    }

    public void setQuestions(boolean questions) {
        this.questions = questions;
    }

    public boolean isMotivation() {
        return motivation;
    }

    public void setMotivation(boolean motivation) {
        this.motivation = motivation;
    }

    public boolean isFocusOnResult() {
        return focusOnResult;
    }

    public void setFocusOnResult(boolean focusOnResult) {
        this.focusOnResult = focusOnResult;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
