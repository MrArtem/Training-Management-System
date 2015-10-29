package com.exadel.training.controller;

import com.exadel.training.controller.model.trainingModels.AddingTrainingModel;
import com.exadel.training.controller.model.trainingModels.ApproveGetTrainingModel;
import com.exadel.training.controller.model.trainingModels.LessonModel;
import com.exadel.training.dao.domain.*;
import com.exadel.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingCRUDController {
    @Autowired
    private TrainingService trainingService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    void createTraining(@RequestBody AddingTrainingModel addingTrainingModel) {
        trainingService.createTraining(addingTrainingModel.getCoachId()
                , addingTrainingModel.getTitle()
                , addingTrainingModel.getDescription()
                , addingTrainingModel.getShortInfo()
                , addingTrainingModel.getLanguage()
                , addingTrainingModel.getMaxSize()
                , addingTrainingModel.isInner()
                , addingTrainingModel.getPlace()
                , addingTrainingModel.getTagList()
                , addingTrainingModel.getAdditionalInfo()
                , addingTrainingModel.getIsRepeating()
                , addingTrainingModel.getLessonList()
                , addingTrainingModel.getRepeatModel());

    }

    @RequestMapping(value = "/confirm/{id}", method = RequestMethod.POST)
    void confirmAddTraining(@PathVariable("id") long actionId, @RequestBody AddingTrainingModel addingTrainingModel) {
        trainingService.confirmTraining(actionId
                , addingTrainingModel.getTitle()
                , addingTrainingModel.getDescription()
                , addingTrainingModel.getShortInfo()
                , addingTrainingModel.getLanguage()
                , addingTrainingModel.getMaxSize()
                , addingTrainingModel.isInner()
                , addingTrainingModel.getPlace()
                , addingTrainingModel.getTagList()
                , addingTrainingModel.getLessonList()
                , addingTrainingModel.getRepeatModel());
    }

    @RequestMapping(value = "cancel_create/{id}", method = RequestMethod.PUT)
    void cancelCreate(@PathVariable("id") Long actionId) {
        trainingService.cancelCreate(actionId);
    }

    @RequestMapping(value = "cancel_change/{id}", method = RequestMethod.PUT)
    void cancelChange(@PathVariable("id") Long actionId) {
        trainingService.cancelChange(actionId);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    void editTraining(@PathVariable("id") long trainingId, @RequestBody AddingTrainingModel addingTrainingModel) {
        trainingService.editTraining(trainingId
                , addingTrainingModel.getTitle()
                , addingTrainingModel.getDescription()
                , addingTrainingModel.getShortInfo()
                , addingTrainingModel.getLanguage()
                , addingTrainingModel.getMaxSize()
                , addingTrainingModel.isInner()
                , addingTrainingModel.getPlace()
                , addingTrainingModel.getTagList()
                , addingTrainingModel.getAdditionalInfo()
                , addingTrainingModel.getLessonList()
                , addingTrainingModel.getRepeatModel());
    }

    @RequestMapping(value = "/getApproveTraining/{id}", method = RequestMethod.GET)
    public ApproveGetTrainingModel getApproveTrainingModel(@PathVariable("id") long actionId) {
        ApproveAction approveAction = trainingService.getApproveAction(actionId);
        ApproveTraining approveTraining = approveAction.getApproveTraining();
        ApproveGetTrainingModel approveTrainingModel = new ApproveGetTrainingModel();
        Training training = approveAction.getTraining();
        User coach = training.getCoach();
        //TODO code review
        if (approveTraining != null) {
            approveTrainingModel.setTitle(approveTraining.getTitle());
            approveTrainingModel.setDescription(approveTraining.getDescription());
            approveTrainingModel.setAdditionalInfo(approveTraining.getAdditionalInfo());
            approveTrainingModel.setMaxSize(approveTraining.getMaxSize());
            approveTrainingModel.setTagList(approveTraining.getTagList());
            approveTrainingModel.setShortInfo(approveTraining.getExcerpt());
            approveTrainingModel.setTagList(approveTraining.getTagList());
            approveTrainingModel.setCoachId(coach.getId());
            approveTrainingModel.setCoachName(coach.getFirstName() + " " + coach.getLastName());
        } else {
            approveTrainingModel.setTitle(training.getTitle());
            approveTrainingModel.setDescription(training.getDescription());
            approveTrainingModel.setMaxSize(training.getMaxSize());
            approveTrainingModel.setTagList(training.getTagList());
            approveTrainingModel.setShortInfo(training.getExcerpt());
            approveTrainingModel.setCoachId(coach.getId());
            approveTrainingModel.setCoachName(coach.getFirstName() + " " + coach.getLastName());
            approveTrainingModel.setTagList(training.getTagList());
        }
        approveTrainingModel.setIsRepeating(training.isRepeat());

        if (training.isRepeat()) {
            approveTrainingModel.setRepeatModel(trainingService.getApproveRepeatModel(actionId));
        } else {
            List<ApproveLesson> approveLessonList = trainingService.getApproveLessonList(actionId);
            List<LessonModel> lessonModelList = new ArrayList<LessonModel>();
            for (ApproveLesson approveLesson : approveLessonList) {
                LessonModel lessonModel = new LessonModel();
                lessonModel.setPlace(approveLesson.getPlace());
                lessonModel.setDate(approveLesson.getDate());
                lessonModelList.add(lessonModel);
            }
            approveTrainingModel.setLessonList(lessonModelList);
        }


        return approveTrainingModel;
    }


    @RequestMapping(value = "/{id}/lesson", method = RequestMethod.PUT)
    void editLesson(@PathVariable("id") long trainingId, @RequestBody LessonModel lessonModel) {
        trainingService.editLesson(trainingId,lessonModel);
    }

    @RequestMapping(value = "/{id}/lesson", method = RequestMethod.POST)
    void addLesson(@PathVariable("id") long trainingId, @RequestBody LessonModel lessonModel) {
        trainingService.addLesson(trainingId, lessonModel);
    }

    @RequestMapping(value = "/{id}/lesson", method = RequestMethod.DELETE)
    void removeLesson(@PathVariable("id") long trainingId, @RequestBody LessonModel lessonModel) {
        trainingService.removeLesson(trainingId, lessonModel);
    }

    @RequestMapping(value = "/{id}/confirm/lesson", method = RequestMethod.PUT)
    void confirmChangeLesson(@PathVariable("id") long actionId, @RequestBody LessonModel lessonModel) {

    }
}
