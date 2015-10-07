package com.exadel.training.controller.model;


import com.exadel.training.dao.domain.Tag;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;

import java.util.List;

public class TrainingModel {

    private long trainingId;

    private String title;

    private String shortInfo;

    private String description;

    private String coachName;

    private long startDate;

    private long endDate;

    private double rating;

    private List<Tag> tagList;

    private boolean canSubscribe;

    private long coachId;

    private int language;

    private boolean isCoach;

    public TrainingModel() {
    }

    public long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(long trainingId) {
        this.trainingId = trainingId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortInfo() {
        return shortInfo;
    }

    public void setShortInfo(String shortInfo) {
        this.shortInfo = shortInfo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public long getStartDate() {
        return startDate;
    }

    public void setStartDate(long startDate) {
        this.startDate = startDate;
    }

    public long getEndDate() {
        return endDate;
    }

    public void setEndDate(long endDate) {
        this.endDate = endDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public boolean isCanSubscribe() {
        return canSubscribe;
    }

    public void setCanSubscribe(boolean canSubscribe) {
        this.canSubscribe = canSubscribe;
    }

    public long getCoachId() {
        return coachId;
    }

    public void setCoachId(long coachId) {
        this.coachId = coachId;
    }

    public int getLanguage() {
        return language;
    }

    public void setLanguage(int language) {
        this.language = language;
    }

    public boolean isCoach() {
        return isCoach;
    }

    public void setIsCoach(boolean isCoach) {
        this.isCoach = isCoach;
    }

    public void setTraining(Training training) {
        title = training.getTitle();
        shortInfo = training.getExcerpt();
        description = training.getDescription();
        if(training.getCountListenerRating() != 0) {
            rating = (double) training.getSumRating() / training.getCountListenerRating();
        } else {
            rating = 0;
        }
        language = training.getLanguage();
        tagList = training.getTagList();
        User coach = training.getCoach();
        coachName = coach.getFirstName() + " " + coach.getLastName();
        coachId = coach.getId();
    }
}
