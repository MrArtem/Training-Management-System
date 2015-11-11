package com.exadel.training.service;

import com.exadel.training.controller.model.fileModels.FileUpload;
import com.exadel.training.dao.domain.FileStorage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by ayudovin on 31.10.2015.
 */
public interface FileStorageService {
    void addFile(Map.Entry<String,String> entry, long idTraining)  throws IOException;
    void deleteFile(long idFileStorage);

    FileStorage getFileStorageByID(long idFile);

    List<FileStorage> getAllFileByTraining(long idTraining);
}
