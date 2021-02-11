package com.example.vaccination.schedule.validator;

import com.example.vaccination.schedule.dto.UserRequestDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<ValidPassword, String> {
    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password != null && password.matches(PASSWORD_PATTERN);
    }
}
