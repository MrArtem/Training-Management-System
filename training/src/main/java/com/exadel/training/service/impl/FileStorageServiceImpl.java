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
    public void addFile(FileUpload fileUpload) throws IOException {
    /* for(int i = 0; i<fileUpload.getFiles().size(); i++) {
         FileStorage fileStorage = new FileStorage();
         List<String> names = fileUpload.getNames();
         List<String> files = fileUpload.getFiles();

         fileStorage.setName(names.get(i));
         fileStorage.setFile(decoder.decodeBuffer(files.get(i)));
         fileStorage.setTraining(trainingService.getTraining(fileUpload.getIdTraining()));

         fileStorageDAO.addFile(fileStorage);
     }*/
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
