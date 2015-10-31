package com.exadel.training.controller;

import com.exadel.training.controller.model.fileModels.FileUpload;
import com.google.common.base.Strings;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * Created by ayudovin on 26.10.2015.
 */
@RestController
@RequestMapping("/file_controller")
public class FileController {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void uploadFile(@RequestParam(value="file", required=false) MultipartFile file, @RequestParam(value="files") Object data) throws IOException {

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] decodedBytes = decoder.decodeBuffer(data.toString());
    }
}
