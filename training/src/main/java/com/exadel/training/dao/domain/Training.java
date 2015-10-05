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

    @NotNull
    private String title;

    private String description;

    @Column(length = 5000)
    private int language;

    private int maxSize;

    private boolean isInner;

    private String excerpt;

    private int sumRating;

    private int countListenerRating;

    private boolean isCanceled;

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

    public Training() {
    }

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

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }


    public int getCountListenerRating() {
        return countListenerRating;
    }

    public void setCountListenerRating(int countListenerRating) {
        this.countListenerRating = countListenerRating;
    }

    public int getSumRating() {
        return sumRating;
    }

    public void setSumRating(int sumRating) {
        this.sumRating = sumRating;
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

    public boolean isCanceled() {
        return isCanceled;
    }

    public void setIsCanceled(boolean isCanceled) {
        this.isCanceled = isCanceled;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }
}
