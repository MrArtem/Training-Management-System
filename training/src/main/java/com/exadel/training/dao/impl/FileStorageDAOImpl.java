package com.exadel.training.dao.impl;

import com.exadel.training.dao.FileStorageDAO;
import com.exadel.training.dao.domain.FileStorage;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by ayudovin on 31.10.2015.
 */
@Repository
public class FileStorageDAOImpl implements FileStorageDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public void addFile(FileStorage fileStorage) {
        sessionFactory.getCurrentSession().save(fileStorage);
    }
}
