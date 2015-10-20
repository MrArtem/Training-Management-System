package com.exadel.training.service.impl;

import com.exadel.training.dao.CommentDAO;
import com.exadel.training.dao.TrainingDAO;
import com.exadel.training.dao.UserDAO;
import com.exadel.training.dao.domain.Comment;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;
import com.exadel.training.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDAO commentDAO;

    @Autowired
    private TrainingDAO trainingDAO;

    @Autowired
    private UserDAO userDAO;

    private final int AVERAGE_EFFECTIVE = 3;

    private final int BOOLEAN_FIELDS_NUMBER = 5;

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
    public void addComment(Comment comment) {
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
    @Transactional
    public List<Comment> getUserCommentList(Long userId) {
        return userDAO.getUserByID(userId).getCommentList();
    }
}
