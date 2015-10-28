package com.exadel.training.service;

import com.exadel.training.controller.model.userModels.ExUserModel;
import com.exadel.training.controller.model.userModels.UserModel;
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

    List<Training> getListenerTrainingListOfUser(long id);
    List<Training> getCoachTrainingListOfUser(long id);
    List<Training> visitedTrainings (long idUser);
    List<Training> actualTrainings(long idUser);
}
