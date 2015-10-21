package com.exadel.training.service.impl;

import com.exadel.training.dao.FeedbackDAO;
import com.exadel.training.dao.UserDAO;
import com.exadel.training.dao.domain.Feedback;
import com.exadel.training.dao.domain.User;
import com.exadel.training.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ayudovin on 06.10.2015.
 */
@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {
    public static final long ILLEGAL_ID = 0;

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private FeedbackDAO feedbackDAO;

    @Override
    public List<Feedback> getFeedbackListForUser(long id) {
        if (id == ILLEGAL_ID) {
           throw new IllegalArgumentException("id can't be 0");
        }

        return userDAO.getUserByID(id).getFeedbackList();
    }

    @Override
    public List<Feedback> getFeedbackListFromTrainingForUser(long idUser, long idTraining) {
        if (idUser == ILLEGAL_ID || idTraining == ILLEGAL_ID) {
            throw new IllegalArgumentException("id can't be 0");
        }

        return feedbackDAO.getFeedbackListFromTrainingForUser(idUser, idTraining);
    }

    @Override
    public Feedback getFeedback(Long id) {
        return feedbackDAO.getFeedback(id);
    }
}
