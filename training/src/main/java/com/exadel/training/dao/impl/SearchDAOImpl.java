package com.exadel.training.dao.impl;

import com.exadel.training.dao.SearchDAO;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;
import org.apache.lucene.search.Query;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ayudovin on 08.10.2015.
 */
@Repository
public class SearchDAOImpl implements SearchDAO, InitializingBean {


    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void afterPropertiesSet() throws Exception {
        Session session = sessionFactory.openSession();
        FullTextSession fullTextSession = org.hibernate.search.Search.getFullTextSession(session);
        fullTextSession.createIndexer().startAndWait();
        session.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> searchUser(String searchWord) {
        Session session = sessionFactory.getCurrentSession();
        FullTextSession fullTextSession = org.hibernate.search.Search.getFullTextSession(session);
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
                .buildQueryBuilder().forEntity(User.class).get();

        Query query = queryBuilder
                .keyword()
                .fuzzy()
                .withPrefixLength(5)
                .onFields("firstName", "lastName", "email", "phone")
                .matching(searchWord)
                .createQuery();

        FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(query, User.class);
        List<User> result = fullTextQuery.list();

        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Training> searchTraining(String searchWord) {
        Session session = sessionFactory.getCurrentSession();
        FullTextSession fullTextSession = org.hibernate.search.Search.getFullTextSession(session);
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
                .buildQueryBuilder().forEntity(Training.class).get();

        Query query = queryBuilder
                .keyword()
                .fuzzy()
                .withPrefixLength(5)
                .onFields("title", "description", "excerpt", "coach.firstName", "coach.lastName", "coach.email", "coach.phone", "tagList.specialty")
                        .matching(searchWord)
                        .createQuery();

        FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(query, Training.class);
        List<Training> result = fullTextQuery.list();

        return result;
    }
}
