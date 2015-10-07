package com.exadel.training.service;

import com.exadel.training.dao.domain.Training;


public interface TrainingService {

    Training getTraining(long id);

    boolean canRate(long id);
}
