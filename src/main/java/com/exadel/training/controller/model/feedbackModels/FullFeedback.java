package com.exadel.training.controller.model.feedbackModels;

import com.exadel.training.dao.domain.Feedback;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

/**
 * Created by ayudovin on 03.11.2015.
 */
public class FullFeedback {

    private long idFeedback;

    private boolean attendance;
    private boolean attitude;
    private boolean commSkills;
    private boolean questions;
    private boolean motivation;
    private boolean focusOnResult;
    private String other;
    private long date;

    private long idUser;
    private long idTraining;

    public FullFeedback() {
    }

    public FullFeedback(Feedback feedback) {
        this.idFeedback = feedback.getId();

        this.attendance = feedback.isAttendance();
        this.attitude = feedback.isAttitude();
        this.commSkills = feedback.isAttitude();
        this.commSkills = feedback.isCommSkills();
        this.questions = feedback.isQuestions();
        this.motivation = feedback.isMotivation();
        this.focusOnResult = feedback.isFocusOnResult();
        this.other = feedback.getOther();
        this.date = feedback.getDate();

        this.idUser = feedback.getUser().getId();
        this.idTraining = feedback.getTraining().getId();
    }

    public long getIdFeedback() {
        return idFeedback;
    }

    public void setIdFeedback(long idFeedback) {
        this.idFeedback = idFeedback;
    }

    public boolean isAttendance() {
        return attendance;
    }

    public void setAttendance(boolean attendance) {
        this.attendance = attendance;
    }

    public boolean isAttitude() {
        return attitude;
    }

    public void setAttitude(boolean attitude) {
        this.attitude = attitude;
    }

    public boolean isCommSkills() {
        return commSkills;
    }

    public void setCommSkills(boolean commSkills) {
        this.commSkills = commSkills;
    }

    public boolean isQuestions() {
        return questions;
    }

    public void setQuestions(boolean questions) {
        this.questions = questions;
    }

    public boolean isMotivation() {
        return motivation;
    }

    public void setMotivation(boolean motivation) {
        this.motivation = motivation;
    }

    public boolean isFocusOnResult() {
        return focusOnResult;
    }

    public void setFocusOnResult(boolean focusOnResult) {
        this.focusOnResult = focusOnResult;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public long getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(long idTraining) {
        this.idTraining = idTraining;
    }
}
