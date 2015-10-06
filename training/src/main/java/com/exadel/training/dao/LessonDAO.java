package com.exadel.training.dao;

import com.exadel.training.dao.domain.Lesson;

import java.util.List;

/**
 * Created by azapolski on 10/6/2015.
 */
public interface LessonDAO {

    void addLesson(Lesson lesson);

    void changeLesson(Lesson lesson);

    Lesson getLessonById(long id);

    List<Lesson> getLessonListByTraining(long trainingId);
}