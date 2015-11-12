package com.exadel.training.controller.model.trainingModels;

import java.util.List;

public class ApproveGetTrainingModel extends TrainingModel {

    private List<LessonModel> lessonList;

    private RepeatModel repeatModel;

    private String additionalInfo;

    private Boolean isInner;

    public List<LessonModel> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<LessonModel> lessonList) {
        this.lessonList = lessonList;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public RepeatModel getRepeatModel() {
        return repeatModel;
    }

    public void setRepeatModel(RepeatModel repeatModel) {
        this.repeatModel = repeatModel;
    }

    public Boolean getIsInner() {
        return isInner;
    }

    public void setIsInner(Boolean isInner) {
        this.isInner = isInner;
    }
}
