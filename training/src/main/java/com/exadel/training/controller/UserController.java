package com.exadel.training.controller;

import com.exadel.training.controller.model.TrainingListModel;
import com.exadel.training.controller.model.userModels.UserModel;
import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.security.User.CustomUser;
import com.exadel.training.service.UserService;
import com.exadel.training.validate.annotation.LegalID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.context.SecurityContextHolder;

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

    @RequestMapping(value = "/user_info/{idUser}", method = RequestMethod.GET)
    @LegalID
    public UserModel getUserInfo(@PathVariable("idUser") long idUser) {
        CustomUser customUser =  (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long idCurrentUser = customUser.getUserId();
        UserModel userModel = new UserModel(userService.getUserById(idCurrentUser), userService.isCoachOfCurrentUser(idCurrentUser, idUser));

        return userModel;
    }

    @RequestMapping(value = "/visitedTraining/{idUser}", method = RequestMethod.GET)
    @LegalID
    public List<TrainingListModel> getVisitedTraining(@PathVariable("idUser") long idUser) {
        List<TrainingListModel> trainingListModelList = new ArrayList<TrainingListModel>();

        for(Training training : userService.visitedTrainings(idUser)) {
            trainingListModelList.add(new TrainingListModel(training));
        }

        return trainingListModelList;
    }

    @RequestMapping(value = "/actualTraining/{idUser}", method = RequestMethod.GET)
    @LegalID
    public List<TrainingListModel> getActualTraining(@PathVariable("idUser") long idUser) {
        List<TrainingListModel> trainingListModelList = new ArrayList<TrainingListModel>();

        for(Training training : userService.actualTrainings(idUser)) {
            trainingListModelList.add(new TrainingListModel(training));
            //todo get next date and place

        }

        return trainingListModelList;
    }

}
