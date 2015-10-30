package com.exadel.training.controller;

import com.exadel.training.controller.model.*;
import com.exadel.training.controller.model.trainingModels.*;
import com.exadel.training.controller.model.userModels.ExUserModel;
import com.exadel.training.controller.model.userModels.UserModel;
import com.exadel.training.dao.domain.*;
import com.exadel.training.security.User.CustomUser;
import com.exadel.training.service.*;
import com.exadel.training.validate.TagValidator;
import com.exadel.training.validate.annotation.LegalID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/training")
public class TrainingBaseController {

    private final Integer PAGE_SIZE = 10;

    @Autowired
    private TrainingService trainingService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;
    @Autowired
    private ListenerService listenerService;


//    @Autowired
//    private TagValidator tagValidator;
//
//    @InitBinder
//    private void initBinder(WebDataBinder webDataBinder) {
//        webDataBinder.setValidator(tagValidator);
//    }

    @LegalID
    @Secured({"ADMIN", "USER", "EX_COACH", "EX_USER"})
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    GetTrainingModel getTrainingMadel(@PathVariable("id") long trainingId) {
        GetTrainingModel getTrainingModel = new GetTrainingModel();
        Training training = trainingService.getTraining(trainingId);
        getTrainingModel.setTraining(training);
        getTrainingModel.setStartDate(lessonService.getStartDateByTraining(trainingId));
        getTrainingModel.setEndDate(lessonService.getEndDateByTraining(trainingId));
        return getTrainingModel;
    }

    @LegalID
    @Secured({"ADMIN", "USER", "EX_COACH", "EX_USER"})
    @RequestMapping(value = "/{id}/lesson_list", method = RequestMethod.GET)
    List<Lesson> getLessonListByTraining(@PathVariable("id") long trainingId) {
        return lessonService.getLessonByTraining(trainingId);
    }

    @LegalID
    @Secured({"ADMIN", "USER", "EX_COACH"})
    @RequestMapping(value = "/{id}/listener_list")
    List<ListenerModel> getListenerList(@PathVariable("id") long trainingId) {
        List<User> listenerList = listenerService.getListenerListAccepted(trainingId);
        List<ListenerModel> listenerModelList = new ArrayList<ListenerModel>();
        for (User user : listenerList) {
            ListenerModel listenerModel = new ListenerModel();
            listenerModel.setUserId(user.getId());
            listenerModel.setUsername(user.getFirstName() + " " + user.getLastName());
            listenerModel.setEmail(user.getEmail());
            if (user.getRole() == User.Role.EX_USER) {
                listenerModel.setIsExternal(true);
            }
            listenerModelList.add(listenerModel);
        }
        return listenerModelList;
    }

    @Secured({"ADMIN", "USER"})
    @RequestMapping(value = "/add_tag", method = RequestMethod.POST)
    public void addTag(@Valid @RequestBody Tag tag) {
        tagService.addTag(tag);
    }

    @Secured({"ADMIN", "USER"})
    @RequestMapping(value = "/tag_list", method = RequestMethod.GET)
    public List<Tag> getTagList() {
        return tagService.getTagList();
    }

    @Secured({"ADMIN", "USER"})
    @RequestMapping(value = "/training_list", method = RequestMethod.GET)
    public List<TrainingListModel> getTrainingList(@RequestParam("is_actual") Boolean isActual,
                                          @RequestParam("page") Integer page,
                                                   @RequestParam(value = "tag", required = false) List<String> specialtyList) {
        List<Tag> tagList = new ArrayList<Tag>();
        if (specialtyList != null) {
            for (String specialty : specialtyList) {
                tagList.add(tagService.getTagBySpecialty(specialty));
            }
        }
        List<Training> trainingList;
        trainingList = trainingService.getTrainingListByTagList(page, PAGE_SIZE, isActual, tagList);
        List<TrainingListModel> trainingListModelList = new ArrayList<TrainingListModel>();
        for (Training training : trainingList) {
            TrainingListModel trainingListModel = new TrainingListModel();
            trainingListModel.setId(training.getId());
            trainingListModel.setTitle(training.getTitle());
            trainingListModel.setExcerpt(training.getExcerpt());
            trainingListModel.setCoachId(training.getCoach().getId());
            trainingListModel.setCoachName(training.getCoach().getFirstName() +
                    " " + training.getCoach().getLastName());
            trainingListModel.setTagList(training.getTagList());
            //todo get user here
            User user = new User();
            trainingListModel.setIsCoach(userService.isCoach(user.getId(), training.getId()));
            //todo get next date and place
            trainingListModelList.add(trainingListModel);
        }
        return trainingListModelList;
    }

    //@LegalID
    @RequestMapping(value = "/{id}/add_comment")
    @Secured({"ADMIN", "USER", "EX_COACH"})
    public void addComment(@PathVariable("id") Long trainingId, @RequestBody CommentModel commentModel) {
        Comment comment = new Comment();
        comment.setClear(commentModel.getClear());
        comment.setCreativity(commentModel.getCreativity());
        comment.setEffective(commentModel.getEffective());
        comment.setInteresting(commentModel.getInteresting());
        comment.setNewMaterial(commentModel.getNewMaterial());
        comment.setRecommendation(commentModel.getRecommendation());
        comment.setOther(commentModel.getOther());
        comment.setDate(new Date().getTime());
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserById(customUser.getUserId());
        comment.setUser(user);
        comment.setTraining(trainingService.getTraining(trainingId));
        commentService.addComment(comment);
    }

