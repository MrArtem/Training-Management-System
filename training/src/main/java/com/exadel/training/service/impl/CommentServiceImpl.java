package com.exadel.training.service.impl;

import com.exadel.training.controller.model.CommentModel;
import com.exadel.training.dao.CommentDAO;
import com.exadel.training.dao.TrainingDAO;
import com.exadel.training.dao.UserDAO;
import com.exadel.training.dao.domain.Comment;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;
import com.exadel.training.security.User.CustomUser;
import com.exadel.training.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{
    private final int AVERAGE_EFFECTIVE = 3;
    private final int BOOLEAN_FIELDS_NUMBER = 5;

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private TrainingDAO trainingDAO;

    @Autowired
    private UserDAO userDAO;



    private Boolean isCommentPositive(Comment comment) {
        int countPositive = 0;
        int countFields = BOOLEAN_FIELDS_NUMBER;
        if (comment.getClear()){
            countPositive++;
        }
        if (comment.getCreativity()){
            countPositive++;
        }
        if (comment.getInteresting()){
            countPositive++;
        }
        if (comment.getNewMaterial()){
            countPositive++;
        }
        if (comment.getRecommendation()){
            countPositive++;
        }
        if (comment.getEffective() > AVERAGE_EFFECTIVE){
            countPositive++;
        }
        if (comment.getEffective() != AVERAGE_EFFECTIVE){
            countFields++;
        }
        if(countPositive * 2 >= countFields) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public void addComment(CommentModel commentModel, Long trainingId) {
        Comment comment = new Comment();
        comment.setClear(commentModel.getClear());
        comment.setCreativity(commentModel.getCreativity());
        comment.setEffective(commentModel.getEffective());
        comment.setInteresting(commentModel.getInteresting());
        comment.setNewMaterial(commentModel.getNewMaterial());
        comment.setRecommendation(commentModel.getRecommendation());
        comment.setOther(commentModel.getOther());
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDAO.getUserByID(customUser.getUserId());
        comment.setUser(user);
        comment.setTraining(trainingDAO.getTrainingById(trainingId));
        comment.setIsPositive(isCommentPositive(comment));
        comment.setDate(new Date().getTime());
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
    public List<Comment> getTrainingCommentList(Long trainingId) {
        return trainingDAO.getTrainingById(trainingId).getCommentList();
    }

    @Override
    @Transactional
    public List<Comment> getCoachCommentList(Long coachId) {
        List<Comment> commentList = new ArrayList<Comment>();
        User user = userDAO.getUserByID(coachId);
        for (Training training : user.getTrainingsCoach()) {
            for (Comment comment : training.getCommentList()) {
                commentList.add(comment);
            }
        }
        return commentList;
    }

    @Override
    public Comment getComment(Long id) {
        return commentDAO.getComment(id);
    }

    @Override
    @Transactional
    public List<Comment> getUserCommentList(Long userId) {
        return userDAO.getUserByID(userId).getCommentList();
    }
}
