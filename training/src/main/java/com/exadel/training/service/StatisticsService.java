package com.exadel.training.service;

import com.exadel.training.controller.model.StatisticsModel;
import com.itextpdf.text.DocumentException;

public interface StatisticsService {
    String getStatistics(StatisticsModel statisticsModel) throws DocumentException;
}
