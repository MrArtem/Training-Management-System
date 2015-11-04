package com.exadel.training.controller;

import com.exadel.training.controller.model.fileModels.FileDownload;
import com.exadel.training.controller.model.fileModels.FileUpload;
import com.exadel.training.dao.domain.FileStorage;
import com.exadel.training.service.FileStorageService;
import com.exadel.training.validate.annotation.LegalID;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import org.springframework.validation.Validator;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void uploadFile(@RequestParam(value="file", required=false) MultipartFile file,
                           @RequestParam(value="files") Object data, @RequestParam(value="idTraining") String idTraining) throws IOException {
        Map<String, String> result = new ObjectMapper().readValue(data.toString(), HashMap.class);
       // fileStorageService.addFile(result, Long.parseLong(idTraining));
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
