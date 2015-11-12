package com.exadel.training.dao.domain;

import javax.persistence.*;

@Entity
@Table
public class News {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    private Long idRow;
    private Long date;
    @Enumerated(value = EnumType.STRING)
    private TableName tableName;
    @Enumerated(value = EnumType.STRING)
    private ActionType actionType;

    public News() {
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public TableName getTableName() {
        return tableName;
    }

    public void setTableName(TableName tableName) {
        this.tableName = tableName;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public enum TableName {
        TRAINING, COMMENT, FEEDBACK
    }

    public enum ActionType {
        EDIT, CREATE, REMOVE, SET_ATTENDANCE, LEAVE, JOIN, RATE, ADD_FILE, REMOVE_FILE
    }
}
