package com.exadel.training.service;

import com.exadel.training.dao.domain.Comment;

import java.util.List;

public interface CommentService {

    void addComment(Comment comment);

    void removeComment(Long commentId);

    Comment getComment(Long id);

    List<Comment> getTrainingCommentList(Long trainingId);

    List<Comment> getCoachCommentList(Long coachId);

    List<Comment> getUserCommentList(Long userId);

}
