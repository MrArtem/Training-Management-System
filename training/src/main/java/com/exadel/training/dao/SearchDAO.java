package com.exadel.training.dao;

import com.exadel.training.dao.domain.User;

import javax.validation.groups.ConvertGroup;
import java.util.List;

/**
 * Created by ayudovin on 08.10.2015.
 */
public interface SearchDAO {
    List<User> searchUser(String searchWord);
}
