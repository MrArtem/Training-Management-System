package com.exadel.training.dao.impl;

import com.exadel.training.dao.UserDAO;
import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;
import com.exadel.training.dao.domain.UserPassword;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public void savePassword(UserPassword userPassword) {
        sessionFactory.getCurrentSession().save(userPassword);
    }

    @Override
    public Boolean isCoachOfCurrentUser(long idCurrentUser, long idCoach) {
        return (Boolean) sessionFactory.getCurrentSession()
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
                .createQuery("select distinct t from Training as t  inner join t.listenerList as listener where listener.user.id = :idUser and " +
                        "(select max(l.date) from t.lessonList as l) < :currentDate ")
                .setParameter("idUser", idUser)
                .setParameter("currentDate", new Date().getTime())
                .list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Training> actualTrainings(long idUser) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Training.class, "training");
        criteria.createAlias("training.listenerList", "listener", JoinType.LEFT_OUTER_JOIN);
        criteria.createAlias("training.lessonList", "lesson");

        Criterion criterionListener = Restrictions.and(Restrictions.eq("listener.user.id", idUser));
        Criterion criterionStateListener = Restrictions.and(Restrictions.eq("listener.state", Listener.State.ACCEPTED));
        Criterion criterionCoach = Restrictions.and(Restrictions.eq("training.coach.id", idUser));

        criteria.add(Restrictions.or(Restrictions.and(criterionListener, criterionStateListener), criterionCoach));
        criteria.add(Restrictions.gt("lesson.date", new Date().getTime()));

        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Training> waitingTrainings(long idUser) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Training.class, "training");
        criteria.createAlias("training.listenerList", "listener");
        criteria.createAlias("training.lessonList", "lesson");

        Criterion criterionListener = Restrictions.and(Restrictions.eq("listener.user.id", idUser));
        Criterion criterionWait = Restrictions.and(Restrictions.eq("listener.state", Listener.State.WAITING));

        criteria.add(Restrictions.and(criterionListener, criterionWait));
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
        return (User) sessionFactory.getCurrentSession()
                .createQuery("from User as u where u.login = :userLogin")
                .setParameter("userLogin", userLogin)
                .uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsersByRole(User.Role role) {
        return sessionFactory.getCurrentSession()
                .createQuery("select u from User as u where u.role = :role")
                .setParameter("role", role)
                .list();
    }
}
