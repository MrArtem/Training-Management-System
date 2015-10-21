package com.exadel.training.service.impl;

import com.exadel.training.dao.TagDAO;
import com.exadel.training.dao.domain.Tag;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TagServiceImpl implements TagService{

    @Autowired
    private TagDAO tagDAO;


    @Override
    public void addTag(Tag tag) {
        tagDAO.addTag(tag);
    }

    @Override
    public List<Tag> getTagList() {
        return tagDAO.getTagList();
    }

    @Override
    public Tag getTagBySpecialty(String specialty) {
        return tagDAO.getTagBySpecialty(specialty);
    }
}
