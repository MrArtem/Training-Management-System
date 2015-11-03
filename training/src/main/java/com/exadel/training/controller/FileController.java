package com.exadel.training.controller;

import com.exadel.training.controller.model.fileModels.FileDownload;
import com.exadel.training.controller.model.fileModels.FileUpload;
import com.exadel.training.dao.domain.FileStorage;
import com.exadel.training.service.FileStorageService;
import com.exadel.training.validate.annotation.LegalID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ayudovin on 26.10.2015.
 */
@RestController
@RequestMapping("/file_controller")
public class FileController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    @Qualifier("fileUploadValidator")
    Validator fileUploadValidator;

    @InitBinder
    private void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.setValidator(fileUploadValidator);
    }

    @Secured({"ADMIN", "USER", "EX_COACH"})
    @RequestMapping(value = "/add_files", method = RequestMethod.POST)
    public void uploadFile(@RequestBody FileUpload fileUpload) throws IOException {
        fileStorageService.addFile(fileUpload);
    }

    @Secured({"ADMIN", "USER", "EX_COACH"})
    @RequestMapping(value = "/get_files/{idTraining}", method = RequestMethod.GET)
    @LegalID
    public List<FileDownload> getFiles(@PathVariable("idTraining") long idTraining) {
        List<FileDownload> fileDownloadList = new ArrayList<FileDownload>();

        for(FileStorage fileStorage : fileStorageService.getAllFileByTraining(idTraining)) {
            fileDownloadList.add(new FileDownload(fileStorage));
        }

        return fileDownloadList;
    }

    @Secured({"ADMIN", "USER", "EX_COACH"})
    @RequestMapping(value = "/delete_file/{idFile}", method = RequestMethod.DELETE)
    public void deleteFile(@PathVariable("idFile") long idFile) {
        fileStorageService.deleteFile(idFile);
    }


}
