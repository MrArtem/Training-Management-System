package com.exadel.training.controller.model.trainingModels;

public class LessonModel {
    private Long prevLessonId;

    private Long date;

    private String place;

    public LessonModel() {
    }

    public Long getPrevLessonId() {
        return prevLessonId;
    }

    public void setPrevLessonId(Long prevLessonId) {
        this.prevLessonId = prevLessonId;
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
