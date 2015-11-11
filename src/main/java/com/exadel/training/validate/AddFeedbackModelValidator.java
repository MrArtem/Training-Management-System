package com.exadel.training.validate;

import com.exadel.training.controller.model.feedbackModels.AddFeedbackModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by HP on 29.10.2015.
 */
@Component
public class AddFeedbackModelValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return AddFeedbackModel.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        AddFeedbackModel addFeedbackModel = (AddFeedbackModel)o;

        long idTraining = addFeedbackModel.getTrainingID();
        long idUser = addFeedbackModel.getUserID();

        if (isValidID(idUser) || isValidID(idTraining)) {
            errors.reject("id <= 0");
        }

    }
    private boolean isValidID(long id) {
        return id <= 0;
    }
}
