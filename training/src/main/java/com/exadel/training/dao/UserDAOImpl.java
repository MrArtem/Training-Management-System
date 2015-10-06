package com.exadel.training.dao;

import com.exadel.training.dao.domain.User;
import com.exadel.training.dao.impl.UserDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by HP on 05.10.2015.
 */
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
}
