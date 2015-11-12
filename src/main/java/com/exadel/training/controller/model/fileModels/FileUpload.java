package com.exadel.training.controller.model.fileModels;

import java.util.Map;

/**
 * Created by ayudovin on 26.10.2015.
 */
public class FileUpload {
    Map<String, String> files;
    private long idTraining;

    public FileUpload() {
    }

    public long getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(long idTraining) {
        this.idTraining = idTraining;
    }

    public Map<String, String> getFiles() {
        return files;
    }

    public void setFiles(Map<String, String> files) {
        this.files = files;
    }
}
