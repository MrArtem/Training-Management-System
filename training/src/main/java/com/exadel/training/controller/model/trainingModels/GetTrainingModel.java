package com.exadel.training.controller.model.trainingModels;


import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;

public class GetTrainingModel extends TrainingModel {

    private boolean canSubscribe;

    private Long startDate;

    private Long endDate;

    private double rating;

    private boolean isCoach;

    public GetTrainingModel() {
    }

    public void setTraining(Training training) {
        setTitle(training.getTitle());
        setShortInfo(training.getExcerpt());
        setDescription(training.getDescription());
        if (training.getCountListenerRating() != 0) {
            rating = (double) training.getSumRating() / training.getCountListenerRating();
        } else {
            rating = 0;
        }
        setLanguage(training.getLanguage());
        setTagList(training.getTagList());
        User coach = training.getCoach();
        setCoachName(coach.getFirstName() + " " + coach.getLastName());
        setCoachId(coach.getId());
        setIsRepeating(training.isRepeat());
    }

    public boolean isCanSubscribe() {
        return canSubscribe;
    }

    public void setCanSubscribe(boolean canSubscribe) {
        this.canSubscribe = canSubscribe;
    }

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public Long getEndDate() {
        return endDate;
    }

    public void setEndDate(Long endDate) {
        this.endDate = endDate;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean getIsCoach() {
        return isCoach;
    }

    public void setIsCoach(boolean isCoach) {
        this.isCoach = isCoach;
    }
}
