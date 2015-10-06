package com.exadel.training.service.impl;

import com.exadel.training.dao.CommentDAO;
import com.exadel.training.dao.domain.Comment;
import com.exadel.training.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDAO commentDAO;

    @Override
    @Transactional
    public void addComment(Comment comment) {
        commentDAO.addComment(comment);
    }

    @Override
    @Transactional
    public void removeComment(Long commentId) {
        Comment comment = commentDAO.getComment(commentId);
        comment.setIsDeleted(true);
        commentDAO.editComment(comment);
    }

    @Override
    @Transactional
    public void editComment(Comment comment) {
        commentDAO.editComment(comment);
    }
}
