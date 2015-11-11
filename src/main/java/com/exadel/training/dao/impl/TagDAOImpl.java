package com.exadel.training.dao.impl;

import com.exadel.training.dao.TagDAO;
import com.exadel.training.dao.domain.Tag;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TagDAOImpl implements TagDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addTag(Tag tag) {
        sessionFactory.getCurrentSession().save(tag);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Tag> getTagList() {
        return sessionFactory.getCurrentSession().createCriteria(Tag.class).list();
    }

    @Override
    public Tag getTag(Long tagId) {
        return sessionFactory.getCurrentSession().load(Tag.class, tagId);
    }

    @Override
    public Tag getTagBySpecialty(String specialty) {
        return (Tag) sessionFactory.getCurrentSession().createCriteria(Tag.class)
                .add(Restrictions.eq("specialty", specialty)).uniqueResult();
    }
}
