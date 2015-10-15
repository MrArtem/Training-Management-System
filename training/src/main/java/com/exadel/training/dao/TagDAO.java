package com.exadel.training.dao;

import com.exadel.training.dao.domain.Tag;

import java.util.List;

public interface TagDAO {

    Tag getTag(Long tagId);

    void addTag(Tag tag);

    List<Tag> getTagList();
}
