package com.example.vaccination.schedule.dto;

import static com.example.vaccination.schedule.configuration.Constants.PATTERN_DATE;

import com.example.vaccination.schedule.validator.ValidEmail;
import com.example.vaccination.schedule.validator.ValidName;
import com.example.vaccination.schedule.validator.ValidPassword;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class UserRequestDto {
    @ValidName
    private String name;
    @ValidName
    private String familyName;
    @DateTimeFormat(pattern = PATTERN_DATE)
    private String dateOfBirth;
    @ValidEmail
    private String email;
    @ValidPassword
    private String password;
    private boolean deleted;
}
