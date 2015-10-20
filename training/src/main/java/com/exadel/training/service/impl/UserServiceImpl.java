package com.exadel.training.service.impl;

import com.exadel.training.dao.TrainingDAO;
import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.UserDAO;
import com.exadel.training.dao.domain.User;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 06.10.2015.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    public static final long ILLEGAL_ID = 0;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TrainingDAO trainingDAO;

    @Override
    public Boolean isCoach(long idUser, long idTraining) {
        if (idUser == ILLEGAL_ID || idTraining == ILLEGAL_ID) {
            throw new IllegalArgumentException("id cant't be 0 ");
        }
        return trainingDAO.getTrainingById(idTraining).getCoach().getId() == idUser ? true : false;
    }

    @Override
    public Boolean isCoachOfCurrentUser(long idCurrentUser, long idCoach) {
        return userDAO.isCoachOfCurrentUser(idCurrentUser, idCoach);
    }

    @Override
    public User getUserById(long id) {
        if (id == ILLEGAL_ID) {
            throw new IllegalArgumentException("id cant't be 0 ");
        }

        return userDAO.getUserByID(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

    @Override
    public List<Training> getListenerTrainingListOfUser(long id) {
        if (id == ILLEGAL_ID) {
            throw new IllegalArgumentException("id cant't be 0 ");
        }
        List<Training> trainingList = new ArrayList<Training>();

        for (Listener listener : userDAO.getUserByID(id).getTrainingListListener()) {
            trainingList.add(listener.getTraining());
        }

        return trainingList;
    }

    @Override
    public List<Training> getCoachTrainingListOfUser(long id) {
        if (id == ILLEGAL_ID) {
            throw new IllegalArgumentException("id cant't be 0 ");
        }

        return userDAO.getUserByID(id).getTrainingsCoach();
    }
}
