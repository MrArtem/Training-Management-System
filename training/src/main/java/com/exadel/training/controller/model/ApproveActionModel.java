package com.exadel.training.controller.model;

import com.exadel.training.dao.domain.ApproveAction;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class ApproveActionModel {

    private Long id;

    private Long date;

    private ApproveAction.Type type;

    private String tableName;

    private Long coachId;

    private String coachName;

    private Long trainingId;

    private String trainingTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public ApproveAction.Type getType() {
        return type;
    }

    public void setType(ApproveAction.Type type) {
        this.type = type;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Long getCoachId() {
        return coachId;
    }

    public void setCoachId(Long coachId) {
        this.coachId = coachId;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public Long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
    }

    public String getTrainingTitle() {
        return trainingTitle;
    }

    public void setTrainingTitle(String trainingTitle) {
        this.trainingTitle = trainingTitle;
    }
}
