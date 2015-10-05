package com.exadel.training.dao.domain;

import javax.persistence.*;

@Entity
@Table
public class ApproveLesson {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    Lesson lesson;

    private Long date;

    private String place;

    public ApproveLesson() {
    }

    public Long getId() {
        return id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
