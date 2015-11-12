package com.exadel.training.dao.impl;

import com.exadel.training.dao.TrainingDAO;
import com.exadel.training.dao.UserDAO;
import com.exadel.training.dao.domain.*;
import com.exadel.training.security.authentication.CustomAuthentication;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by azapolski on 10/5/2015.
 */
@Repository
public class TrainingDAOImpl implements TrainingDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private UserDAO userDAO;


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
        return sessionFactory.getCurrentSession().load(Training.class, id);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Training> getTrainingListByTagList(Integer page, Integer pageSize, Boolean isActual, List<Tag> tagList) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Training.class, "training");
        if (tagList.size() == 0) {
            if (isActual) {
                CustomAuthentication customUser =
                        (CustomAuthentication) SecurityContextHolder.getContext().getAuthentication();
                User user = userDAO.getUserByID(customUser.getUserId());
                criteria.createAlias("training.listenerList", "listener");
                Criterion isCoach = Restrictions.eq("coach", user);
                Criterion isSubscribed = Restrictions.eq("listener.user", user);
                criteria.add(Restrictions.or(isCoach, isSubscribed));
                criteria.add(Restrictions.eq("listener.state", Listener.State.ACCEPTED));
            }
            criteria = criteria.add(Restrictions.eq("state", Training.State.NONE));
            Long date = new Date().getTime();
            criteria = criteria.createCriteria("lessonList").add(Restrictions.gt("date", date));
            criteria = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria = criteria.addOrder(Order.asc("date"));
            return criteria.list();
        } else {
            criteria = criteria.add(Restrictions.eq("state", Training.State.NONE));
            if (isActual) {
                CustomAuthentication customUser =
                        (CustomAuthentication) SecurityContextHolder.getContext().getAuthentication();
                User user = userDAO.getUserByID(customUser.getUserId());
                criteria.createAlias("training.listenerList", "listener");
                Criterion isCoach = Restrictions.eq("coach", user);
                Criterion isSubscribed = Restrictions.eq("listener.user", user);
                criteria.add(Restrictions.or(isCoach, isSubscribed));
                criteria.add(Restrictions.eq("listener.state", Listener.State.ACCEPTED));
            }
            Long date = new Date().getTime();
            DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Training.class);
            detachedCriteria = detachedCriteria.add(Restrictions.eq("state", Training.State.NONE));
            detachedCriteria = detachedCriteria.setProjection(Projections.property("id"));
            detachedCriteria = detachedCriteria.createCriteria("lessonList").add(Restrictions.gt("date", date));
            detachedCriteria = detachedCriteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            criteria.add(Subqueries.propertyIn("id", detachedCriteria));
            criteria.setProjection(Projections.projectionList().add(Projections.groupProperty("id"))
                    .add(Projections.count("id").as("count")));
            criteria = criteria.addOrder(Order.desc("count"));
            criteria = criteria.createCriteria("tagList");
            Junction disjunction = Restrictions.disjunction();
            for (Tag tag : tagList) {
                disjunction = disjunction.add(Restrictions.eq("specialty", tag.getSpecialty()));
            }
            criteria = criteria.add(disjunction);
            List<Training> trainingList = new ArrayList<Training>();
            List<Object[]> result = criteria.list();
            for (Object[] object : result) {
                long id = (Long) object[0];
                Training training = getTrainingById(id);
                trainingList.add(training);
            }
            return trainingList;
        }
    }

    @Override
    public List<Listener> getListenerList(long trainingId) {
        Session session = sessionFactory.getCurrentSession();
        Training training = session.load(Training.class, trainingId);
        return training.getListenerList();
    }

    @Override
    public List<Lesson> getLessonList(long trainingId) {
        Session session = sessionFactory.getCurrentSession();
        Training training = session.load(Training.class, trainingId);
        return training.getLessonList();
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Training> getTrainingListForStatistic() {
        return sessionFactory.getCurrentSession().createQuery("from Training tr where tr.state = :state ")
                .setParameter("state", Training.State.NONE)
                .list();
    }
}