    @LegalID
    @RequestMapping(value = "/{trainingId}/remove_comment/{commentId}")
    @Secured({"ADMIN", "USER", "EX_COACH"})
    public void removeComment(@PathVariable("trainingId") Long trainingId,
                              @PathVariable("commentId") Long commentId) {
        commentService.removeComment(commentId);
    }

    @RequestMapping(value = "/{id}/comment_list")
    @LegalID
    @Secured({"ADMIN", "USER", "EX_COACH", "EX_USER"})
    public List<CommentModel> getTrainingCommentList(@PathVariable("id") Long trainingId) {
        List<Comment> commentList = commentService.getTrainingCommentList(trainingId);
        List<CommentModel> commentModelList = new ArrayList<CommentModel>();
        for (Comment comment : commentList) {
            CommentModel commentModel = new CommentModel();
            commentModel.setClear(comment.getClear());
            commentModel.setCreativity(comment.getCreativity());
            commentModel.setEffective(comment.getEffective());
            commentModel.setInteresting(comment.getInteresting());
            commentModel.setNewMaterial(comment.getNewMaterial());
            commentModel.setRecommendation(comment.getRecommendation());
            commentModel.setOther(comment.getOther());
            commentModel.setDate(comment.getDate());
            commentModel.setIsPositive(comment.getIsPositive());
            commentModel.setIsDeleted(comment.getIsDeleted());
            commentModel.setId(comment.getId());
            commentModel.setUserId(comment.getUser().getId());
            commentModel.setUserName(comment.getUser().getFirstName() + " " + comment.getUser().getLastName());
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
        for (Comment comment : commentList) {
            CommentModel commentModel = new CommentModel();
            commentModel.setClear(comment.getClear());
            commentModel.setCreativity(comment.getCreativity());
            commentModel.setEffective(comment.getEffective());
            commentModel.setInteresting(comment.getInteresting());
            commentModel.setNewMaterial(comment.getNewMaterial());
            commentModel.setRecommendation(comment.getRecommendation());
            commentModel.setOther(comment.getOther());
            commentModel.setDate(comment.getDate());
            commentModel.setIsPositive(comment.getIsPositive());
            commentModel.setIsDeleted(comment.getIsDeleted());
            commentModel.setId(comment.getId());
            commentModel.setUserId(comment.getUser().getId());
            commentModel.setUserName(comment.getUser().getFirstName() + " " + comment.getUser().getLastName());
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
        for (Comment comment : commentList) {
            CommentModel commentModel = new CommentModel();
            commentModel.setClear(comment.getClear());
            commentModel.setCreativity(comment.getCreativity());
            commentModel.setEffective(comment.getEffective());
            commentModel.setInteresting(comment.getInteresting());
            commentModel.setNewMaterial(comment.getNewMaterial());
            commentModel.setRecommendation(comment.getRecommendation());
            commentModel.setOther(comment.getOther());
            commentModel.setDate(comment.getDate());
            commentModel.setIsPositive(comment.getIsPositive());
            commentModel.setIsDeleted(comment.getIsDeleted());
            commentModel.setId(comment.getId());
            commentModel.setUserId(comment.getUser().getId());
            commentModel.setUserName(comment.getUser().getFirstName() + " " + comment.getUser().getLastName());
            commentModelList.add(commentModel);
        }
        return commentModelList;
    }
    @LegalID
    @Secured({"ADMIN", "USER"})
    @RequestMapping(value = "{id}/addListener", method = RequestMethod.POST)
    void addListener(@PathVariable("id") long trainingId) {
        CustomUser customUser =  (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = customUser.getUserId();
        listenerService.addListener(trainingId, userId);
    }

    @LegalID
    @Secured({"ADMIN", "USER"})
    @RequestMapping(value = "{id}/leave/{userId}", method = RequestMethod.PUT)
    void leaveListener(@PathVariable("id") long trainingId, @PathVariable("userId") Long userId) {
        listenerService.leaveListener(trainingId, userId);

    }

    @LegalID
    @Secured({"ADMIN", "USER"})
    @RequestMapping(value = "{id}/leave", method = RequestMethod.PUT)
    void leaveListener(@PathVariable("id") long trainingId) {
        CustomUser customUser =  (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = customUser.getUserId();
        listenerService.leaveListener(trainingId, userId);
    }

    @LegalID
    @Secured({"ADMIN"})
    @RequestMapping(value = "{id}/addExListener", method = RequestMethod.POST)
    void addListener(@PathVariable("id") long trainingId,@RequestBody ExUserModel exUserModel) {
        Long userId = userService.addExternalUser(exUserModel);
        listenerService.addListener(trainingId,userId);
    }

    @LegalID
    @Secured({"ADMIN", "USER"})
    @RequestMapping(value = "{id}/set_rating/{rating}")
    RatingModel setRating(@PathVariable("id") long trainingId, @PathVariable("rating") int rating) {
        CustomUser customUser =  (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = customUser.getUserId();
        return new RatingModel(trainingService.setRating(trainingId, rating, userId));
    }
}
