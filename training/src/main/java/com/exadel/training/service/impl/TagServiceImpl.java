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
public class TagServiceImpl implements TagService{

    @Autowired
    private TagDAO tagDAO;


    @Override
    @Transactional
    public void addTag(String specialty, String colour) {
        Tag tag = new Tag();
        tag.setColour(colour);
        tag.setSpecialty(specialty);
        tagDAO.addTag(tag);
    }

    @Override
    @Transactional
    public List<Tag> getTagList() {
        return tagDAO.getTagList();
    }

    //TODO: criteria
    @Override
    public List<Training> getTrainingListByTagList(List<Tag> tagList) {
        return null;
    }
}
