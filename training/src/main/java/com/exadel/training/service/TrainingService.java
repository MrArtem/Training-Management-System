package com.exadel.training.service;

import com.exadel.training.controller.model.trainingModels.LessonModel;
import com.exadel.training.controller.model.trainingModels.RepeatModel;
import com.exadel.training.dao.domain.*;

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
            , String place
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
            , String place
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
            , String place
            , List<Long> tagIdList
            , String additionalInfo
            , List<LessonModel> lessonModelList
            , RepeatModel repeatModel
    );

    List<Training> getTrainingListByTagList(Integer page, Integer pageSize,Boolean isActual, List<Tag> tagList);

    ApproveAction getApproveAction(long actionId);

    List<ApproveLesson> getApproveLessonList(long actionId);

    RepeatModel getApproveRepeatModel(long actionId);

    void editLesson(long trainingId, LessonModel lessonModel);

    void addLesson(long trainingId, LessonModel lessonModel);

    void removeLesson(long trainingId, LessonModel lessonModel);

    void confirmEditLesson(long actionId, LessonModel lessonModel);
    List<Training> getTrainingListByTagList(Integer page, Integer pageSize, Boolean isActual, List<Tag> tagList);


    double setRating(long trainingId, int rating, long userId);
}
