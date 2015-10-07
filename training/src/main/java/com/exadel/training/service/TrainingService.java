package com.exadel.training.service;

import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Training;

import java.util.List;


public interface TrainingService {

    Training getTraining(long id);

    boolean canRate(long id);

    List<Listener> getListenerListRecord(long trainingId);
}
