package com.exadel.training.service;

import com.exadel.training.controller.model.fileModels.FileUpload;
import com.exadel.training.dao.domain.FileStorage;

import java.io.IOException;
import java.util.List;

/**
 * Created by ayudovin on 31.10.2015.
 */
public interface FileStorageService {
    void addFile(FileUpload fileUpload) throws IOException;
    void deleteFile(long idFileStorage);

    List<FileStorage> getAllFileByTraining(long idTraining);
}
