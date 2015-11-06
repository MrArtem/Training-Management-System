package com.exadel.training.controller;

import com.exadel.training.controller.model.trainingModels.TrainingListModel;
import com.exadel.training.controller.model.userModels.ExCoachModel;
import com.exadel.training.controller.model.userModels.ExUserModel;
import com.exadel.training.controller.model.userModels.UserModel;
import com.exadel.training.dao.domain.Lesson;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.notification.Notification;
import com.exadel.training.notification.help.MessageGenerator;
import com.exadel.training.security.User.CustomUser;
import com.exadel.training.service.LessonService;
import com.exadel.training.service.UserService;
import com.exadel.training.validate.annotation.LegalID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayudovin on 12.10.2015.
 */
@RestController
@RequestMapping("/user_controller")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LessonService lessonService;

    @Secured({"ADMIN", "USER", "EX_COACH"})
    @RequestMapping(value = "/user_info/{idUser}", method = RequestMethod.GET)
    @LegalID
    public UserModel getUserInfo(@PathVariable("idUser") long idUser) {
        CustomUser customUser =  (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long idCurrentUser = customUser.getUserId();
        UserModel userModel = new UserModel(userService.getUserById(idUser), userService.isCoachOfCurrentUser(idCurrentUser, idUser));

        return userModel;
    }

    @Secured({"ADMIN", "USER"})
    @RequestMapping(value = "/visitedTraining/{idUser}", method = RequestMethod.GET)
    @LegalID
    public List<TrainingListModel> getVisitedTraining(@PathVariable("idUser") long idUser) {
        List<TrainingListModel> trainingListModelList = new ArrayList<TrainingListModel>();

        for(Training training : userService.visitedTrainings(idUser)) {
            trainingListModelList.add(new TrainingListModel(training));
        }

        return trainingListModelList;
    }

    @Secured({"ADMIN", "USER"})
    @RequestMapping(value = "/actualTraining/{idUser}", method = RequestMethod.GET)
    @LegalID
    public List<TrainingListModel> getActualTraining(@PathVariable("idUser") long idUser) {
        List<TrainingListModel> trainingListModelList = new ArrayList<TrainingListModel>();

        for(Training training : userService.actualTrainings(idUser)) {
            TrainingListModel trainingListModel = new TrainingListModel(training);
            Lesson nextLesson = lessonService.getNextLesson(training.getId());
            if (nextLesson != null) {
                trainingListModel.setNextDate(nextLesson.getDate());
                trainingListModel.setNextPlace(nextLesson.getPlace());
            }
            trainingListModelList.add(trainingListModel);
        }

        return trainingListModelList;
    }

    @Secured({"ADMIN"})
    @RequestMapping(value = "/add_ex_user", method = RequestMethod.POST, consumes = "application/json")
    public void addExUser(@RequestBody ExUserModel exUserModel) {
        userService.addExternalUser(exUserModel);
    }

    @Secured({"ADMIN"})
    @RequestMapping(value = "/add_ex_coach", method = RequestMethod.POST, consumes = "application/json")
    public void addExCoach(@RequestBody ExCoachModel exCoachModel) {

    }

}
