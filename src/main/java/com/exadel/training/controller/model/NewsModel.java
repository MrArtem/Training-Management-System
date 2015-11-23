package com.exadel.training.controller.model;

import com.exadel.training.dao.domain.News;
import com.exadel.training.dao.domain.User;

public class NewsModel {
    private String userName;

    private Long userId;

    private String title;

    private News.ActionType actionType;

    private Long idItem;

    private Long date;

    private News.TableName tableName;

    private Long trainingId;

    public NewsModel(News news) {
        User user = news.getUser();
        this.userName = user.getFirstName() + " " + user.getLastName();
        this.userId = user.getId();
        this.actionType = news.getActionType();
        this.idItem = news.getIdRow();
        this.date = news.getDate();
        this.tableName = news.getTableName();
    }

    public NewsModel() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public News.ActionType getActionType() {
        return actionType;
    }

    public void setActionType(News.ActionType actionType) {
        this.actionType = actionType;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public News.TableName getTableName() {
        return tableName;
    }

    public void setTableName(News.TableName tableName) {
        this.tableName = tableName;
    }

    public Long getTrainingId() {
        return trainingId;
    }

    public void setTrainingId(Long trainingId) {
        this.trainingId = trainingId;
    }

    public Long getIdItem() {
        return idItem;
    }

    public void setIdItem(Long idItem) {
        this.idItem = idItem;
    }
}
