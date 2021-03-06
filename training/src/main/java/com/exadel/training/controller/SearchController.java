package com.exadel.training.controller;

import com.exadel.training.controller.model.trainingModels.TrainingListModel;
import com.exadel.training.controller.model.userModels.UserModel;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;
import com.exadel.training.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayudovin on 21.10.2015.
 */
@RestController
@RequestMapping("/search_controller")
public class SearchController {

    @Autowired
    SearchService searchService;

    @Secured({"ADMIN", "USER"})
    @RequestMapping(value = "/search_user", method = RequestMethod.POST, consumes = "application/json")
    public List<UserModel> searchUser(@RequestBody String searchWord) {
        List<UserModel> userModelList = new ArrayList<UserModel>();

        for(User user : searchService.searchUser(searchWord)) {
            userModelList.add(new UserModel(user));
        }

        return userModelList;
    }

    @Secured({"ADMIN", "USER"})
    @RequestMapping(value = "/search_training", method = RequestMethod.POST, consumes = "application/json")
    public List<TrainingListModel> searchTraining(@RequestBody String searchWord) {
        List<TrainingListModel> trainingListModels = new ArrayList<TrainingListModel>();

         for(Training training : searchService.searchTraining(searchWord)) {
             trainingListModels.add(new TrainingListModel(training));
         }

         return trainingListModels;
    }
}
