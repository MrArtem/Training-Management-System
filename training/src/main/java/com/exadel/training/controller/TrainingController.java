package com.exadel.training.controller;

import com.exadel.training.controller.model.*;
import com.exadel.training.dao.domain.Lesson;
import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.service.LessonService;
import com.exadel.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingController {
    @Autowired
    private TrainingService trainingService;
    @Autowired
    private LessonService lessonService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    TrainingModel getTrainingMadel(@PathVariable("id") long trainingId) {
        TrainingModel trainingModel = new TrainingModel();
        Training training = trainingService.getTraining(trainingId);
        trainingModel.setTraining(training);
        trainingModel.setStartDate(lessonService.getStartDateByTraining(trainingId));
        trainingModel.setEndDate(lessonService.getEndDateByTraining(trainingId));
        return trainingModel;
    }

    @RequestMapping(value = "/{id}/lesson_list", method = RequestMethod.GET)
    List<Lesson> getLessonListByTraining(@PathVariable("id") long trainingId) {
        return lessonService.getLessonByTraining(trainingId);
    }

    @RequestMapping(value = "/{id}/listener_list")
    List<ListenerModel> getListenerList(@PathVariable("id") long trainingId) {
        List<Listener> listenerList = trainingService.getListenerListRecord(trainingId);
        List<ListenerModel> listenerModelList = new ArrayList<ListenerModel>();
        for (Listener listener : listenerList) {
            listenerModelList.add(new ListenerModel(listener));
        }
        return listenerModelList;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    void createTraining(@RequestBody AddingTrainingModel addingTrainingModel) {
        trainingService.createTraining(addingTrainingModel.getCoachId()
                , addingTrainingModel.getTitle()
                , addingTrainingModel.getDescription()
                , addingTrainingModel.getShortInfo()
                , addingTrainingModel.getLanguage()
                , addingTrainingModel.getMaxSize()
                , addingTrainingModel.isInner()
                , addingTrainingModel.getTagList()
                , addingTrainingModel.getAdditionalInfo()
                , addingTrainingModel.getIsRepeating()
                , addingTrainingModel.getLessonList()
                , addingTrainingModel.getRepeatModel());

    }

    @RequestMapping(value = "/confirm/{id}", method = RequestMethod.POST)
    void confirmAddTraining(@PathVariable("id") long trainingId, @RequestBody AddingTrainingModel addingTrainingModel) {
        trainingService.confirmTraining(trainingId
                , addingTrainingModel.getTitle()
                , addingTrainingModel.getDescription()
                , addingTrainingModel.getShortInfo()
                , addingTrainingModel.getLanguage()
                , addingTrainingModel.getMaxSize()
                , addingTrainingModel.isInner()
                , addingTrainingModel.getTagList()
                , addingTrainingModel.getAdditionalInfo()
                , addingTrainingModel.getLessonList()
                , addingTrainingModel.getRepeatModel());
    }

    @RequestMapping(value = "cancel_create/{id}", method = RequestMethod.PUT)
    void cancelCreate(@PathVariable("id") Long trainingId) {
        trainingService.cancelCreate(trainingId);
    }

    @RequestMapping(value = "cancel_change/{id}", method = RequestMethod.PUT)
    void cancelChange(@PathVariable("id") Long trainingId) {
        trainingService.cancelChange(trainingId);
    }

    @RequestMapping(value = "/getAdd", method = RequestMethod.GET)
    AddingTrainingModel getAdd() {
        AddingTrainingModel addingTrainingModel = new AddingTrainingModel();
        List<LessonModel> lessonList = new ArrayList<LessonModel>();
        lessonList.add(new LessonModel());
        addingTrainingModel.setLessonList(lessonList);
        RepeatModel repeatModel = new RepeatModel();
        repeatModel.setLessonList(new LessonModel[7]);
        addingTrainingModel.setRepeatModel(repeatModel);
        return addingTrainingModel;
    }
}
