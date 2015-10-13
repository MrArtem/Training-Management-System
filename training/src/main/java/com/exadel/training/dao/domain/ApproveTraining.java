package com.exadel.training.dao.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class ApproveTraining {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Training training;

    @OneToMany
    private List<Tag> tagList;


    private String title;

    private String description;

    private String excerpt;

    @Column(length = 5000)
    private Integer language;

    private Boolean isInner;

    private Integer maxSize;

    private String additionalInfo;

    public ApproveTraining() {
    }

    public Long getId() {
        return id;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Boolean getIsInner() {
        return isInner;
    }

    public void setIsInner(Boolean isInner) {
        this.isInner = isInner;
    }

    public Integer getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }
}
