package com.exadel.training.controller;

import com.exadel.training.controller.model.AddingTrainingModel;
import com.exadel.training.controller.model.ListenerModel;
import com.exadel.training.controller.model.TrainingModel;
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
        for(Listener listener : listenerList) {
            listenerModelList.add(new ListenerModel(listener));
        }
        return listenerModelList;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    void addTraining(@RequestBody AddingTrainingModel addingTrainingModel) {
        if(addingTrainingModel.getIsRepeating()) {
            trainingService.addTrainingRepeat(addingTrainingModel.getCoachId()
                    ,addingTrainingModel.getTitle()
                    ,addingTrainingModel.getDescription()
                    ,addingTrainingModel.getShortInfo()
                    ,addingTrainingModel.getLanguage()
                    ,addingTrainingModel.getMaxSize()
                    ,addingTrainingModel.getPlace()
                    ,addingTrainingModel.getAdditionalInfo()
                    ,addingTrainingModel.getRepeatModel());
        } else {
            trainingService.addTrainingNotRepeat(addingTrainingModel.getCoachId()
                    , addingTrainingModel.getTitle()
                    , addingTrainingModel.getDescription()
                    , addingTrainingModel.getShortInfo()
                    , addingTrainingModel.getLanguage()
                    , addingTrainingModel.getMaxSize()
                    , addingTrainingModel.getPlace()
                    , addingTrainingModel.getAdditionalInfo()
                    , addingTrainingModel.getLessonList());
        }
    }

    @RequestMapping(value = "/confirm_add/{id}", method = RequestMethod.POST)
    void confirmAddTraining(@PathVariable("id") long trainingId,@RequestBody AddingTrainingModel addingTrainingModel) {
        
    }
}
