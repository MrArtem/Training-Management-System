package com.exadel.training.controller.model.fileModels;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by ayudovin on 26.10.2015.
 */
public class FileUpload {

    private long idTraining;
    private String name;
    private List<String> multipartFileList;

    public FileUpload() {
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

    public List<String> getMultipartFileList() {
        return multipartFileList;
    }

    public void setMultipartFileList(List<String> multipartFileList) {
        this.multipartFileList = multipartFileList;
    }
}
