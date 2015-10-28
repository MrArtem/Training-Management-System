package com.exadel.training.validate;

import com.exadel.training.controller.model.trainingModels.AddingTrainingModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class AddingTrainingModelValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return AddingTrainingModel.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.title");
    }
}
