package com.example.vaccination.schedule.dto;

import com.example.vaccination.schedule.validator.ValidName;
import com.example.vaccination.schedule.validator.ValidPassword;
import lombok.Data;

@Data
public class UserLoginDto {
    @ValidName
    private String username;
    @ValidPassword
    private String password;
}
