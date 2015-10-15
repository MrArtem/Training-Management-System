package com.exadel.training.controller.model.trainingModels;


import java.util.List;

public class AddingTrainingModel {

    private Long coachId;

    private String title;

    private String shortInfo;

    private String description;

    private Integer language;

    private Integer maxSize;

    private String place;

    private String additionalInfo;

    private Boolean isRepeating;

    private List<Long> tagList;

    private List<LessonModel> lessonList;

    private RepeatModel repeatModel;

    private boolean isInner;

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
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

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Boolean getIsRepeating() {
        return isRepeating;
    }

    public void setIsRepeating(Boolean isRepeating) {
        this.isRepeating = isRepeating;
    }

    public List<Long> getTagList() {
        return tagList;
    }

    public void setTagList(List<Long> tagList) {
        this.tagList = tagList;
    }

    public List<LessonModel> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<LessonModel> lessonList) {
        this.lessonList = lessonList;
    }

    public RepeatModel getRepeatModel() {
        return repeatModel;
    }

    public void setRepeatModel(RepeatModel repeatModel) {
        this.repeatModel = repeatModel;
    }

    public boolean isInner() {
        return isInner;
    }

    public void setIsInner(boolean isInner) {
        this.isInner = isInner;
    }
}
