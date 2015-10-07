package com.exadel.training.service;


import com.exadel.training.dao.domain.Lesson;

import java.util.List;

public interface LessonService {

    List<Lesson> getLessonByTraining(long trainingId);

    long getStartDateByTraining(long trainingId);

    long getEndDateByTraining(long trainingId);
}
