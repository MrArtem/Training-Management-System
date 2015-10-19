package com.exadel.training.dao.impl;


import com.exadel.training.dao.ListenerDAO;
import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ListenerDAOImpl implements ListenerDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Listener> getListenerListRecord(long trainingId) {
        Session session = sessionFactory.getCurrentSession();
        Training training = session.load(Training.class, trainingId);
        return sessionFactory.getCurrentSession()
                .createQuery("from Listener lis " +
                        "where lis.state != :state and lis.training = :training")
                .setParameter("state",Listener.State.LEAVE)
                .setParameter("training",training)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Listener> getListenerListAccepted(long trainingId) {
        Session session = sessionFactory.getCurrentSession();
        Training training = session.load(Training.class, trainingId);
        return sessionFactory.getCurrentSession()
                .createQuery("from Listener lis " +
                        "where lis.state = :state and lis.training = :training")
                .setParameter("state",Listener.State.ACCEPTED)
                .setParameter("training",training)
                .list();
    }

    @Override
    public void addListener(Listener listener) {
        sessionFactory.getCurrentSession().save(listener);
    }

    @Override
    public void changeListener(Listener listener) {
        sessionFactory.getCurrentSession().update(listener);
    }

    @Override
    public Listener getListenerByTrainingUser(long trainingId, long userId) {
        Session session = sessionFactory.getCurrentSession();
        Training training = session.load(Training.class, trainingId);
        User user =  session.load(User.class, userId);
        return (Listener) sessionFactory.getCurrentSession()
                .createQuery("from Listener lis " +
                        "where lis.user = :user and lis.training = :training")
                .setParameter("user",user)
                .setParameter("training",training)
                .uniqueResult();
    }

    @Override
    public void removeListener(Listener listener) {
        sessionFactory.getCurrentSession().delete(listener);
    }
}
