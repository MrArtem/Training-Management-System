package com.exadel.training.dao.impl;

import com.exadel.training.dao.domain.Lesson;
import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.TrainingDAO;
import com.exadel.training.dao.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by azapolski on 10/5/2015.
 */
@Repository
@Transactional
public class TrainingDAOImpl implements TrainingDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void addTraining(Training training) {
        sessionFactory.getCurrentSession().save(training);
    }

    @Override
    public void changeTraining(Training training) {
        sessionFactory.getCurrentSession().update(training);
    }

    @Override
    public Training getTrainingById(long id) {
        return sessionFactory.getCurrentSession().load(Training.class,id);
    }

    @Override
    public List<Training> getTrainingListActual(long trainingId) {
        return null;
    }

    @Override
    public List<Listener> getListenerList(long trainingId) {
        Session session = sessionFactory.getCurrentSession();
        Training training  = session.load(Training.class, trainingId);
        return training.getListenerList();
    }

    @Override
    public List<Lesson> getLessonList(long trainingId) {
        Session session = sessionFactory.getCurrentSession();
        Training training = session.load(Training.class, trainingId);
        return training.getLessonList();
    }
}
