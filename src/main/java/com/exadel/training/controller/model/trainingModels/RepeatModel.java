package com.exadel.training.controller.model.trainingModels;


public class RepeatModel {

    private Long startDate;

    private Long endDate;

    private LessonModel[] lessonList;

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public LessonModel[] getLessonList() {
        return lessonList;
    }

    public void setLessonList(LessonModel[] lessonList) {
        this.lessonList = lessonList;
    }
}
