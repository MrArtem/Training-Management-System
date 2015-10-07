package com.exadel.training.dao.impl;

import com.exadel.training.dao.ApproveActionDAO;
import com.exadel.training.dao.domain.ApproveAction;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApproveActionDAOImpl implements ApproveActionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addApproveAction(ApproveAction approveAction) {
        sessionFactory.getCurrentSession().save(approveAction);
    }

    @Override
    public void removeApproveAction(ApproveAction approveAction) {
        sessionFactory.getCurrentSession().delete(approveAction);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ApproveAction> getApproveActionList() {
        return sessionFactory.getCurrentSession().createCriteria(ApproveAction.class).list();
    }
}
