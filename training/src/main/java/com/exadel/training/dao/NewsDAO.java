package com.exadel.training.dao;

import com.exadel.training.dao.domain.News;

import java.util.List;

public interface NewsDAO {
    void addNews(News news);

    List<News> getNewsList(Integer page, Integer pageSize);
}
