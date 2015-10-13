package com.exadel.training.dao;

import com.exadel.training.dao.domain.Lesson;

import java.util.List;

public interface LessonDAO {

    void addLesson(Lesson lesson);

    void changeLesson(Lesson lesson);

    void removeLesson(Lesson lesson);

    Lesson getLessonById(long id);

    List<Lesson> getLessonListByTraining(long trainingId);

    Long getStartDateByTraining(long trainingId);

    Long getEndDateByTraining(long trainingId);
}
