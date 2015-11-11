package com.exadel.training.dao;

import com.exadel.training.dao.domain.*;

import java.util.List;

public interface TrainingDAO {

    void addTraining(Training training);

    void changeTraining(Training training);

    Training getTrainingById(long id);

    List<Listener> getListenerList(long trainingId);

    List<Lesson> getLessonList(long trainingId);

    List<Training> getTrainingListByTagList(Integer page, Integer pageSize, Boolean isActual, List<Tag> tagList);

    List<Training> getTrainingListForStatistic();
}
