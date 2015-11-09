package com.exadel.training.controller;

import com.exadel.training.controller.model.NewsModel;
import com.exadel.training.dao.domain.Comment;
import com.exadel.training.dao.domain.Feedback;
import com.exadel.training.dao.domain.News;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.service.CommentService;
import com.exadel.training.service.FeedbackService;
import com.exadel.training.service.NewsService;
import com.exadel.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsService;

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private FeedbackService feedbackService;

    @Secured({"ADMIN"})
    @MessageMapping("/news")
    @SendTo("/pipe/news")
    public List<NewsModel> getNewsList(@RequestParam("page") Integer page,
                                @RequestParam("page_size") Integer pageSize) {
        List<NewsModel> newsModelList = new ArrayList<NewsModel>();
        for(News news : newsService.getNewsList(page, pageSize)) {
            NewsModel newsModel = new NewsModel(news);
            switch(news.getTableName()) {
                case TRAINING:
                    Training training = trainingService.getTraining(news.getIdRow());
                    newsModel.setTitle(training.getTitle());
                    break;
                case COMMENT:
                    Comment comment = commentService.getComment(news.getIdRow());
                    newsModel.setTitle(comment.getTraining().getTitle());
                    break;
                case FEEDBACK:
                    Feedback feedback = feedbackService.getFeedback(news.getIdRow());
                    newsModel.setTitle(feedback.getTraining().getTitle());
            }
            newsModelList.add(newsModel);
        }
        return newsModelList;
    }
}
