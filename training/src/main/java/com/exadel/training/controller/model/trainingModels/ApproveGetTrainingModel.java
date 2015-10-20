package com.exadel.training.controller.model.trainingModels;

import java.util.List;

public class ApproveGetTrainingModel extends TrainingModel {

    private List<LessonModel> lessonModelList;

    private RepeatModel repeatModel;

    private String additionalInfo;

    public List<LessonModel> getLessonModelList() {
        return lessonModelList;
    }

    public void setLessonModelList(List<LessonModel> lessonModelList) {
        this.lessonModelList = lessonModelList;
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
}
