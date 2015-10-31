package com.exadel.training.controller;

import com.exadel.training.controller.model.StatisticsModel;
import com.exadel.training.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping("/statistics")
//    public String getStatistics(@RequestBody StatisticsModel statisticsModel) {
    public String getStatistics() {
        StatisticsModel statisticsModel = new StatisticsModel();
        statisticsModel.setId(3L);
        statisticsModel.setStartDate(0L);
        statisticsModel.setEndDate(1446199758917L);
        statisticsModel.setStatisticsType(StatisticsModel.StatisticsType.TRAINING);
        return statisticsService.getStatistics(statisticsModel);
    }
}
