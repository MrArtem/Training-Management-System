package com.exadel.training.service;

import com.exadel.training.dao.domain.Training;

import java.util.List;

/**
 * Created by HP on 06.10.2015.
 */
public interface UserServie {
    List<Training> getTrainingListOfUserListener(long id);
    List<Training> getTrainingListOfUserCoach(long id);
}
