package com.example.vaccination.schedule.dto;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String familyName;
    private LocalDate dateOfBirth;
    private String email;
}
