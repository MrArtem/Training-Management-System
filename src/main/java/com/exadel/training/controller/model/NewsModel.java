package com.exadel.training.controller.model;

import com.exadel.training.dao.domain.News;
import com.exadel.training.dao.domain.User;

public class NewsModel {
    private String userName;

    private Long userId;

    private String title;

    private News.ActionType actionType;

    private Long idRow;

    private Long date;

    private News.TableName tableName;

    public NewsModel(News news) {
        User user = news.getUser();
        this.userName = user.getFirstName() + " " + user.getLastName();
        this.userId = user.getId();
        this.actionType = news.getActionType();
        this.idRow = news.getIdRow();
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

    public Long getIdRow() {
        return idRow;
    }

    public void setIdRow(Long idRow) {
        this.idRow = idRow;
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
}
