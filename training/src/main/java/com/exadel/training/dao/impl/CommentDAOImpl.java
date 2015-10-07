package com.exadel.training.dao.impl;

import com.exadel.training.dao.CommentDAO;
import com.exadel.training.dao.domain.Comment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
