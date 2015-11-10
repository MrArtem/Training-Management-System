package com.exadel.training.controller.model;

import com.exadel.training.dao.domain.Comment;

public class CommentModel {

    private Long id;

    private String userName;

    private Long userId;

    private Boolean clear;

    private Boolean interesting;

    private Boolean newMaterial;

    private Integer effective;

    private Boolean creativity;

    private Boolean recommendation;

    private String other;

    private Boolean isDeleted;

    private Boolean isPositive;

    private Long date;

    public CommentModel() {
    }

    public CommentModel(Comment comment) {
        this.setClear(comment.getClear());
        this.setCreativity(comment.getCreativity());
        this.setEffective(comment.getEffective());
        this.setInteresting(comment.getInteresting());
        this.setNewMaterial(comment.getNewMaterial());
        this.setRecommendation(comment.getRecommendation());
        this.setOther(comment.getOther());
        this.setDate(comment.getDate());
        this.setIsPositive(comment.getIsPositive());
        this.setIsDeleted(comment.getIsDeleted());
        this.setId(comment.getId());
        this.setUserId(comment.getUser().getId());
        this.setUserName(comment.getUser().getFirstName() + " " + comment.getUser().getLastName());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Boolean getClear() {
        return clear;
    }

    public void setClear(Boolean clear) {
        this.clear = clear;
    }

    public Boolean getInteresting() {
        return interesting;
    }

    public void setInteresting(Boolean interesting) {
        this.interesting = interesting;
    }

    public Boolean getNewMaterial() {
        return newMaterial;
    }

    public void setNewMaterial(Boolean newMaterial) {
        this.newMaterial = newMaterial;
    }

    public Integer getEffective() {
        return effective;
    }

    public void setEffective(Integer effective) {
        this.effective = effective;
    }

    public Boolean getCreativity() {
        return creativity;
    }

    public void setCreativity(Boolean creativity) {
        this.creativity = creativity;
    }

    public Boolean getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Boolean recommendation) {
        this.recommendation = recommendation;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Boolean getIsPositive() {
        return isPositive;
    }

    public void setIsPositive(Boolean isPositive) {
        this.isPositive = isPositive;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }
}
