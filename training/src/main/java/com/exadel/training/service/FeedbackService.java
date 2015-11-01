package com.exadel.training.service;

import com.exadel.training.controller.model.feedbackModels.AddFeedbackModel;
import com.exadel.training.dao.domain.Feedback;

import java.util.List;

/**
 * Created by ayudovin on 06.10.2015.
 */
public interface FeedbackService {
    void addFeedback(AddFeedbackModel addFeedbackModel);

    boolean isFeedbackPositive(Feedback feedback);

    Feedback getFeedback(Long id);

    List<Feedback> getFeedbackListForUser(long id);
    List<Feedback> getFeedbackListAboutUser(long idUser, long startDate, long endDate);
    List<Feedback> getFeedbackListFromTrainingForUser(long idUser, long idTraining);
    List<Feedback> getFeedbackListFromTraining(long idTraining, long startDate, long endDate);
}
