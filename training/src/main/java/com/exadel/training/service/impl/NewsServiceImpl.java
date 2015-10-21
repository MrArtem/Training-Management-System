package com.exadel.training.service.impl;

import com.exadel.training.dao.NewsDAO;
import com.exadel.training.dao.domain.News;
import com.exadel.training.dao.domain.User;
import com.exadel.training.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsDAO newsDAO;

    @Override
    public void addNews(User user, News.TableName tableName, News.ActionType actionType, Long idRow) {
        News news = new News();
        news.setUser(user);
        news.setTableName(tableName);
        news.setActionType(actionType);
        news.setIdRow(idRow);
        news.setDate(new Date().getTime());
        newsDAO.addNews(news);
    }

    @Override
    public List<News> getNewsList(Integer page, Integer pageSize) {
        return newsDAO.getNewsList(page, pageSize);
    }
}
