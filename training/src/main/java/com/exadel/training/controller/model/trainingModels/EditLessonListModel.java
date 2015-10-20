package com.exadel.training.controller.model.trainingModels;

import java.util.List;

/**
 * Created by azapolski on 10/15/2015.
 */
public class EditLessonListModel {

    List<LessonModel> lessonModelList;

    RepeatModel repeatModel;

    public EditLessonListModel() {
    }

    public List<LessonModel> getLessonModelList() {
        return lessonModelList;
    }

    public void setLessonModelList(List<LessonModel> lessonModelList) {
        this.lessonModelList = lessonModelList;
    }

    public RepeatModel getRepeatModel() {
        return repeatModel;
    }

    public void setRepeatModel(RepeatModel repeatModel) {
        this.repeatModel = repeatModel;
    }
}
