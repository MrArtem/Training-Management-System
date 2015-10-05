package com.exadel.training.dao.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by ayudovin on 05.10.2015.
 */
@Entity
@Table
public class Training {

    @Id
    @GeneratedValue
    private long id;

    private String title;

    private String description;

    @Column(length = 5000)
    private int language;

    private boolean isInner;

    private String exept;

    private int sumRaiting;

    private int countListenerRaiting;

    @ManyToOne(cascade = CascadeType.ALL)
    private User coach;

    @ManyToMany(mappedBy = "trainingListListener")
    private List<User> listenerList;

    @OneToMany(mappedBy = "training")
    private List<Lesson> lessonList;

    @OneToMany(mappedBy = "training")
    private List<Comment> commentList;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Tag> tagList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public boolean isInner() {
        return isInner;
    }

    public void setIsInner(boolean isInner) {
        this.isInner = isInner;
    }

    public String getExept() {
        return exept;
    }

    public void setExept(String exept) {
        this.exept = exept;
    }

    public int getSumRaiting() {
        return sumRaiting;
    }

    public void setSumRaiting(int sumRaiting) {
        this.sumRaiting = sumRaiting;
    }

    public int getCountListenerRaiting() {
        return countListenerRaiting;
    }

    public void setCountListenerRaiting(int countListenerRaiting) {
        this.countListenerRaiting = countListenerRaiting;
    }

    public User getCoach() {
        return coach;
    }

    public void setCoach(User coach) {
        this.coach = coach;
    }

    public List<User> getListenerList() {
        return listenerList;
    }

    public void setListenerList(List<User> listenerList) {
        this.listenerList = listenerList;
    }

    public List<Lesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }
}
