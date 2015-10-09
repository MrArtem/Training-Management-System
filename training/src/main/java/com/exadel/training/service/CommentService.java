package com.exadel.training.service;

import com.exadel.training.dao.domain.Comment;

public interface CommentService {

    void addComment(Comment comment);

    void editComment(Comment comment);

    void removeComment(Long commentId);
}
