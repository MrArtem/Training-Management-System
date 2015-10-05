package com.exadel.training.dao;

import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.impl.TrainingDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManagerFactory;


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
}
