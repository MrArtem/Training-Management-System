package com.exadel.training.dao.domain;

import javax.persistence.*;

/**
 * Created by ayudovin on 05.10.2015.
 */
@Entity
@Table
public class Attendance {

    @Id
    @GeneratedValue
    private long id;

    private String comment;

    private boolean presence;

    private boolean isSubscribe;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    private Lesson lesson;

    public Attendance(){
        this.presence = true;
    }

    public long getId() {
        return id;
    }

    public boolean isSubscribe() {
        return isSubscribe;
    }

    public void setIsSubscribe(boolean isSubscribe) {
        this.isSubscribe = isSubscribe;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isPresence() {
        return presence;
    }

    public void setPresence(boolean presence) {
        this.presence = presence;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
