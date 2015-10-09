package com.exadel.training.dao.domain;

import javax.persistence.*;

@Entity
@Table
public class ApproveAction {

    public enum TableName {
        APPROVE_TRAINING, APPROVE_LESSON
    }

    public enum Type {
        CREATE, EDIT, REMOVE
    }

    @Id
    @GeneratedValue
    private Long id;

    private Long date;

    @Enumerated(value = EnumType.STRING)
    private TableName tableName;

    private Long actionId;

    @Enumerated(value = EnumType.STRING)
    private Type type;

    public ApproveAction() {
    }

    public Long getId() {
        return id;
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

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
}
