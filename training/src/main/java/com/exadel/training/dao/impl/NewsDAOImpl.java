package com.exadel.training.dao.impl;

import com.exadel.training.dao.NewsDAO;
import com.exadel.training.dao.domain.News;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDAOImpl implements NewsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addNews(News news) {
        sessionFactory.getCurrentSession().save(news);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<News> getNewsList(Integer page, Integer pageSize) {
        return sessionFactory.getCurrentSession().createCriteria(News.class).addOrder(Order.desc("date"))
                .setFirstResult(page).setMaxResults(pageSize).list();
    }
}
