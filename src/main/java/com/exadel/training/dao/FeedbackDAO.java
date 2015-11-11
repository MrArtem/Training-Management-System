package com.exadel.training.dao;

import com.exadel.training.dao.domain.Feedback;

import java.util.List;

/**
 * Created by ayudovin on 06.10.2015.
 */
public interface FeedbackDAO {
    Feedback getFeedback(long id);

    void addFeedback(Feedback feedback);

    List<Feedback> getFeedbackListFromTrainingForUser(long idUser, long idTraining);
    List<Feedback> getFeedbackListAboutUser(long idUser, long startDate, long endDate);
    List<Feedback> getFeedbackListFromTraining(long idTraining, long startDate, long endDate);
}
