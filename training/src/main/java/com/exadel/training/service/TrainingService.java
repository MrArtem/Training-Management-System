package com.exadel.training.service;

import com.exadel.training.controller.model.trainingModels.LessonModel;
import com.exadel.training.controller.model.trainingModels.RepeatModel;
import com.exadel.training.dao.domain.ApproveAction;
import com.exadel.training.dao.domain.ApproveLesson;
import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Training;

import java.util.List;


public interface TrainingService {

    Training getTraining(long id);

    boolean canRate(long id);

    List<Listener> getListenerListRecord(long trainingId);

    void createTraining(Long coachId
            , String title
            , String description
            , String shortInfo
            , Integer language
            , Integer maxSize
            , boolean isInner
            , List<Long> tagIdList
            , String additionalInfo
            , boolean isRepeating
            , List<LessonModel> lessonModelList
            , RepeatModel repeatModel
    );

    void confirmTraining(Long actionId
            , String title
            , String description
            , String shortInfo
            , Integer language
            , Integer maxSize
            , boolean isInner
            , List<Long> tagIdList
            , List<LessonModel> lessonModelList
            , RepeatModel repeatModel
    );

    void cancelCreate(Long actionId);

    void cancelChange(Long actionId);

    void editTraining(Long trainingId
            , String title
            , String description
            , String shortInfo
            , Integer language
            , Integer maxSize
            , boolean isInner
            , List<Long> tagIdList
            , String additionalInfo
            , List<LessonModel> lessonModelList
            , RepeatModel repeatModel
    );

    List<Training> getTrainingListByTagList(Integer page, Integer pageSize,Boolean isActual, List<Tag> tagList);

    ApproveAction getApproveAction(long actionId);

    List<ApproveLesson> getApproveLessonList(long actionId);

    RepeatModel getApproveRepeatModel(long actionId);
}
