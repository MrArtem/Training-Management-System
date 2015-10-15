package com.exadel.training.service;

import com.exadel.training.dao.domain.Feedback;

import java.util.List;

/**
 * Created by ayudovin on 06.10.2015.
 */
public interface FeedbackService {
    List<Feedback> getFeedbackListForUser(long id);
    List<Feedback> getFeedbackListFromTrainingForUser(long idUser, long idTraining);
}
