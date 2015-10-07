package com.exadel.training.dao.impl;


import com.exadel.training.dao.ListenerDAO;
import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Training;
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
}
