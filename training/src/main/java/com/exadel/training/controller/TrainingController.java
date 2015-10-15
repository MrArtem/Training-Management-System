package com.exadel.training.controller;

import com.exadel.training.controller.model.ListenerModel;
import com.exadel.training.controller.model.TrainingModel;
import com.exadel.training.dao.domain.Lesson;
import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.service.LessonService;
import com.exadel.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TrainingController {
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private LessonService lessonService;

    @RequestMapping(value = "/training/{id}", method = RequestMethod.GET)
    TrainingModel getTrainingMadel(@PathVariable("id") long trainingId) {
        TrainingModel trainingModel = new TrainingModel();
        Training training = trainingService.getTraining(trainingId);
        trainingModel.setTraining(training);
        trainingModel.setStartDate(lessonService.getStartDateByTraining(trainingId));
        trainingModel.setEndDate(lessonService.getEndDateByTraining(trainingId));
        return trainingModel;
    }

    @RequestMapping(value = "/training/{id}/lesson_list", method = RequestMethod.GET)
    List<Lesson> getLessonListByTraining(@PathVariable("id") long trainingId) {
        return lessonService.getLessonByTraining(trainingId);
    }

    @RequestMapping(value = "/training/{id}/listener_list")
    List<ListenerModel> getListenerList(@PathVariable("id") long trainingId) {
        List<Listener> listenerList = trainingService.getListenerListRecord(trainingId);
        List<ListenerModel> listenerModelList = new ArrayList<ListenerModel>();
        for(Listener listener : listenerList) {
            listenerModelList.add(new ListenerModel(listener));
        }
        return listenerModelList;
    }
}
