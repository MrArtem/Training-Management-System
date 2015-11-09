package com.exadel.training.service.impl;

import com.exadel.training.controller.model.NewsModel;
import com.exadel.training.dao.NewsDAO;
import com.exadel.training.dao.domain.*;
import com.exadel.training.service.CommentService;
import com.exadel.training.service.FeedbackService;
import com.exadel.training.service.NewsService;
import com.exadel.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private NewsDAO newsDAO;

    @Autowired
    private TrainingService trainingService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private FeedbackService feedbackService;

    @Override
    public void addNews(User user, News.TableName tableName, News.ActionType actionType, Long idRow) {
        News news = new News();
        news.setUser(user);
        news.setTableName(tableName);
        news.setActionType(actionType);
        news.setIdRow(idRow);
        news.setDate(new Date().getTime());
        newsDAO.addNews(news);
        NewsModel newsModel = new NewsModel(news);
        switch (news.getTableName()) {
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
        //template.convertAndSend("/pipe/news", news);
    }

    @Override
    public List<News> getNewsList(Integer page, Integer pageSize) {
        return newsDAO.getNewsList(page, pageSize);
    }
}
