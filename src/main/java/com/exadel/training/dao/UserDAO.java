package com.exadel.training.dao;

import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;

import java.util.List;

/**
 * Created by HP on 05.10.2015.
 */
public interface UserDAO {
    void save(User user);
    void update(User user);

    Boolean isCoachOfCurrentUser(long idCurrentUser, long idCoach);

    User getUserByID(long id);
    User getUserByLogin(String login);

    List<User> getUsersByRole(User.Role role);
    List<Training> visitedTrainings(long idUser);
    List<Training> actualTrainings(long idUser);
    List<Training> waitingTrainings(long idUser);
    List<Training> getCoachTrainingsBetweenDates(long idCoach, long startDate, long endDate);
    List<Training> getUserTrainingsByState(long idUser, Listener.State state);
    List<Training> getUserTrainingsByState(long idUser, Listener.State state, long startDate, long endDate);
}
