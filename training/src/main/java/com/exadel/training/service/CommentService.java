package com.exadel.training.service;

import com.exadel.training.controller.model.CommentModel;
import com.exadel.training.dao.domain.Comment;

import java.util.List;

public interface CommentService {

    void addComment(CommentModel commentModel, Long trainingId);

    void removeComment(Long commentId);

    Comment getComment(Long id);

    List<Comment> getTrainingCommentList(Long trainingId);

    List<Comment> getCoachCommentList(Long coachId);

    List<Comment> getUserCommentList(Long userId);

    List<Comment> getTrainingCommentListByDate(Long idTraining, Long startDate, Long endDate);

    List<Comment> getUserCommentListByDate(Long idUser, Long startDate, Long endDate);

}
