package com.exadel.training.dao;

import com.exadel.training.dao.domain.Lesson;
import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;

import java.util.List;

public interface TrainingDAO {

    void addTraining(Training training);

    void changeTraining(Training training);

    Training getTrainingById(long id);

    List<Training> getTrainingListActual(long trainingId);

    List<Listener> getListenerList(long trainingId);

    List<Lesson> getLessonList(long trainingId);
}
