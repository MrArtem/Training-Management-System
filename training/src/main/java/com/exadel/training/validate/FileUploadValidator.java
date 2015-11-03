package com.exadel.training.validate;


import com.exadel.training.controller.model.fileModels.FileUpload;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by ayudovin on 03.11.2015.
 */
@Component
public class FileUploadValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return FileUpload.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        FileUpload fileUpload = (FileUpload)o;

        if (this.isValidID(fileUpload.getIdTraining())) {
          errors.reject("id <= 0");
        }

        if(fileUpload.getFiles() == null || fileUpload.getNames() == null) {
            errors.reject("null can't be in a model for upload files");
        }

    }
    private boolean isValidID(long id) {
        return id <= 0 ? false : true;
    }
}
