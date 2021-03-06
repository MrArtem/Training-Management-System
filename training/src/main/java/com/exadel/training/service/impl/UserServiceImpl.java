package com.exadel.training.service.impl;

import com.exadel.training.controller.model.userModels.ExCoachModel;
import com.exadel.training.controller.model.userModels.ExUserModel;
import com.exadel.training.controller.model.userModels.UserModel;
import com.exadel.training.dao.TrainingDAO;
import com.exadel.training.dao.domain.Listener;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.UserDAO;
import com.exadel.training.dao.domain.User;
import com.exadel.training.dao.domain.UserPassword;
import com.exadel.training.service.UserService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testng.util.Strings;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HP on 06.10.2015.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TrainingDAO trainingDAO;


    @Override
    public long addExternalUser(ExUserModel exUserModel) {
        User user = new User();

        //todo check login
        if (Strings.isNullOrEmpty(exUserModel.getLogin())) {
            user.setLogin(exUserModel.getLogin());
        }
        user.setEmail(exUserModel.getEmail());
        user.setFirstName(exUserModel.getFirstName());
        user.setLastName(exUserModel.getLastName());
        user.setPhone(exUserModel.getPhone());

        userDAO.save(user);
        return user.getId();
    }

    @Override
    public long addExternalCoach(ExCoachModel exCoachModel) {
        User user = new User();

        user.setEmail(exCoachModel.getEmail());
        user.setFirstName(exCoachModel.getFirstname());
        user.setLastName(exCoachModel.getLastname());
        user.setPhone(exCoachModel.getPhone());

        userDAO.save(user);
        long idUser = user.getId();

        UserPassword userPassword = new UserPassword();
        userPassword.setId(idUser);
        userPassword.setPassword(RandomStringUtils.randomAlphabetic(8));
        //todo save password

        return 0;
    }

    @Override
    public Boolean isCoach(long idUser, long idTraining) {
        return trainingDAO.getTrainingById(idTraining).getCoach().getId() == idUser;
    }

    @Override
    public Boolean isCoachOfCurrentUser(long idCurrentUser, long idCoach) {
        return userDAO.isCoachOfCurrentUser(idCurrentUser, idCoach);
    }

    @Override
    public User getUserById(long id) {
        return userDAO.getUserByID(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

    @Override
    public List<Training> getListenerTrainingListOfUser(long idUser) {
        List<Training> trainingList = new ArrayList<Training>();

        for (Listener listener : userDAO.getUserByID(idUser).getTrainingListListener()) {
            trainingList.add(listener.getTraining());
        }

        return trainingList;
    }

    @Override
    public List<Training> getCoachTrainingList(long idUser) {
        return userDAO.getUserByID(idUser).getTrainingsCoach();
    }

    @Override
    public List<Training> getCoachTrainingList(long idCoach, long startDate, long endDate) {
        return userDAO.getCoachTrainingsBetweenDates(idCoach, startDate, endDate);
    }

    @Override
    public List<Training> visitedTrainings(long idUser) {
        return userDAO.visitedTrainings(idUser);
    }

    @Override
    public List<Training> actualTrainings(long idUser) {
        return userDAO.actualTrainings(idUser);
    }

    @Override
    public List<Training> getUserTrainingsByState(long idUser, Listener.State state) {
        return userDAO.getUserTrainingsByState(idUser, state);
    }

    @Override
    public List<Training> getUserTrainingsByState(long idUser, Listener.State state, long startDate, long endDate) {
        return userDAO.getUserTrainingsByState(idUser, state, startDate, endDate);
    }
}
