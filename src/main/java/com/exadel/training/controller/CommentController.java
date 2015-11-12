package com.exadel.training.controller;

import com.exadel.training.controller.model.CommentModel;
import com.exadel.training.dao.domain.Comment;
import com.exadel.training.dao.domain.User;
import com.exadel.training.security.authentication.CustomAuthentication;
import com.exadel.training.service.CommentService;
import com.exadel.training.validate.annotation.LegalID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/training")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    @Qualifier("tagValidator")
    private Validator tagValidator;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(tagValidator);
    }


    @LegalID
    @RequestMapping(value = "/{trainingId}/remove_comment/{commentId}")
    @Secured({"ADMIN", "USER", "EX_COACH"})
    public void removeComment(@PathVariable("trainingId") Long trainingId,
                              @PathVariable("commentId") Long commentId) {
        commentService.removeComment(commentId);
    }

    @LegalID
    @RequestMapping(value = "/{id}/add_comment")
    @Secured({"ADMIN", "USER", "EX_COACH"})
    public List<CommentModel> addComment(@PathVariable("id") Long trainingId, @RequestBody CommentModel commentAddingModel) {
        List<Comment> commentList = commentService.addComment(commentAddingModel, trainingId);
        List<CommentModel> commentModelList = new ArrayList<CommentModel>();
        CustomAuthentication customUser = (CustomAuthentication) SecurityContextHolder.getContext().getAuthentication();
        String role = "";
        for (GrantedAuthority grantedAuthority : customUser.getAuthorities()) {
            role = grantedAuthority.getAuthority();
        }
        boolean withName = false;
        for (Comment comment : commentList) {
            if ((role.compareTo(User.Role.ADMIN.toString()) == 0)
                    || (customUser.getUserId() == comment.getUser().getId())) {
                withName = true;
            }
            CommentModel commentModel = new CommentModel(comment, withName);
            commentModelList.add(commentModel);
        }
        return commentModelList;
    }

    @RequestMapping(value = "/{id}/comment_list")
    @LegalID
    @Secured({"ADMIN", "USER", "EX_COACH", "EX_USER"})
    public List<CommentModel> getTrainingCommentList(@PathVariable("id") Long trainingId) {
        List<Comment> commentList = commentService.getTrainingCommentList(trainingId);
        List<CommentModel> commentModelList = new ArrayList<CommentModel>();
        CustomAuthentication customUser = (CustomAuthentication) SecurityContextHolder.getContext().getAuthentication();
        String role = "";
        for (GrantedAuthority grantedAuthority : customUser.getAuthorities()) {
            role = grantedAuthority.getAuthority();
        }
        boolean withName = false;
        for (Comment comment : commentList) {
            if ((role.compareTo(User.Role.ADMIN.toString()) == 0)
                    || (customUser.getUserId() == comment.getUser().getId())) {
                withName = true;
            }
            CommentModel commentModel = new CommentModel(comment, withName);
            commentModelList.add(commentModel);
        }
        return commentModelList;
    }


    @LegalID
    @Secured({"ADMIN", "USER"})
    @RequestMapping(value = "/user/{id}/coach_comment_list")
    public List<CommentModel> getCoachCommentList(@PathVariable("id") Long coachId) {
        List<Comment> commentList = commentService.getCoachCommentList(coachId);
        List<CommentModel> commentModelList = new ArrayList<CommentModel>();
        CustomAuthentication customUser = (CustomAuthentication) SecurityContextHolder.getContext().getAuthentication();
        String role = "";
        for (GrantedAuthority grantedAuthority : customUser.getAuthorities()) {
            role = grantedAuthority.getAuthority();
        }
        boolean withName = false;
        for (Comment comment : commentList) {
            if ((role.compareTo(User.Role.ADMIN.toString()) == 0)
                    || (customUser.getUserId() == comment.getUser().getId())) {
                withName = true;
            }
            CommentModel commentModel = new CommentModel(comment, withName);
            commentModelList.add(commentModel);
        }
        return commentModelList;
    }

    @LegalID
    @Secured({"ADMIN", "USER"})
    @RequestMapping(value = "/user/{id}/comment_list")
    public List<CommentModel> getUserCommentList(@PathVariable("id") Long userId) {
        List<Comment> commentList = commentService.getUserCommentList(userId);
        List<CommentModel> commentModelList = new ArrayList<CommentModel>();
        CustomAuthentication customUser = (CustomAuthentication) SecurityContextHolder.getContext().getAuthentication();
        String role = "";
        for (GrantedAuthority grantedAuthority : customUser.getAuthorities()) {
            role = grantedAuthority.getAuthority();
        }
        boolean withName = false;
        for (Comment comment : commentList) {
            if ((role.compareTo(User.Role.ADMIN.toString()) == 0)
                    || (customUser.getUserId() == comment.getUser().getId())) {
                withName = true;
            }
            CommentModel commentModel = new CommentModel(comment, withName);
            commentModelList.add(commentModel);
        }
        return commentModelList;
    }
}
