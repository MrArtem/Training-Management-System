package com.exadel.training.dao.domain;

import javax.persistence.*;

@Entity
@Table
public class Listener {
    public enum State {
        WAITING, LEAVE, ACCEPTED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Training training;

    @Enumerated(value = EnumType.STRING)
    private State state;

    private boolean canRate;

    public Listener() {
        canRate = true;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean isCanRate() {
        return canRate;
    }

    public void setCanRate(boolean canRate) {
        this.canRate = canRate;
    }
}
