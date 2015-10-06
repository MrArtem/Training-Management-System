package com.exadel.training.service;

import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;

import java.util.List;

/**
 * Created by HP on 06.10.2015.
 */
public interface UserService {
    User getUserById(long id);

    List<Training> getTrainingListOfUserListener(long id);
    List<Training> getTrainingListOfUserCoach(long id);
}
