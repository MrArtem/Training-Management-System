package com.exadel.training.controller;

import com.exadel.training.controller.model.trainingModels.TrainingListModel;
import com.exadel.training.controller.model.userModels.ExCoachModel;
import com.exadel.training.controller.model.userModels.ExUserModel;
import com.exadel.training.controller.model.userModels.PasswordExCoach;
import com.exadel.training.controller.model.userModels.UserModel;
import com.exadel.training.dao.domain.Lesson;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;
import com.exadel.training.security.authentication.CustomAuthentication;
import com.exadel.training.service.LessonService;
import com.exadel.training.service.UserService;
import com.exadel.training.utils.Utils;
import com.exadel.training.validate.annotation.LegalID;
import org.springframework.beans.factory.annotation.Autowired;
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
    LessonService lessonService;
    @Autowired
    private UserService userService;

    @Secured({"ADMIN", "USER", "EX_COACH"})
    @RequestMapping(value = "/user_info/{idUser}", method = RequestMethod.GET)
    @LegalID
    public UserModel getUserInfo(@PathVariable("idUser") long idUser) {
        CustomAuthentication customUser =
                (CustomAuthentication) SecurityContextHolder.getContext().getAuthentication();
        long idCurrentUser = customUser.getUserId();
        UserModel userModel = new UserModel(userService.getUserById(idUser), userService.isCoachOfCurrentUser(idCurrentUser, idUser));

        return userModel;
    }

    @Secured({"ADMIN", "USER"})
    @RequestMapping(value = "/visitedTraining/{idUser}", method = RequestMethod.GET)
    @LegalID
    public List<TrainingListModel> getVisitedTraining(@PathVariable("idUser") long idUser) {
        List<TrainingListModel> trainingListModelList = new ArrayList<TrainingListModel>();

        for (Training training : userService.visitedTrainings(idUser)) {
            trainingListModelList.add(new TrainingListModel(training));
        }

        return trainingListModelList;
    }

    @Secured({"ADMIN", "USER"})
    @RequestMapping(value = "/actualTraining/{idUser}", method = RequestMethod.GET)
    @LegalID
    public List<TrainingListModel> getActualTraining(@PathVariable("idUser") long idUser) {
        List<TrainingListModel> trainingListModelList = new ArrayList<TrainingListModel>();

        for (Training training : Utils.emptyIfNull(userService.actualTrainings(idUser))) {
            trainingListModelList.add(this.createTrainingListModel(training));
        }

        return trainingListModelList;
    }

    @Secured({"ADMIN", "USER"})
    @RequestMapping(value = "/waitingTraining/{idUser}", method = RequestMethod.GET)
    @LegalID
    public List<TrainingListModel> getWaitingTraining(@PathVariable("idUser") long idUser) {
        List<TrainingListModel> trainingListModelList = new ArrayList<TrainingListModel>();

        for (Training training : Utils.emptyIfNull(userService.waitingTrainings(idUser))) {
            trainingListModelList.add(this.createTrainingListModel(training));
        }

        return trainingListModelList;
    }
    private TrainingListModel createTrainingListModel(Training training) {
        TrainingListModel trainingListModel = new TrainingListModel(training);
        Lesson nextLesson = lessonService.getNextLesson(training.getId());
        if (nextLesson != null) {
            trainingListModel.setNextDate(nextLesson.getDate());
            trainingListModel.setNextPlace(nextLesson.getPlace());
        }

        return trainingListModel;
    }

    @Secured({"ADMIN"})
    @RequestMapping(value = "/add_ex_user", method = RequestMethod.POST, consumes = "application/json")
    public void addExUser(@RequestBody ExUserModel exUserModel) {
        userService.addExternalUser(exUserModel);
    }

    @Secured({"ADMIN"})
    @RequestMapping(value = "/add_ex_coach", method = RequestMethod.POST, consumes = "application/json")
    public List<UserModel> addExCoach(@RequestBody ExCoachModel exCoachModel) {
        userService.addExternalCoach(exCoachModel);

        List<UserModel> userList = new ArrayList<UserModel>();
        for (User user : userService.getUsersByRole(User.Role.EX_COACH)) {
            userList.add(new UserModel(user));
        }
        return userList;
    }

    @Secured({"ADMIN"})
    @RequestMapping(value = "/get_all_ex_coach", method = RequestMethod.GET)
    public List<UserModel> getAllExCoach() {
        List<UserModel> userList = new ArrayList<UserModel>();
        for (User user : userService.getUsersByRole(User.Role.EX_COACH)) {
            userList.add(new UserModel(user));
        }
        return userList;
    }

    @Secured({"EX_COACH"})
    @RequestMapping(value = "/set_password", method = RequestMethod.POST, consumes = "application/json")
    public void setPassword(@RequestBody PasswordExCoach passwordExCoach) {
        userService.setPasswordExCoach(passwordExCoach);
    }

    @Secured({"ADMIN", "USER", "EX_COACH"})
    @RequestMapping(value = "/set_phone/{id}/{phone}", method = RequestMethod.GET)
    public void setPhone(@PathVariable("phone") String phone, @PathVariable("id") long id) {
        userService.setPhone(phone, id);
    }
}
