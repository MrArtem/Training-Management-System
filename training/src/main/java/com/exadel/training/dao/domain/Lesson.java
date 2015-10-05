package com.exadel.training.dao.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ayudovin on 05.10.2015.
 */
@Entity
@Table
public class Lesson {
    @Id
    @GeneratedValue
    private long id;

    private Date date;

    private String place;

    @ManyToOne(cascade = CascadeType.ALL)
    private Training training;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }
}
