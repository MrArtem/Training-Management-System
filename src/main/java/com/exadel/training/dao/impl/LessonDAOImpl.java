package com.exadel.training.dao.impl;

import com.exadel.training.dao.LessonDAO;
import com.exadel.training.dao.domain.Lesson;
import com.exadel.training.dao.domain.Training;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by azapolski on 10/6/2015.
 */
@Repository
public class LessonDAOImpl implements LessonDAO {

    @Autowired
    SessionFactory sessionFactory;


    private Long getTime() {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        return calendar.getTimeInMillis();
    }

    @Override
    public void addLesson(Lesson lesson) {
        sessionFactory.getCurrentSession().save(lesson);
    }

    @Override
    public void changeLesson(Lesson lesson) {
        sessionFactory.getCurrentSession().update(lesson);
    }

    @Override
    public void removeLesson(Lesson lesson) {
        sessionFactory.getCurrentSession().delete(lesson);
    }

    @Override
    public Lesson getLessonByID(long idLesson) {
        return (Lesson) sessionFactory.getCurrentSession()
                .createQuery("select l from Lesson as l where l.id = :idLesson")
                .setParameter("idLesson", idLesson)
                .uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Lesson> getLessonListActualByTraining(long trainingId) {
        Session session = sessionFactory.getCurrentSession();
        Training training = session.load(Training.class, trainingId);
        return session.createQuery("from Lesson less where less.training = :training and less.state != :state")
                .setParameter("training", training)
                .setParameter("state", Lesson.State.REMOVAL)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Long getStartDateByTraining(long trainingId) {
        Session session = sessionFactory.getCurrentSession();
        Training training = session.load(Training.class, trainingId);
        return (Long) session.createQuery("select min(les.date) from Lesson les where les.training =:training")
                .setParameter("training", training).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Long getEndDateByTraining(long trainingId) {
        Session session = sessionFactory.getCurrentSession();
        Training training = session.load(Training.class, trainingId);
        return (Long) session.createQuery("select max(les.date) from Lesson les where les.training =:training")
                .setParameter("training", training).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Lesson getNextLesson(long trainingId) {
        Session session = sessionFactory.getCurrentSession();
        Training training = session.load(Training.class, trainingId);
        return (Lesson) session.createQuery("from  Lesson less where less.training = :training and less.date = " +
                "(select min(l.date) from Lesson l where l.training = :training and l.date >= :curDate )")
                .setParameter("training", training)
                .setParameter("curDate", getTime())
                .uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Lesson> getLessonListByTrainingAndState(long trainingId, Lesson.State state) {
        Session session = sessionFactory.getCurrentSession();
        Training training = session.load(Training.class, trainingId);
        return session.createQuery("from Lesson less where less.training = :training and less.state = :state")
                .setParameter("training", training)
                .setParameter("state", state)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Lesson> getLessonListActual(long idTraining, long startDate, long endDate) {
        Session session = sessionFactory.getCurrentSession();
        Training training = session.load(Training.class, idTraining);
        return sessionFactory.getCurrentSession().createQuery(" from Lesson less where less.date >= :startDate " +
                "and less.date <= :endDate and less.state != :state and less.training = :training")
                .setParameter("state", Lesson.State.REMOVAL)
                .setParameter("endDate", endDate)
                .setParameter("startDate", startDate)
                .setParameter("training", training)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Lesson> getLessonListActualFrom(long trainingId, long startDate) {
        Session session = sessionFactory.getCurrentSession();
        Training training = session.load(Training.class, trainingId);
        return sessionFactory.getCurrentSession().createQuery(" from Lesson less where less.date >= :startDate and " +
                " less.state != :state and less.training = :training")
                .setParameter("state", Lesson.State.REMOVAL)
                .setParameter("startDate", startDate)
                .setParameter("training", training)
                .list();
    }
}
