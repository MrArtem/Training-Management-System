package com.exadel.training.dao.domain;

import org.hibernate.search.annotations.*;
import org.hibernate.search.annotations.Index;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by ayudovin on 05.10.2015.
 */
@Entity
@Table
@Indexed
public class Training {

    @Id
    @GeneratedValue
    private long id;

    @NotNull
    @Field(index = org.hibernate.search.annotations.Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Analyzer(definition = "customAnalyzer")
    private String title;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Analyzer(definition = "customAnalyzer")
    private String description;

    @Column(length = 5000)
    private int language;

    private int maxSize;

    private boolean isInner;

    @Field(index = Index.YES, analyze = Analyze.YES, store = Store.YES)
    @Analyzer(definition = "customAnalyzer")
    private String excerpt;

    private int sumRating;

    private int countListenerRating;

    private boolean isCanceled;

    @IndexedEmbedded
    @ManyToOne(cascade = CascadeType.ALL)
    private User coach;

    @OneToMany(mappedBy = "training")
    private List<Listener> listenerList;

    @OneToMany(mappedBy = "training")
    private List<Lesson> lessonList;

    @OneToMany(mappedBy = "training")
    private List<Comment> commentList;

    @IndexedEmbedded
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Tag> tagList;

    @OneToMany(mappedBy = "training")
    private List<Feedback> feedbackList;

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

    public List<Listener> getListenerList() {
        return listenerList;
    }

    public void setListenerList(List<Listener> listenerList) {
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

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }
}
