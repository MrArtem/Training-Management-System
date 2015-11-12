package com.exadel.training.service.impl;

import com.exadel.training.dao.SearchDAO;
import com.exadel.training.dao.domain.Training;
import com.exadel.training.dao.domain.User;
import com.exadel.training.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by ayudovin on 09.10.2015.
 */
@Service
@Transactional
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDAO searchDAO;

    @Override
    public List<User> searchUser(String searchWord) {
        return searchDAO.searchUser(searchWord);
    }

    @Override
    public List<Training> searchTraining(String searchWord) {
        return searchDAO.searchTraining(searchWord);
    }
}
