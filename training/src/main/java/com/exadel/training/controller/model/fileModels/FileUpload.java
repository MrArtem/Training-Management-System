package com.exadel.training.controller.model.fileModels;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by ayudovin on 26.10.2015.
 */
public class FileUpload {

    private List<MultipartFile> multipartFileList;

    FileUpload() {
    }

    public List<MultipartFile> getMultipartFileList() {
        return multipartFileList;
    }

    public void setMultipartFileList(List<MultipartFile> multipartFileList) {
        this.multipartFileList = multipartFileList;
    }
}
