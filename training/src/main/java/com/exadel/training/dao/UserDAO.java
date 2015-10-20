package com.exadel.training.dao;

import com.exadel.training.dao.domain.User;

/**
 * Created by HP on 05.10.2015.
 */
public interface UserDAO {
    void save(User user);
    void update(User user);

    Boolean isCoachOfCurrentUser(long idCurrentUser, long idCoach);

    User getUserByID(long id);
    User getUserByLogin(String login);
}
