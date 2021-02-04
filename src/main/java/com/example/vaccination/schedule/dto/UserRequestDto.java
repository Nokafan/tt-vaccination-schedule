package com.example.vaccination.schedule.dto;

import com.example.vaccination.schedule.configuration.Constants;
import com.example.vaccination.schedule.validator.ValidEmail;
import com.example.vaccination.schedule.validator.ValidName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class UserRequestDto {

    @ValidName
    private String name;
    @ValidName
    private String familyName;
    @DateTimeFormat(pattern = Constants.PATTERN_DATE)
    private String dateOfBirth;
    @ValidEmail
    private String email;
    private String password;
    private boolean deleted;
}
