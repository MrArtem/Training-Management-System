package com.exadel.training.dao;

import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;

import java.util.List;

/**
 * Created by ayudovin on 08.10.2015.
 */
public interface SearchDAO {
    List<User> searchUser(String searchWord);

    List<Training> searchTraining(String searchWord);
}
