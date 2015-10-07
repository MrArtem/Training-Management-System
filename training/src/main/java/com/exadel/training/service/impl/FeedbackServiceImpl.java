package com.exadel.training.service.impl;

import com.exadel.training.dao.UserDAO;
import com.exadel.training.dao.domain.Feedback;
import com.exadel.training.dao.domain.User;
import com.exadel.training.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by ayudovin on 06.10.2015.
 */
public class FeedbackServiceImpl implements FeedbackService {
    public static final long ILLEGAL_ID = 0;

    @Autowired
    private UserDAO userDAO;

    @Override
    public List<Feedback> getFeedbackListForUser(long id) {
        if (id == ILLEGAL_ID) {
           throw new IllegalArgumentException("id can't be 0");
        }

        return userDAO.getUserByID(id).getFeedbackList();
    }
}
