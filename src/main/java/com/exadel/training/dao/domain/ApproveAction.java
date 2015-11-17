package com.exadel.training.dao.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class ApproveAction {

    @Id
    @GeneratedValue
    private Long id;
    private Long date;
    @OneToOne
    private Training training;
    @OneToOne(cascade = CascadeType.ALL)
    private ApproveTraining approveTraining;
    @OneToMany(mappedBy = "approveAction", cascade = CascadeType.ALL)
    private List<ApproveLesson> approveLessonList;
    @Enumerated(value = EnumType.STRING)
    private Type type;

    public ApproveAction() {
    }

    public ApproveTraining getApproveTraining() {
        return approveTraining;
    }

    public void setApproveTraining(ApproveTraining approveTraining) {
        this.approveTraining = approveTraining;
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

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public List<ApproveLesson> getApproveLessonList() {
        return approveLessonList;
    }

    public void setApproveLessonList(List<ApproveLesson> approveLessonList) {
        this.approveLessonList = approveLessonList;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        CREATE, EDIT, REMOVE
    }
}
