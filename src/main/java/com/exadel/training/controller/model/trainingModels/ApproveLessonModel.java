package com.exadel.training.controller.model.trainingModels;


public class ApproveLessonModel {
    private Long oldDate;
    private Long newDate;
    private String oldPlace;
    private String newPlace;

    public Long getOldDate() {
        return oldDate;
    }

    public void setOldDate(Long oldDate) {
        this.oldDate = oldDate;
    }

    public Long getNewDate() {
        return newDate;
    }

    public void setNewDate(Long newDate) {
        this.newDate = newDate;
    }

    public String getOldPlace() {
        return oldPlace;
    }

    public void setOldPlace(String oldPlace) {
        this.oldPlace = oldPlace;
    }

    public String getNewPlace() {
        return newPlace;
    }

    public void setNewPlace(String newPlace) {
        this.newPlace = newPlace;
    }
}
