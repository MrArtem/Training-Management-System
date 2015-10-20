package com.exadel.training.controller;

import com.exadel.training.controller.model.CommentModel;
import com.exadel.training.controller.model.TrainingListModel;
import com.exadel.training.controller.model.trainingModels.*;
import com.exadel.training.dao.domain.*;
import com.exadel.training.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/training")
public class TrainingController {

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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    GetTrainingModel getTrainingMadel(@PathVariable("id") long trainingId) {
        GetTrainingModel getTrainingModel = new GetTrainingModel();
        Training training = trainingService.getTraining(trainingId);
        getTrainingModel.setTraining(training);
        getTrainingModel.setStartDate(lessonService.getStartDateByTraining(trainingId));
        getTrainingModel.setEndDate(lessonService.getEndDateByTraining(trainingId));
        return getTrainingModel;
    }

    @RequestMapping(value = "/{id}/lesson_list", method = RequestMethod.GET)
    List<Lesson> getLessonListByTraining(@PathVariable("id") long trainingId) {
        return lessonService.getLessonByTraining(trainingId);
    }

    @RequestMapping(value = "/{id}/listener_list")
    List<ListenerModel> getListenerList(@PathVariable("id") long trainingId) {
        List<Listener> listenerList = trainingService.getListenerListRecord(trainingId);
        List<ListenerModel> listenerModelList = new ArrayList<ListenerModel>();
        for (Listener listener : listenerList) {
            listenerModelList.add(new ListenerModel(listener));
        }
        return listenerModelList;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    void createTraining(@RequestBody AddingTrainingModel addingTrainingModel) {
        trainingService.createTraining(addingTrainingModel.getCoachId()
                , addingTrainingModel.getTitle()
                , addingTrainingModel.getDescription()
                , addingTrainingModel.getShortInfo()
                , addingTrainingModel.getLanguage()
                , addingTrainingModel.getMaxSize()
                , addingTrainingModel.isInner()
                , addingTrainingModel.getTagList()
                , addingTrainingModel.getAdditionalInfo()
                , addingTrainingModel.getIsRepeating()
                , addingTrainingModel.getLessonList()
                , addingTrainingModel.getRepeatModel());

    }
    @RequestMapping(value = "/a", method = RequestMethod.GET)
    AddingTrainingModel add() {
        return new AddingTrainingModel();
    }

    @RequestMapping(value = "/confirm/{id}", method = RequestMethod.POST)
    void confirmAddTraining(@PathVariable("id") long actionId, @RequestBody AddingTrainingModel addingTrainingModel) {
        trainingService.confirmTraining(actionId
                , addingTrainingModel.getTitle()
                , addingTrainingModel.getDescription()
                , addingTrainingModel.getShortInfo()
                , addingTrainingModel.getLanguage()
                , addingTrainingModel.getMaxSize()
                , addingTrainingModel.isInner()
                , addingTrainingModel.getTagList()
                , addingTrainingModel.getLessonList()
                , addingTrainingModel.getRepeatModel());
    }

    @RequestMapping(value = "cancel_create/{id}", method = RequestMethod.PUT)
    void cancelCreate(@PathVariable("id") Long actionId) {
        trainingService.cancelCreate(actionId);
    }

    @RequestMapping(value = "cancel_change/{id}", method = RequestMethod.PUT)
    void cancelChange(@PathVariable("id") Long actionId) {
        trainingService.cancelChange(actionId);
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
    void editTraining(@PathVariable("id") long trainingId, @RequestBody AddingTrainingModel addingTrainingModel) {
        trainingService.editTraining(trainingId
                , addingTrainingModel.getTitle()
                , addingTrainingModel.getDescription()
                , addingTrainingModel.getShortInfo()
                , addingTrainingModel.getLanguage()
                , addingTrainingModel.getMaxSize()
                , addingTrainingModel.isInner()
                , addingTrainingModel.getTagList()
                , addingTrainingModel.getAdditionalInfo()
                , addingTrainingModel.getLessonList()
                , addingTrainingModel.getRepeatModel());
    }

    @RequestMapping(value = "/add_tag", method = RequestMethod.POST)
    public void addTag(@RequestBody Tag tag) {
        tagService.addTag(tag);
    }

    @RequestMapping(value = "/tag_list", method = RequestMethod.GET)
    public List<Tag> getTagList() {
        return tagService.getTagList();
    }

    @RequestMapping(value = "/training_list", method = RequestMethod.GET)
    public List<TrainingListModel> getTrainingList(@RequestParam("is_actual") Boolean isActual,
                                          @RequestParam("page") Integer page,
                                                   @RequestParam("tag") List<String> specialtyList) {
        List<Tag> tagList = new ArrayList<Tag>();
        for (String specialty : specialtyList) {
            tagList.add(tagService.getTagBySpecialty(specialty));
        }
        List<Training> trainingList;
        trainingList = trainingService.getTrainingListByTagList(page, PAGE_SIZE, isActual, tagList);
        List<TrainingListModel> trainingListModelList = new ArrayList<TrainingListModel>();
        for(Training training : trainingList) {
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

    @RequestMapping(value = "/training/{id}/add_comment")
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
        //Get current user
        User user = new User();
        comment.setUser(user);
        comment.setTraining(trainingService.getTraining(trainingId));
        commentService.addComment(comment);
    }

    @RequestMapping(value = "training/{trainingId}/remove_comment/{commentId}")
    public void removeComment(@PathVariable("trainingId") Long trainingId,
                              @PathVariable("commentId") Long commentId) {
        commentService.removeComment(commentId);
    }

    @RequestMapping(value = "/training/{id}/comment_list")
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

    @RequestMapping(value = "/getApproveTraining/{id}", method = RequestMethod.GET)
    public ApproveGetTrainingModel getApproveTrainingModel(@PathVariable("id") long actionId) {
        ApproveAction approveAction = trainingService.getApproveAction(actionId);
        ApproveTraining approveTraining = approveAction.getApproveTraining();
        ApproveGetTrainingModel approveTrainingModel = new ApproveGetTrainingModel();
        Training training = approveAction.getTraining();
        User coach = training.getCoach();

        if (approveTraining != null) {
            approveTrainingModel.setTitle(approveTraining.getTitle());
            approveTrainingModel.setDescription(approveTraining.getDescription());
            approveTrainingModel.setAdditionalInfo(approveTraining.getAdditionalInfo());
            approveTrainingModel.setMaxSize(approveTraining.getMaxSize());
            approveTrainingModel.setTagList(approveTraining.getTagList());
            approveTrainingModel.setShortInfo(approveTraining.getExcerpt());
            approveTrainingModel.setTagList(approveTraining.getTagList());
            approveTrainingModel.setCoachId(coach.getId());
            approveTrainingModel.setCoachName(coach.getFirstName() + " " + coach.getLastName());
        } else {
            approveTrainingModel.setTitle(training.getTitle());
            approveTrainingModel.setDescription(training.getDescription());
            approveTrainingModel.setMaxSize(training.getMaxSize());
            approveTrainingModel.setTagList(training.getTagList());
            approveTrainingModel.setShortInfo(training.getExcerpt());
            approveTrainingModel.setCoachId(coach.getId());
            approveTrainingModel.setCoachName(coach.getFirstName() + " " + coach.getLastName());
            approveTrainingModel.setTagList(training.getTagList());
        }
        approveTrainingModel.setIsRepeating(training.isRepeat());

        if (training.isRepeat()) {
            approveTrainingModel.setRepeatModel(trainingService.getApproveRepeatModel(actionId));
        } else {
            List<ApproveLesson> approveLessonList = trainingService.getApproveLessonList(actionId);
            List<LessonModel> lessonModelList = new ArrayList<LessonModel>();
            for (ApproveLesson approveLesson : approveLessonList) {
                LessonModel lessonModel = new LessonModel();
                lessonModel.setPlace(approveLesson.getPlace());
                lessonModel.setDate(approveLesson.getDate());
                lessonModelList.add(lessonModel);
            }
            approveTrainingModel.setLessonModelList(lessonModelList);
        }


        return approveTrainingModel;
    }
}
