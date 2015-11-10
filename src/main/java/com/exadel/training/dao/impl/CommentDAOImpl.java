package com.exadel.training.dao.impl;

import com.exadel.training.dao.CommentDAO;
import com.exadel.training.dao.domain.Comment;
import com.exadel.training.dao.domain.Feedback;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentDAOImpl implements CommentDAO{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addComment(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
    }

    @Override
    public void editComment(Comment comment) {
        sessionFactory.getCurrentSession().update(comment);
    }

    @Override
    public Comment getComment(Long commentId) {
        return sessionFactory.getCurrentSession().load(Comment.class, commentId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Comment> getTrainingCommentListByDate(Long idTraining, Long startDate, Long endDate) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Comment.class, "comment");

        criteria.createAlias("comment.training", "training");
        criteria.add(Restrictions.eq("training.id", idTraining));
        criteria.add(Restrictions.between("comment.date", startDate, endDate));
        criteria.addOrder(Order.asc("comment.date"));
        return criteria.list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Comment> getUserCommentListByDate(Long idUser, Long startDate, Long endDate) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Comment.class, "comment");

        criteria.createAlias("comment.user", "user");
        criteria.add(Restrictions.eq("user.id", idUser));
        criteria.add(Restrictions.between("comment.date", startDate, endDate));
        criteria.addOrder(Order.asc("comment.date"));
        return criteria.list();
    }
}
