package com.exadel.training.controller.model;

import com.exadel.training.dao.domain.Tag;

import java.util.List;

public class TrainingListModel {

    private Long id;

    private String title;

    private String excerpt;

    private Long nextDate;

    private String nextPlace;

    private Long coachId;

    private String coachName;

    private List<Tag> tagList;

    private Boolean isCoach;

    public TrainingListModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public Long getNextDate() {
        return nextDate;
    }

    public void setNextDate(Long nextDate) {
        this.nextDate = nextDate;
    }

    public String getNextPlace() {
        return nextPlace;
    }

    public void setNextPlace(String nextPlace) {
        this.nextPlace = nextPlace;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public Boolean getIsCoach() {
        return isCoach;
    }

    public void setIsCoach(Boolean isCoach) {
        this.isCoach = isCoach;
    }
}
