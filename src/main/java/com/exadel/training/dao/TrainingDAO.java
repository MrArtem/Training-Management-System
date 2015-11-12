package com.exadel.training.dao;

import com.exadel.training.dao.domain.Lesson;
import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Tag;
import com.exadel.training.dao.domain.Training;

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
