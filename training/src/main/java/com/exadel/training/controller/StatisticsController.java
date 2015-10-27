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
    public String getStatistics(@RequestBody StatisticsModel statisticsModel) {
        return statisticsService.getStatistics(statisticsModel);
    }
}
