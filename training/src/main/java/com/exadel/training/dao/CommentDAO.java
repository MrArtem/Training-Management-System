package com.exadel.training.dao;

import com.exadel.training.dao.domain.Comment;

import java.util.List;

public interface CommentDAO {
    Comment getComment(Long commentId);

    void addComment(Comment comment);

    void editComment(Comment comment);
}
