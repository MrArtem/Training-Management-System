package com.exadel.training.dao.impl;

import com.exadel.training.dao.ApproveDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ApproveDAOImpl<T> implements ApproveDAO<T> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public T getApprove(Class<T> c, Long id) {
        return c.cast(sessionFactory.getCurrentSession().load(c, id));
    }

    @Override
    public void addApprove(T t) {
        sessionFactory.getCurrentSession().save(t);
    }

    @Override
    public void removeApprove(T t) {
        sessionFactory.getCurrentSession().delete(t);
    }

    @Override
    public void editApprove(T t) {
        sessionFactory.getCurrentSession().update(t);
    }
}
