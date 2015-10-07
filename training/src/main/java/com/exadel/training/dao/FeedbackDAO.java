package com.exadel.training.dao;

import com.exadel.training.dao.domain.Feedback;

import java.util.List;

/**
 * Created by ayudovin on 06.10.2015.
 */
public interface FeedbackDAO {
    Feedback getFeedvackByID(long id);
    void addFeedback(Feedback feedback);
}
