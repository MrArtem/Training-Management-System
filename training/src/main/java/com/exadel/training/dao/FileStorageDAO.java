package com.exadel.training.dao;

import com.exadel.training.dao.domain.FileStorage;

import java.util.List;

/**
 * Created by ayudovin on 31.10.2015.
 */
public interface FileStorageDAO {
    void addFile(FileStorage fileStorage);
    void deleteFile(long idFileStorage);
}
