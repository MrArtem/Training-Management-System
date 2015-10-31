package com.exadel.training.dao.impl;

import com.exadel.training.dao.LessonDAO;
import com.exadel.training.dao.domain.Lesson;
import com.exadel.training.dao.domain.Training;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by azapolski on 10/6/2015.
 */
@Repository
@Transactional
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
    public Lesson getLessonById(long id) {
        return sessionFactory.getCurrentSession().load(Lesson.class, id);
    }

    @Override
    public List<Lesson> getLessonListByTraining(long trainingId) {
        //todo lesson state
        Session session = sessionFactory.getCurrentSession();
        Training training = session.load(Training.class, trainingId);
        return training.getLessonList();
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
        return (Lesson)session.createQuery("from  Lesson less where less.training = :training and less.data = " +
                "(select min(l.date) from Lesson l where l.training = :training and l.data >= :curDate )")
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
    public List<Lesson> getLessonListActual(long startDate, long endDate) {
        return sessionFactory.getCurrentSession(). createQuery(" from Lesson less where less.date >= :startDate and less.date <= :endDate and less.state != :state")
                .setParameter("state", Lesson.State.REMOVAL)
                .setParameter("endDate", endDate)
                .setParameter("startDate", startDate)
                .list();
    }
}
