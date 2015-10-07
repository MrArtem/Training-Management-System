package com.exadel.training.service;

import com.exadel.training.dao.domain.Tag;

import java.util.List;

public interface TagService {

    void addTag(String specialty, String colour);

    List<Tag> getTagList();
}
