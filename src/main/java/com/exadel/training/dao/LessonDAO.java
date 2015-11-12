package com.exadel.training.dao;

import com.exadel.training.dao.domain.Lesson;

import java.util.List;

public interface LessonDAO {

    void addLesson(Lesson lesson);

    void changeLesson(Lesson lesson);

    void removeLesson(Lesson lesson);

    Lesson getLessonByID(long idLesson);

    List<Lesson> getLessonListActualByTraining(long trainingId);

    Long getStartDateByTraining(long trainingId);

    Long getEndDateByTraining(long trainingId);

    Lesson getNextLesson(long trainingId);

    List<Lesson> getLessonListByTrainingAndState(long trainingId, Lesson.State state);

    List<Lesson> getLessonListActual(long idTraining, long startDate, long endDate);

    List<Lesson> getLessonListActualFrom(long trainingId, long startDate);
}
