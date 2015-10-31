package com.exadel.training.controller.model.feedbackModels;

import com.exadel.training.dao.domain.Feedback;

/**
 * Created by ayudovin on 20.10.2015.
 */
public class FeedbackModel {

    private String coachName;
    private long coachID;
    private long feedbackID;
    private boolean isPositive;

    public FeedbackModel() {
    }

    public FeedbackModel(Feedback feedback) {
        this.coachID = feedback.getTraining().getCoach().getId();
        this.feedbackID = feedback.getId();
        this.coachName = feedback.getTraining().getCoach().getLastName() + " " + feedback.getTraining().getCoach().getFirstName();
    }

    public long getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(long feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public long getCoachID() {
        return coachID;
    }

    public void setCoachID(long coachID) {
        this.coachID = coachID;
    }

    public boolean isPositive() {
        return isPositive;
    }

    public void setIsPositive(boolean isPositive) {
        this.isPositive = isPositive;
    }
}
