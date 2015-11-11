package com.exadel.training.dao.impl;

import com.exadel.training.dao.FeedbackDAO;
import com.exadel.training.dao.domain.Feedback;
import com.exadel.training.dao.domain.Training;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
    public Feedback getFeedback(long id) {
        return sessionFactory.getCurrentSession().load(Feedback.class,id);
    }

    @Override
    public void addFeedback(Feedback feedback) {
        sessionFactory.getCurrentSession().save(feedback);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Feedback> getFeedbackListFromTrainingForUser(long idUser, long idTraining) {
      return sessionFactory.getCurrentSession()
              .createQuery("select f from Feedback as f where f.training.id = :idTraining and f.user.id = :idUser")
              .setParameter("idUser", idUser)
              .setParameter("idTraining", idTraining)
              .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Feedback> getFeedbackListAboutUser(long idUser, long startDate, long endDate) {
      return sessionFactory.getCurrentSession()
              .createQuery("select f from Feedback as f " +
                      "where f.user.id = :idUser and f.date between :startDate and :endDate order by f.date")
              .setParameter("idUser", idUser)
              .setParameter("startDate", startDate)
              .setParameter("endDate", endDate)
              .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Feedback> getFeedbackListFromTraining(long idTraining, long startDate, long endDate) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Feedback.class, "feedback");

        criteria.createAlias("feedback.training", "training");
        criteria.add(Restrictions.eq("training.id", idTraining));
        criteria.add(Restrictions.between("feedback.date", startDate, endDate));
        criteria.addOrder(Order.asc("feedback.date"));

        return criteria.list();
    }


}
