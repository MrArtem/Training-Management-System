package com.exadel.training.controller.model;

public class StatisticsModel {

    public enum StatisticsType {
        USER, TRAINING
    }

    private StatisticsType statisticsType;

    private Long startDate;

    private Long endDate;

    private Long id;

    public StatisticsModel() {
    }

    public StatisticsType getStatisticsType() {
        return statisticsType;
    }

    public void setStatisticsType(StatisticsType statisticsType) {
        this.statisticsType = statisticsType;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
