package com.exadel.training.dao.impl;

import com.exadel.training.dao.LessonDAO;
import com.exadel.training.dao.domain.Lesson;
import com.exadel.training.dao.domain.Training;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by azapolski on 10/6/2015.
 */
@Repository
@Transactional
public class LessonDAOImpl implements LessonDAO{
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void addLesson(Lesson lesson) {
        sessionFactory.getCurrentSession().save(lesson);
    }

    @Override
    public void changeLesson(Lesson lesson) {
        sessionFactory.getCurrentSession().update(lesson);
    }

    @Override
    public Lesson getLessonById(long id) {
        return sessionFactory.getCurrentSession().load(Lesson.class, id);
    }

    @Override
    public List<Lesson> getLessonListByTraining(long trainingId) {
        Session session =  sessionFactory.getCurrentSession();
        Training training = session.load(Training.class, trainingId);
        return training.getLessonList();
    }

    @Override
    public Long getStartDateByTraining(long trainingId) {
        Session session = sessionFactory.getCurrentSession();
        Training training = session.load(Training.class,trainingId);
        return (Long)session.createQuery("select max(les.date) from Lesson les where les.training =:training")
                .setParameter("training",training).uniqueResult();
    }

    @Override
    public Long getEndDateByTraining(long trainingId) {
        return 0L;
    }
}
