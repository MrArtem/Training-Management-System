package com.exadel.training.dao.impl;

import com.exadel.training.dao.ApproveActionDAO;
import com.exadel.training.dao.domain.ApproveAction;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ApproveActionDAOImpl implements ApproveActionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ApproveAction getApproveAction(Long id) {
        return sessionFactory.getCurrentSession().load(ApproveAction.class, id);
    }

    @Override
    public ApproveAction getApproveActionByTrainingId(Long trainingId) {
        return (ApproveAction) sessionFactory.getCurrentSession().createCriteria(ApproveAction.class)
                .createAlias("training", "t")
                .add(Restrictions.eq("t.id", trainingId)).uniqueResult();
    }

    @Override
    public void addApproveAction(ApproveAction approveAction) {
        sessionFactory.getCurrentSession().save(approveAction);
    }

    @Override
    public void removeApproveAction(ApproveAction approveAction) {
        sessionFactory.getCurrentSession().delete(approveAction);
    }

    @Override
    public Long getApproveActionNumber() {
        return (Long) sessionFactory.getCurrentSession().createCriteria(ApproveAction.class)
                .setProjection(Projections.rowCount()).uniqueResult();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ApproveAction> getApproveActionList(Integer page, Integer pageSize) {
        return sessionFactory.getCurrentSession().createCriteria(ApproveAction.class)
                .setFirstResult(page * pageSize).setMaxResults(pageSize).list();
    }
}
