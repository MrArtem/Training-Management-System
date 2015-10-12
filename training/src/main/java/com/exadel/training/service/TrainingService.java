package com.exadel.training.service;

import com.exadel.training.controller.model.LessonModel;
import com.exadel.training.controller.model.RepeatModel;
import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Training;

import java.util.List;


public interface TrainingService {

    Training getTraining(long id);

    boolean canRate(long id);

    List<Listener> getListenerListRecord(long trainingId);

    void addTrainingNotRepeat(Long coachId
            , String title
            , String description
            , String shortInfo
            , Integer language
            , Integer maxSize
            , boolean isInner
            , String place
            , String additionalInfo
            , List<LessonModel> lessonModelList);

    void addTrainingRepeat(Long coachId
            , String title
            , String description
            , String shortInfo
            , Integer language
            , Integer maxSize
            , boolean isInner
            , String place
            , String additionalInfo
            , RepeatModel repeatModel);
}
