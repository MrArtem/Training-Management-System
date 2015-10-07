package com.exadel.training.dao.impl;

import com.exadel.training.dao.FeedbackDAO;
import com.exadel.training.dao.domain.Feedback;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ayudovin on 06.10.2015.
 */
@Repository
public class FeedbackDAOImpl implements FeedbackDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Feedback getFeedvackByID(long id) {
        return sessionFactory.getCurrentSession().load(Feedback.class,id);
    }

    @Override
    public void addFeedback(Feedback feedback) {
        sessionFactory.getCurrentSession().save(feedback);
    }
}
