package com.exadel.training.service;

import com.exadel.training.dao.domain.Tag;
import com.exadel.training.dao.domain.Training;

import java.util.List;

public interface TagService {

    void addTag(Tag tag);

    List<Tag> getTagList();

    List<Training> getTrainingListByTagList(List<Tag> tagList);
}
