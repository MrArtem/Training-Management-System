package com.exadel.training.dao.impl;

import com.exadel.training.dao.UserDAO;
import com.exadel.training.dao.domain.Lesson;
import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.internal.CriteriaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.Date;
import java.util.List;

/**
 * Created by HP on 05.10.2015.
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void update(User user) {
         sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public Boolean isCoachOfCurrentUser(long idCurrentUser, long idCoach) {
        return (Boolean)sessionFactory.getCurrentSession()
                .createQuery("select case when (count(l)>0) then true else false end from Listener as l inner join l.training as t where l.user.id = :idCurrentUser and t.coach.id = :idCoach")
                .setParameter("idCurrentUser", idCurrentUser)
                .setParameter("idCoach", idCoach)
                .uniqueResult();
    }

    @Override
    public User getUserByID(long id) {
        return sessionFactory.getCurrentSession().load(User.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Training> visitedTrainings(long idUser) {
        return sessionFactory.getCurrentSession()
                .createQuery("select distinct t from Training as t inner join t.lessonList as lesson inner join t.listenerList as listener where listener.user.id = :idUser and :currentDate > all elements(lesson.date) ")
                .setParameter("idUser", idUser)
                .setParameter("currentDate", new Date().getTime())
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Training> actualTrainings(long idUser) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Training.class, "training");
        criteria.createAlias("training.listenerList", "listener");
        criteria.createAlias("training.lessonList", "lesson");

        Criterion criterionListener = Restrictions.and(Restrictions.eq("listener.user.id", idUser));
        Criterion criterionCoach = Restrictions.and(Restrictions.eq("training.coach.id", idUser));

        criteria.add(Restrictions.or(criterionListener,criterionCoach));
        criteria.add(Restrictions.gt("lesson.date", new Date().getTime()));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public List<Training> getCoachTrainingsBetweenDates(long idCoach, long startDate, long endDate) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Training.class, "training");

        criteria.createAlias("training.lessonList", "lesson");
        criteria.add(Restrictions.between("lesson.date", startDate, endDate));
        criteria.add(Restrictions.eq("training.coach.id", idCoach));

        return criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Training> getUserTrainingsByState(long idUser, Listener.State state) {
        return sessionFactory.getCurrentSession()
                .createQuery("select t from Training as t inner join t.listenerList as l where l.user.id = :idUser and l.state = :state ")
                .setParameter("idUser", idUser)
                .setParameter("state", state)
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Training> getUserTrainingsByState(long idUser, Listener.State state, long startDate, long endDate) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Training.class, "training");

        criteria.createAlias("training.lessonList", "lesson");
        criteria.createAlias("training.listenerList", "listener");

        criteria.add(Restrictions.between("lesson.date", startDate, endDate));
        criteria.add(Restrictions.eq("listener.state", state));
        criteria.add(Restrictions.eq("listener.user.id", idUser));

        return criteria.list();
    }


    @Override
    public User getUserByLogin(String userLogin) {
        return (User)sessionFactory.getCurrentSession()
                .createQuery("from User as u where u.login = :userLogin")
                .setParameter("userLogin", userLogin)
                .uniqueResult();
    }
}
