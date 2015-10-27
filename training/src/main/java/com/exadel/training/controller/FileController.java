package com.exadel.training.controller;

import com.exadel.training.controller.model.fileModels.FileUpload;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by ayudovin on 26.10.2015.
 */
@RestController
@RequestMapping("/file_controller")
public class FileController {

    @RequestMapping(value = "/", method = RequestMethod.POST, headers=("content-type=multipart/*"))
    public void uploadFile( @RequestBody MultipartFile file) {
        MultipartFile multipartFile = file;
    }
}
