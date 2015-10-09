package com.exadel.training.dao.impl;

import com.exadel.training.dao.FeedbackDAO;
import com.exadel.training.dao.domain.Feedback;
import com.exadel.training.dao.domain.Training;
import org.hibernate.Criteria;
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

    @Override
    public List<Feedback> getFeedbackListFromTrainingForUser(long idUser, long idTraining) {
      return sessionFactory.getCurrentSession()
              .createQuery("select f from Feedback as f where f.training.id = :idTraining and f.user.id = :idUser")
              .setParameter("idUser", idUser)
              .setParameter("idTraining", idTraining)
              .list();
    }
}
