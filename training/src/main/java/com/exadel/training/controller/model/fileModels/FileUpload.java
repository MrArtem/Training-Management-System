package com.exadel.training.controller.model.fileModels;

import com.exadel.training.dao.domain.FileStorage;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.util.List;

/**
 * Created by ayudovin on 26.10.2015.
 */
public class FileUpload {
    private long idTraining;
    private String name;
    private List<String> files;

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

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }
}
