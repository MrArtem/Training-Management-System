package com.exadel.training.service.impl;

import com.exadel.training.dao.LessonDAO;
import com.exadel.training.dao.domain.Lesson;
import com.exadel.training.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService{

    @Autowired
    private LessonDAO lessonDAO;

    @Override
    public List<Lesson> getLessonByTraining(long trainingId) {
        return lessonDAO.getLessonListByTraining(trainingId);
    }

    @Override
    public long getStartDateByTraining(long trainingId) {
        return 0;
    }

    @Override
    public long getEndDateByTraining(long trainingId) {
        return 0;
    }
}
