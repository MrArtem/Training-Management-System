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
    private List<String> names;
    private List<String> files;

    public FileUpload() {
    }

    public long getIdTraining() {
        return idTraining;
    }

    public void setIdTraining(long idTraining) {
        this.idTraining = idTraining;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }
}
