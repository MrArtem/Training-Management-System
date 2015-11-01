package com.exadel.training.controller;

import com.exadel.training.controller.model.fileModels.FileDownload;
import com.exadel.training.controller.model.fileModels.FileUpload;
import com.exadel.training.dao.domain.FileStorage;
import com.exadel.training.service.FileStorageService;
import com.exadel.training.validate.annotation.LegalID;
import com.google.common.base.Strings;
import org.bouncycastle.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by ayudovin on 26.10.2015.
 */
@RestController
@RequestMapping("/file_controller")
public class FileController {


    @Autowired
    private FileStorageService fileStorageService;

    @RequestMapping(value = "/add_files", method = RequestMethod.POST)
    public void uploadFile(@RequestBody FileUpload fileUpload) throws IOException {
        fileStorageService.addFile(fileUpload);
    }

    @RequestMapping(value = "/get_files/{idTraining}", method = RequestMethod.GET)
    @LegalID
    public List<FileDownload> getFiles(@PathVariable("idTraining") long idTraining) {
        List<FileDownload> fileDownloadList = new ArrayList<>();

        for(FileStorage fileStorage : fileStorageService.getAllFileByTraining(idTraining)) {
            fileDownloadList.add(new FileDownload(fileStorage));
        }

        return fileDownloadList;
    }
}
