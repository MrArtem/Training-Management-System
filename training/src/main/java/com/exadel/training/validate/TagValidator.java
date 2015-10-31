package com.exadel.training.validate;

import com.exadel.training.dao.domain.Tag;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TagValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Tag.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "specialty", "error.specialty");
    }
}
