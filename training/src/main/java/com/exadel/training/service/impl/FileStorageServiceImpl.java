package com.exadel.training.service.impl;

import com.exadel.training.controller.model.fileModels.FileUpload;
import com.exadel.training.dao.FileStorageDAO;
import com.exadel.training.dao.domain.FileStorage;
import com.exadel.training.service.FileStorageService;
import com.exadel.training.service.TrainingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by ayudovin on 31.10.2015.
 */
@Transactional
@Service
public class FileStorageServiceImpl implements FileStorageService {

    private BASE64Decoder decoder = new BASE64Decoder();
    @Autowired
    private FileStorageDAO fileStorageDAO;
    @Autowired
    private TrainingService trainingService;

    @Override
    public void addFile(Map.Entry<String,String> entry, long idTraining) throws IOException {
            FileStorage fileStorage = new FileStorage();

            fileStorage.setName(entry.getKey());
            fileStorage.setFile(decoder.decodeBuffer(entry.getValue()));
            fileStorage.setTraining(trainingService.getTraining(idTraining));

            fileStorageDAO.addFile(fileStorage);
    }

    @Override
    public void deleteFile(long idFileStorage) {
        fileStorageDAO.deleteFile(idFileStorage);
    }

    @Override
    public List<FileStorage> getAllFileByTraining(long idTraining) {
        return trainingService.getTraining(idTraining).getFileStorageList();
    }
}
