package com.exadel.training.dao.impl;

import com.exadel.training.dao.domain.Training;

/**
 * Created by azapolski on 10/5/2015.
 */
public interface TrainingDAO {

    void addTraining(Training training);

    void changeTraining(Training training);

}
