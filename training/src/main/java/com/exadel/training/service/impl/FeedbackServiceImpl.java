package com.exadel.training.service.impl;

import com.exadel.training.controller.model.feedbackModels.AddFeedbackModel;
import com.exadel.training.dao.FeedbackDAO;
import com.exadel.training.dao.TrainingDAO;
import com.exadel.training.dao.UserDAO;
import com.exadel.training.dao.domain.Feedback;
import com.exadel.training.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ayudovin on 06.10.2015.
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {
    private static final int AVERAGE_EFFECTIVE = 3;

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private FeedbackDAO feedbackDAO;
    @Autowired
    private TrainingDAO trainingDAO;

    @Override
    public boolean isFeedbackPositive(Feedback feedback) {
        int coutOfpositive = 0;

        if (feedback.isAttendance()) {
            coutOfpositive ++;
        }
        if (feedback.isAttitude()) {
            coutOfpositive ++;
        }
        if (feedback.isCommSkills()) {
            coutOfpositive ++;
        }
        if (feedback.isFocusOnResult()) {
            coutOfpositive ++;
        }
        if (feedback.isMotivation()) {
            coutOfpositive ++;
        }
        if (feedback.isQuestions()) {
            coutOfpositive ++;
        }

        return coutOfpositive >= AVERAGE_EFFECTIVE;
    }

    @Transactional
    @Override
    public void addFeedback(AddFeedbackModel addFeedbackModel) {
        feedbackDAO.addFeedback(this.setProperties(addFeedbackModel));
    }
    private Feedback setProperties(AddFeedbackModel addFeedbackModel) {
        Feedback feedback = new Feedback();

        feedback.setAttendance(addFeedbackModel.isAttendance());
        feedback.setAttitude(addFeedbackModel.isAttitude());
        feedback.setCommSkills(addFeedbackModel.isCommSkills());
        feedback.setFocusOnResult(addFeedbackModel.isFocusOnResult());
        feedback.setMotivation(addFeedbackModel.isMotivation());
        feedback.setQuestions(addFeedbackModel.isQuestions());

        feedback.setTraining(trainingDAO.getTrainingById(addFeedbackModel.getTrainingID()));
        feedback.setUser(userDAO.getUserByID(addFeedbackModel.getUserID()));

        return feedback;
    }

    @Transactional
    @Override
    public List<Feedback> getFeedbackListForUser(long id) {
        return userDAO.getUserByID(id).getFeedbackList();
    }

    @Transactional
    @Override
    public List<Feedback> getFeedbackListFromTrainingForUser(long idUser, long idTraining) {
        return feedbackDAO.getFeedbackListFromTrainingForUser(idUser, idTraining);
    }

    @Transactional
    @Override
    public Feedback getFeedback(Long id) {
        return feedbackDAO.getFeedback(id);
    }
}
