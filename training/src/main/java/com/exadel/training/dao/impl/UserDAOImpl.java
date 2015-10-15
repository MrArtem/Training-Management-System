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
    public User getUserByID(long id) {
        return sessionFactory.getCurrentSession().load(User.class, id);
    }

    @Override
    public User getUserByLogin(String userLogin) {
        return (User)sessionFactory.getCurrentSession()
                .createQuery("from User as u where u.login = :userLogin")
                .setParameter("userLogin", userLogin)
                .uniqueResult();
    }
}
