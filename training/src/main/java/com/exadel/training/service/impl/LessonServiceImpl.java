package com.exadel.training.service.impl;

import com.exadel.training.dao.LessonDAO;
import com.exadel.training.dao.domain.Lesson;
import com.exadel.training.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LessonServiceImpl implements LessonService{

    @Autowired
    private LessonDAO lessonDAO;

    @Override
    public List<Lesson> getLessonByTrainingActual(long trainingId) {
        return lessonDAO.getLessonListActualByTraining(trainingId);
    }

    @Override
    public Long getStartDateByTraining(long trainingId) {
        return lessonDAO.getStartDateByTraining(trainingId);
    }

    @Override
    public Long getEndDateByTraining(long trainingId) {
        return lessonDAO.getEndDateByTraining(trainingId);
    }

    @Override
    public Lesson getNextLesson(long trainingId) {
        return lessonDAO.getNextLesson(trainingId);
    }

    @Override
    public Lesson getLessonByID(long lessonId) {
        return lessonDAO.getLessonById(lessonId);
    }

    @Override
    public List<Lesson> getLessonListActual(long startDate, long endDate) {
        return lessonDAO.getLessonListActual(startDate, endDate);
    }
}
