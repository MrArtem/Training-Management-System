package com.exadel.training.service;

import com.exadel.training.dao.domain.Tag;

import java.util.List;

public interface TagService {

    List<Tag> addTag(Tag tag);

    Tag getTagBySpecialty(String specialty);

    List<Tag> getTagList();
}
