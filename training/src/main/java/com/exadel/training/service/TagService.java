package com.exadel.training.service;

import com.exadel.training.dao.domain.Tag;
import com.exadel.training.dao.domain.Training;

import java.util.List;

public interface TagService {

    Long addTag(Tag tag);

    Tag getTagBySpecialty(String specialty);

    List<Tag> getTagList();
}
