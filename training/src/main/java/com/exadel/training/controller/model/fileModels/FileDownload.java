package com.exadel.training.controller.model.fileModels;

import com.exadel.training.dao.domain.FileStorage;
import sun.misc.BASE64Encoder;

/**
 * Created by ayudovin on 31.10.2015.
 */
public class FileDownload {

    private BASE64Encoder encoder = new BASE64Encoder();

    private long idTraining;
    private String name;
    private String file;

    public FileDownload() {
    }

    public FileDownload(FileStorage fileStorage) {
        this.idTraining = fileStorage.getTraining().getId();
        this.name = fileStorage.getName();
        this.file = encoder.encode(fileStorage.getFile());
    }

    public long getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(long idTraining) {
        this.idTraining = idTraining;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
