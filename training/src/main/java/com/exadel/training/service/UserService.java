package com.exadel.training.service;

import com.exadel.training.controller.model.userModels.ExUserModel;
import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;

import java.util.List;

/**
 * Created by HP on 06.10.2015.
 */
public interface UserService {
    long addExternalUser(ExUserModel exUserModel);

    Boolean isCoach(long idUser, long idTraining);
    Boolean isCoachOfCurrentUser(long idCurrentUser, long idCoach);

    User getUserById(long id);
    User getUserByLogin(String login);

    List<Training> getListenerTrainingListOfUser(long idUser);
    List<Training> getCoachTrainingList(long idUser);
    List<Training> getCoachTrainingList(long idCoach, long startDate, long endDate);
    List<Training> visitedTrainings (long idUser);
    List<Training> actualTrainings(long idUser);
    List<Training> getUserTrainingsByState(long idUser, Listener.State state);
    List<Training> getUserTrainingsByState(long idUser, Listener.State state, long startDate, long endDate);
}
