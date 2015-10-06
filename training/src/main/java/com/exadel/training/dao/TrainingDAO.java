package com.exadel.training.dao;

import com.exadel.training.dao.domain.Lesson;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;

import java.util.List;

/**
 * Created by azapolski on 10/5/2015.
 */
public interface TrainingDAO {

    void addTraining(Training training);

    void changeTraining(Training training);

    Training getTrainingById(long id);

    List<Training> getTrainingListActual(long trainingId);

    List<User> getListenerList(long trainingId);

    List<Lesson> getLessonList(long trainingId);
}
