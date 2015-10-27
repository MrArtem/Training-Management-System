package com.exadel.training.service;


import com.exadel.training.dao.domain.Lesson;

import java.util.List;

public interface LessonService {

    List<Lesson> getLessonByTraining(long trainingId);

    Long getStartDateByTraining(long trainingId);

    Long getEndDateByTraining(long trainingId);

    Lesson getNextLesson(long trainingId);
    Lesson getLessonByID(long lessonId);
}
