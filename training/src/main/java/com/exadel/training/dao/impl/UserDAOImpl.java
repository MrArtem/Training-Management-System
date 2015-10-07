package com.exadel.training.dao.impl;

import com.exadel.training.dao.UserDAO;
import com.exadel.training.dao.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public Boolean isCoach(long idUser, long idTraining) {
        return (Boolean)sessionFactory.getCurrentSession()
                .createQuery("select case when count(c)>0 then true else false end from User as u inner join u.trainingListCoach as c where c.id = :idTraining and u.id = :idUser")
                .setParameter("idTraining", idTraining)
                .setParameter("idUser", idUser)
                .uniqueResult();
    }

    @Override
    public User getUserByID(long id) {
        return sessionFactory.getCurrentSession().load(User.class, id);
    }
}
