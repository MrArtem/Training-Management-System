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
        AddingTrainingModel addingTrainingModel = (AddingTrainingModel) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "error.title");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shortInfo", "error.shortInfo");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "error.description");
        ValidationUtils.rejectIfEmpty(errors, "language", "error.language");

        Integer maxSize = addingTrainingModel.getMaxSize();
        if (maxSize == null || maxSize <= 0) {
            errors.rejectValue("maxSize", "negative || null");
        }

        if (addingTrainingModel.getLessonList() == null && addingTrainingModel.getRepeatModel() == null) {
            errors.rejectValue("not LessonList && RepeatingModel", "not LessonList && RepeatingModel");
        }


    }
}
