package com.example.vaccination.schedule.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<ValidName, String> {
    private static final String NAME_PATTERN = "^[a-zA-Z](\\s?[a-zA-Z]){2,29}$";

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return name != null && name.matches(NAME_PATTERN);
    }
}
