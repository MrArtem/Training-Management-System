package com.exadel.training.controller.model.feedbackModels;

/**
 * Created by ayudovin on 21.10.2015.
 */
public class AddFeedbackModel {

    private long userID;
    private long traingID;
    private boolean attendance;
    private boolean attitude;
    private boolean commSkills;
    private boolean questions;
    private boolean motivation;
    private boolean focusOnResult;
    private String other;

    public AddFeedbackModel() {
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getTraingID() {
        return traingID;
    }

    public void setTraingID(long traingID) {
        this.traingID = traingID;
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
}
