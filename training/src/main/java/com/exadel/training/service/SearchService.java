package com.exadel.training.service;

import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;

import java.util.List;

/**
 * Created by ayudovin on 09.10.2015.
 */
public interface SearchService {
    List<User> searchUser(String searchWord);
    List<Training> searchTraining(String searchWord);
}
