package com.exadel.training.dao.domain;

import javax.persistence.*;

@Entity
@Table
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Training training;

    @ManyToOne
    private User user;

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

    public Comment() {
        isDeleted = false;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Boolean getCreativity() {
        return creativity;
    }

    public void setCreativity(Boolean creativity) {
        this.creativity = creativity;
    }
}
