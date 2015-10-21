package com.exadel.training.controller;

import com.exadel.training.controller.model.feedbackModels.AddFeedbackModel;
import com.exadel.training.controller.model.feedbackModels.FeedbackModel;
import com.exadel.training.dao.domain.Feedback;
import com.exadel.training.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayudovin on 20.10.2015.
 */
@RestController
@RequestMapping("/feedback_controller")
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @RequestMapping(value = "/feedbacks_of_user/{idUser}", method = RequestMethod.GET)
    public List<FeedbackModel> getFeedbacListOfUser(@PathVariable("idUser") long idUser) {
         List<FeedbackModel> feedbackModelList = new ArrayList<FeedbackModel>();

        for(Feedback feedback : feedbackService.getFeedbackListForUser(idUser)) {
            feedbackModelList.add(new FeedbackModel(feedback));
        }

        return feedbackModelList;
    }

    @RequestMapping(value = "/add_feedback", method = RequestMethod.GET)
    public void addFeedback() {
        AddFeedbackModel addFeedbackModel = new AddFeedbackModel();
        addFeedbackModel.setQuestions(true);
        addFeedbackModel.setFocusOnResult(true);
        addFeedbackModel.setTraingID(1);
        addFeedbackModel.setUserID(1);

        feedbackService.addFeedback(addFeedbackModel);
    }
}
