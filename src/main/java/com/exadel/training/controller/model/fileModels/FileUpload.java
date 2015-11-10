package com.exadel.training.controller.model.fileModels;

import com.exadel.training.dao.domain.FileStorage;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.util.List;
import java.util.Map;

/**
 * Created by ayudovin on 26.10.2015.
 */
public class FileUpload {
    private long idTraining;
    Map<String, String> files;

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
