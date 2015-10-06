package com.exadel.training.service.impl;

import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.UserDAO;
import com.exadel.training.dao.domain.User;
import com.exadel.training.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by HP on 06.10.2015.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public User getUserById(long id) {
        return userDAO.getUserByID(id);
    }

    @Override
    public List<Training> getTrainingListOfUserListener(long id) {
        return userDAO.getUserByID(id).getTrainingsListener();
    }

    @Override
    public List<Training> getTrainingListOfUserCoach(long id) {
        return userDAO.getUserByID(id).getTrainingsCoach();
    }
}
