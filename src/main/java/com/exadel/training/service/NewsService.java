package com.exadel.training.service;

import com.exadel.training.dao.domain.News;
import com.exadel.training.dao.domain.User;

import java.util.List;

public interface NewsService {
    void addNews(User user, News.TableName tableName, News.ActionType actionType, Long idRow);

    List<News> getNewsList(Integer page, Integer pageSize);
}
